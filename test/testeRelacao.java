
import br.udesc.loman.modelo.Relacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Pedro Heleno Isolani
 */
public class testeRelacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Relacao relacao = new Relacao();
        relacao.setDescricao("Relação 1");
        System.out.println(relacao.toString());
//        LoManListener.getDAOFactory().getRelacaoDAO().alterar(relacao);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loman");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(relacao);
        em.getTransaction().commit();
        System.out.println(relacao.toString());
//        em.refresh(relacao);
        String sql = "SELECT r FROM Relacao As r WHERE r.id = (SELECT Max(rr.id) FROM Relacao AS rr)";
        Query q = em.createQuery(sql);
        Relacao relacao2 = (Relacao) q.getSingleResult();
        System.out.println(relacao2.toString());
//        relacao = JPAUtil.getInstance().getGerenciadorEntidade().getReference(Relacao.class, relacao);
        System.out.println(relacao.toString());
    }
}
