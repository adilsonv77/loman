package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.SlideDAO;
import br.udesc.loman.modelo.Slide;

public class JPASlideDAO extends JPADAO<Slide> implements SlideDAO {

    public JPASlideDAO() throws Exception {
        super(Slide.class);
    }
}
