package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class FuncionarioDAO extends DAO {
    
    public FuncionarioDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    public void insert(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionario (id_funcionario, nome) VALUES ("
                +f.getId()+", '"
                +f.getNome()+"')";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_funcionario="+codigo;
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
        
    public void edit(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionario SET nome='"+f.getNome()
                +"' WHERE funcionario.id_funcionario="+f.getId();
        PreparedStatement stm = con.prepareStatement(sql);
        stm.execute();
    }
    
    public ArrayList<Funcionario> selectFuncionario() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        return selectFuncionarioPorQuery(sql);
    }
    
    public FuncionarioDetalhes selectFuncionarioDetalhes(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Funcionario> lista = selectFuncionarioPorQuery("SELECT * FROM funcionario WHERE funcionario.id_funcionario="+id);
        if(lista.isEmpty()) {
            return null;
        }
        Funcionario funcionario = lista.get(0);
        FuncionarioDetalhes detalhes;
        detalhes = new FuncionarioDetalhes(funcionario.getId(), funcionario.getNome());
        detalhes.setEventos(new EventoDAO().selectEventosPorUsuario(id));
        return detalhes;
    }
    
    public ArrayList<Funcionario> selectFuncionarioPorQuery(String sql) throws SQLException {
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Funcionario> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Funcionario(rs.getInt("id_funcionario"), rs.getString("nome")));
        }
        return list;
    }
}
