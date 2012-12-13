package br.udesc.loman.dao.jpa;

import br.udesc.dao.jpa.JPADAO;
import java.util.HashMap;
import java.util.Map;
import br.udesc.loman.dao.core.UsuarioDAO;
import br.udesc.loman.modelo.Usuario;
import java.util.List;

public class JPAUsuarioDAO extends JPADAO<Usuario> implements UsuarioDAO {

    public JPAUsuarioDAO() throws Exception {
        super(Usuario.class);
    }

    @Override
    public Usuario buscarLogin(String nick, String senha) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nick", nick);
        params.put("senha", senha);

        Usuario usuario = getJpaUtil().consultaNomeada("usuario.login", params);

        return usuario;

    }

    @Override
    public Usuario buscarPeloNick(String nick) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nick", nick);

        Usuario usuario = getJpaUtil().consultaNomeada("usuario.porNick", params);

        return usuario;

    }
    
    @Override
    public List<Usuario> listarPorNome(String nomePesquisado) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nome", "%"+nomePesquisado+"%");
        return getJpaUtil().listarConsultaNomeada("usuario.porNome", params);

    }
}
