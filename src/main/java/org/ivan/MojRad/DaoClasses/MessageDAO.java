package org.ivan.MojRad.DaoClasses;

//vazni importi 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.ivan.MojRad.classes.Message;

import java.util.ArrayList;
import java.util.HashMap;
public class MessageDAO {
   private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
	private static String GETMYMESSAGES = "SELECT *,CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END 'ID' FROM notifiMessages WHERE UserRespID=? OR UserReqID=? ORDER BY SendTime DESC;";
	private static String COUNTNOTIFICATIONS = "SELECT COUNT(MessageID) AS CIM,CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END 'ID' FROM notifiMessages WHERE (UserRespID=? OR UserReqID=?) AND notifStatus = 't' AND UserID=?;";
	private static String GETONEMESSAGE = "SELECT * FROM notifiMessages WHERE MessageID = ?;";
	private static String INSERTMESSAGE = "INSERT INTO messages(FriendshipID,Text,SendTime) VALUES(?,?,CURRENT_TIMESTAMP);";
	private static String UPDATEMESSAGE = "UPDATE messages SET Text=?,SendTime=CURRENT_TIMESTAMP WHERE MessageID = ?;";
	private static String LASTSENDMESSAGE = "SELECT * FROM messages WHERE FriendshipID = ? ORDER BY SendTime DESC LIMIT 1;";
	
	public MessageDAO(){
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
	public HashMap<Integer, String[]> getMyMessages(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		HashMap<Integer, String[]> messNotif = new HashMap<Integer, String[]>();
		String[] helpList;
		int i=0;
		
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETMYMESSAGES);

			pstm.setInt(1, UserID);
			pstm.setInt(2, UserID);
			pstm.setInt(3, UserID);
			pstm.execute();
			
			rs = pstm.getResultSet();
			while(rs.next()){ 
				
				helpList=new String[]
						{rs.getString("FriendshipID"),
						rs.getString("UserReqID"),
						rs.getString("UserRespID"),
						rs.getString("friendStatus"),
						rs.getString("MessageID"),
						rs.getString("Text"),
						rs.getString("SendTime"),
						rs.getString("NotificationID"),
						rs.getString("UserID"),
						rs.getString("notifStatus"),
						rs.getString("ID")};
				messNotif.put(i, helpList);
				i++;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return messNotif; 
	}
	
	public int countNotifications(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		int count = 0;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(COUNTNOTIFICATIONS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, UserID);
			pstm.setInt(2, UserID);
			pstm.setInt(3, UserID);
			pstm.setInt(4, UserID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ 
				count=rs.getInt("CIM");
				
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
	
	public ArrayList<String> readOneMessage(int MessageID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<String> message = new ArrayList<String>();
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETONEMESSAGE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, MessageID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				message.add(rs.getString("FriendshipID"));
				message.add(rs.getString("UserReqID"));
				message.add(rs.getString("UserRespID"));
				message.add(rs.getString("friendStatus"));
				message.add(rs.getString("MessageID"));
				message.add(rs.getString("Text"));
				message.add(rs.getString("SendTime"));
				message.add(rs.getString("NotificationID"));
				message.add(rs.getString("UserID"));
				message.add(rs.getString("notifStatus"));
				
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
		return message; 
	}
	
	public void insertMessage(int FriendShipID, String Text){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
	
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTMESSAGE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, FriendShipID);
			pstm.setString(2, Text);
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
	
	public void updateMessage(String Text, int MessageID ){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEMESSAGE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, Text);
			pstm.setInt(2, MessageID);
			System.out.println(pstm.toString());
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
	
	public Message lastSendMessage(int FriendshipID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		Message mess = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(LASTSENDMESSAGE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, FriendshipID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				mess = new Message();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				mess.setMessageID(rs.getInt("MessageID"));
				mess.setFriendshipID(rs.getInt("FriendshipID"));
				mess.setText(rs.getString("Text"));
				mess.setSendTime(rs.getTimestamp("SendTime"));
				
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
		return mess; 
	}
	// DEFINICIJA OSTALIH METODA ...
	

}
