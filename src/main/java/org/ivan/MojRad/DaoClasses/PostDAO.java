package org.ivan.MojRad.DaoClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.ivan.MojRad.classes.Post;
import org.ivan.MojRad.classes.Validate;

import java.util.ArrayList;
public class PostDAO {
   private DataSource ds;


	private static String GETPOSTSBYID = "SELECT * FROM posts WHERE UserID = ? ORDER BY CreateTime DESC;";
	private static String INSERTPOST = "INSERT INTO posts(UserID,Text,Link,CreateTime) VALUES(?,?,?,CURRENT_TIMESTAMP);";
	private static String GETPOSTSOFMYFRIENDS = "SELECT * FROM posts WHERE UserID IN (SELECT UserID FROM users WHERE UserID IN(SELECT CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END 'ID' FROM friendships WHERE (UserRespID=? OR UserReqID=?) AND Status = 't')) ORDER BY CreateTime DESC;";
	
	public PostDAO(){
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
	 
	public ArrayList<Post> getPostsByID(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		ArrayList<Post> posts = new ArrayList<Post>();
		Post post = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPOSTSBYID);

			pstm.setInt(1, UserID);
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){
				post = new Post();
				
				post.setPostID(rs.getInt("PostID"));
				post.setUserID(rs.getInt("UserID"));
				post.setText(rs.getString("Text"));
				post.setLink(rs.getString("Link"));
				post.setCreateDate(rs.getTimestamp("CreateTime"));
				
				posts.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts; 
	}
	
	public void insertPost(int UserID, String Text, String Link){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTPOST);
			
			if(Validate.check(Text)){
				pstm.setString(2, Text);
			} else {
				pstm.setString(2, null);
			}
			
			
			if(Validate.check(Link)){
				pstm.setString(3, Link);
			} else {
				pstm.setString(3, null);
			}

			pstm.setInt(1, UserID);
			pstm.execute();
			

		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// DEFINICIJA OSTALIH METODA ... 
	
	public ArrayList<Post> getPostsOfMyFriends(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		ArrayList<Post> posts = new ArrayList<Post>();
		Post post = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPOSTSOFMYFRIENDS);

			pstm.setInt(1, UserID);
			pstm.setInt(2, UserID);
			pstm.setInt(3, UserID);
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){
				post = new Post();
				
				post.setPostID(rs.getInt("PostID"));
				post.setUserID(rs.getInt("UserID"));
				post.setText(rs.getString("Text"));
				post.setLink(rs.getString("Link"));
				post.setCreateDate(rs.getTimestamp("CreateTime"));
				
				posts.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts; 
	}
	
}
