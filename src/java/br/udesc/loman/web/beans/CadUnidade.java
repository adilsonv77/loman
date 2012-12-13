package br.udesc.loman.web.beans;

import br.udesc.loman.controle.CadastroUnidadesUC;
import br.udesc.loman.modelo.*;
import br.udesc.loman.web.AutenticacaoUtil;
import br.udesc.loman.web.LoManListener;
import br.udesc.web.CRUDSemPesquisa;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
public class CadUnidade extends CRUDSemPesquisa<Unidade> {

    private final CadastroUnidadesUC cuuc;
    private List<Unidade> unidadesCoordenador = new ArrayList<Unidade>();

    public CadUnidade() {
        super(new CadastroUnidadesUC(LoManListener.getDAOFactory()), new String[]{});
        this.cuuc = (CadastroUnidadesUC) cuc;
        try {
        } catch (Exception ex) {
            Logger.getLogger(CadUnidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disponibilizarMaterial() throws Exception {
        novo();
        setSelecUnidade(new Unidade());
        this.ocorrenciasUnidade = new ArrayList<Ocorrencia>();
        this.unidadesCoordenador = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesPorCoordenador(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadesConcluidas = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesComTarefasConcluidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadeDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/disponibilizarunidadecad.jsf");        
    }

    public void revisarUnidade() throws Exception {
        novo();
        setSelecUnidade(new Unidade());
        this.ocorrenciasUnidade = new ArrayList<Ocorrencia>();
        this.unidadesConcluidas = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesComTarefasConcluidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadesProntasParaRevisao = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadeDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/revisarunidadecad.jsf");        
    }

    public void tramitarUnidade() throws Exception {
        novo();
        setSelecUnidade(new Unidade());
        this.ocorrenciasUnidade = new ArrayList<Ocorrencia>();
        this.unidadesConcluidas = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesComTarefasConcluidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadesProntasParaRevisao = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadeDesabilitado = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/loman/tramitarunidadecad.jsf");        
    }   

    public void setarOcorrenciasUnidadeComMaterial() throws Exception {
        this.ocorrenciasUnidadeComMaterial = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasUnidadeMateriais(getSelecUnidade());
    }
    List<Ocorrencia> ocorrenciasUnidadeComMaterial = new ArrayList<Ocorrencia>();

    public List<Ocorrencia> getOcorrenciasUnidadeComMaterial() {
        return ocorrenciasUnidadeComMaterial;
    }

    public void setOcorrenciasUnidadeComMaterial(List<Ocorrencia> ocorrenciasUnidadeComMaterial) {
        this.ocorrenciasUnidadeComMaterial = ocorrenciasUnidadeComMaterial;
    }
    private boolean unidadeDesabilitado = true;

    public boolean isUnidadeDesabilitado() {
        return unidadeDesabilitado;
    }

    public void setUnidadeDesabilitado(boolean unidadeDesabilitado) {
        this.unidadeDesabilitado = unidadeDesabilitado;
    }
    
    private List<Unidade> unidadesEmAndamento = new ArrayList<Unidade>();

    public List<Unidade> getUnidadesEmAndamento() {
        return unidadesEmAndamento;
    }

    public void setUnidadesEmAndamento(List<Unidade> unidadesEmAndamento) {
        this.unidadesEmAndamento = unidadesEmAndamento;
    }        
    
    private List<Unidade> unidadesComProblemas = new ArrayList<Unidade>();

    public List<Unidade> getUnidadesComProblemas() {
        return unidadesComProblemas;
    }

    public void setUnidadesComProblemas(List<Unidade> unidadesComProblemas) {
        this.unidadesComProblemas = unidadesComProblemas;
    }        

    public List<Unidade> getUnidadesCoordenador() {
        return unidadesCoordenador;
    }

    public void setUnidadesCoordenador(List<Unidade> unidadesCoordenador) {
        this.unidadesCoordenador = unidadesCoordenador;
    }
    private Unidade unidade = new Unidade();

    public Unidade getSelecUnidade() {
        return unidade;
    }

    public void setSelecUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    List<Unidade> unidadesProntasParaRevisao = new ArrayList<Unidade>();

    public List<Unidade> getUnidadesProntasParaRevisao() {
        return unidadesProntasParaRevisao;
    }

    public void setUnidadesProntasParaRevisao(List<Unidade> unidadesProntasParaRevisao) {
        this.unidadesProntasParaRevisao = unidadesProntasParaRevisao;
    }
    
    List<Unidade> unidadesConcluidas = new ArrayList<Unidade>();

    public List<Unidade> getUnidadesConcluidas() {
        return unidadesConcluidas;
    }

    public void setUnidadesConcluidas(List<Unidade> unidadesConcluidas) {
        this.unidadesConcluidas = unidadesConcluidas;
    }
    private Material material = new Material();

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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
        this.ocorrenciasUnidade = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasUnidade(getSelecUnidade());
    }   

    public void onRowSelectUnidade(SelectEvent event) throws Exception {
        this.setSelecUnidade((Unidade) event.getObject());
        this.ocorrenciasUnidadeComMaterial = LoManListener.getDAOFactory().getOcorrenciaDAO().buscaOcorrenciasUnidadeMateriais(getSelecUnidade());
        atualizarObjetivosUnidade();
        atualizarOcorrenciasUnidade();
        this.unidadeDesabilitado = false;
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
        this.material.setUnidade(getSelecUnidade());
        this.material.setCaminho(caminhoBanco);
        materiais.add(material);
        this.getSelecUnidade().setMateriais(materiais);
        //***
        //Setanto a Ocorrencia
        this.associarOcorrenciaUnidade("Upload de material");
        this.ocorrencia.setMaterial(material);
        this.getSelecUnidade().setOcorrencias(ocorrencias);
        LoManListener.getDAOFactory().getUnidadeDAO().alterar(getSelecUnidade());
        atualizarOcorrenciasUnidade();
        //***
        //Gravando arquivo no disco
        PrintStream newFile = new PrintStream(caminhoMaterial);
        newFile.write(buffer);
        newFile.close();
        //***
        //Atualizando listas e campos
        this.unidadesCoordenador = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesPorCoordenador(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadesConcluidas = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesComTarefasConcluidas(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.unidadesProntasParaRevisao = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrenciasUnidade = new ArrayList<Ocorrencia>();
        //***
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
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

    public void associarOcorrenciaUnidade(String descricao) {
        this.ocorrencia.setUnidade(getSelecUnidade());
        this.ocorrencia.setDataModificacao(new java.util.Date());
        this.ocorrencia.setUsuario(AutenticacaoUtil.getInstance().getUsuarioSessao());
        this.ocorrencia.setDescricao(descricao);
        this.ocorrencias.add(ocorrencia);
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
    //OBJETIVOS
    private List<Objetivo> objetivosSelecionados = new ArrayList<Objetivo>();

    public List<Objetivo> getObjetivosSelecionados() {
        return objetivosSelecionados;
    }

    public void setObjetivosSelecionados(List<Objetivo> objetivosSelecionados) {
        this.objetivosSelecionados = objetivosSelecionados;
    }
    //**

    public void atualizarObjetivosUnidade() throws Exception {
        this.objetivosSelecionados = LoManListener.getDAOFactory().getObjetivoDAO().buscaObjetivosConcluidos(getSelecUnidade());
    }

    public void ocorrenciasTramiteUnidade(String descricao) throws Exception {
        ocorrenciasUnidade = new ArrayList<Ocorrencia>();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setUnidade(getSelecUnidade());
        ocorrencia.setUsuario(AutenticacaoUtil.getInstance().getUsuarioSessao());
        ocorrencia.setDataModificacao(new java.util.Date());
        LoManListener.getDAOFactory().getOcorrenciaDAO().incluir(ocorrencia);
    }

    public void tramiteUnidade() throws Exception {
        for (Objetivo o : getSelecUnidade().getObjetivos()) {
            System.out.println(o.getDescricao());
            if (objetivosSelecionados.contains(o)) {
                o.setStatus(true);
            } else {
                if (!objetivosSelecionados.contains(o)) {
                    o.setStatus(false);
                }
            }
        }
        if (getSelecUnidade().getObjetivos().equals(objetivosSelecionados)) {
            ocorrenciasTramiteUnidade("Unidade Concluída");
            setarOcorrenciasUnidadeComMaterial();
        } else {
            ocorrenciasTramiteUnidade("Unidade Com Problemas");
        }
        mensagem(FacesMessage.SEVERITY_INFO, "Unidade", getSelecUnidade().getNome() + " atualizada com sucesso!");
        LoManListener.getDAOFactory().getUnidadeDAO().alterar(getSelecUnidade());
        setSelecUnidade(new Unidade());
        this.unidadesProntasParaRevisao = LoManListener.getDAOFactory().getUnidadeDAO().buscarUnidadesProntasParaRevisao(AutenticacaoUtil.getInstance().getUsuarioSessao());
    }
    
    public void limparObservacao() {
        this.ocorrencia.setObservacao(null);
    }

    public String formatarDataOcorrencia(Ocorrencia oco) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(oco.getDataModificacao());
    }

    public String formatarNomeArquivo(Ocorrencia oco) {
        String[] g = oco.getMaterial().getCaminho().split(Pattern.quote("/"));
        return g[g.length - 1];
    }

    public void mensagem(FacesMessage.Severity severity, String titulo, String mensagem) {
        FacesMessage msg = new FacesMessage(severity, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
