package org.ivan.MojRad.classes;

public class Suggest {
	private int SuggestID;
	private int UserReqID;
	private int UserRespID;

	public Suggest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Suggest(int userReqID, int userRespID) {
		super();
		UserReqID = userReqID;
		UserRespID = userRespID;
	}

	public int getSuggestID() {
		return SuggestID;
	}

	public void setSuggestID(int suggestID) {
		SuggestID = suggestID;
	}

	public int getUserReqID() {
		return UserReqID;
	}

	public void setUserReqID(int userReqID) {
		UserReqID = userReqID;
	}

	public int getUserRespID() {
		return UserRespID;
	}

	public void setUserRespID(int userRespID) {
		UserRespID = userRespID;
	}

}
