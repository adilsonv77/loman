package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Unidade;
import br.udesc.loman.modelo.Usuario;
import java.util.List;

public interface UnidadeDAO extends DAO<Unidade> {

    List<Unidade> listarPorNome(String chave);
    
    public Unidade buscarPeloNome(String nome);
    
    List<Unidade> buscarUnidadesPorCoordenador(Usuario usuario);
    
    List<Unidade> buscarUnidadesComTarefasConcluidas(Usuario usuario);
    
    List<Unidade> buscarUnidadesComProblemas(Usuario usuario);
    
    List<Unidade> buscarUnidadesProntasParaRevisao(Usuario usuario);
    
    List<Unidade> buscarUnidadesEmAndamento(Usuario usuario);

}
	