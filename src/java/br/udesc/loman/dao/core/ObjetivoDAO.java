package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Objetivo;
import br.udesc.loman.modelo.Unidade;
import java.util.List;

public interface ObjetivoDAO extends DAO<Objetivo>{
    
    List<Objetivo> buscaObjetivosConcluidos(Unidade unidade);
        
}
