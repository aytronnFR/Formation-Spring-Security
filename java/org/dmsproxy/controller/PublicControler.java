package org.dmsproxy.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dmsproxy.services.UserService;

@Path("/public")
public class PublicControler {

    @Inject
    UserService uSvc;

    @GET
    @Path("/")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String publicResource() {
        // uSvc.createUser("steve", "user", Optional.of("password"));
        return "public";
   }
}