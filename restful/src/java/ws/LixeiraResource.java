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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.Lixeira;
import modelo.LixeiraDAO;
import modelo.LixeiraDetalhes;

/**
 * REST Web Service
 *
 * @author gdragoni
 */
@Path("lixeira")
public class LixeiraResource {

    @Context
    private UriInfo context;
    private LixeiraDAO dao;
    private Gson gson;
    /**
     * Creates a new instance of LixeiraResource
     */
    public LixeiraResource() throws ClassNotFoundException, SQLException {
       dao = new LixeiraDAO();
       gson = new Gson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insereLixeira(String json) throws SQLException {
        Gson gson = new Gson();
        Lixeira l = gson.fromJson(json, Lixeira.class);
        dao.insert(l);
    }
    
    @DELETE
    @Path("{id}")
    public void deletaLixeira(@PathParam("id") int id) throws SQLException {
        dao.delete(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editLixeira(String json) throws SQLException {
        Gson gson = new Gson();
        Lixeira c = gson.fromJson(json, Lixeira.class);
        dao.edit(c);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaLixeiraPorTipo(@QueryParam("tipo") String tipo) throws SQLException {
        ArrayList<Lixeira> list = tipo == null ? dao.selectLixeira() : dao.selectLixeira(tipo);
        return gson.toJson(list);
    }
    
    @GET
    @Path("detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaLixeiraDetalhes(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        LixeiraDetalhes lixeiraDetalhes = dao.selectLixeiraDetalhes(id);
        if(lixeiraDetalhes == null) {
            return "Nâo foi encontrado lixeira com o ID informado";
        }
        return gson.toJson(lixeiraDetalhes);
    }
}
