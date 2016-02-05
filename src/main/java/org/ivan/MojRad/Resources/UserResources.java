package org.ivan.MojRad.Resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ivan.MojRad.DaoClasses.UserDAO;
import org.ivan.MojRad.Service.UserService;
import org.ivan.MojRad.classes.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {
	
	UserDAO usDao = new UserDAO();
	
	UserService uServ = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAlls(){
		return usDao.getAllUsers();
	}
	
	@GET
	@Path("/{UserID}")
	public User getUser(@PathParam("UserID") int UserID ){
		return uServ.getUser(UserID);
	}
	
	@DELETE
	@Path("/{UserID}")
	public void deleteUser(@PathParam("UserID") int UserID){
	    usDao.deleteUserByID(UserID);
	}
	
	
	@POST
	public void addUser(User user){
		usDao.insertUser(user);
		
	}

}
