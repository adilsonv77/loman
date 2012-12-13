package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.TarefaDAO;
import br.udesc.loman.modelo.Slide;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.modelo.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPATarefaDAO extends JPADAO<Tarefa> implements TarefaDAO {

    public JPATarefaDAO() throws Exception {
        super(Tarefa.class);
    }

    @Override
    public List<Tarefa> buscaTarefasDisponiveis(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("tarefa.disponiveis", params);
    }

    @Override
    public List<Tarefa> buscaTarefasAssumidas(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("tarefa.assumidas", params);
    }
    
    @Override
    public List<Tarefa> buscaTarefasConlcuidas(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("tarefa.concluidas", params);
    }

    @Override
    public List<Tarefa> buscaTarefasProntasParaRevisao(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("tarefa.prontaspararevisao", params);
    }

    @Override
    public List<Tarefa> buscaTarefasSlide(Slide slide) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("slide", slide);
        return getJpaUtil().listarConsultaNomeada("tarefa.slide", params);
    }

    @Override
    public List<Tarefa> buscaTarefasProgramadorOuDesigner(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("tarefa.programadordesigner", params);
    }

    @Override
    public List<Tarefa> buscaTarefasProgramadorOuDesignerOuCoordenador(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("tarefa.programadordesignercoordenador", params);
    }   
    
}
