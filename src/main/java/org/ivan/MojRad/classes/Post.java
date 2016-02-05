package org.ivan.MojRad.classes;

import java.sql.Timestamp;;

public class Post {
	private int PostID;
	private int UserID;
	private String Text;
	private String Link;
	private Timestamp CreateDate;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int userID, String text, String link) {
		super();
		UserID = userID;
		Text = text;
		Link = link;
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



	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public Timestamp getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Timestamp createDate) {
		CreateDate = createDate;
	}

}
