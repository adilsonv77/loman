package br.udesc.loman.dwr;

import br.udesc.loman.controle.CadastroRoteirosUC;
import br.udesc.loman.modelo.Conteudo;
import br.udesc.loman.modelo.Relacao;
import br.udesc.loman.modelo.Roteiro;
import br.udesc.loman.web.LoManListener;
import br.udesc.loman.web.beans.CadRoteiro;
import br.udesc.web.CRUDSemPesquisa;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class MapaConceitual extends CRUDSemPesquisa<Roteiro> {

    private final CadastroRoteirosUC cruc;

    public MapaConceitual() {
        super(new CadastroRoteirosUC(LoManListener.getDAOFactory()), new String[]{});
        this.cruc = (CadastroRoteirosUC) cuc;
    }

    private CadRoteiro getBeanRoteiro() {
        WebContext wcf = WebContextFactory.get();
        HttpSession session = wcf.getHttpServletRequest().getSession();
        return (CadRoteiro) session.getAttribute("cadRoteiro");
    }

    public void init() throws Exception {
        getBeanRoteiro().atualizaListaDeConteudos();
    }

    public void adicionarConteudo(long idNodo, String descricaoNodo) throws Exception {
        Conteudo cont = cruc.buscaConteudoPorChave(idNodo);
        cont.setMapa(true);
        cruc.alterarConteudo(cont);
        getBeanRoteiro().atualizaListaDeConteudos();
    }

    public void adicionarRelacao(long idNodo_1, long idNodo_2) throws Exception {
        //Setando Arestas para a Relação
        List<Conteudo> conteudosRelacao = new ArrayList<Conteudo>();
        Conteudo cont1 = cruc.buscaConteudoPorChave(idNodo_1);
        Conteudo cont2 = cruc.buscaConteudoPorChave(idNodo_2);
        conteudosRelacao.add(cont1);
        conteudosRelacao.add(cont2);

        //Incluindo Nova Relação no Banco de Dados
        Relacao relacao = new Relacao();
        relacao.setDescricao("Relaciona-se");
        relacao.setConteudos(conteudosRelacao);
        cruc.incluirRelacao(relacao);
        getBeanRoteiro().refresh();
    }

    public void adicionarAresta(long id_rel, long id_cont) throws Exception {
        Relacao rel = cruc.buscarRelacaoPorChave(id_rel);
        Conteudo cont = cruc.buscaConteudoPorChave(id_cont);
        rel.getConteudos().add(cont);
        cruc.alterarRelacao(rel);                        
        getBeanRoteiro().refresh();
    }

    //LISTAR CONTEUDOS
    public List<Long> listarConteudosId() {
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        List<Long> rs = new ArrayList<Long>(cs.size());
        for (Conteudo c : cs) {
            rs.add(c.getId());
        }
        return rs;
    }

    public List<String> listarConteudosDescricao() {
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        List<String> rs = new ArrayList<String>(cs.size());
        for (Conteudo c : cs) {
            rs.add(c.getDescricao());
        }

        return rs;
    }
    //***

    //LISTAR ARESTAS
    public List<Long> listarArestasConteudosId() {
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        List<Long> rs = new ArrayList<Long>();
        for (Conteudo c : cs) {
            if (!c.getRelacoes().isEmpty()) {
                for (Relacao r : c.getRelacoes()) {
                    rs.add(c.getId());
                }
            }
        }
        return rs;
    }

    public List<Long> listarArestasRelacoesId() {
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        List<Long> rs = new ArrayList<Long>();
        for (Conteudo c : cs) {
            if (!c.getRelacoes().isEmpty()) {
                for (Relacao r : c.getRelacoes()) {
                    rs.add(r.getId());
                }
            }
        }
        return rs;
    }
    //**

    //LISTAR RELACOES
    public List<Long> listarRelacoesId() {
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        List<Long> rs = new ArrayList<Long>();
        ArrayList<Long> relacoes = new ArrayList<Long>();
        for (Conteudo c : cs) {
            if (!c.getRelacoes().isEmpty()) {
                for (Relacao r : c.getRelacoes()) {
                    if (!relacoes.contains(r.getId())) {
                        relacoes.add(r.getId());
                        rs.add(r.getId());
                    }
                }
            }
        }
        return rs;
    }

    public List<String> listarRelacoesDescricao() {
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        List<String> rs = new ArrayList<String>();
        ArrayList<Long> relacoes = new ArrayList<Long>();
        for (Conteudo c : cs) {
            if (!c.getRelacoes().isEmpty()) {
                for (Relacao r : c.getRelacoes()) {
                    if (!relacoes.contains(r.getId())) {
                        relacoes.add(r.getId());
                        rs.add(r.getDescricao());
                    }
                }
            }
        }
        return rs;
    }
    //***

    public void setarRelacao(long idRelacao, String descricaoRelacao) throws Exception {
        getBeanRoteiro().getRelacao().setId(idRelacao);
        getBeanRoteiro().getRelacao().setDescricao(descricaoRelacao);
        getBeanRoteiro().carregaConteudosDaRelacao();
    }

    public void alterarRelacao(String descricao) throws Exception {
        Relacao relacao = getBeanRoteiro().getRelacao();
        List<Conteudo> conteudosRelacao = getBeanRoteiro().carregaConteudosDaRelacao();
        relacao.setDescricao(descricao);
        relacao.setConteudos(conteudosRelacao);
        cruc.alterarRelacao(relacao);
        getBeanRoteiro().atualizaListaDeConteudos();
    }

    public void excluirRelacao() throws Exception {
        Relacao relacao = getBeanRoteiro().getRelacao();
        List<Conteudo> cs = getBeanRoteiro().getConteudosMapa();
        boolean achou = false;
        for (Conteudo c : cs) {
            for (Relacao r : c.getRelacoes()) {
                if (r.getId() == relacao.getId()) {
                    for (Conteudo cx : r.getConteudos()) {
                        cx.getRelacoes().remove(r);
                        cruc.alterarConteudo(cx);
                    }
                    achou = true;
                    cruc.excluirRelacao(r);
                    break;
                }
            }
            if (achou) {
                break;
            }
        }
    }

    public void excluirConteudoSemRelacao(long idConteudo) throws Exception {
        Conteudo cont = cruc.buscaConteudoPorChave(idConteudo);
        cont.setMapa(false);
        cruc.alterarConteudo(cont);
        getBeanRoteiro().refresh();
    }

    public void excluirConteudoComRelacao(long idConteudo) throws Exception {
        Conteudo cont = cruc.buscaConteudoPorChave(idConteudo);
        List<Relacao> relacoesExcluir = new ArrayList<Relacao>();
        relacoesExcluir.addAll(cont.getRelacoes());
        cont.setMapa(false);
        cruc.alterarConteudo(cont);
        for (Relacao rel : cont.getRelacoes()) {
            for (Conteudo cx : rel.getConteudos()) {
                if (cx.getRelacoes().contains(rel)) {
                    cx.getRelacoes().remove(rel);
                    rel.getConteudos().remove(cx);
                    cruc.alterarConteudo(cx);
                }
            }
        }
        for (Relacao rel : relacoesExcluir) {
            cruc.excluirRelacao(rel);
        }
        getBeanRoteiro().refresh();
    }   
}
