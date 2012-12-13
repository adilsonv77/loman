package br.udesc.loman.dao.jpa;

import br.udesc.loman.dao.core.*;

public class JPADAOFactory implements LoManDAOFactory {

    private JPAUsuarioDAO daoUsuario;    
    private JPAProjetoDAO daoProjeto;
    private JPAUnidadeDAO daoUnidade;
    private JPARoteiroDAO daoRoteiro;
    private JPATarefaDAO daoTarefa;
    private JPAOcorrenciaDAO daoOcorrencia;
    private JPAObjetivoDAO daoObjetivo;
    private JPASlideDAO daoSlide;
    private JPAConteudoDAO daoConteudo;
    private JPARelacaoDAO daoRelacao;

    @Override
    public UsuarioDAO getUsuarioDAO() throws Exception {
        if (daoUsuario == null) {
            daoUsuario = new JPAUsuarioDAO();
        }
        return daoUsuario;
    }

    @Override
    public ProjetoDAO getProjetoDAO() throws Exception {
        if (daoProjeto == null) {
            daoProjeto = new JPAProjetoDAO();
        }
        return daoProjeto;
    }

    @Override
    public UnidadeDAO getUnidadeDAO() throws Exception {
        if (daoUnidade == null) {
            daoUnidade = new JPAUnidadeDAO();
        }
        return daoUnidade;
    }

    @Override
    public RoteiroDAO getRoteiroDAO() throws Exception {
        if (daoRoteiro == null) {
            daoRoteiro = new JPARoteiroDAO();
        }
        return daoRoteiro;
    }

    @Override
    public TarefaDAO getTarefaDAO() throws Exception {
        if (daoTarefa == null) {
            daoTarefa = new JPATarefaDAO();
        }
        return daoTarefa;
    }

    @Override
    public OcorrenciaDAO getOcorrenciaDAO() throws Exception {
        if (daoOcorrencia == null) {
            daoOcorrencia = new JPAOcorrenciaDAO();
        }
        return daoOcorrencia;
    }

    @Override
    public ObjetivoDAO getObjetivoDAO() throws Exception {
        if (daoObjetivo == null) {
            daoObjetivo = new JPAObjetivoDAO();
        }
        return daoObjetivo;
    }

    @Override
    public SlideDAO getSlideDAO() throws Exception {
        if (daoSlide == null) {
            daoSlide = new JPASlideDAO();
        }
        return daoSlide;
    }

    @Override
    public ConteudoDAO getConteudoDAO() throws Exception {
        if (daoConteudo == null) {
            daoConteudo= new JPAConteudoDAO();
        }
        return daoConteudo;
    }

    @Override
    public RelacaoDAO getRelacaoDAO() throws Exception {
        if (daoRelacao == null) {
            daoRelacao= new JPARelacaoDAO();
        }
        return daoRelacao;
    }

}