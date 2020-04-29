package modelo;

import java.sql.Date;

/**
 *
 * @author gdragoni
 */
public class HistoricoTampa {
    private int id_historico_tampa, id_lixeira;
    private Boolean valor;
    private Date data;
    private String hora;
    
    public HistoricoTampa(int id_historico_tampa, int id_lixeira, Boolean valor, Date data, String hora) {
        this.id_historico_tampa = id_historico_tampa;
        this.id_lixeira = id_lixeira;
        this.valor = valor;
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
     * @return the valor
     */
    public Boolean getValor() {
        return valor;
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
     * @return the id_historico_tampa
     */
    public int getId_historico_tampa() {
        return id_historico_tampa;
    }
}
