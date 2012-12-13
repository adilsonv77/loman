package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="materiais")
public class Material implements Serializable, TemGetId {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_material")
    private long id;        
    
    @Column(name="ds_observacao")
    private String observacao;    
    
    @Column(name="ds_caminho")
    private String caminho;
    
    @ManyToOne()
    @JoinColumn(name = "cd_tarefa")
    private Tarefa tarefa;
    
    @ManyToOne()
    @JoinColumn(name = "cd_unidade")
    private Unidade unidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
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
        
}