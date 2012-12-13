package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="slides")
public class Slide implements Serializable, TemGetId{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_slide")
    private long id;
    
    @Column(name="ds_titulo", length=50)
    private String titulo;
    
    @Column(name="ds_descricao", length=400)
    private String descricao;
    
    @ManyToOne()
    @JoinColumn(name = "cd_roteiro")
    private Roteiro roteiro;
    
    @OneToMany(mappedBy = "slide", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();
    
    public Roteiro getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
