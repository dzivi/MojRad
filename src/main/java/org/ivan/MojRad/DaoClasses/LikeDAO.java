package org.ivan.MojRad.DaoClasses;

//vazni importi 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.ivan.MojRad.classes.User;

import java.util.ArrayList;
public class LikeDAO {
   private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
	private static String COUNTLIKES = " SELECT COUNT(LikeID) AS CL FROM likes WHERE PostID=?;";
	private static String GETLIKEUSERS = "SELECT u.UserID,u.FirstName,u.LastName FROM users u JOIN likes l ON u.UserID = l.UserID WHERE l.PostID=?;";
	private static String WHEREILIKE = "SELECT LikeID FROM likes WHERE PostID = ? AND UserID = ?;";
	private static String INSERTLIKE = "INSERT INTO likes (PostID,UserID) VALUES (?,?);";
	
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE � UVEK ISTO
	public LikeDAO(){
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
	public int countLikes(int PostID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		int count = 0;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(COUNTLIKES);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, PostID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ 
			count = rs.getInt("CL");
				
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
		return count; 
	}
	
	public ArrayList<User> getLikeUsers(int PostID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<User> likeUsers = new ArrayList<User>();
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETLIKEUSERS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, PostID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				user.setUserID(rs.getInt("UserID"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				likeUsers.add(user);
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
		return likeUsers; 
	}
	
	public String insertLike(int PostID, int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
		String msg = "Liked!";
				
		
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(WHEREILIKE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, PostID);
			pstm.setInt(2, UserID);
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				msg = "You are ready like this post!";
			} else {
				con = ds.getConnection();
				pstm = con.prepareStatement(INSERTLIKE);

				// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setInt(1, PostID);
				pstm.setInt(2, UserID);
				
				pstm.execute();

	//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
				rs = pstm.getResultSet();
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
		return msg; 
	}
	// DEFINICIJA OSTALIH METODA ... 
}
