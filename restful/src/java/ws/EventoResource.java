/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Evento;
import modelo.EventoDAO;

/**
 * REST Web Service
 *
 * @author grei_
 */
@Path("evento")
public class EventoResource {

    @Context
    private UriInfo context;
    private EventoDAO dao;
    private Gson gson;
    /**
     * Creates a new instance of EventoResource
     */
    public EventoResource() throws ClassNotFoundException, SQLException {
        dao = new EventoDAO();
       gson = new Gson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insereEvento(String json) throws SQLException {
        Gson gson = new Gson();
        Evento l = gson.fromJson(json, Evento.class);
        dao.insert(l);
    }
    
    @DELETE
    @Path("{id}")
    public void deletaEvento(@PathParam("id") int id) throws SQLException {
        dao.delete(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editEvento(String json) throws SQLException {
        Gson gson = new Gson();
        Evento c = gson.fromJson(json, Evento.class);
        dao.edit(c);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaEvento() throws SQLException {
        ArrayList<Evento> list = dao.selectEvento();
        return gson.toJson(list);
    }
    
    @GET
    @Path("{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaEventoPorData(@PathParam("date") String date) throws SQLException {
        ArrayList<Evento> list = dao.selectEventoPorData(date);
        return gson.toJson(list);
    }
    
    @GET
    @Path("{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaEventoPorDatas(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate) throws SQLException {
        ArrayList<Evento> list = dao.selectEventoPorDatas(fromDate, toDate);
        return gson.toJson(list);
    }
}
