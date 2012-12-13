package br.udesc.loman.modelo;

import br.udesc.modelo.TemGetId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery(
        name="projeto.porTitulo",
        query="SELECT p FROM Projeto as p where upper(p.titulo) like upper(:titulo) "
        + "AND p.coordenador = (:usuario)"),
    
    @NamedQuery(
        name="projeto.igualTitulo",
        query="SELECT p FROM Projeto as p where upper(p.titulo) = upper(:titulo)"),
    
    @NamedQuery(
        name="projeto.coordenadorprofessorconteudista",
        query="SELECT DISTINCT p FROM Projeto AS p "
        + "JOIN p.equipe AS e "
        + "WHERE (e.usuario = (:usuario) AND e.papelEnum = br.udesc.loman.modelo.PapelEnum.PROFESSOR_CONTEUDISTA) "
        + "OR p.coordenador = (:usuario)"),
    
    @NamedQuery(
        name="projeto.professorconteudista",
        query="SELECT DISTINCT p FROM Projeto AS p "
        + "JOIN p.equipe AS e "
        + "WHERE (e.usuario = (:usuario) AND e.papelEnum = br.udesc.loman.modelo.PapelEnum.PROFESSOR_CONTEUDISTA)"),                
    
    @NamedQuery(
        name="projeto.coordenador",
        query="SELECT DISTINCT p FROM Projeto AS p "
        + "WHERE p.coordenador = (:usuario) "
        + "ORDER BY p.titulo")
})


@Entity
@Table(name = "projetos")
public class Projeto implements Serializable, TemGetId {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_projeto")
    private long id;
    
    @Column(name = "ds_titulo")
    private String titulo;
    
    @Column(name = "ds_descricao")
    private String descricao;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "dt_inicio")
    private Date dataInicio;
    
    @ManyToOne()
    @JoinColumn(name = "cd_coordenador")
    private Usuario coordenador;
    
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<MembroEquipe> equipe = new ArrayList<MembroEquipe>();
    
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Unidade> unidades = new ArrayList<Unidade>();

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }
    
    public Usuario getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Usuario coordenador) {
        this.coordenador = coordenador;
    }

    public Projeto() {
    }

    public Projeto(Long id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date data_inicio) {
        this.dataInicio = data_inicio;
    }

    public List<MembroEquipe> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<MembroEquipe> equipe) {
        this.equipe = equipe;
    }

    public void addMembro(Usuario usuario, PapelEnum papelEnum) {

        MembroEquipe me = new MembroEquipe(usuario, papelEnum, this);
        equipe.add(me);

    }
    
    public void addUnidade(Unidade unidade) {
        unidades.add(unidade);
        unidade.setProjeto(this);
    }
}
