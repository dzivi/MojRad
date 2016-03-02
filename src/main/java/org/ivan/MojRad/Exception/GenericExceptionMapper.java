package org.ivan.MojRad.Exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ivan.MojRad.DaoClasses.ErrorClass;

//@Provider
public class GenericExceptionMapper  implements ExceptionMapper<Throwable>{

@Override
public Response toResponse(Throwable ex) {
	 ErrorClass error = new ErrorClass(ex.getMessage(), 500, "Nista!!");
	return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(error).build();
 }
}
