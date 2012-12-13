package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "relacoes")
public class Relacao implements Serializable, TemGetId {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_relacao")
    private long id;
    @Column(name = "ds_descricao")
    private String descricao;
    
    @ManyToMany
    @JoinTable(name="arestas",
    joinColumns={@JoinColumn(name="cd_relacao")},
    inverseJoinColumns={
    @JoinColumn(name="cd_conteudo")})            
    private List<Conteudo> conteudos = new ArrayList<Conteudo>();

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

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }        

    @Override
    public String toString() {
        return descricao;
    }                
    
}