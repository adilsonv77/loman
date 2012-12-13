package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.RoteiroDAO;
import br.udesc.loman.modelo.Roteiro;

public class JPARoteiroDAO extends JPADAO<Roteiro> implements RoteiroDAO {

    public JPARoteiroDAO() throws Exception {
        super(Roteiro.class);
    }  
    
}