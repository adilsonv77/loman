package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Ocorrencia;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.modelo.Unidade;
import java.util.List;

public interface OcorrenciaDAO extends DAO<Ocorrencia> {   
    
    List<Ocorrencia> buscaOcorrenciasTarefa(Tarefa tarefa); 
    
    List<Ocorrencia> buscaOcorrenciasTarefaMateriais(Tarefa tarefa);
    
    List<Ocorrencia> buscaOcorrenciasUnidadeMateriais(Unidade unidade);
    
    List<Ocorrencia> buscaOcorrenciasUnidade(Unidade unidade);
    
}	