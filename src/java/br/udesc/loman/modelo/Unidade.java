package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
        name="Unidade.porNome",
        query="SELECT p FROM Unidade as p where upper(p.nome) like upper(:nome)"),
    
    @NamedQuery(
        name="Unidade.igualNome",
        query="SELECT p FROM Unidade as p where upper(p.nome) = upper(:nome)"),
    
    @NamedQuery(
        name="unidades.coordenador",
        query="SELECT DISTINCT u "
        + "FROM Projeto AS p "
        + "JOIN p.unidades AS u "
        + "WHERE p.coordenador = (:usuario) "
        + "ORDER BY p.titulo"),
    
    @NamedQuery(
        name="unidades.prontaspararevisao",
        query="SELECT DISTINCT u "
        + "FROM Projeto AS p "
        + "JOIN p.unidades AS u "        
        + "WHERE br.udesc.loman.modelo.Status.CONCLUIDA = ALL (SELECT t.status FROM u.roteiro.tarefas AS t) "
        + "AND p.coordenador = (:usuario) "
        + "AND u.roteiro.tarefas IS NOT EMPTY "
        + "ORDER BY p.titulo"),
    
    @NamedQuery(
        name="unidades.concluidas",
        query="SELECT DISTINCT u "
        + "FROM Projeto AS p "
        + "JOIN p.unidades AS u "
        + "JOIN p.equipe AS e "                
        + "WHERE (e.usuario = (:usuario) "
        + "OR p.coordenador = (:usuario)) "
        + "AND br.udesc.loman.modelo.Status.CONCLUIDA = ALL (SELECT t.status FROM u.roteiro.tarefas AS t) "
        + "AND true = ALL (Select o.status FROM u.objetivos AS o) "
        + "AND u.objetivos IS NOT EMPTY "
        + "AND u.roteiro.tarefas IS NOT EMPTY "
        + "ORDER BY p.titulo"),
    
    @NamedQuery(
        name="unidades.comproblemas",
        query="SELECT DISTINCT u "
        + "FROM Projeto AS p "
        + "JOIN p.unidades AS u "
        + "JOIN u.roteiro.tarefas AS t "        
        + "WHERE br.udesc.loman.modelo.Status.COM_PROBLEMAS = t.status "        
        + "AND p.coordenador = (:usuario) "
        + "AND u.roteiro.tarefas IS NOT EMPTY "
        + "ORDER BY p.titulo"),
    
    @NamedQuery(
        name="unidades.emandamento",
        query="SELECT DISTINCT u "
        + "FROM Projeto AS p "
        + "JOIN p.unidades AS u "
        + "JOIN u.roteiro.tarefas AS t "        
        + "WHERE br.udesc.loman.modelo.Status.EM_ANDAMENTO = t.status "        
        + "AND p.coordenador = (:usuario) "
        + "AND u.roteiro.tarefas IS NOT EMPTY "
        + "ORDER BY p.titulo")
        
})


@Entity
@Table(name = "unidades")
public class Unidade implements Serializable, TemGetId {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_unidade")
    private long id = 0;
    
    @Column(name = "ds_nome", length = 50)
    private String nome = "";
    
    @Column(name = "ds_descricao", length = 200)
    private String descricao = "";
    
    @Column(name = "nr_duracao")
    private int duracao;        
    
    @ManyToOne()
    @JoinColumn(name = "cd_projeto")
    private Projeto projeto;    
    
    @OneToOne(mappedBy = "unidade", cascade = CascadeType.ALL)        
    private Roteiro roteiro = new Roteiro();
    
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
    
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Material> materiais = new ArrayList<Material>();
    
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Objetivo> objetivos = new ArrayList<Objetivo>();
    
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Conteudo> conteudos = new ArrayList<Conteudo>();
    
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<Atividade> atividades = new ArrayList<Atividade>();
    
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<FormaAvaliacao> formasAvaliacao = new ArrayList<FormaAvaliacao>();

    public Unidade() {
        super();
        this.roteiro = new Roteiro();
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }        
    
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }        

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<FormaAvaliacao> getFormasAvaliacao() {
        return formasAvaliacao;
    }

    public void setFormasAvaliacao(List<FormaAvaliacao> formasAvaliacao) {
        this.formasAvaliacao = formasAvaliacao;
    }
         
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }
        
}
