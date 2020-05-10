/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author gdragoni
 */
public class LixeiraDetalhes extends Lixeira {
    private ArrayList<Evento> eventos;
    private ArrayList<HistoricoTampa> historicoTampa;
    private ArrayList<HistoricoCapacidade> historicoCapacidade;
    
    public LixeiraDetalhes(int id, float lat, float lng, String tipo, String ambiente) {
        super(id, lat, lng, tipo, ambiente);
    }

    /**
     * @return the historicoTampa
     */
    public ArrayList<HistoricoTampa> getHistoricoTampa() {
        return historicoTampa;
    }

    /**
     * @param historicoTampa the historicoTampa to set
     */
    public void setHistoricoTampa(ArrayList<HistoricoTampa> historicoTampa) {
        this.historicoTampa = historicoTampa;
    }

    /**
     * @return the historicoCapacidade
     */
    public ArrayList<HistoricoCapacidade> getHistoricoCapacidade() {
        return historicoCapacidade;
    }

    /**
     * @param historicoCapacidade the historicoCapacidade to set
     */
    public void setHistoricoCapacidade(ArrayList<HistoricoCapacidade> historicoCapacidade) {
        this.historicoCapacidade = historicoCapacidade;
    }

    /**
     * @return the eventos
     */
    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    /**
     * @param eventos the eventos to set
     */
    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }
}
