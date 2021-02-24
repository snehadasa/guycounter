package com.guycounter.resources;

import com.codahale.metrics.annotation.Timed;
import com.guycounter.core.PplGroups;
import com.guycounter.db.GroupDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.OptionalLong;
import java.util.Optional;

@Path("/group/")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {
    private GroupDao groupDao;

    public GroupResource(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("{id}")
    public Optional<PplGroups> findPerson(@PathParam("id") OptionalLong id) {
        return groupDao.findById(id.getAsLong());
    }

//    @GET
//    @Timed
//    @UnitOfWork
//    @Path("name/{name}")
//    public Optional<People> findPersonByName(@PathParam("name") String name) {
//        return peopleDao.findById(id.getAsLong());
//    }

    @POST
    @Timed
    @UnitOfWork
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public PplGroups createOrUpdateRecord(PplGroups groups) {
        return groupDao.create(groups);
    }
}
