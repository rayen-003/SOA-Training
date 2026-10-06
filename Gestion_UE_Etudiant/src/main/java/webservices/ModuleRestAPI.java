package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

    @Path("/Module")
    public class ModuleRestAPI {
        static ModuleBusiness helper = new ModuleBusiness();

        //GETLIST
        @Path("/list")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getListModule() {
            return Response.status(200).entity(helper.getAllModules()).build();
        }

        //GET BY MATRICULE
        @Path("/{matricule}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
            Module m = helper.getModuleByMatricule(matricule);
            if (m != null) {
                return Response.status(200).entity(m).build();
            }
            else {
                return Response.status(404).entity("Erreur").build();
            }
        }

        //GET BY TYPE : /api/Module?type=PROFESSIONNEL
        @Path("/")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getModulesByType(@QueryParam("type") Module.TypeModule type) {
            return Response.status(200).entity(helper.getModulesByType(type)).build();
        }

        //GET BY UE : /api/Module/ue/1
        @Path("/ue/{code}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getModulesByUE(@PathParam("code") int code) {
            UniteEnseignement ue = new UniteEnseignement();
            ue.setCode(code);
            return Response.status(200).entity(helper.getModulesByUE(ue)).build();
        }

        //ADD
        @Path("/add")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.TEXT_PLAIN)
        public Response addModule(Module m) {
            if (m.getUniteEnseignement() == null) {
                return Response.status(400).entity("Erreur").build();
            }
            if (helper.addModule(m)) {
                return Response.status(201).entity("ok").build();
            }
            else {
                return Response.status(400).entity("Erreur").build();
            }
        }

        //UPDATE
        @Path("/update/{matricule}")
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.TEXT_PLAIN)
        public Response updateModule(@PathParam("matricule") String matricule, Module m) {
            if (helper.updateModule(matricule, m)) {
                return Response.status(200).entity("ok").build();
            }
            else {
                return Response.status(404).entity("Erreur").build();
            }
        }

        //DELETE
        @Path("/delete/{matricule}")
        @DELETE
        @Produces(MediaType.TEXT_PLAIN)
        public Response deleteModule(@PathParam("matricule") String matricule) {
            if (helper.deleteModule(matricule)) {
                return Response.status(200).entity("ok").build();
            }
            else {
                return Response.status(404).entity("Erreur").build();
            }
        }
    }
