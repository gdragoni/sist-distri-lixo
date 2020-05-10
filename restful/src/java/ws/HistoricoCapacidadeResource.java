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
import modelo.HistoricoCapacidade;
import modelo.HistoricoCapacidadeDAO;

/**
 * REST Web Service
 *
 * @author gdragoni
 */
@Path("historicoCapacidade")
public class HistoricoCapacidadeResource {

    @Context
    private UriInfo context;
    private HistoricoCapacidadeDAO dao;
    private Gson gson;
    /**
     * Creates a new instance of HistoricoCapacidadeResource
     */
    public HistoricoCapacidadeResource() throws ClassNotFoundException, SQLException {
        dao = new HistoricoCapacidadeDAO();
        gson = new Gson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insereHistoricoCapacidade(String json) throws SQLException {
        Gson gson = new Gson();
        HistoricoCapacidade l = gson.fromJson(json, HistoricoCapacidade.class);
        dao.insert(l);
    }
    
    @DELETE
    @Path("{id}")
    public void deletaHistoricoCapacidade(@PathParam("id") int id) throws SQLException {
        dao.delete(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editHistoricoCapacidade(String json) throws SQLException {
        Gson gson = new Gson();
        HistoricoCapacidade c = gson.fromJson(json, HistoricoCapacidade.class);
        dao.edit(c);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaHistoricoCapacidade() throws SQLException {
        ArrayList<HistoricoCapacidade> list = dao.selectHistoricoCapacidade();
        return gson.toJson(list);
    }
    
    @GET
    @Path("{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaHistoricoCapacidadePorData(@PathParam("date") String date) throws SQLException {
        ArrayList<HistoricoCapacidade> list = dao.selectHistoricoCapacidade(date);
        return gson.toJson(list);
    }
    
    @GET
    @Path("{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public String selecionaHistoricoCapacidadeEntreDatas(@PathParam("toDate") String toDate, @PathParam("fromDate") String fromDate) throws SQLException {
        ArrayList<HistoricoCapacidade> list = dao.selectHistoricoCapacidade(fromDate, toDate);
        return gson.toJson(list);
    }
}
