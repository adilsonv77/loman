package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.ObjetivoDAO;
import br.udesc.loman.modelo.Objetivo;
import br.udesc.loman.modelo.Unidade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPAObjetivoDAO extends JPADAO<Objetivo> implements ObjetivoDAO {

    public JPAObjetivoDAO() throws Exception {
        super(Objetivo.class);
    }

    @Override
    public List<Objetivo> buscaObjetivosConcluidos(Unidade unidade) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unidade", unidade);
        return getJpaUtil().listarConsultaNomeada("objetivo.concluido", params);
    }
}
