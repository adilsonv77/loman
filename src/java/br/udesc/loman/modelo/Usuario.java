package br.udesc.loman.modelo;

import br.udesc.loman.security.Criptografia;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
        name="usuario.porNick",
        query="SELECT u FROM Usuario as u where upper(u.nick) = upper(:nick)"),
    
    @NamedQuery(
        name="usuario.porNome",
        query="SELECT u FROM Usuario as u where upper(u.nome) like upper(:nome)"),
    
    @NamedQuery(name="usuario.login",
        query="SELECT u FROM Usuario AS u WHERE UPPER(u.nick) like UPPER(:nick) AND UPPER(u.senha) = UPPER(:senha)")        
})

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable, TemGetId {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private long id = 0;
    @Column(name = "ds_nome", length = 50)
    private String nome = "";
    @Column(name = "ds_nick", length = 20, unique=true)
    private String nick = "";
    @Column(name = "ds_senha", length = 10)
    private String senha = "";        
//    @Column(name = "ds_senha", length = 10)
//    private String senhaMD5;
    @Column(name = "ds_email", length = 150)
    private String email = "";
    @Column(name = "bo_administrador")
    private boolean administrador = false;
   
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }        
    
    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

//    public String getSenhaMD5() {
//        return senhaMD5;
//    }
//
//    public void setSenhaMD5(String senhaMD5) {
//        this.senhaMD5 = Criptografia.criptografar(senha);
//    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long ID) {
        this.id = ID;
    }

    public long getId() {
        return id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setSenha(String senha) {
        this.senha = senha;
//        setSenhaMD5(senha);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNick() {
        return nick;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}
