package br.udesc.loman.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "membrosequipe")
public class MembroEquipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cd_papel_enum")
    private PapelEnum papelEnum;
    @Id
    @ManyToOne()
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;
    @Id
    @ManyToOne()
    @JoinColumn(name = "cd_projeto")
    private Projeto projeto;

    public MembroEquipe() {
    }

    public MembroEquipe(Usuario usuario, PapelEnum papelEnum, Projeto projeto) {
        this.usuario = usuario;
        this.papelEnum = papelEnum;
        this.projeto = projeto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public PapelEnum getPapelEnum() {
        return papelEnum;
    }

    public void setPapelEnum(PapelEnum papelEnum) {
        this.papelEnum = papelEnum;
    }        
}
