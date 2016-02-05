package org.ivan.MojRad.classes;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	private int UserID;
	private String FirstName;
	private String LastName;
	private Date Birdthday;
	private String Sex;
	private Timestamp CreateDate;
	private String Email;
	private String Password;
	private Timestamp LastActivity;
	private String OnlineStatus;
	private int LevelID;
	private String NickName;
	private String ProfilePicture;
	private String Hash;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}



	public Timestamp getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Timestamp getLastActivity() {
		return LastActivity;
	}

	public void setLastActivity(Timestamp lastActivity) {
		LastActivity = lastActivity;
	}

	public String getOnlineStatus() {
		return OnlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		OnlineStatus = onlineStatus;
	}

	public int getLevelID() {
		return LevelID;
	}

	public void setLevelID(int levelID) {
		LevelID = levelID;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getProfilePicture() {
		return ProfilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		ProfilePicture = profilePicture;
	}

	public String getHash() {
		return Hash;
	}

	public void setHash(String hash) {
		Hash = hash;
	}


	public Date getBirdthday() {
		return Birdthday;
	}

	public void setBirdthday(Date birdthday) {
		Birdthday = birdthday;
	}

	public User(String firstName, String lastName, Date birdthday,
			String sex, String email, String password) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Birdthday = birdthday;
		Sex = sex;
		Email = email;
		Password = password;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}


	//definicija konstruktora za probu suggfiends
	
	public User(int userID, String firstName, String lastName,
			String profilePicture) {
		super();
		UserID = userID;
		FirstName = firstName;
		LastName = lastName;
		ProfilePicture = profilePicture;
	}

}
