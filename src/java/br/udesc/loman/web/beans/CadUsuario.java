package br.udesc.loman.web.beans;

import br.udesc.loman.controle.CadastroUsuariosUC;
import br.udesc.loman.modelo.Projeto;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.modelo.Usuario;
import br.udesc.loman.web.AutenticacaoUtil;
import br.udesc.loman.web.LoManListener;
import br.udesc.web.CRUD;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CadUsuario extends CRUD<Usuario, String> {

    private final CadastroUsuariosUC cuuc;

    public CadUsuario() {
        super(new CadastroUsuariosUC(LoManListener.getDAOFactory(), AutenticacaoUtil.getInstance()),
                "usuario", LoManListener.getDAOFactory(), new String[]{"nome", "nick"});
        this.cuuc = (CadastroUsuariosUC) cuc;
        try {
            super.novo();
        } catch (Exception ex) {
            Logger.getLogger(CadUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verificarUsuarioAdministrador() throws Exception {
        List<Usuario> usuarios = LoManListener.getDAOFactory().getUsuarioDAO().listarTodos();
        for (Usuario u : usuarios) {
            if (u == getUsuarioSessao()) {
                if (u.isAdministrador() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarUsuarioCoordenador() throws Exception {
        List<Projeto> projetos = LoManListener.getDAOFactory().getProjetoDAO().listarTodos();
        for (Projeto p : projetos) {
            if (p.getCoordenador() == getUsuarioSessao()) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUsuarioProfessorConteudista() throws Exception {
        List<Projeto> projetos = LoManListener.getDAOFactory().getProjetoDAO().buscarProjetosProfessorConteudista(AutenticacaoUtil.getInstance().getUsuarioSessao());
        if (projetos.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificarUsuarioCoordenadorOuProfessorConteudista() throws Exception {
        List<Projeto> projetos = LoManListener.getDAOFactory().getProjetoDAO().buscarProjetosCoordenadorProfessorConteudista(AutenticacaoUtil.getInstance().getUsuarioSessao());
        if (projetos.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificarUsuarioAdministradorOuCoordenadorOuProfessorConteudista() throws Exception {
        if (verificarUsuarioCoordenadorOuProfessorConteudista() || verificarUsuarioAdministrador()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarUsuarioProgramadorOuDesigner() throws Exception {
        List<Tarefa> tarefas = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasProgramadorOuDesigner(AutenticacaoUtil.getInstance().getUsuarioSessao());
        if (tarefas.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean verificarUsuarioProgramadorOuDesignerOuCoordenador() throws Exception {
        List<Tarefa> tarefas = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasProgramadorOuDesignerOuCoordenador(AutenticacaoUtil.getInstance().getUsuarioSessao());
        if (tarefas.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Usuario getUsuarioSessao() {
        return AutenticacaoUtil.getInstance().getUsuarioSessao();
    }

    public String getSenha2() {
        return cuuc.getSenha2();
    }

    public void setSenha2(String senha2) {
        this.cuuc.setSenha2(senha2);
    }

    @Override
    public void novo() throws Exception {
        super.novo();
        setSenha2("");
    }

    public void novoUsuario() throws Exception {
        novo();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/usuarioscad.jsf");        
    }

    @Override
    protected String getDadoCampoPesquisa() {
        return this.getRegistro().getNome();
    }

    @Override
    protected void setDadoCampoPesquisa(String valor) {
        this.getRegistro().setNome(valor);
    }

    public String login() throws Exception {
        Usuario usuario = LoManListener.getDAOFactory().getUsuarioDAO().buscarLogin(getRegistro().getNick(), getRegistro().getSenha());
        if (usuario != null) {
            AutenticacaoUtil.getInstance().salvarSessao(usuario);
            return "index";            
        }
        tratarMessage(FacesMessage.SEVERITY_ERROR, "panelLoginUsuario", "erroLogin");
        return "login";
    }

    public void logout() throws IOException {
        AutenticacaoUtil.getInstance().destroiHttpSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/login.jsf");        
    }
}
