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
import modelo.Funcionario;
import modelo.FuncionarioDAO;

/**
 * REST Web Service
 *
 * @author gdragoni
 */
@Path("funcionario")
public class FuncionarioResource {

    @Context
    private UriInfo context;
    private FuncionarioDAO dao;
    private Gson gson;
    /**
     * Creates a new instance of FuncionarioResource
     */
    public FuncionarioResource() throws ClassNotFoundException, SQLException {
        dao = new FuncionarioDAO();
        gson = new Gson();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insereFuncionario(String json) throws SQLException {
        Gson gson = new Gson();
        Funcionario l = gson.fromJson(json, Funcionario.class);
        dao.insert(l);
    }
    
    @DELETE
    @Path("{id}")
    public void deletaFuncionario(@PathParam("id") int id) throws SQLException {
        dao.delete(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editFuncionario(String json) throws SQLException {
        Gson gson = new Gson();
        Funcionario c = gson.fromJson(json, Funcionario.class);
        dao.edit(c);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaFuncionario() throws SQLException {
        ArrayList<Funcionario> list = dao.selectFuncionario();
        return gson.toJson(list);
    }
}
