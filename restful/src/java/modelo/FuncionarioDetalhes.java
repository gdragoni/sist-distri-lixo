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
public class FuncionarioDetalhes extends Funcionario {
    private ArrayList<Evento> eventos;
    
    public FuncionarioDetalhes(int id, String nome) {
        super(id, nome);
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
