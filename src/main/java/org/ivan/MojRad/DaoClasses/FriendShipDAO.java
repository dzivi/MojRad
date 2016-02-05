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
import java.util.HashMap;

public class FriendShipDAO {
	private DataSource ds;

	// DEFINICIJA KONEKCIONIH STRINGOVA
	private static String SELECTUSER = "SELECT CASE UserReqID WHEN ? THEN UserRespID ELSE UserReqID END ID FROM friendships WHERE (UserRespID=? OR UserReqID=?) AND Status = 't';";
	private static String GETSUGGUSERHASFRIENDS = "SELECT UserID FROM users WHERE UserID  IN (SELECT CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END ID FROM friendships WHERE (UserRespID=? OR UserReqID=? AND Status='t')) AND UserID NOT IN (?,?);";
	private static String GETSUGGUSERNOFRIENDS = "SELECT UserID,FirstName,LastName,ProfilePicture FROM users WHERE UserID NOT IN (SELECT CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END ID FROM friendships WHERE (UserRespID=? OR UserReqID=?)) AND UserID !=? ORDER BY RAND()  LIMIT 5;";
	private static String GETFINALSUGGFRIENDS = "SELECT UserID,FirstName,LastName,ProfilePicture FROM users WHERE UserID=?";

	private static String INSERTFRIENDSHIP = "INSERT INTO friendships(UserReqID,UserRespID,ReqTime,RespTime) VALUES(?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);";
	private static String ONLINEFRIENDS = "SELECT UserID,FirstName,LastName,ProfilePicture FROM users  WHERE UserID IN( (SELECT CASE UserReqID WHEN ? THEN UserRespID  ELSE UserReqID END ID FROM friendships  WHERE (UserRespID=? OR UserReqID=?) AND Status = 't')) AND OnlineStatus='t';";
	private static String EXISTFRIENDSHIP = "SELECT COUNT(FriendshipID) AS Friendships FROM friendships WHERE (UserReqID = ? AND UserRespID = ?) OR (UserReqID = ? AND UserRespID = ?);";
	private static String COUNTREQUESTS = "SELECT COUNT(FriendshipID) AS CR FROM friendships WHERE UserRespID = ? AND Status = 'f';";
	private static String FRIENDSHIPIDBYUSERS = "SELECT FriendshipID FROM friendships WHERE (UserReqID = ? AND UserRespID = ?) OR (UserReqID = ? AND UserRespID = ?) AND Status = 't';";
	
	private static String GETREQUESTS = "SELECT UserReqID FROM friendships WHERE UserRespID = ? AND Status = 'f';";
	private static String ACCEPTREQUEST = "UPDATE friendships  SET Status = 't'  WHERE UserReqID = ? AND UserRespID = ?;";
	private static String DELETEREQUEST = "DELETE FROM friendships WHERE UserReqID = ? AND UserRespID = ?";
	
	private static String WEAREFRIENDS = "SELECT COUNT(FriendshipID) AS Friendships FROM friendships WHERE ((UserReqID = ? AND UserRespID = ?) OR (UserReqID = ? AND UserRespID = ?)) AND Status = 't';";
	
	public FriendShipDAO() {
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

	public ArrayList<User> suggFriends(int id) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		ArrayList<User> userFriends = new ArrayList<User>(); // userFriends
																// vraca sve
																// prijatelje po
																// ID
		User user = null;

		HashMap<Integer, ArrayList<Integer>> friendsOfFriends = new HashMap<Integer, ArrayList<Integer>>();// prijatelji od mojih prijatelja
		ArrayList<Integer> helpList = new ArrayList<Integer>(); // helpList for catching data

		try {
			con = ds.getConnection();
			// Vracanje svih prijatelja osobe
			pstm = con.prepareStatement(SELECTUSER);
			pstm.setInt(1, id);
			pstm.setInt(2, id);
			pstm.setInt(3, id);
			pstm.execute();
			rs = pstm.getResultSet();
			while (rs.next()) {
				user = new User();
				user.setUserID(rs.getInt("ID"));
				userFriends.add(user);
			}
			// Provera da li je lista userFriends prazna

			if (userFriends != null && userFriends.size() > 0) { // Ako ima
																	// traze se
																	// zajednicki
																	// prijatelji
																	// od mojih
																	// prijatelja
				// Getting friends of my friends sesction
				for (int i = 0; i < userFriends.size(); i++) {
					pstm = con.prepareStatement(GETSUGGUSERHASFRIENDS);
					pstm.setInt(1, userFriends.indexOf(i));
					pstm.setInt(2, userFriends.indexOf(i));
					pstm.setInt(3, userFriends.indexOf(i));
					pstm.setInt(4, id);
					pstm.setInt(5, userFriends.indexOf(i));
					pstm.execute();
					rs = pstm.getResultSet();
					while (rs.next()) {
						helpList.add(rs.getInt("ID"));
					}
					if (helpList != null && helpList.size() > 0) {
						friendsOfFriends.put(userFriends.indexOf(i), helpList);
						helpList.clear();
					}
				}
				// and Getting friends of my friends

				// Getting mutual friends
				ArrayList<Integer> helpListK = new ArrayList<Integer>();
				ArrayList<Integer> helpListN = new ArrayList<Integer>();

				ArrayList<Integer> preFinalFriends = new ArrayList<Integer>();
				int p, q;
				for (int k = 0; k < (userFriends.size() - 1); k++) {
					for (int n = k + 1; n < (userFriends.size()); n++) {

						if (friendsOfFriends.size() > 1) {
							helpListK = (ArrayList<Integer>) friendsOfFriends
									.get(k);

							helpListN = (ArrayList<Integer>) friendsOfFriends
									.get(n);

							for (p = 0; p < helpListK.size(); p++) {
								for (q = 0; q < helpListN.size(); q++) {
									if (helpListK.indexOf(p) == helpListN
											.indexOf(q)) {
										preFinalFriends.add(helpListK
												.indexOf(p));
									}
								}
							}

							helpListK.clear();
							helpListN.clear();

						}
					}
				}
				// End getting

				// Final filtering friends
				for (User pom : userFriends) {
					for (q = 0; q < preFinalFriends.size(); q++) {
						if (pom.getUserID() == preFinalFriends.indexOf(q)) {
							preFinalFriends.set(q, 0);
						}
					}
				}
				// End filtering

				// Final friends
				ArrayList<User> finalFriends = new ArrayList<User>();
				for (p = 0; p < preFinalFriends.size(); p++) {
					pstm = con.prepareStatement(GETFINALSUGGFRIENDS);
					pstm.setInt(1, preFinalFriends.indexOf(p));
					pstm.execute();
					rs = pstm.getResultSet();
					while (rs.next()) {
						user = new User();
						user.setUserID(rs.getInt("ID"));
						user.setFirstName(rs.getString("FirstName"));
						user.setLastName(rs.getString("LastName"));
						user.setProfilePicture(rs.getString("ProfilePicture"));
						finalFriends.add(user);
					}

				}
				if (finalFriends != null && finalFriends.size() > 0) {
					userFriends.clear();
					userFriends = finalFriends;
				} else {
					userFriends.clear();
					pstm = con.prepareStatement(GETSUGGUSERNOFRIENDS);
					pstm.setInt(1, id);
					pstm.setInt(2, id);
					pstm.setInt(3, id);
					pstm.setInt(4, id);

					pstm.execute();
					rs = pstm.getResultSet();
					while (rs.next()) {
						user = new User();
						user.setUserID(rs.getInt("UserID"));
						user.setFirstName(rs.getString("FirstName"));
						user.setLastName(rs.getString("LastName"));
						user.setProfilePicture(rs.getString("ProfilePicture"));
						userFriends.add(user);
					}
				}
				// End final friends

			} else { // izbacuje max 5 random korisnika koji mi nisu prijatelji

				pstm = con.prepareStatement(GETSUGGUSERNOFRIENDS);
				pstm.setInt(1, id);
				pstm.setInt(2, id);
				pstm.setInt(3, id);
				pstm.setInt(4, id);

				pstm.execute();
				rs = pstm.getResultSet();
				while (rs.next()) {
					user = new User();
					user.setUserID(rs.getInt("UserID"));
					user.setFirstName(rs.getString("FirstName"));
					user.setLastName(rs.getString("LastName"));
					user.setProfilePicture(rs.getString("ProfilePicture"));
					userFriends.add(user);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userFriends;
	}

	// DEFINICIJA METODE
	public void insertFriendShip(int UserReqID, int UserRespID) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTFRIENDSHIP);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setInt(1, UserReqID);
			pstm.setInt(2, UserRespID);
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

	public int existFriendship(int UserReqID, int UserRespID) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		int status = 0;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EXISTFRIENDSHIP);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setInt(1, UserReqID);
			pstm.setInt(2, UserRespID);
			pstm.setInt(3, UserRespID);
			pstm.setInt(4, UserReqID);
			pstm.execute();

			// ****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO
			rs = pstm.getResultSet();

			if (rs.next()) {
				status = rs.getInt("Friendships");
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
	
	public int weAreFriends(int UserReqID, int UserRespID) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		int status = 0;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(WEAREFRIENDS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setInt(1, UserReqID);
			pstm.setInt(2, UserRespID);
			pstm.setInt(3, UserRespID);
			pstm.setInt(4, UserReqID);
			pstm.execute();

			// ****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO
			rs = pstm.getResultSet();

			if (rs.next()) {
				status = rs.getInt("Friendships");
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

	// DEFINICIJA METODE
	public ArrayList<User> onlineFriends(int UserID) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<User> lu = new ArrayList<User>();
		User user = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ONLINEFRIENDS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI
			pstm.setInt(1, UserID);
			pstm.setInt(2, UserID);
			pstm.setInt(3, UserID);
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
				user.setProfilePicture(rs.getString("ProfilePicture"));

				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE
				// VRACA ONDA NE TREBA
				lu.add(user);
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
		return lu;
	}
	
	public int numOfRequests(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		int count=0;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(COUNTREQUESTS);

		
			pstm.setInt(1, UserID);
			pstm.execute();
			rs = pstm.getResultSet();

			if(rs.next()){ 
				count = rs.getInt("CR");
				
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
	
		return count; 
	}
	
	public int getFriendShipIDByFriends(int UserReqID, int UserRespID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		int friendshipID = 0;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(FRIENDSHIPIDBYUSERS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, UserReqID);
			pstm.setInt(2, UserRespID);
			pstm.setInt(3, UserRespID);
			pstm.setInt(4, UserReqID);
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				friendshipID=rs.getInt("FriendshipID");
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
		return friendshipID; 
	}
	// DEFINICIJA OSTALIH METODA ... 
	
	
	
	public ArrayList<User> getRequests(int UserID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETREQUESTS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, UserID);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				user.setUserID(rs.getInt("UserReqID"));
				
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
	
	public void acceptFriendReq(int UserReqID,int UserRespID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ACCEPTREQUEST);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, UserReqID);
			pstm.setInt(2, UserRespID);
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
	
	public void deleteRequest(int UserReqID, int UserRespID){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEREQUEST);

			
			pstm.setInt(1, UserReqID);
			pstm.setInt(2, UserRespID);
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
