 package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="roteiros")
public class Roteiro implements Serializable, TemGetId {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_roteiro")
    private long id;
   
    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL)        
    private List<Slide> slides = new ArrayList<Slide>();
        
    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL)        
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();        
    
    @OneToOne()
    @JoinColumn(name = "cd_unidade")
    private Unidade unidade;

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }        

    public Roteiro() {
        super();
        this.slides = new ArrayList<Slide>();
        this.tarefas = new ArrayList<Tarefa>();
    }        

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }        
    
}