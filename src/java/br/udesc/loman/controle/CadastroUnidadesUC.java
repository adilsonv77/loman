package br.udesc.loman.controle;

import br.udesc.controle.CadastroUC;
import br.udesc.controle.UdescException;
import br.udesc.loman.dao.core.LoManDAOFactory;
import br.udesc.loman.dao.core.UnidadeDAO;
import br.udesc.loman.modelo.Unidade;
import java.util.ArrayList;
import java.util.List;

public class CadastroUnidadesUC implements CadastroUC<Unidade, String> {

    private LoManDAOFactory daoFactory;

    public CadastroUnidadesUC(LoManDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public Unidade novo() throws Exception {
        Unidade m = new Unidade();
        return m;
    }

    @Override
    public List<UdescException> salvar(Unidade matrizdi) throws Exception {
        UnidadeDAO dao = daoFactory.getUnidadeDAO();
        List<UdescException> excs = new ArrayList<UdescException>();

        if (matrizdi.getNome().trim().equals("")) {
            excs.add(new UdescException("nome", "nameRequiredErrorMessage"));
        }

        Unidade m2 = dao.buscarPeloNome(matrizdi.getNome());
        if (m2 != null && m2.getId() != matrizdi.getId()) {
            excs.add(new UdescException("nome", "nameAlreadyRegisteredErrorMessage"));
        }

        if (matrizdi.getDescricao().trim().equals("")) {
            excs.add(new UdescException("descricao", "descriptionRequiredErrorMessage"));
        }
        
        if (excs.isEmpty()) {
            if (matrizdi.getId() == 0) {
                dao.incluir(matrizdi);
            } else {
                dao.alterar(matrizdi);
            }
        }
        return excs;
    }

    @Override
    public List<Unidade> listar() throws Exception {
        return daoFactory.getUnidadeDAO().listarTodos();
    }

    @Override
    public Unidade carregar(long id) throws Exception {
        return daoFactory.getUnidadeDAO().ler(id);
    }

    @Override
    public void excluir(long id) throws Exception {
        daoFactory.getUnidadeDAO().excluir(daoFactory.getUnidadeDAO().ler(id));
    }

    @Override
    public List<Unidade> listarPesquisa(String chave) throws Exception {
        return daoFactory.getUnidadeDAO().listarPorNome(chave);
    }
}