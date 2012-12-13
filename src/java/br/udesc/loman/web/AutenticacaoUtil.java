package br.udesc.loman.web;

import br.udesc.loman.controle.AutenticacaoControle;
import br.udesc.loman.dao.core.UsuarioDAO;
import br.udesc.loman.modelo.Usuario;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AutenticacaoUtil implements AutenticacaoControle {

    private static AutenticacaoUtil instance;

    public static AutenticacaoUtil getInstance() {

        if (instance == null) {
            instance = new AutenticacaoUtil();
        }
        return instance;
    }

    private AutenticacaoUtil() {
    }

    public void destroiHttpSession(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
    }

    @Override
    public void salvarSessao(Usuario usuario)
            throws Exception {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("usuario", usuario);

        UsuarioDAO uDAO = LoManListener.getDAOFactory().getUsuarioDAO();
        String ip = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
//        uDAO.registrarLogin(usuario, new Date(), ip);
    }

    @Override
    public Usuario getUsuarioSessao() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        return (usuario);
    }

}
