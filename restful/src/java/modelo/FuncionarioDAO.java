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
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        ArrayList<Funcionario> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Funcionario(rs.getInt("id_funcionario"), rs.getString("nome")));
        }
        return list;
    }
}
