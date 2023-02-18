package com.myservlet;
import com.service.Person;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;



@Path("persons")
public class Demo {

    static ArrayList<Person> person1 = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        Person p2 = new Person("sherif", 23);
        Person p1 = new Person("hassan", 27);
        person1.add(p1);
        person1.add(p2);
        return Response.accepted().entity(person1).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(Person p) {
        person1.add(p);
        URI uri = UriBuilder.fromPath("/{persons}").build(p);
        return Response.created(uri).entity(person1).build();
    }

    @DELETE
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("name") String name) {
        for (int i = 0; i < person1.size(); i++) {
            if (person1.get(i).getName().equals(name)) {
                person1.remove(i);
            }
        }
        return Response.ok().entity(person1).build();
    }

    @PUT
    @Path("{name}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
    public Response updateUser(@PathParam("name") String name, String input) {

        for (int i = 0; i < person1.size(); i++) {
            if (person1.get(i).getName().equals(name)) {
                person1.get(i).setName(input);
            }
        }
        return Response.accepted().entity(person1).build();
    }

}
