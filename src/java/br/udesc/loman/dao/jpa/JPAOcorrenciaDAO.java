package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.OcorrenciaDAO;
import br.udesc.loman.modelo.Ocorrencia;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.modelo.Unidade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPAOcorrenciaDAO extends JPADAO<Ocorrencia> implements OcorrenciaDAO {

    public JPAOcorrenciaDAO() throws Exception {
        super(Ocorrencia.class);
    }

    @Override
    public List<Ocorrencia> buscaOcorrenciasTarefa(Tarefa tarefa) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tarefa", tarefa);
        return getJpaUtil().listarConsultaNomeada("ocorrencia.tarefas", params);
    }

    @Override
    public List<Ocorrencia> buscaOcorrenciasTarefaMateriais(Tarefa tarefa) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tarefa", tarefa);
        return getJpaUtil().listarConsultaNomeada("ocorrenciataerfa.materiais", params);
    }

    @Override
    public List<Ocorrencia> buscaOcorrenciasUnidade(Unidade unidade) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unidade", unidade);
        return getJpaUtil().listarConsultaNomeada("ocorrencia.unidade", params);
    }

    @Override
    public List<Ocorrencia> buscaOcorrenciasUnidadeMateriais(Unidade unidade) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unidade", unidade);
        return getJpaUtil().listarConsultaNomeada("ocorrenciaunidade.materiais", params);
    }
    
    
}
