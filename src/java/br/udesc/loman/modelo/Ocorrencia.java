package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
        name="ocorrencia.tarefas",
        query="SELECT o From Ocorrencia AS o "
        + "JOIN o.tarefa AS t "
        + "WHERE o.tarefa = :tarefa"),
    
        @NamedQuery(
        name="ocorrenciataerfa.materiais",
        query="SELECT o From Ocorrencia AS o "
        + "JOIN o.tarefa AS t "
        + "WHERE o.tarefa = :tarefa "
        + "AND o.material IS NOT NULL"),
        
        @NamedQuery(
        name="ocorrenciaunidade.materiais",
        query="SELECT o From Ocorrencia AS o "
        + "JOIN o.unidade AS t "
        + "WHERE o.unidade = :unidade "
        + "AND o.material IS NOT NULL"),
        
        @NamedQuery(
        name="ocorrencia.unidade",
        query="SELECT o From Ocorrencia AS o "
        + "WHERE o.unidade = :unidade ")
})

@Entity
@Table(name="ocorrencias")
public class Ocorrencia implements Serializable, TemGetId {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_ocorrencia")
    private long id;       
    
    @Column(name="ds_descricao")
    private String descricao;
    
    @Column(name="ds_observacao")
    private String observacao;
    
    @Temporal(TemporalType.DATE)
    @Column(name="dt_modificacao")
    private Date dataModificacao;
    
    @ManyToOne()
    @JoinColumn(name="cd_tarefa")
    private Tarefa tarefa;
    
    @ManyToOne()
    @JoinColumn(name="cd_unidade")
    private Unidade unidade;
    
    @ManyToOne()
    @JoinColumn(name="cd_usuario")
    private Usuario usuario;  
    
    @ManyToOne()
    @JoinColumn(name="cd_material")
    private Material material;
    
    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }       

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }           

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }        

    public Ocorrencia() {              
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }        
}