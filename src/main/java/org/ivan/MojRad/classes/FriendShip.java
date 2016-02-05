package org.ivan.MojRad.classes;

import java.sql.Date;

import com.sun.jmx.snmp.Timestamp;

public class FriendShip {
	private int FriendShipID;
	private int UserReqID;
	private int UserRespID;
	private Timestamp RequestTime;
	private Date ResponseTime;
	private String Status;

	public FriendShip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FriendShip(int userReqID, int userRespID) {
		super();
		UserReqID = userReqID;
		UserRespID = userRespID;
	}

	public int getFriendShipID() {
		return FriendShipID;
	}

	public void setFriendShipID(int friendShipID) {
		FriendShipID = friendShipID;
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

	public Timestamp getRequestTime() {
		return RequestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		RequestTime = requestTime;
	}

	public Date getResponseTime() {
		return ResponseTime;
	}

	public void setResponseTime(Date responseTime) {
		ResponseTime = responseTime;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
