package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
@Path("/UE")

public class UniteEnsRestAPI {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    //GETLIST
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUe() {
        return Response.status(200).entity(helper.getListeUE()).build();

    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUniteEnseignement(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity("ok").build();
        }
        else  {
            return Response.status(400).entity("Erreur").build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{code}")
    public Response getUEByCode(@PathParam("code")int code) {
        return Response.status(200).entity(helper.getUEByCode(code)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getUEBySemestre(@QueryParam("Semestre")int Semestre) {
        return Response.status(200).entity(helper.getUEBySemestre(Semestre)).build();
    }
    //UPDATEgi
    @Path("/update/{code}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUniteEnseignement(@PathParam("code") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200).entity("ok").build();
        }
        else {
            return Response.status(404).entity("Erreur").build();
        }
    }

    //DELETE
    @Path("/delete/{code}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUniteEnseignement(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200).entity("ok").build();
        }
        else {
            return Response.status(404).entity("Erreur").build();
        }
    }
}
