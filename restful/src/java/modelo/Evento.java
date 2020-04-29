package modelo;

import java.sql.Date;

/**
 *
 * @author gdragoni
 */
public class Evento {
    private int id_evento, id_lixeira, id_usuario;
    private Date data;
    private String hora, descricao;
    
    public Evento(int id_evento, int id_lixeira, int id_usuario, Date data, String hora, String descricao) {
        this.id_evento = id_evento;
        this.id_lixeira = id_lixeira;
        this.id_usuario = id_usuario;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    /**
     * @return the id_lixeira
     */
    public int getId_lixeira() {
        return id_lixeira;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the id_evento
     */
    public int getId_evento() {
        return id_evento;
    }
}
