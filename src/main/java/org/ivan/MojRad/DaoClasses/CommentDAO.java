package org.ivan.MojRad.DaoClasses;

//vazni importi 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.ivan.MojRad.classes.Comment;

import java.util.ArrayList;
public class CommentDAO {
   private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
	private static String GETCOMMENTS = "SELECT * FROM comments WHERE PostID = ? ORDER BY CreateTime;";
	private static String INSERTCOMMENT = "INSERT INTO comments (PostID,UserID,Text,CreateTime) VALUES(?,?,?,CURRENT_TIMESTAMP);";
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE � UVEK ISTO
	public CommentDAO(){
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	// DEFINICIJA METODE 
	public ArrayList<Comment> getComments(int PostID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Comment comm = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETCOMMENTS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, PostID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				comm = new Comment();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				comm.setCommentID(rs.getInt("CommentID"));
				comm.setPostID(rs.getInt("PostID"));
				comm.setUserID(rs.getInt("UserID"));
				comm.setText(rs.getString("Text"));
				comm.setCreateDate(rs.getTimestamp("CreateTime"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				comments.add(comm);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return comments; 
	}
	// DEFINICIJA OSTALIH METODA ... 
	
	public void insertComment(int PostID, int UserID, String Text){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTCOMMENT);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, PostID);
			pstm.setInt(2, UserID);
			pstm.setString(3, Text);
			pstm.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		
	}
	// DEFINICIJA OSTALIH METODA ... 
}
