package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO extends DAO<Usuario> {

    Usuario buscarLogin(String nick, String senha);

    Usuario buscarPeloNick(String nick);

    List<Usuario> listarPorNome(String nome);
}
