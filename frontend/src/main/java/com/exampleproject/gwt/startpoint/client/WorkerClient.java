package com.exampleproject.gwt.startpoint.client;



import com.exampleproject.model.shared.TestDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("http://localhost:8091")
public interface WorkerClient extends RestService {

	@GET
	@Path("/test1")
	void get(MethodCallback<String> callback);

	@GET
	@Path("/test")
	void gett(MethodCallback<String> callback);

	@GET
	@Path("/testDb")
	void getDb(MethodCallback<Boolean> callback);

}
