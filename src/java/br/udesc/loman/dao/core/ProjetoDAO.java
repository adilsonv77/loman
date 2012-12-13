package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Projeto;
import br.udesc.loman.modelo.Usuario;
import java.util.List;

public interface ProjetoDAO extends DAO<Projeto> {

    List<Projeto> listarPorTitulo(String chave, Usuario usuario);

    public Projeto buscarPeloTitulo(String titulo);
    
    List<Projeto> buscarProjetosCoordenadorProfessorConteudista(Usuario usuario);
    
    List<Projeto> buscarProjetosProfessorConteudista(Usuario usuario);
    
    List<Projeto> buscarProjetosCoordenador(Usuario usuario);

}
	