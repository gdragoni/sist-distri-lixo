package modelo;

import java.sql.Date;

/**
 *
 * @author gdragoni
 */
public class HistoricoCapacidade {
    private int id_historico_capacidade, id_lixeira;
    private float capacidade;
    private Date data;
    private String hora;
    
    public HistoricoCapacidade(int id_historico_capacidade, int id_lixeira, float capacidade, Date data, String hora) {
        this.id_historico_capacidade = id_historico_capacidade;
        this.id_lixeira = id_lixeira;
        this.capacidade = capacidade;
        this.data = data;
        this.hora = hora;
    }

    /**
     * @return the id_lixeira
     */
    public int getId_lixeira() {
        return id_lixeira;
    }

    /**
     * @return the capacidade
     */
    public float getCapacidade() {
        return capacidade;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @return the id_historico_capacidade
     */
    public int getId_historico_capacidade() {
        return id_historico_capacidade;
    }
}
