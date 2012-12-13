package br.udesc.loman.controle;

import br.udesc.controle.CadastroUC;
import br.udesc.controle.UdescException;
import br.udesc.loman.dao.core.LoManDAOFactory;
import br.udesc.loman.dao.core.RoteiroDAO;
import br.udesc.loman.modelo.*;
import br.udesc.loman.web.LoManListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroRoteirosUC implements CadastroUC<Roteiro, String> {

    private LoManDAOFactory daoFactory;

    public CadastroRoteirosUC(LoManDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Roteiro novo() throws Exception {
        Roteiro r = new Roteiro();
        return r;
    }

    @Override
    public List<UdescException> salvar(Roteiro rot) throws Exception {
        RoteiroDAO dao = daoFactory.getRoteiroDAO();
        List<UdescException> excs = new ArrayList<UdescException>();                

        if (excs.isEmpty()) {
            if (rot.getId() == 0) {
                dao.incluir(rot);
                System.out.println(rot.getId() + " -> incluir roteiro");
            } else {
                dao.alterar(rot);
                System.out.println(rot.getId() + " -> alterar roteiro");
            }
        }
        return excs;
    }

    @Override
    public List<Roteiro> listar() throws Exception {
        System.out.println("listar");
        return daoFactory.getRoteiroDAO().listarTodos();
    }

    @Override
    public Roteiro carregar(long l) throws Exception {
        return daoFactory.getRoteiroDAO().ler(l);
    }

    @Override
    public void excluir(long l) throws Exception {
        daoFactory.getRoteiroDAO().excluir(daoFactory.getRoteiroDAO().ler(l));
    }

    @Override
    public List<Roteiro> listarPesquisa(String t2) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Projeto> buscarProjetosCoordenadorProfessorConteudista(Usuario usuario) throws Exception{
        return LoManListener.getDAOFactory().getProjetoDAO().buscarProjetosCoordenadorProfessorConteudista(usuario);
    }
    
    public List<Unidade> buscarUnidadesComProblemas(Usuario usuario) throws Exception{
        return LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesComProblemas(usuario);
    }
    
    public List<Ocorrencia> buscaOcorrenciasUnidade(Unidade unidade) throws Exception{
        return LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasUnidade(unidade);
    }
    
    public List<Conteudo> buscarConteudosMapa(Unidade unidade) throws Exception{
        return LoManListener.getDAOFactory().getConteudoDAO().buscarConteudosMapa(unidade);
    }
    
    public List<Conteudo> buscarConteudosDisponiveis(Unidade unidade) throws Exception{
        return LoManListener.getDAOFactory().getConteudoDAO().buscarConteudosDisponiveis(unidade);
    }
    
    public List<Tarefa> buscaTarefasSlide(Slide slide) throws Exception{
        return LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasSlide(slide);
    }
    
    public void incluirSlide(Slide slide) throws Exception{
        LoManListener.getDAOFactory().getSlideDAO().incluir(slide);
    }
    
    public void incluirRelacao(Relacao relacao) throws Exception{
        LoManListener.getDAOFactory().getRelacaoDAO().incluir(relacao);
    }
    
    public void alterarSlide(Slide slide) throws Exception{
        LoManListener.getDAOFactory().getSlideDAO().alterar(slide);
    }
    
    public void alterarRelacao(Relacao relacao) throws Exception{
        LoManListener.getDAOFactory().getRelacaoDAO().alterar(relacao);
    }
    
    public void excluirTarefa(Tarefa tarefa) throws Exception{
        LoManListener.getDAOFactory().getTarefaDAO().excluir(tarefa);
    }
    
    public void excluirSlide(Slide slide) throws Exception{
        LoManListener.getDAOFactory().getSlideDAO().excluir(slide);
    }
    
    public List<Conteudo> buscarConteudosRelacao(Relacao relacao) throws Exception{
        return LoManListener.getDAOFactory().getConteudoDAO().buscarConteudosRelacao(relacao);
    }
    
    public void alterarConteudo(Conteudo conteudo) throws Exception{
        LoManListener.getDAOFactory().getConteudoDAO().alterar(conteudo);
    }        
    
    public void excluirRelacao(Relacao relacao) throws Exception{
        LoManListener.getDAOFactory().getRelacaoDAO().excluir(relacao);
    }
       
    public Conteudo buscaConteudoPorChave(long id) throws Exception{
        return LoManListener.getDAOFactory().getConteudoDAO().buscaConteudoPorChave(id);
    }
    
    public Relacao buscarRelacaoPorChave(long id) throws Exception{
        return LoManListener.getDAOFactory().getRelacaoDAO().ler(id);
    }
}