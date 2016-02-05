package org.ivan.MojRad.classes;

public class Notification {
	private int NotificationID;
	private int MessageID;
	private int UserID;
	private String Status;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(int messageID, int userID, String status) {
		super();
		MessageID = messageID;
		UserID = userID;
		Status = status;
	}
	public int getNotificationID() {
		return NotificationID;
	}
	public void setNotificationID(int notificationID) {
		NotificationID = notificationID;
	}
	public int getMessageID() {
		return MessageID;
	}
	public void setMessageID(int messageID) {
		MessageID = messageID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	
}
