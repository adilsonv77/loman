package br.udesc.loman.controle;

import br.udesc.controle.CadastroUC;
import br.udesc.controle.UdescException;
import br.udesc.loman.dao.core.LoManDAOFactory;
import br.udesc.loman.dao.core.TarefaDAO;
import br.udesc.loman.modelo.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class CadastroTarefasUC implements CadastroUC<Tarefa, String> {

    private LoManDAOFactory daoFactory;

    public CadastroTarefasUC(LoManDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Tarefa novo() throws Exception {
        Tarefa t = new Tarefa();
        return t;
    }

    @Override
    public List<UdescException> salvar(Tarefa tar) throws Exception {
        TarefaDAO dao = daoFactory.getTarefaDAO(); 
        List<UdescException> excs = new ArrayList<UdescException>();                

        if (excs.isEmpty()) {
            if (tar.getId() == 0) {
                dao.incluir(tar);
                System.out.println(tar.getId() + " -> incluir tarefa");
            } else {
                dao.alterar(tar);
                System.out.println(tar.getId() + " -> alterar tarefa");
            }
        }
        return excs;
    }

    @Override
    public List<Tarefa> listar() throws Exception {
        System.out.println("listar");
        return daoFactory.getTarefaDAO().listarTodos();
    }

    @Override
    public Tarefa carregar(long l) throws Exception {
        return daoFactory.getTarefaDAO().ler(l);
    }

    @Override
    public void excluir(long l) throws Exception {
        daoFactory.getTarefaDAO().excluir(daoFactory.getTarefaDAO().ler(l));
    }

    @Override
    public List<Tarefa> listarPesquisa(String t2) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}