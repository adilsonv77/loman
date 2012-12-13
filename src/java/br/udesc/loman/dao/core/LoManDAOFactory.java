package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAOFactory;

public interface LoManDAOFactory extends DAOFactory {

	UsuarioDAO getUsuarioDAO() throws Exception;	
	
	ProjetoDAO getProjetoDAO() throws Exception;
        
        UnidadeDAO getUnidadeDAO() throws Exception;
        
        RoteiroDAO getRoteiroDAO() throws Exception;
        
        TarefaDAO getTarefaDAO() throws Exception;
        
        SlideDAO getSlideDAO() throws Exception;
        
        OcorrenciaDAO getOcorrenciaDAO() throws Exception;
        
        ObjetivoDAO getObjetivoDAO() throws Exception;
        
        ConteudoDAO getConteudoDAO() throws Exception;
        
        RelacaoDAO getRelacaoDAO() throws Exception;
        
}