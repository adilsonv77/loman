package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Slide;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.modelo.Usuario;
import java.util.List;

public interface TarefaDAO extends DAO<Tarefa> {   
    
    List<Tarefa> buscaTarefasDisponiveis(Usuario usuario);
    
    List<Tarefa> buscaTarefasAssumidas(Usuario usuario);
    
    List<Tarefa> buscaTarefasConlcuidas(Usuario usuario);
    
    List<Tarefa> buscaTarefasProntasParaRevisao(Usuario usuario);
    
    List<Tarefa> buscaTarefasSlide(Slide slide);
    
    List<Tarefa> buscaTarefasProgramadorOuDesigner(Usuario usuario);
    
    List<Tarefa> buscaTarefasProgramadorOuDesignerOuCoordenador(Usuario usuario);        
    
}	