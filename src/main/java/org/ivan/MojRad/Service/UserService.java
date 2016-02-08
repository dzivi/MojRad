package org.ivan.MojRad.Service;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.ivan.MojRad.DaoClasses.UserDAO;
import org.ivan.MojRad.Exception.DataNotFoundException;
import org.ivan.MojRad.classes.User;

public class UserService {
	
	UserDAO usDao = new UserDAO();
	
	public User getUser(int UserID ){
		
		User userRes = usDao.getUserbyID(UserID);
		if(userRes == null ){
			throw new DataNotFoundException("User with ID " + UserID + " not found!");
		}
		return userRes;
	
	}
	
	

}
