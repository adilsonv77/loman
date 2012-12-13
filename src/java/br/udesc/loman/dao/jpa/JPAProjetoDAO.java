package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import br.udesc.loman.dao.core.ProjetoDAO;
import br.udesc.loman.modelo.Projeto;
import br.udesc.loman.modelo.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPAProjetoDAO extends JPADAO<Projeto> implements ProjetoDAO {

    public JPAProjetoDAO() throws Exception {
        super(Projeto.class);
    }

    @Override
    public List<Projeto> listarPorTitulo(String chave, Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", "%"+chave+"%");
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("projeto.porTitulo", params);
    }

    @Override
    public Projeto buscarPeloTitulo(String titulo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("titulo", titulo);
        Projeto obj = getJpaUtil().consultaNomeada("projeto.igualTitulo", params);
        return obj;
    }

    @Override
    public List<Projeto> buscarProjetosCoordenadorProfessorConteudista(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("projeto.coordenadorprofessorconteudista", params);
    }

    @Override
    public List<Projeto> buscarProjetosProfessorConteudista(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("projeto.professorconteudista", params);
    }

    @Override
    public List<Projeto> buscarProjetosCoordenador(Usuario usuario) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuario", usuario);
        return getJpaUtil().listarConsultaNomeada("projeto.coordenador", params);
    }
}
