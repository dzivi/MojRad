package org.ivan.MojRad.DaoClasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.ivan.MojRad.classes.User;

import java.util.ArrayList;
import java.sql.*;
import org.ivan.MojRad.classes.User;
public class UserDAO {
	private DataSource ds;

	// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String INSERTUSER = "INSERT INTO users(FirstName,LastName,Birdthday,Sex,Email,Password,CreateDate,NickName) VALUES(?,?,?,?,?,?,?,?);";
	private static String GETUSERBYUSERANDPASS = "SELECT * FROM users WHERE Email = ? AND Password = ?;";
	private static String USEREXISTS= "SELECT COUNT(`UserID`) AS BrojKorisnika FROM users WHERE Email = ? AND Password = ?;";
	private static String SEARCHUSER = "SELECT * FROM users WHERE CONCAT(FirstName,LastName) LIKE ? OR CONCAT(LastName,FirstName) LIKE ? OR LastName LIKE ? OR FirstName LIKE ?;";
	private static String GETALLMYFRIENDS = "SELECT UserID,FirstName,LastName, ProfilePicture FROM users WHERE UserID IN(SELECT CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END 'ID' FROM friendships WHERE (UserRespID=? OR UserReqID=?) AND Status = 't');";
	private static String GETUSERBYID = "SELECT * FROM users WHERE UserID=?;";
	private static String UPDATEONLINESTATUS = "UPDATE users SET OnlineStatus = ? WHERE UserID = ?;";
	private static String UPDATELASTACTIVITY = "UPDATE users SET LastActivity = CURRENT_TIMESTAMP WHERE UserID= ?";
	private static String UPDATEUSER = "UPDATE users SET FirstName = ?, LastName = ?, Sex = ?, Email = ?  WHERE UserID = ?;";
	private static String GETALLUSERS = "SELECT UserID,FirstName,LastName, ProfilePicture FROM users ORDER BY FirstName;";
	private static String DELETEUSERBYID = "DELETE FROM users WHERE UserID = ?";
	private static String UPDATEUSERPASS = "UPDATE users SET Password = ? WHERE UserID = ?;";
	private static String UPDATEUSERPROFILEPIC = "UPDATE users SET ProfilePicture = ? WHERE UserID = ?;";
	
	public UserDAO() {
		try {
			InitialContext cxt = new InitialContext();
			if (cxt == null) {
			}
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/mysql");
			if (ds == null) {
			}
		} catch (NamingException e) {
		}
	}

	/*************** check if user exists ************************/
	public int existUser(String email, String password) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		int status = 0;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(USEREXISTS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setString(1, email);
			pstm.setString(2, password);
			pstm.execute();

			// ****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO
			rs = pstm.getResultSet();

			if(rs.next()){
				status = rs.getInt("BrojKorisnika");
			}

			// ****KRAJ OBRADE ResultSet-a
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return status;
	}

	/********* DEFINICIJA METODE 
	 * @return ****************************/
	public void insertUser(User user) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTUSER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setString(1, user.getFirstName());
			pstm.setString(2, user.getLastName());
			pstm.setString(3, user.getBirdthday().toString());
			pstm.setString(4, user.getSex());
			pstm.setString(5, user.getEmail());
			pstm.setString(6, user.getPassword());
			pstm.setString(7, user.getCreateDate().toString());
			pstm.setString(8, user.getNickName());
		
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

	/********************** DEFINICIJA METODE *******************/
	public User getUser(String email, String password) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU

		User user = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETUSERBYUSERANDPASS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setString(1, email);
			pstm.setString(2, password);
			pstm.execute();

			// ****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO
			rs = pstm.getResultSet();

			while (rs.next()) { // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA
				// IZ RESULTSET-A rs
				user.setUserID(rs.getInt("UserID"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setBirdthday(rs.getDate("Birdthday"));
				user.setSex(rs.getString("Sex"));
				user.setCreateDate(rs.getTimestamp("CreateDate"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setLastActivity(rs.getTimestamp("LastActivity"));
				user.setOnlineStatus(rs.getString("OnlineStatus"));
				user.setLevelID(rs.getInt("LevelID"));
				user.setNickName(rs.getString("NickName"));
				user.setProfilePicture(rs.getString("ProfilePicture"));
				user.setHash(rs.getString("Hash"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE
				// VRACA ONDA NE TREBA

			}
			// ****KRAJ OBRADE ResultSet-a
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return user;
	}
	
	
	public ArrayList<User> searchUser(String parameter){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<User> lu = new ArrayList<User>();
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SEARCHUSER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, "%"+parameter+"%");
			pstm.setString(2, "%"+parameter+"%");
			pstm.setString(3, "%"+parameter+"%");
			pstm.setString(4, "%"+parameter+"%");
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
				user.setProfilePicture(rs.getString("ProfilePicture"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lu.add(user);
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
		return lu; 
	}
	
	public ArrayList<User> getAllFriends(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETALLMYFRIENDS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, UserID);
			pstm.setInt(2, UserID);
			pstm.setInt(3, UserID);
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
				user.setProfilePicture(rs.getString("ProfilePicture"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				users.add(user);
				
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
		return users; 
	}
	
	public User getUserbyID(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETUSERBYID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, UserID);
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
				user.setBirdthday(rs.getDate("Birdthday"));
				user.setSex(rs.getString("Sex"));
				user.setCreateDate(rs.getTimestamp("CreateDate"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setLastActivity(rs.getTimestamp("LastActivity"));
				user.setOnlineStatus(rs.getString("OnlineStatus"));
				user.setLevelID(rs.getInt("LevelID"));
				user.setNickName(rs.getString("NickName"));
				user.setProfilePicture(rs.getString("ProfilePicture"));
				user.setHash(rs.getString("Hash"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				
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
		return user; 
	}
	
	public void updateOnlineStatus(String tf, int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEONLINESTATUS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, tf);
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
	
	public void updateLastActivity(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATELASTACTIVITY);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
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
	
	
	public void updateUser(int UserID, String FirstName, String LastName, String Sex, String Email){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEUSER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI FirstName = ?, LastName = ?, Sex = ?, Email = ?
			pstm.setString(1, FirstName);
			pstm.setString(2, LastName);
			pstm.setString(3, Sex);
			pstm.setString(4, Email);
			pstm.setInt(5, UserID);
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
	
	public void updateUserPass(User user){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEUSERPASS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI FirstName = ?, LastName = ?, Sex = ?, Email = ?
			pstm.setString(1, user.getPassword());
			pstm.setInt(2, user.getUserID());
			
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
	
	public void updateUserProfilePic(String ProfilePicture, int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEUSERPROFILEPIC);

			
			pstm.setString(1, ProfilePicture);
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
	
	public ArrayList<User> getAllUsers(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETALLUSERS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
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
				user.setProfilePicture(rs.getString("ProfilePicture"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				users.add(user);
				
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
		return users; 
	}
	
	public void deleteUserByID(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEUSERBYID);

			
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
	
}
