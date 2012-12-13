package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.UnidadeDAO;
import br.udesc.loman.modelo.Unidade;
import br.udesc.loman.modelo.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPAUnidadeDAO extends JPADAO<Unidade> implements UnidadeDAO {

    public JPAUnidadeDAO() throws Exception {
        super(Unidade.class);
    }

    @Override
    public List<Unidade> listarPorNome(String chave) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", "%" + chave + "%");
        return getJpaUtil().listarConsultaNomeada("Unidade.porNome", params);
    }

    @Override
    public Unidade buscarPeloNome(String nome) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", nome);

        Unidade obj = getJpaUtil().consultaNomeada("Unidade.igualNome", params);
        return obj;
    }

    @Override
    public List<Unidade> buscarUnidadesPorCoordenador(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("unidades.coordenador", params);
    }

    @Override
    public List<Unidade> buscarUnidadesComTarefasConcluidas(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("unidades.concluidas", params);
    }

    @Override
    public List<Unidade> buscarUnidadesComProblemas(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("unidades.comproblemas", params);
    }

    @Override
    public List<Unidade> buscarUnidadesProntasParaRevisao(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("unidades.prontaspararevisao", params);
    }

    @Override
    public List<Unidade> buscarUnidadesEmAndamento(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("unidades.emandamento", params);
    }
}
