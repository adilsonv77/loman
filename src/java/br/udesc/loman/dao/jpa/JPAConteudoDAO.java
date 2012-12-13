package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.ConteudoDAO;
import br.udesc.loman.modelo.Conteudo;
import br.udesc.loman.modelo.Relacao;
import br.udesc.loman.modelo.Unidade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAConteudoDAO extends JPADAO<Conteudo> implements ConteudoDAO {

    public JPAConteudoDAO() throws Exception {
        super(Conteudo.class);
    }

    @Override
    public List<Conteudo> buscaConteudosPorUnidade(Unidade unidade) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unidade", unidade);
        return getJpaUtil().listarConsultaNomeada("conteudo.unidade", params);
    }

    @Override
    public List<Conteudo> buscarConteudosMapa(Unidade unidade) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unidade", unidade);
        return getJpaUtil().listarConsultaNomeada("conteudo.mapa", params);
    }

    @Override
    public List<Conteudo> buscarConteudosDisponiveis(Unidade unidade) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unidade", unidade);
        return getJpaUtil().listarConsultaNomeada("conteudo.disponivel", params);
    }

    @Override
    public Conteudo buscaConteudoPorChave(long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        Conteudo obj = getJpaUtil().consultaNomeada("conteudo.id", params);
        return obj;
    }

    @Override
    public List<Conteudo> buscarConteudosRelacao(Relacao relacao) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("relacao", relacao);
        return getJpaUtil().listarConsultaNomeada("conteudo.relacao", params);
    }
}
