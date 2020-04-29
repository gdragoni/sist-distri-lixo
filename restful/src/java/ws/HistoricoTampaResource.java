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
import modelo.HistoricoTampa;
import modelo.HistoricoTampaDAO;

/**
 * REST Web Service
 *
 * @author gdragoni
 */
@Path("historicoTampa")
public class HistoricoTampaResource {

    @Context
    private UriInfo context;
    private HistoricoTampaDAO dao;
    private Gson gson;
    /**
     * Creates a new instance of HistoricoTampaResource
     */
    public HistoricoTampaResource() throws ClassNotFoundException, SQLException {
        dao = new HistoricoTampaDAO();
        gson = new Gson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insereHistoricoTampa(String json) throws SQLException {
        Gson gson = new Gson();
        HistoricoTampa l = gson.fromJson(json, HistoricoTampa.class);
        dao.insert(l);
    }
    
    @DELETE
    @Path("{id}")
    public void deletaHistoricoTampa(@PathParam("id") int id) throws SQLException {
        dao.delete(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editHistoricoTampa(String json) throws SQLException {
        Gson gson = new Gson();
        HistoricoTampa c = gson.fromJson(json, HistoricoTampa.class);
        dao.edit(c);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaHistoricoTampa() throws SQLException {
        ArrayList<HistoricoTampa> list = dao.selectHistoricoTampa();
        return gson.toJson(list);
    }
}
