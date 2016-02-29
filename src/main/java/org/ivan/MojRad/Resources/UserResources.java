package org.ivan.MojRad.Resources;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
	
	@GET
	@Path("/{email}/{pass}")
	public User getUser(@PathParam("email") String email, @PathParam("pass") String password){
	 
		return usDao.getUser(email, password);
	}
	
	
	
	@DELETE
	@Path("/{UserID}")
	public void deleteUser(@PathParam("UserID") int UserID){
	    usDao.deleteUserByID(UserID);
	}
	
	
	@POST
	public  void   addUser(User user){
		usDao.insertUser(user);
	}
	
	
	@PUT
	public void updateUser(User user){
		usDao.updateUser(user);
	}

}
