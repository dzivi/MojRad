package org.ivan.MojRad.classes;


import java.sql.Timestamp;

public class Message {
	private int MessageID;
	private int FriendshipID;
	private String Text;
	private Timestamp SendTime;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(int friendshipID, String text) {
		super();
		FriendshipID = friendshipID;
		Text = text;
	}

	public int getMessageID() {
		return MessageID;
	}

	public void setMessageID(int messageID) {
		MessageID = messageID;
	}

	public int getFriendshipID() {
		return FriendshipID;
	}

	public void setFriendshipID(int friendshipID) {
		FriendshipID = friendshipID;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Timestamp getSendTime() {
		return SendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		SendTime = sendTime;
	}

}
