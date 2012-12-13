package br.udesc.loman.controle;

import br.udesc.controle.CadastroUC;
import br.udesc.controle.UdescException;
import br.udesc.loman.dao.core.LoManDAOFactory;
import br.udesc.loman.dao.core.ProjetoDAO;
import br.udesc.loman.modelo.*;
import br.udesc.loman.web.AutenticacaoUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadastroProjetosUC implements CadastroUC<Projeto, String> {

    private LoManDAOFactory daoFactory;

    public CadastroProjetosUC(LoManDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public Projeto novo() throws Exception {
        Projeto p = new Projeto();
        p.setDataInicio(new Date());
        return p;
                
    }

    @Override
    public List<UdescException> salvar(Projeto proj) throws Exception {
        ProjetoDAO dao = daoFactory.getProjetoDAO();
        List<UdescException> excs = new ArrayList<UdescException>();
        
        if (proj.getTitulo().trim().equals("")) {
            excs.add(new UdescException("titulo", "titleRequiredErrorMessage"));
        }
        
        Projeto p2 = dao.buscarPeloTitulo(proj.getTitulo());
        if (p2 != null && p2.getId() != proj.getId()) {
            excs.add(new UdescException("titulo", "titleAlreadyRegisteredErrorMessage"));
        }
        
        if (proj.getDescricao().trim().equals("")) {
            excs.add(new UdescException("descricao", "descriptionRequiredErrorMessage"));
        }
        
        if (proj.getCoordenador() == null) {
            excs.add(new UdescException("coordenador", "coordinatorRequiredErrorMessage"));
        } else {
            List<MembroEquipe> eq = proj.getEquipe();
            for (MembroEquipe me:eq) {
                if (me.getUsuario().getId() == proj.getCoordenador().getId()) {
                    excs.add(new UdescException("coordenador", "coordinatorAlreadyAtTeamErrorMessage"));
                    break;
                }
            }
        }
        
        if (proj.getEquipe().isEmpty()) {
            excs.add(new UdescException("equipe", "needsSomeMemberRequiredErrorMessage"));            
        }
        
         if (excs.isEmpty())
            if (proj.getId() == 0) {
                dao.incluir(proj);
                System.out.println("incluir projeto");
            } else {
                dao.alterar(proj);
                System.out.println("alterar projeto");
            }
        
        return excs;
    }

    @Override
    public List<Projeto> listar() throws Exception {
        System.out.println("listar");
        return daoFactory.getProjetoDAO().buscarProjetosCoordenador(AutenticacaoUtil.getInstance().getUsuarioSessao());
    }

    @Override
    public Projeto carregar(long id) throws Exception {
        return daoFactory.getProjetoDAO().ler(id);
    }

    @Override
    public void excluir(long id) throws Exception {
        daoFactory.getProjetoDAO().excluir(daoFactory.getProjetoDAO().ler(id));
    }

    @Override
    public List<Projeto> listarPesquisa(String chave) throws Exception {
        System.out.println("listarPesquisa " + chave);
        return daoFactory.getProjetoDAO().listarPorTitulo(chave, AutenticacaoUtil.getInstance().getUsuarioSessao());
    }

    public List<Usuario> listarPossiveisMembros(Projeto p, String query) throws Exception  {
        
        List<Usuario> usuarios = daoFactory.getUsuarioDAO().listarPorNome(query);
        
        List<MembroEquipe> equipe = p.getEquipe();
        
        int x = 0;
        while (x < usuarios.size()) {
            Usuario usuario = usuarios.get(x);
            if (p.getCoordenador() == null || 
                    (p.getCoordenador() != null && p.getCoordenador().getId() != usuario.getId())) {
                
                boolean achou = false;
                for (MembroEquipe me:equipe)
                    if (me.getUsuario().getId() == usuario.getId()) {
                        usuarios.remove(x);
                        achou = true;
                        break;
                    }
                
                if (!achou)
                    x++;
                
            } else {
                usuarios.remove(x);
            }
        }
        
        return usuarios;
    }

    public void addMembro(Projeto projeto, Usuario usuario, PapelEnum papelEnum) throws UdescException {
        if (usuario == null)
            throw new UdescException("equipe", "needsAnUserToMemberErrorMessage");
        
        List<MembroEquipe> equipe = projeto.getEquipe();
        
        for (MembroEquipe me:equipe) {
            if (me.getUsuario().getId() == usuario.getId()) {
                throw new UdescException("equipe", "thisMemberAlreadyExistsErrorMessage");
            }
        }
        
        projeto.addMembro(usuario, papelEnum);
    }
    
}
