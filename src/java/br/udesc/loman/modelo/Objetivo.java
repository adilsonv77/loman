package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import javax.persistence.*;

@NamedQueries({
    
    @NamedQuery(
        name="objetivo.concluido",
        query="SELECT o FROM Objetivo AS o WHERE o.status = true AND o.unidade = :unidade")
})

@Entity
@Table(name="objetivos")
public class Objetivo implements Serializable, TemGetId{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cd_objetivo")
    private long id;
    
    @Column(name="ds_descricao", length=400)
    private String descricao;
    
    @Column(name = "nr_status")
    private boolean status = false;
    
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return descricao;
    }        
        
}
