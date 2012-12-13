package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="formasAvaliacao")
public class FormaAvaliacao implements Serializable, TemGetId{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_forma_avaliacao")
    private long id;
    
    @Column(name="ds_descricao", length=400)
    private String descricao;        
    
    @ManyToOne()
    @JoinColumn(name="cd_unidade")
    private Unidade unidade;
   
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
        
}
