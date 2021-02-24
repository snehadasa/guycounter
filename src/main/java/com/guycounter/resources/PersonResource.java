package com.guycounter.resources;

import com.codahale.metrics.annotation.Timed;
import com.guycounter.core.People;
import com.guycounter.db.PeopleDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.OptionalLong;
import java.util.Optional;

@Path("/person/")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private PeopleDao peopleDao;

    public PersonResource(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("{id}")
    public Optional<People> findPerson(@PathParam("id") OptionalLong id) {
        return peopleDao.findById(id.getAsLong());
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
    public People createOrUpdateRecord(People people) {
        return peopleDao.create(people);
    }
}