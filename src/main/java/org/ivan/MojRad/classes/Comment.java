package org.ivan.MojRad.classes;

import java.sql.Date;

import java.sql.Timestamp;

public class Comment {
	private int CommentID;
	private int PostID;
	private int UserID;
	private String Text;
	private Timestamp CreateDate;
	
	public Timestamp getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int postID, int userID, String text) {
		super();
		PostID = postID;
		UserID = userID;
		Text = text;
	}

	public int getCommentID() {
		return CommentID;
	}

	public void setCommentID(int commentID) {
		CommentID = commentID;
	}

	public int getPostID() {
		return PostID;
	}

	public void setPostID(int postID) {
		PostID = postID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

}
