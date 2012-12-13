package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
        name="tarefa.porTitulo",
        query="SELECT t FROM Tarefa AS t WHERE UPPER(t.titulo) LIKE UPPER(:titulo)"),
    
    @NamedQuery(
        name="tarefa.igualTitulo",
        query="SELECT t FROM Tarefa AS t WHERE UPPER(t.titulo) = UPPER(:titulo)"),
    
    @NamedQuery(
        name="tarefa.assumidas",
        query="SELECT DISTINCT t "
        + "FROM Tarefa AS t "
        + "JOIN t.usuario AS usuario "
        + "JOIN t.status AS s "
        + "WHERE t.usuario = :usuario "
        + "AND t.status = br.udesc.loman.modelo.Status.EM_ANDAMENTO "
        + "ORDER BY t.dataEntrega"),
    
    @NamedQuery(
        name="tarefa.concluidas",
        query="SELECT DISTINCT t "
        + "FROM Tarefa AS t "
        + "JOIN t.usuario AS usuario "
        + "JOIN t.status AS s "
        + "WHERE t.usuario = :usuario "
        + "AND t.status = br.udesc.loman.modelo.Status.CONCLUIDA "
        + "ORDER BY t.dataEntrega"),
    
    @NamedQuery(
        name="tarefa.disponiveis",
        query="SELECT DISTINCT t "
        + "FROM Projeto AS p "
        + "JOIN p.equipe AS e "
        + "JOIN p.unidades AS u "
        + "JOIN u.roteiro.tarefas AS t "
        + "WHERE (t.status = br.udesc.loman.modelo.Status.ABERTA "
        + "OR t.status = br.udesc.loman.modelo.Status.COM_PROBLEMAS) "
        + "AND e.papelEnum = t.papelEnum "
        + "AND e.usuario = (:usuario) "
        + "ORDER BY t.dataEntrega"),
    
    @NamedQuery(
        name="tarefa.prontaspararevisao",
        query="SELECT DISTINCT t "
        + "FROM Projeto AS p "
        + "JOIN p.equipe AS e "
        + "JOIN p.unidades AS u "
        + "JOIN u.roteiro.tarefas AS t "
        + "WHERE t.status = br.udesc.loman.modelo.Status.PRONTA_PARA_REVISAO "
        + "AND p.coordenador = (:usuario) "
        + "ORDER BY t.dataEntrega"),
    
    @NamedQuery(
        name="tarefa.slide",
        query="SELECT DISTINCT t "
        + "FROM Tarefa AS t "
        + "WHERE t.slide = :slide "
        + "ORDER BY t.titulo"),
    
    @NamedQuery(name="tarefa.programadordesigner",
        query="SELECT DISTINCT t "
        + "FROM Projeto AS p "
        + "JOIN p.equipe AS e "
        + "JOIN p.unidades AS u "
        + "JOIN u.roteiro.tarefas AS t "
        + "WHERE (e.usuario = (:usuario) "
        + "AND ((e.papelEnum = br.udesc.loman.modelo.PapelEnum.DESIGNER) "
        + "OR (e.papelEnum = br.udesc.loman.modelo.PapelEnum.PROGRAMADOR))) "),
    
    @NamedQuery(name="tarefa.programadordesignercoordenador",
        query="SELECT DISTINCT t "
        + "FROM Projeto AS p "
        + "JOIN p.equipe AS e "
        + "JOIN p.unidades AS u "
        + "JOIN u.roteiro.tarefas AS t "
        + "WHERE (e.usuario = (:usuario) "
        + "AND ((e.papelEnum = br.udesc.loman.modelo.PapelEnum.DESIGNER) "
        + "OR (e.papelEnum = br.udesc.loman.modelo.PapelEnum.PROGRAMADOR))) "
        + "OR p.coordenador = (:usuario)")
})

@Entity
@Table(name="tarefas")
public class Tarefa implements Serializable, TemGetId {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_tarefa")
    private long id;
    
    @Column(name="ds_titulo")
    private String titulo;
    
    @Column(name="ds_descricao")
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    @Column(name="dt_entrega")
    private Date dataEntrega;
    
    @ManyToOne()
    @JoinColumn(name="cd_usuario", nullable=true)
    private Usuario usuario;        
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="nr_papel")
    private PapelEnum papelEnum; 
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name="nr_status", nullable = false)
    private Status status = Status.ABERTA;    
    
    @ManyToOne()
    @JoinColumn(name="cd_roteiro")
    private Roteiro roteiro;
    
    @ManyToOne()
    @JoinColumn(name="cd_slide")
    private Slide slide;
    
    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
    
    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL)
    private List<Material> materiais = new ArrayList<Material>();        

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
    
    public Tarefa() {              
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   

    public PapelEnum getPapelEnum() {
        return papelEnum;
    }

    public void setPapelEnum(PapelEnum papelEnum) {
        this.papelEnum = papelEnum;
    }   

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }        
    
    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date data_entrega) {
        this.dataEntrega = data_entrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }        

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }   
    
}