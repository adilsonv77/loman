package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = "conteudo.id",
    query = "SELECT c FROM Conteudo AS c WHERE c.id = :id"),
    @NamedQuery(name = "conteudo.unidade",
    query = "SELECT c FROM Conteudo AS c WHERE c.unidade = :unidade"),
    @NamedQuery(name = "conteudo.mapa",
    query = "SELECT c FROM Conteudo AS c WHERE c.unidade = :unidade AND c.mapa = true"),
    @NamedQuery(name = "conteudo.disponivel",
    query = "SELECT c FROM Conteudo AS c WHERE c.unidade = :unidade AND c.mapa = false"),
    @NamedQuery(name = "conteudo.relacao",
    query = "SELECT c FROM Conteudo AS c WHERE c.relacoes = :relacao")
})
@Entity
@Table(name = "conteudos")
public class Conteudo implements Serializable, TemGetId {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_conteudo")
    private long id;
    @Column(name = "ds_descricao", length = 400)
    private String descricao;
    @Column(name = "nr_mapa")
    private boolean mapa;
    @ManyToOne()
    @JoinColumn(name = "cd_unidade")
    private Unidade unidade;
    @ManyToMany(mappedBy = "conteudos")
    private List<Relacao> relacoes = new ArrayList<Relacao>();

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

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public boolean isMapa() {
        return mapa;
    }

    public void setMapa(boolean mapa) {
        this.mapa = mapa;
    }

    public List<Relacao> getRelacoes() {
        return relacoes;
    }

    public void setRelacoes(List<Relacao> relacoes) {
        this.relacoes = relacoes;
    }
}
