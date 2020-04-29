package modelo;

/**
 *
 * @author gdragoni
 */
public class Funcionario {
    private int id;
    private String nome;
    
    public Funcionario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
}
