package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class HistoricoTampaDAO extends DAO {
    public HistoricoTampaDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    public void insert(HistoricoTampa ht) throws SQLException {
        String sql = "INSERT INTO historico_lixeira_sensor_tampa (id_lixeira, valor) VALUES ("
                +ht.getId_lixeira()+", "
                +ht.getValor()+")";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM historico_lixeira_sensor_tampa WHERE id_historico_tampa="+codigo;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
        
    public void edit(HistoricoTampa ht) throws SQLException {
        String sql = "UPDATE historico_lixeira_sensor_tampa SET id_lixeira="+ht.getId_lixeira()
                +", valor="+ht.getValor()+" WHERE historico_lixeira_sensor_tampa.id_historico_tampa="+ht.getId_historico_tampa();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public ArrayList<HistoricoTampa> selectHistoricoTampa() throws SQLException {
        String sql = "SELECT * FROM historico_lixeira_sensor_tampa";
        return selectHistoricoTampaPorQuery(sql);
    }
    
    public ArrayList<HistoricoTampa> selectHistoricoTampa(String date) throws SQLException {
        String sql = "SELECT * FROM historico_lixeira_sensor_tampa WHERE historico_lixeira_sensor_tampa.data='"+date+"'";
        return selectHistoricoTampaPorQuery(sql);
    }
    
    public ArrayList<HistoricoTampa> selectHistoricoTampa(String fromDate, String toDate) throws SQLException {
        String sql = "SELECT * FROM historico_lixeira_sensor_tampa WHERE historico_lixeira_sensor_tampa.data BETWEEN '"+fromDate+"' AND '"+toDate+"'";
        return selectHistoricoTampaPorQuery(sql);
    }
    
    public ArrayList<HistoricoTampa> selectHistoricoTampaPorLixeira(int id) throws SQLException {
        String sql = "SELECT * FROM historico_lixeira_sensor_tampa WHERE historico_lixeira_sensor_tampa.id_lixeira="+id;
        return selectHistoricoTampaPorQuery(sql);
    }
    
    private ArrayList<HistoricoTampa> selectHistoricoTampaPorQuery(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<HistoricoTampa> list = new ArrayList<>();
        while(rs.next()){
            list.add(new HistoricoTampa(rs.getInt("id_historico_tampa"), rs.getInt("id_lixeira"), rs.getBoolean("valor"), rs.getDate("data"), rs.getString("hora")));
        }
        return list;
    }
}
