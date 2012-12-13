
import br.udesc.dao.jpa.JPAUtil;
import br.udesc.loman.dao.jpa.JPADAOFactory;
import br.udesc.loman.modelo.Projeto;
import br.udesc.loman.modelo.Unidade;
import br.udesc.loman.modelo.Usuario;

public class TesteProjetoUnidade {
    
    public static void main(String[] args) throws Exception {
        JPAUtil.inicializar("loman");
        JPADAOFactory daoFac = new JPADAOFactory();
        
        Usuario u1 = daoFac.getUsuarioDAO().ler(1);
        
        Projeto projeto1 = new Projeto();
        projeto1.setTitulo("Projeto de Iniciativa Científica");
        projeto1.setDataInicio(new java.util.Date());
        projeto1.setDescricao("Descrição do projeto de teste - Iniciativa Científica");
        projeto1.setCoordenador(u1);
        
        Unidade unid1 = new Unidade();
        unid1.setNome("Unid1");
        unid1.setProjeto(projeto1);
        
        projeto1.getUnidades().add(unid1);
        
        daoFac.getProjetoDAO().incluir(projeto1);
    }
    
}
