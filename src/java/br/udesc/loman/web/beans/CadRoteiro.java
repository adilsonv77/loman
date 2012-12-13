package br.udesc.loman.web.beans;

import br.udesc.dao.jpa.JPAUtil;
import br.udesc.loman.controle.CadastroRoteirosUC;
import br.udesc.loman.modelo.*;
import br.udesc.loman.web.AutenticacaoUtil;
import br.udesc.loman.web.LoManListener;
import br.udesc.web.CRUDSemPesquisa;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class CadRoteiro extends CRUDSemPesquisa<Roteiro> {

    private List<Projeto> projetos = new ArrayList<Projeto>();
    private List<Unidade> unidades = new ArrayList<Unidade>();
    private final CadastroRoteirosUC cruc;
    private TreeNode raiz;
    private TreeNode selectedNode;

    public CadRoteiro() {
        super(new CadastroRoteirosUC(LoManListener.getDAOFactory()), new String[]{});
        this.cruc = (CadastroRoteirosUC) cuc;

        raiz = new DefaultTreeNode("raiz", null);
        try {
            this.projetos = cruc.buscarProjetosCoordenadorProfessorConteudista(AutenticacaoUtil.getInstance().getUsuarioSessao());
            TreeNode descProj = new DefaultTreeNode("Projetos", raiz);
            for (Projeto pr : projetos) {
                TreeNode treeProj = new DefaultTreeNode(pr.getTitulo(), descProj);
                for (Unidade un : pr.getUnidades()) {
                    new DefaultTreeNode(un, treeProj);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CadRoteiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void novoRoteiro() throws Exception {
        novo();
        novaTarefa();
        setSelecUnidade(new Unidade());
        slideDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/roteiroscad.jsf");
    }

    public void verificarRevisoesUnidade() throws Exception {
        novo();
        setSelecUnidade(new Unidade());
        this.unidadeDesabilitado = true;
        this.unidadesComProblemas = cruc.buscarUnidadesComProblemas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/verificarrevisoesunidadecad.jsf");
    }

    public void testeMindMap() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/roteiromapaconceitual.jsf");
    }
    private List<Conteudo> conteudos = new ArrayList<Conteudo>();

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }
    private List<Conteudo> conteudosMapa = new ArrayList<Conteudo>();

    public List<Conteudo> getConteudosMapa() {
        return conteudosMapa;
    }

    public void setConteudosMapa(List<Conteudo> conteudosMapa) {
        this.conteudosMapa = conteudosMapa;
    }
    private List<Conteudo> conteudosDisponiveis = new ArrayList<Conteudo>();

    public List<Conteudo> getConteudosDisponiveis() {
        return conteudosDisponiveis;
    }

    public void setConteudosDisponiveis(List<Conteudo> conteudosDisponiveis) {
        this.conteudosDisponiveis = conteudosDisponiveis;
    }
    private boolean unidadeDesabilitado = true;

    public boolean isUnidadeDesabilitado() {
        return unidadeDesabilitado;
    }

    public void setUnidadeDesabilitado(boolean unidadeDesabilitado) {
        this.unidadeDesabilitado = unidadeDesabilitado;
    }

    public void editarRoteiro(Unidade unidade) {
        novaTarefa();
        setSelecUnidade(unidade);
        setRegistro(unidade.getRoteiro());
        slideDesabilitado = true;
    }

    public String formatarDataOcorrencia(Ocorrencia oco) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(oco.getDataModificacao());
    }
    private Ocorrencia ocorrencia = new Ocorrencia();

    public Ocorrencia getSelecOcorrencia() {
        return ocorrencia;
    }

    public void setSelecOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
    private List<Ocorrencia> ocorrenciasUnidade = new ArrayList<Ocorrencia>();

    public List<Ocorrencia> getOcorrenciasUnidade() {
        return ocorrenciasUnidade;
    }

    public void setOcorrenciasUnidade(List<Ocorrencia> ocorrenciasUnidade) {
        this.ocorrenciasUnidade = ocorrenciasUnidade;
    }

    public void atualizarOcorrenciasUnidade() throws Exception {
        this.ocorrencia = new Ocorrencia();
        this.ocorrenciasUnidade = cruc.buscaOcorrenciasUnidade(getSelecUnidade());
    }
    List<Unidade> unidadesComProblemas = new ArrayList<Unidade>();

    public List<Unidade> getUnidadesComProblemas() {
        return unidadesComProblemas;
    }

    public void setUnidadesComProblemas(List<Unidade> unidadesComProblemas) {
        this.unidadesComProblemas = unidadesComProblemas;
    }

    public void atualizaListaDeConteudos() throws Exception {
        this.conteudosMapa = cruc.buscarConteudosMapa(getSelecUnidade());
        this.conteudosDisponiveis = cruc.buscarConteudosDisponiveis(getSelecUnidade());
    }

    public void onNodeSelect(NodeSelectEvent event) throws Exception {
        novoSlide();
        novaTarefa();
        RequestContext context = RequestContext.getCurrentInstance();
        if (event.getTreeNode().getData().getClass() != String.class) {
            this.unidade = (Unidade) event.getTreeNode().getData();
            this.registro.setUnidade(getSelecUnidade());
            this.registro.setId(getSelecUnidade().getRoteiro().getId());
            this.registro.setSlides(getSelecUnidade().getRoteiro().getSlides());
            this.registro.setTarefas(getSelecUnidade().getRoteiro().getTarefas());
            this.slide.setRoteiro(this.registro);
            this.atualizaListaDeConteudos();

        } else {
            this.unidade = null;
        }
        context.addCallbackParam("mostrarJanela", this.unidade != null);
    }
    /**
     * * Projeto **
     */
    private Projeto projeto = new Projeto();

    public Projeto getSelecProjeto() {
        return projeto;
    }

    public void setSelecProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public void onRowSelectProjeto(SelectEvent event) {
        this.projeto = (Projeto) event.getObject();
    }
    /**
     * * Unidade **
     */
    private Unidade unidade = new Unidade();

    public Unidade getSelecUnidade() {
        return unidade;
    }

    public void setSelecUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public void onRowSelectUnidade(SelectEvent event) throws Exception {
        this.unidade = (Unidade) event.getObject();
        this.registro.setUnidade(getSelecUnidade());
        this.registro.setId(getSelecUnidade().getRoteiro().getId());
        this.registro.setSlides(getSelecUnidade().getRoteiro().getSlides());
        this.registro.setTarefas(getSelecUnidade().getRoteiro().getTarefas());
        this.slide.setRoteiro(this.registro);
        atualizarOcorrenciasUnidade();
        this.unidadeDesabilitado = false;
    }

    public void setarRegistro() {
        this.registro.setUnidade(getSelecUnidade());
        this.registro.setId(getSelecUnidade().getRoteiro().getId());
        this.registro.setSlides(getSelecUnidade().getRoteiro().getSlides());
        this.registro.setTarefas(getSelecUnidade().getRoteiro().getTarefas());
        this.slide.setRoteiro(this.registro);
    }

    public TreeNode getRaiz() {
        return raiz;
    }

    public List<Projeto> getProjetos() throws Exception {
        return projetos;
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle(ActionEvent event) throws Exception {
        if (selectedNode != null) {
            mensagem(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());
        }
    }
    /**
     * *******************************
     */
    /*
     * Tarefa
     */
    /**
     * *******************************
     */
    private Tarefa tarefa = new Tarefa();
    private int indexTarRot = -1;
    private int indexTarSli = -1;
    private boolean alterandoTarefa = false;

    public boolean isAlterandoTarefa() {
        return alterandoTarefa;
    }

    public void setAlterandoTarefa(boolean alterandoTarefa) {
        this.alterandoTarefa = alterandoTarefa;
    }

    public Tarefa getSelecTarefa() {
        return tarefa;
    }

    public void setSelecTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
        this.indexTarRot = this.registro.getTarefas().indexOf(tarefa);
        this.indexTarSli = this.getSelecSlide().getTarefas().indexOf(tarefa);
    }

    public void novaTarefa() {
        this.tarefa = new Tarefa();
        this.tarefa.setUsuario(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.tarefa.setPapelEnum(PapelEnum.DESIGNER);
        alterandoTarefa = false;
    }

    public void atualizarTarefasSlide() throws Exception {
        this.tarefasSlide = cruc.buscaTarefasSlide(getSelecSlide());
    }

    public void addTarefa() throws Exception {
        if (getSelecTarefa().getTitulo().trim().equals("")) {
            mensagem(FacesMessage.SEVERITY_ERROR, "Titulo", "Campo tÃ­tulo Ã© obrigatÃ³rio!");
        } else {
            if (getSelecTarefa().getDescricao().trim().equals("")) {
                mensagem(FacesMessage.SEVERITY_ERROR, "DescriÃ§Ã£o", "Campo descriÃ§Ã£o Ã© obrigatÃ³rio!");
            } else {
                if (getSelecTarefa().getDataEntrega() == null) {
                    mensagem(FacesMessage.SEVERITY_ERROR, "Data Entrega", "Campo data entrega Ã© obrigatÃ³rio!");
                } else {
                    getSelecTarefa().setSlide(getSelecSlide());
                    getSelecSlide().getTarefas().add(getSelecTarefa());
                    this.registro.getTarefas().add(getSelecTarefa());
                    getSelecTarefa().setRoteiro(this.registro);
                    cruc.salvar(this.registro);
                    alterandoRoteiro = true;
                    atualizarTarefasSlide();
                    mensagem(FacesMessage.SEVERITY_INFO, "Tarefa", getSelecTarefa().getTitulo() + " salva com sucesso!");
                    novaTarefa();
                }
            }
        }
    }

    public void alterarTarefa() throws Exception {
        if (getSelecTarefa().getTitulo().trim().equals("")) {
            mensagem(FacesMessage.SEVERITY_ERROR, "Titulo", "Campo tÃ­tulo Ã© obrigatÃ³rio!");
        } else {
            if (getSelecTarefa().getDescricao().trim().equals("")) {
                mensagem(FacesMessage.SEVERITY_ERROR, "DescriÃ§Ã£o", "Campo descriÃ§Ã£o Ã© obrigatÃ³rio!");
            } else {
                if (getSelecTarefa().getDataEntrega() == null) {
                    mensagem(FacesMessage.SEVERITY_ERROR, "Data Entrega", "Campo data entrega Ã© obrigatÃ³rio!");
                } else {
                    this.registro.getTarefas().set(indexTarRot, getSelecTarefa());
                    this.getSelecSlide().getTarefas().set(indexTarSli, getSelecTarefa());
                    cruc.salvar(this.registro);
                    alterandoRoteiro = true;
                    atualizarTarefasSlide();
                    mensagem(FacesMessage.SEVERITY_INFO, "Tarefa", getSelecTarefa().getTitulo() + " alterada com sucesso!");
                    novaTarefa();
                }
            }
        }
    }

    public void addSlide() throws Exception {
        if (getSelecSlide().getTitulo().trim().equals("")) {
            mensagem(FacesMessage.SEVERITY_ERROR, "Titulo", "Campo tÃ­tulo Ã© obrigatÃ³rio!");
        } else {
            getSelecSlide().setRoteiro(this.registro);
            cruc.incluirSlide(getSelecSlide());
            this.registro.getSlides().add(getSelecSlide());
            mensagem(FacesMessage.SEVERITY_INFO, "Slide", slide.getTitulo() + " incluÃ­do com sucesso!");
            alterandoRoteiro = true;
        }
        novoSlide();
    }

    public void alterarSlide() throws Exception {
        if (getSelecSlide().getTitulo().trim().equals("")) {
            mensagem(FacesMessage.SEVERITY_WARN, "Titulo", "Campo tÃ­tulo Ã© esta vazio!");
            getSelecSlide().setTitulo("Sem TÃ­tulo");
        } else {
            this.registro.getSlides().set(indexSli, getSelecSlide());
            cruc.alterarSlide(getSelecSlide());
            alterandoRoteiro = true;
            novoSlide();
        }
    }

    public void limparTarefa() {
        this.tarefa = new Tarefa();
        this.tarefa.setPapelEnum(PapelEnum.DESIGNER);
    }

    public void deleteTarefa() throws Exception {
        this.registro.getTarefas().remove(indexTarRot);
        getSelecSlide().getTarefas().remove(indexTarSli);
        cruc.alterarSlide(getSelecSlide());
        cruc.excluirTarefa(getSelecTarefa());
        atualizarTarefasSlide();
        alterandoRoteiro = true;
        mensagem(FacesMessage.SEVERITY_INFO, "Tarefa", getSelecTarefa().getTitulo() + " excluida com sucesso!");
        novaTarefa();
    }

    public void alteraTarefa() {
        alterandoTarefa = true;
    }
    /**
     * *******************************
     */
    /*
     * Slide
     */
    /**
     * *******************************
     */
    private Slide slide = new Slide();
    private int indexSli = -1;
    private boolean alterandoSlide = false;

    public boolean isAlterandoSlide() {
        return alterandoSlide;
    }

    public void setAlterandoSlide(boolean alterandoSlide) {
        this.alterandoSlide = alterandoSlide;
    }

    public void alteraSlide() {
        alterandoSlide = true;
    }

    public Slide getSelecSlide() {
        this.indexSli = this.registro.getSlides().indexOf(slide);
        return slide;
    }

    public void setSelecSlide(Slide slide) throws Exception {
        this.slide = slide;
        this.indexSli = this.registro.getSlides().indexOf(slide);
        atualizarTarefasSlide();
    }

    public void novoSlide() {
        this.slide = new Slide();
        alterandoSlide = false;
        slideDesabilitado = true;
    }
    private List<Tarefa> tarefasSlide = new ArrayList<Tarefa>();

    public List<Tarefa> getTarefasSlide() {
        return tarefasSlide;
    }

    public void setTarefasSlide(List<Tarefa> tarefasSlide) {
        this.tarefasSlide = tarefasSlide;
    }

    //NÃ£o usarei mais
    public void onRowSelectSlide(SelectEvent event) throws Exception {
        this.indexSli = this.registro.getSlides().indexOf((Slide) event.getObject());
        alterandoSlide = true;
        this.setSelecSlide((Slide) event.getObject());
        atualizarTarefasSlide();
        this.slideDesabilitado = false;
    }

    public void deleteSlide() throws Exception {
        if (tarefasSlide.isEmpty()) {
            this.registro.getSlides().remove(indexSli);
            cruc.excluirSlide(getSelecSlide());
            alterandoRoteiro = true;
            mensagem(FacesMessage.SEVERITY_INFO, "Slide", getSelecSlide().getTitulo() + " excluido com sucesso!");
        } else {
            mensagem(FacesMessage.SEVERITY_WARN, "Slide", "Slide nÃ£o foi excluÃ­do, ele possui " + getTarefasSlide().size() + " tarefa(s) associada(s)!");
        }
        novoSlide();
    }
    private boolean slideDesabilitado = true;

    public boolean isSlideDesabilitado() {
        return slideDesabilitado;
    }

    public void setSlideDesabilitado(boolean slideDesabilitado) {
        this.slideDesabilitado = slideDesabilitado;
    }
    private boolean alterandoRoteiro;

    public void associarOcorrenciaUnidade(String descricao) {
        this.ocorrencia.setUnidade(getSelecUnidade());
        this.ocorrencia.setDataModificacao(new java.util.Date());
        this.ocorrencia.setUsuario(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrencia.setDescricao(descricao);
        this.ocorrenciasUnidade.add(ocorrencia);
    }

    public void mensagem(FacesMessage.Severity severity, String titulo, String mensagem) {
        FacesMessage msg = new FacesMessage(severity, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private Conteudo conteudo = new Conteudo();

    public Conteudo getSelecConteudo() {
        return conteudo;
    }

    public void setSelecConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }
    private Relacao relacao = new Relacao();

    public Relacao getRelacao() {
        return relacao;
    }

    public void setRelacao(Relacao relacao) {
        this.relacao = relacao;
    }

    public List<Conteudo> carregaConteudosDaRelacao() throws Exception {
        return cruc.buscarConteudosRelacao(getRelacao());
    }

    public void refresh() throws Exception {
        for (Conteudo cont : conteudosMapa) {
            JPAUtil.getInstance().getGerenciadorEntidade().refresh(cont);
        }
        for (Conteudo cont : conteudosDisponiveis) {
            JPAUtil.getInstance().getGerenciadorEntidade().refresh(cont);
        }
    }
}
