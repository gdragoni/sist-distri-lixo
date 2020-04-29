/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author grei_
 */
public class HistoricoCapacidadeDAO extends DAO {
    public HistoricoCapacidadeDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    public void insert(HistoricoCapacidade hc) throws SQLException {
        String sql = "INSERT INTO historico_lixeira_sensor_capacidade (id_lixeira, capacidade) VALUES ("
                +hc.getId_lixeira()+", "
                +hc.getCapacidade()+")";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM historico_lixeira_sensor_capacidade WHERE id_historico_capacidade="+codigo;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
        
    public void edit(HistoricoCapacidade hc) throws SQLException {
        String sql = "UPDATE historico_lixeira_sensor_capacidade SET id_lixeira="+hc.getId_lixeira()
                +", capacidade="+hc.getCapacidade()+" WHERE historico_lixeira_sensor_capacidade.id_historico_capacidade="+hc.getId_historico_capacidade();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public ArrayList<HistoricoCapacidade> selectHistoricoCapacidade() throws SQLException {
        String sql = "SELECT * FROM historico_lixeira_sensor_capacidade";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<HistoricoCapacidade> list = new ArrayList<>();
        while(rs.next()){
            list.add(new HistoricoCapacidade(rs.getInt("id_historico_capacidade"), rs.getInt("id_lixeira"), rs.getFloat("capacidade"), rs.getDate("data"), rs.getString("hora")));
        }
        return list;
    }
}
