package br.udesc.loman.web.beans;

import br.udesc.loman.controle.CadastroTarefasUC;
import br.udesc.loman.modelo.Material;
import br.udesc.loman.modelo.Ocorrencia;
import br.udesc.loman.modelo.Status;
import br.udesc.loman.modelo.Tarefa;
import br.udesc.loman.web.AutenticacaoUtil;
import br.udesc.loman.web.LoManListener;
import br.udesc.web.CRUDSemPesquisa;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class CadTarefa extends CRUDSemPesquisa<Tarefa>{

    private final CadastroTarefasUC ctuc;

    public CadTarefa() {
        super(new CadastroTarefasUC(LoManListener.getDAOFactory()), new String[]{});
        this.ctuc = (CadastroTarefasUC) cuc;
        try {            
        } catch (Exception ex) {
            Logger.getLogger(CadTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void assumirTarefa() throws Exception {
        novo();
        setSelecTarefa(new Tarefa());
        this.tarefasAssumidas = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasAssumidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.tarefasDisponiveis = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasDisponiveis(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        this.tarefaDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/assumirtarefacad.jsf");        
    }

    public void disponibilizarMaterial() throws Exception {
        novo();
        setSelecTarefa(new Tarefa());
        this.tarefasAssumidas = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasAssumidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        this.tarefaDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/disponibilizarmaterialcad.jsf");        
    }

    public void revisarTarefa() throws Exception {
        novo();
        this.tarefa = new Tarefa();
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        this.tarefasProntasParaRevisao = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.tarefaDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/revisartarefacad.jsf");        
    }
    
    public void tramitarTarefa() throws Exception {
        novo();
        this.tarefa = new Tarefa();
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        this.tarefasProntasParaRevisao = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.tarefaDesabilitado = true;
        this.copiaStatus = Status.CONCLUIDA;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/tramitartarefacad.jsf");        
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

    public Tarefa getSelecTarefa() {
        return tarefa;
    }

    public void setSelecTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    private List<Tarefa> tarefasDisponiveis = new ArrayList<Tarefa>();

    public List<Tarefa> getTarefasDisponiveis() throws Exception {
        return tarefasDisponiveis;
    }

    public void setTarefasDisponiveis(List<Tarefa> tarefasDisponiveis) {
        this.tarefasDisponiveis = tarefasDisponiveis;
    }
    private List<Tarefa> tarefasAssumidas = new ArrayList<Tarefa>();

    public List<Tarefa> getTarefasAssumidas() throws Exception {
        return tarefasAssumidas;
    }

    public void setTarefasAssumidas(List<Tarefa> tarefasAssumidas) {
        this.tarefasAssumidas = tarefasAssumidas;
    }
    List<Tarefa> tarefasProntasParaRevisao = new ArrayList<Tarefa>();

    public List<Tarefa> getTarefasProntasParaRevisao() {
        return tarefasProntasParaRevisao;
    }

    public void setTarefasProntasParaRevisao(List<Tarefa> tarefasProntasParaRevisao) {
        this.tarefasProntasParaRevisao = tarefasProntasParaRevisao;
    }

    public String formatarDataTarefa(Tarefa tar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(tar.getDataEntrega());
    }        
    
    Status copiaStatus;

    public Status getCopiaStatus() {
        return copiaStatus;
    }

    public void setCopiaStatus(Status copiaStatus) {
        this.copiaStatus = copiaStatus;
    }           
    
    private boolean tarefaDesabilitado = true;

    public boolean isTarefaDesabilitado() {
        return tarefaDesabilitado;
    }

    public void setTarefaDesabilitado(boolean tarefaDesabilitado) {
        this.tarefaDesabilitado = tarefaDesabilitado;
    }

    public void onRowSelectTarefaDisponivel(SelectEvent event) throws Exception {
        this.ocorrenciasTarefa = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasTarefa(getSelecTarefa());
        this.tarefaDesabilitado = false;
    }

    public void onRowSelectTarefaDisponibilizarMaterial(SelectEvent event) throws Exception {
        this.ocorrenciasTarefa = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasTarefa(getSelecTarefa());
        this.tarefaDesabilitado = false;
    }

    public void onRowSelectRevisarTarefa(SelectEvent event) throws Exception {
        this.ocorrenciasTarefa = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasTarefa(getSelecTarefa());
        this.ocorrenciasTarefaComMaterial = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasTarefaMateriais(getSelecTarefa());        
        this.tarefaDesabilitado = false;
        System.out.println("ON ROW");
        System.out.println("ON ROW");
        System.out.println("ON ROW");
    }
    
    public void onRowSelectTramitarTarefa(SelectEvent event) throws Exception {
        this.ocorrenciasTarefa = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasTarefa(getSelecTarefa());        
        this.copiaStatus = Status.CONCLUIDA;
    }

    public void onRowSelectOcorrenciaRevisarTarefa(SelectEvent event) throws Exception {
        this.ocorrencia = (Ocorrencia) event.getObject();
        this.material = this.ocorrencia.getMaterial();
    }
    
    public void associarOcorrenciaTarefa(String descricao){                
        this.ocorrencia.setUsuario(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrencia.setTarefa(this.getSelecTarefa());
        this.ocorrencia.setDescricao(descricao);
        this.ocorrencia.setDataModificacao(new java.util.Date());
        this.getSelecTarefa().getOcorrencias().add(this.ocorrencia);        
    }

    public void assumirTarefaUsuario() throws Exception {
        //SETANDO OCORRENCIA    
        this.associarOcorrenciaTarefa("Assumir Tarefa");
        ///*** 
        //SETANDO TAREFA        
//        this.getSelecTarefa().setOcorrencias(ocorrencias);
        this.getSelecTarefa().setStatus(Status.EM_ANDAMENTO);
        this.getSelecTarefa().setUsuario(AutenticacaoUtil.getInstance().getUsuarioSessao());
        LoManListener.getDAOFactory().getTarefaDAO().alterar(getSelecTarefa());
        //***        
        this.tarefasAssumidas = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasAssumidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.tarefasDisponiveis = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasDisponiveis(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        this.tarefaDesabilitado = true;
    }

    public void tramiteTarefa() throws Exception {
        //SETANDO OCORRENCIA
        this.getSelecTarefa().setStatus(copiaStatus);
        if (this.getSelecTarefa().getStatus() == Status.COM_PROBLEMAS) {
            this.associarOcorrenciaTarefa("Tarefa com Problemas");
        } else {
            this.associarOcorrenciaTarefa("Tarefa concluida");
        }
        ///***
        //SETANDO TAREFA        
//        this.getSelecTarefa().setOcorrencias(ocorrencias);
        this.getSelecTarefa().setUsuario(this.ocorrencia.getUsuario());
        LoManListener.getDAOFactory().getTarefaDAO().alterar(this.getSelecTarefa());
        //***                
        this.tarefasProntasParaRevisao = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        this.ocorrencia = new Ocorrencia();
        this.tarefaDesabilitado = true;
        mensagem(FacesMessage.SEVERITY_INFO, "Tarefa", getSelecTarefa().getTitulo() + " tramitada com sucesso!");
    }

    public void setarRegistoTarefa(Tarefa tarefa) {
        this.registro = tarefa;
    }
    /**
     * *******************************
     */
    /*
     * Ocorrencia
     */
    /**
     * *******************************
     */
    List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
    private Ocorrencia ocorrencia = new Ocorrencia();

    public Ocorrencia getSelecOcorrencia() {
        return ocorrencia;
    }

    public void setSelecOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
    List<Ocorrencia> ocorrenciasTarefa = new ArrayList<Ocorrencia>();

    public List<Ocorrencia> getOcorrenciasTarefa() {
        return ocorrenciasTarefa;
    }

    public void setOcorrenciasTarefa(List<Ocorrencia> ocorrenciasTarefa) {
        this.ocorrenciasTarefa = ocorrenciasTarefa;
    }
    List<Ocorrencia> ocorrenciasTarefaComMaterial = new ArrayList<Ocorrencia>();

    public List<Ocorrencia> getOcorrenciasTarefaComMaterial() {
        return ocorrenciasTarefaComMaterial;
    }

    public void setOcorrenciasTarefaComMaterial(List<Ocorrencia> ocorrenciaTarefaComMaterial) {
        this.ocorrenciasTarefaComMaterial = ocorrenciaTarefaComMaterial;
    }

    public String formatarDataOcorrencia(Ocorrencia oco) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(oco.getDataModificacao());
    }
    
    public String formatarNomeArquivo(Ocorrencia oco) {
        String[] g = oco.getMaterial().getCaminho().split(Pattern.quote("/"));
        return g[g.length-1];        
    }
    /**
     * *******************************
     */
    /*
     * Material
     */
    /**
     * *******************************
     */
    private Material material = new Material();

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
        mensagem(FacesMessage.SEVERITY_INFO, "Sucesso!", event.getFile().getFileName() + " foi salvo!");
        byte[] buffer = event.getFile().getContents();

        //Tratando nome do arquivo
        String nomeArquivo = event.getFile().getFileName();
        String[] g = nomeArquivo.split(Pattern.quote("."));
        nomeArquivo = "";
        for (int i = 0; i < g.length; i++) {
            if (i == g.length - 1) {
                g[i] = "(" + AutenticacaoUtil.getInstance().getUsuarioSessao().getNick() + ")" + "." + g[i];
            }
            nomeArquivo = nomeArquivo + g[i];
        }
        FacesContext context = FacesContext.getCurrentInstance();
        String caminhoMaterial = context.getExternalContext().getRealPath("/WEB-INF/materiais/" + nomeArquivo);
        String caminhoBanco = "/WEB-INF/materiais/" + nomeArquivo;
        //***
        //Setando o Material
        List<Material> materiais = new ArrayList<Material>();
        this.material.setObservacao(ocorrencia.getObservacao());
        this.material.setTarefa(getSelecTarefa());
        this.material.setCaminho(caminhoBanco);
        materiais.add(material);
        this.getSelecTarefa().setMateriais(materiais);
        //***
        //Setanto a Ocorrencia
        this.associarOcorrenciaTarefa("Upload de material");
        this.ocorrencia.setMaterial(material);
//        this.getSelecTarefa().setOcorrencias(ocorrencias);
        this.getSelecTarefa().setStatus(Status.PRONTA_PARA_REVISAO);
        LoManListener.getDAOFactory().getTarefaDAO().alterar(getSelecTarefa());
        //***
        //Gravando arquivo no disco
        PrintStream newFile = new PrintStream(caminhoMaterial);
        newFile.write(buffer);
        newFile.close();
        //***
        //Atualizando listas e campos
        this.limparObservacao();
        this.tarefasDisponiveis = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasDisponiveis(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.tarefasAssumidas = LoManListener.getDAOFactory().getTarefaDAO().buscaTarefasAssumidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrenciasTarefa = new ArrayList<Ocorrencia>();
        tarefaDesabilitado = true;
        //***
    }

    public void tarefaConcluida() {
        this.getSelecTarefa().setStatus(Status.CONCLUIDA);
    }

    public void limparObservacao() {
        this.ocorrencia.setObservacao(null);
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setarOcorrenciasTarefaComMaterial() throws Exception {
        System.out.println("ACTIONLISTNER");
        System.out.println("ACTIONLISTNER");
        System.out.println("ACTIONLISTNER");
        this.ocorrenciasTarefaComMaterial = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasTarefaMateriais(getSelecTarefa());
    }

    public void dowloadMaterial(Ocorrencia oco) {
        this.setSelecOcorrencia(oco);
//        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/WEB-INF/materiais/Chrysanthemum(Pedro).jpg");                
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(this.getSelecOcorrencia().getMaterial().getCaminho());
        String nomeDownload = this.getSelecOcorrencia().getMaterial().getCaminho();
        String[] novoNome = nomeDownload.split("/");
        nomeDownload = novoNome[novoNome.length - 1];
        file = new DefaultStreamedContent(stream, "image/jpg", nomeDownload);
    }

    public void mensagem(FacesMessage.Severity severity, String titulo, String mensagem) {
        FacesMessage msg = new FacesMessage(severity, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
}
