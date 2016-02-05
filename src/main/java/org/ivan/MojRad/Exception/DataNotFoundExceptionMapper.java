package org.ivan.MojRad.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ivan.MojRad.DaoClasses.ErrorClass;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorClass error = new ErrorClass(ex.getMessage(), 404, "Nothing!!");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
