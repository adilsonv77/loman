package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.RelacaoDAO;
import br.udesc.loman.modelo.Relacao;

public class JPARelacaoDAO extends JPADAO<Relacao> implements RelacaoDAO {

    public JPARelacaoDAO() throws Exception {
        super(Relacao.class);
    }
    
}
