package br.udesc.loman.controle;

import br.udesc.controle.CadastroUC;
import br.udesc.controle.UdescException;
import br.udesc.loman.dao.core.LoManDAOFactory;
import br.udesc.loman.dao.core.UsuarioDAO;
import br.udesc.loman.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;


public class CadastroUsuariosUC implements CadastroUC<Usuario, String> {

    private LoManDAOFactory daoFactory;
    private AutenticacaoControle autenticacao;
    private String senha2;

    public CadastroUsuariosUC(LoManDAOFactory daoFactory,
            AutenticacaoControle autenticacao) {
        this.daoFactory = daoFactory;
        this.autenticacao = autenticacao;
    }

    @Override
    public List<Usuario> listar() throws Exception {
        return daoFactory.getUsuarioDAO().listarTodos();
    }

    @Override
    public Usuario novo() throws Exception {
        return new Usuario();
    }

    @Override
    public Usuario carregar(long id) throws Exception {
        UsuarioDAO usuarioDAO = daoFactory.getUsuarioDAO();
        Usuario usuario = usuarioDAO.ler(id);
        return usuario;
    }

    @Override
    public List<UdescException> salvar(Usuario usuario) throws Exception {
        UsuarioDAO usuarioDAO = daoFactory.getUsuarioDAO();

        List<UdescException> excs = new ArrayList<UdescException>();
        
        if (usuario.getNome().trim().equals("")) {
            excs.add(new UdescException("nome", "userNameRequiredErrorMessage"));
        }
        if (usuario.getNick().trim().equals("")) {
            excs.add(new UdescException("nick", "userNickRequiredErrorMessage"));
        }
        
        if (usuario.getSenha().trim().equals("")) {
            excs.add(new UdescException("senha", "userPasswordRequiredErrorMessage"));
        } 
        
        if (usuario.getEmail().trim().equals("")) {
            excs.add(new UdescException("email", "emailRequiredErrorMessage"));
        } 
        
        Usuario u1 = carregarPorNick(usuario.getNick());
        if (u1 != null && usuario.getId() != u1.getId()) {
             excs.add(new UdescException("nick","userAlreadyRegisteredErrorMessage"));
        } 
        if (usuario.getNick().length() > 10) {
             excs.add(new UdescException("nick","userNickError"));
        } 
        if (usuario.getSenha().length() > 6) {
             excs.add(new UdescException("senha","userPasswordError"));
        } 

//        if (!usuario.getSenha().equals(senha2)) {
//            excs.add(new UdescException("senha","diferentPasswordsError"));
//        }
        
        if (excs.isEmpty())
            if (usuario.getId() == 0) {
                usuarioDAO.incluir(usuario);
                System.out.println("incluir usuario");
            } else {
                usuarioDAO.alterar(usuario);
                System.out.println("alterar usuario");
            }
        
        return excs;
    }

    @Override
    public void excluir(long id) throws Exception {
        UsuarioDAO usuarioDAO = daoFactory.getUsuarioDAO();
        Usuario usuario = usuarioDAO.ler(id);

        if (usuario == autenticacao.getUsuarioSessao()) {
            throw new Exception("userInSession");
        }

        usuarioDAO.excluir(usuario);

    }

    public Usuario carregarPorNick(String nick) throws Exception {
        UsuarioDAO usuarioDAO = daoFactory.getUsuarioDAO();
        return usuarioDAO.buscarPeloNick(nick);
    }
    @Override
    public List<Usuario> listarPesquisa(String chave) throws Exception {
        List<Usuario> lista = daoFactory.getUsuarioDAO().listarPorNome(chave);
        return lista;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

}
