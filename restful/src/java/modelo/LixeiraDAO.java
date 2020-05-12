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
 * @author gdragoni
 */
public class LixeiraDAO extends DAO {
    
    public LixeiraDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    public void insert(Lixeira l) throws SQLException {
        String sql = "INSERT INTO lixeira (id_lixeira, lat, lng, tipo, ambiente) VALUES ("
                +l.getId()+", "
                +""+l.getLat()+", "
                +""+l.getLng()+", "
                +"'"+l.getTipo()+"', '"
                +l.getAmbiente()+"')";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM lixeira WHERE id_lixeira="+codigo;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
        
    public void edit(Lixeira l) throws SQLException {
        String sql = "UPDATE lixeira SET lat="+l.getLat()
                +", lng="+l.getLng()+", tipo='"+l.getTipo()+"', ambiente='"+l.getAmbiente()
                +"' WHERE lixeira.id_lixeira="+l.getId();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public LixeiraDetalhes selectLixeiraDetalhes(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Lixeira> lista = selectLixeiraQuery("SELECT * FROM lixeira WHERE lixeira.id_lixeira="+id);
        if(lista.isEmpty()) {
            return null;
        }
        Lixeira lixeira = lista.get(0);
        LixeiraDetalhes lixeiraDetalhes;
        lixeiraDetalhes = new LixeiraDetalhes(lixeira.getId(), lixeira.getLat(), lixeira.getLng(), lixeira.getTipo(), lixeira.getAmbiente());
        lixeiraDetalhes.setHistoricoCapacidade(new HistoricoCapacidadeDAO().selectHistoricoCapacidadePorLixeira(id));
        lixeiraDetalhes.setEventos(new EventoDAO().selectEventosPorLixeira(id));
        lixeiraDetalhes.setHistoricoTampa(new HistoricoTampaDAO().selectHistoricoTampaPorLixeira(id));
        return lixeiraDetalhes;
    }
    
    public ArrayList<Lixeira> selectLixeira() throws SQLException {
        String sql = "SELECT * FROM lixeira";
        return selectLixeiraQuery(sql);
    }
    
    public ArrayList<Lixeira> selectLixeira(String tipo) throws SQLException {
        String sql = "SELECT * FROM lixeira WHERE lixeira.tipo='"+tipo+"'";
        return selectLixeiraQuery(sql);
    }
    
    private ArrayList<Lixeira> selectLixeiraQuery(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Lixeira> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Lixeira(rs.getInt("id_lixeira"), rs.getFloat("lat"), rs.getFloat("lng"), rs.getString("tipo"), rs.getString("ambiente")));
        }
        return list;
    }
}
