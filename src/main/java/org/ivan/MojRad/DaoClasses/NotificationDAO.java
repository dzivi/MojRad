package org.ivan.MojRad.DaoClasses;

//vazni importi 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.ArrayList;
public class NotificationDAO {
   private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
	private static String UPDATENOTIFICATION = "UPDATE notifications  SET UserID=?, Status=? WHERE NotificationID = ? AND MessageID = ?;";
	private static String UPDATENOTIFICATIONSEEN = "UPDATE notifications  SET Status=? WHERE NotificationID = ? AND MessageID = ?;";
	private static String INSERTNOTIFICATION = "INSERT INTO notifications(MessageID,UserID) VALUES (?,?);";
	
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE � UVEK ISTO
	public NotificationDAO(){
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
	public void updateNotification(int UserID, String Status,int NotificationID, int MessageID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
				
         try {
			con = ds.getConnection();
			
			if(UserID==0){
				pstm = con.prepareStatement(UPDATENOTIFICATIONSEEN);
				// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, Status);
				pstm.setInt(2, NotificationID);
				pstm.setInt(3, MessageID);
				System.out.println(pstm.toString());
				pstm.execute();
			}else{
				pstm = con.prepareStatement(UPDATENOTIFICATION);

				// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setInt(1, UserID);
				pstm.setString(2, Status);
				pstm.setInt(3, NotificationID);
				pstm.setInt(4, MessageID);
				System.out.println(pstm.toString());
				pstm.execute();
			}
		


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertNotification(int MessageID, int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTNOTIFICATION);

			
			pstm.setInt(1, MessageID);
			pstm.setInt(2, UserID);
			
			
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


}