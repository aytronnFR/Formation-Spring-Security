package org.dmsproxy.controller;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/auth")
public class AuthControler {
    
    @GET
    @PermitAll
    public String getToken(@QueryParam("name") String name) {
        return name;
    }
}
