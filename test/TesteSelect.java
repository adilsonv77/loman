
import br.udesc.loman.modelo.Material;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.modelo.Unidade;
import java.util.ArrayList;
import java.util.List;
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
 * @author udesc
 */
public class TesteSelect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loman");
        EntityManager em = emf.createEntityManager();
        
//        String sql = "SELECT DISTINCT u FROM Unidade AS u JOIN u.roteiro.tarefas AS t WHERE br.udesc.loman.modelo.Status.CONCLUIDA = ALL t.status";
        String sql = "SELECT DISTINCT u "
        + "FROM Projeto AS p "
        + "JOIN p.unidades AS u "        
        + "WHERE "
        + "br.udesc.loman.modelo.Status.CONCLUIDA = ALL (SELECT t.status FROM u.roteiro.tarefas AS t) "
                + "AND u.roteiro.tarefas IS NOT EMPTY "
        + "ORDER BY p.titulo";
        
        List<Unidade> unidades;
        Query q2 = em.createQuery(sql);
        unidades = q2.getResultList();
        
        System.out.println("Quanitade de Unidades: " + unidades.size()); 
        

        Tarefa t = em.find(Tarefa.class, 4l);
        String q1 = "SELECT DISTINCT m FROM Tarefa AS t JOIN t.materiais AS m JOIN t.ocorrencias AS o"
                + " WHERE t = (:tarefa)";

        Query q = em.createQuery(q1);
        q.setParameter("tarefa", t);
        q.setParameter("material", "Upload de Material");
        List<Material> lista = new ArrayList<Material>();
        lista = q.getResultList();
        System.out.println(lista.size());
    }
}
