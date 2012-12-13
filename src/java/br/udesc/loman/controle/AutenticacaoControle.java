package br.udesc.loman.controle;

import br.udesc.loman.modelo.Usuario;

public interface AutenticacaoControle {

	void salvarSessao(Usuario usuario) throws Exception;

	Usuario getUsuarioSessao();


}
