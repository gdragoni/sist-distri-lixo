package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class EventoDAO extends DAO {
    public EventoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    public void insert(Evento e) throws SQLException {
        String sql = "INSERT INTO evento (id_lixeira, descricao, id_usuario) VALUES ("
                +e.getId_lixeira()+", "
                +"'"+e.getDescricao()+"', "
                +e.getId_usuario()+")";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM evento WHERE id_evento="+codigo;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
        
    public void edit(Evento e) throws SQLException {
        String sql = "UPDATE evento SET id_lixeira="+e.getId_lixeira()
                +", id_usuario="+e.getId_usuario()+", descricao='"+e.getDescricao()
                +"' WHERE evento.id_evento="+e.getId_evento();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public ArrayList<Evento> selectEvento() throws SQLException {
        String sql = "SELECT * FROM evento";
        return selectEventoPorQuery(sql);
    }
    
    public ArrayList<Evento> selectEventosPorLixeira(int id) throws SQLException {
        String sql = "SELECT * FROM evento WHERE evento.id_lixeira="+id;
        return selectEventoPorQuery(sql);
    }
    
    public ArrayList<Evento> selectEventosPorUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM evento WHERE evento.id_usuario="+id;
        return selectEventoPorQuery(sql);
    }
    
    public ArrayList<Evento> selectEventoPorData(String date) throws SQLException {
        String sql = "SELECT * FROM evento WHERE evento.data='"+date+"'";
        return selectEventoPorQuery(sql);
    }
    
    public ArrayList<Evento> selectEventoPorDatas(String fromDate, String toDate) throws SQLException {
        String sql = "SELECT * FROM evento WHERE evento.data BETWEEN '"+fromDate+"' AND '"+toDate+"'";
        return selectEventoPorQuery(sql);
    }
    
    private ArrayList<Evento> selectEventoPorQuery(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Evento> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Evento(rs.getInt("id_evento"), rs.getInt("id_lixeira"), rs.getInt("id_usuario"), rs.getDate("data"), rs.getString("hora"), rs.getString("descricao")));
        }
        return list;
    }
}
