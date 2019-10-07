package com.exampleproject.gwt.startpoint.client;



import com.exampleproject.model.shared.Organization;
import com.exampleproject.model.shared.TestDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

public interface WorkerClient extends RestService {

	@GET
	@Path("/test")
	void get(MethodCallback<TestDto> callback);

	@GET
	@Path("/testDb")
	void getDb(MethodCallback<Boolean> callback);

	@GET
    @Path("/organization/getAll")
    void getOrgs(MethodCallback<List<Organization>> callback);

	@POST
	@Path("/organization/save")
	void save(Organization request, MethodCallback<Organization> callback);

	@POST
	@Path("/organization/delete")
	void delete(Organization request, MethodCallback<Void> callback);

}
