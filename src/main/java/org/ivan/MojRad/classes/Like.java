package org.ivan.MojRad.classes;

public class Like {
	private int LikeID;
	private int PostID;
	private int UserID;

	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Like(int postID, int userID) {
		super();
		PostID = postID;
		UserID = userID;
	}

	public int getLikeID() {
		return LikeID;
	}

	public void setLikeID(int likeID) {
		LikeID = likeID;
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

}
