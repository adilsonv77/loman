import br.udesc.dao.jpa.JPAUtil;
import br.udesc.loman.controle.AutenticacaoControle;
import br.udesc.loman.controle.CadastroProjetosUC;
import br.udesc.loman.controle.CadastroUsuariosUC;
import br.udesc.loman.dao.jpa.JPADAOFactory;
import br.udesc.loman.modelo.*;
import br.udesc.loman.web.AutenticacaoUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class PopularTabela extends javax.swing.JFrame {

    private final CadastroUsuariosUC cUsuario;
    private final CadastroProjetosUC cProjetos;

    public PopularTabela() throws Exception {
        JPAUtil.inicializar("loman");
        JPADAOFactory jpa = new JPADAOFactory();
        AutenticacaoControle aut = AutenticacaoUtil.getInstance();

        cUsuario = new CadastroUsuariosUC(jpa, aut);
        cProjetos = new CadastroProjetosUC(jpa);

        initComponents();
    }

    private void criarBD() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
        Statement st = con.createStatement();
        st.execute("drop database if exists oas");
        st.execute("create database oas");
        st.close();
        con.close();

        JPAUtil.inicializar("loman");
    }

    private void adicionarRegistros() throws Exception {

        //INÍCIO DA INCLUSÃO DOS USUÁRIOS
        Usuario usuarioJoaoPaulo = new Usuario();
        usuarioJoaoPaulo.setNome("João Paulo");
        usuarioJoaoPaulo.setNick("JP");
        usuarioJoaoPaulo.setSenha("1234");
        usuarioJoaoPaulo.setEmail("joaodasilveira@gmail.com");
        usuarioJoaoPaulo.setAdministrador(true);
        cUsuario.salvar(usuarioJoaoPaulo);        

        Usuario usuarioMariaDaGloria = new Usuario();
        usuarioMariaDaGloria.setNome("Maria da Glória");
        usuarioMariaDaGloria.setNick("Maria");
        usuarioMariaDaGloria.setSenha("654321");
        usuarioMariaDaGloria.setEmail("mariadagloria@gmail.com");
        usuarioMariaDaGloria.setAdministrador(false);
        cUsuario.salvar(usuarioMariaDaGloria);        

        Usuario usuarioAndersonFreitas = new Usuario();
        usuarioAndersonFreitas.setNome("Anderson Freitas");
        usuarioAndersonFreitas.setNick("Anderson");
        usuarioAndersonFreitas.setSenha("123456");
        usuarioAndersonFreitas.setEmail("andersonfreitas@gmail.com");
        usuarioAndersonFreitas.setAdministrador(false);
        cUsuario.salvar(usuarioAndersonFreitas);        

        Usuario usuarioClaudioSouza = new Usuario();
        usuarioClaudioSouza.setNome("Claudio Souza");
        usuarioClaudioSouza.setNick("Claudio");
        usuarioClaudioSouza.setSenha("1234");
        usuarioClaudioSouza.setEmail("claudiosouza@gmail.com");
        usuarioClaudioSouza.setAdministrador(false);
        cUsuario.salvar(usuarioClaudioSouza);        

        Usuario usuarioJuliaPires = new Usuario();
        usuarioJuliaPires.setNome("Júlia Pires");
        usuarioJuliaPires.setNick("Julia");
        usuarioJuliaPires.setSenha("1234");
        usuarioJuliaPires.setEmail("juliapires@gmail.com");
        usuarioJuliaPires.setAdministrador(false);
        cUsuario.salvar(usuarioJuliaPires);        

        Usuario usuarioGabrielaFigueredo = new Usuario();
        usuarioGabrielaFigueredo.setNome("Gabriela Figueredo");
        usuarioGabrielaFigueredo.setNick("Gabriela");
        usuarioGabrielaFigueredo.setSenha("1234");
        usuarioGabrielaFigueredo.setEmail("gabrielafigueredo@gmail.com");
        usuarioGabrielaFigueredo.setAdministrador(false);
        cUsuario.salvar(usuarioGabrielaFigueredo);        

        Usuario usuarioRafaelDeLima = new Usuario();
        usuarioRafaelDeLima.setNome("Rafael de Lima");
        usuarioRafaelDeLima.setNick("Rafa");
        usuarioRafaelDeLima.setSenha("1234");
        usuarioRafaelDeLima.setEmail("rafaelgoncalves@gmail.com");
        usuarioRafaelDeLima.setAdministrador(false);
        cUsuario.salvar(usuarioRafaelDeLima);        

        Usuario usuarioTulioMaravilha = new Usuario();
        usuarioTulioMaravilha.setNome("Túlio Maravilha");
        usuarioTulioMaravilha.setNick("Túlio");
        usuarioTulioMaravilha.setSenha("1234");
        usuarioTulioMaravilha.setEmail("tuliomaravilha@gmail.com");
        usuarioTulioMaravilha.setAdministrador(true);
        cUsuario.salvar(usuarioTulioMaravilha);        

        Usuario usuarioFabioCorrea = new Usuario();
        usuarioFabioCorrea.setNome("Fábio Correa");
        usuarioFabioCorrea.setNick("Fábio");
        usuarioFabioCorrea.setSenha("1234");
        usuarioFabioCorrea.setEmail("fabiolima@gmail.com");
        usuarioFabioCorrea.setAdministrador(false);
        cUsuario.salvar(usuarioFabioCorrea);        

        Usuario usuarioRomarioSilva = new Usuario();
        usuarioRomarioSilva.setNome("Romário Silva");
        usuarioRomarioSilva.setNick("Romário");
        usuarioRomarioSilva.setSenha("1234");
        usuarioRomarioSilva.setEmail("romariosilva@gmail.com");
        usuarioRomarioSilva.setAdministrador(false);
        cUsuario.salvar(usuarioRomarioSilva);        
        
        Usuario usuarioFernandoSantos = new Usuario();
        usuarioFernandoSantos.setNome("Fernando dos Santos");
        usuarioFernandoSantos.setNick("Fefe");
        usuarioFernandoSantos.setSenha("1234");
        usuarioFernandoSantos.setEmail("fernandosantos@gmail.com");
        usuarioFernandoSantos.setAdministrador(false);
        cUsuario.salvar(usuarioFernandoSantos);        
        
        Usuario usuarioRobertoDinamite = new Usuario();
        usuarioRobertoDinamite.setNome("Roberto Dinamite");
        usuarioRobertoDinamite.setNick("Robertinho");
        usuarioRobertoDinamite.setSenha("1234");
        usuarioRobertoDinamite.setEmail("robertodinamite@gmail.com");
        usuarioRobertoDinamite.setAdministrador(false);
        cUsuario.salvar(usuarioRobertoDinamite);        
        
        Usuario usuarioDanielLucas = new Usuario();
        usuarioDanielLucas.setNome("Daniel Lucas");
        usuarioDanielLucas.setNick("Dani");
        usuarioDanielLucas.setSenha("1234");
        usuarioDanielLucas.setEmail("daniellucas@gmail.com");
        usuarioDanielLucas.setAdministrador(false);
        cUsuario.salvar(usuarioDanielLucas);        
        
        Usuario usuarioGeraldoVarela = new Usuario();
        usuarioGeraldoVarela.setNome("Geraldo Menegazzo Varela");
        usuarioGeraldoVarela.setNick("Geraldo");
        usuarioGeraldoVarela.setSenha("1234");
        usuarioGeraldoVarela.setEmail("geraldovarela@gmail.com");
        usuarioGeraldoVarela.setAdministrador(true);
        cUsuario.salvar(usuarioGeraldoVarela);        
        
        Usuario usuarioPabloSchoeffel = new Usuario();
        usuarioPabloSchoeffel.setNome("Pablo Schoeffel");
        usuarioPabloSchoeffel.setNick("Pablo");
        usuarioPabloSchoeffel.setSenha("1234");
        usuarioPabloSchoeffel.setEmail("pabloschoeffel@gmail.com");
        usuarioPabloSchoeffel.setAdministrador(true);
        cUsuario.salvar(usuarioPabloSchoeffel);       
        
        Usuario usuarioDaniloSimoni = new Usuario();
        usuarioDaniloSimoni.setNome("Danilo Simoni");
        usuarioDaniloSimoni.setNick("Danilo");
        usuarioDaniloSimoni.setSenha("1234");
        usuarioDaniloSimoni.setEmail("danilosimoni@gmail.com");
        usuarioDaniloSimoni.setAdministrador(false);
        cUsuario.salvar(usuarioDaniloSimoni);        
        
        Usuario usuarioGabrielJacobsen = new Usuario();
        usuarioGabrielJacobsen.setNome("Gabriel Rigo Jacobsen");
        usuarioGabrielJacobsen.setNick("Gabriel");
        usuarioGabrielJacobsen.setSenha("1234");
        usuarioGabrielJacobsen.setEmail("gabrielrigojacobsen@gmail.com");
        usuarioGabrielJacobsen.setAdministrador(false);
        cUsuario.salvar(usuarioGabrielJacobsen);       
        
        Usuario usuarioCharlesFerrari = new Usuario();
        usuarioCharlesFerrari.setNome("Charles J. Ferrari");
        usuarioCharlesFerrari.setNick("Charles");
        usuarioCharlesFerrari.setSenha("1234");
        usuarioCharlesFerrari.setEmail("charlesferrari@gmail.com");
        usuarioCharlesFerrari.setAdministrador(false);
        cUsuario.salvar(usuarioCharlesFerrari);        
        
        Usuario usuarioPedroIsolani = new Usuario();
        usuarioPedroIsolani.setNome("Pedro Heleno Isolani");
        usuarioPedroIsolani.setNick("Pedro");
        usuarioPedroIsolani.setSenha("1234");
        usuarioPedroIsolani.setEmail("pedroisolani@gmail.com");
        usuarioPedroIsolani.setAdministrador(true);
        cUsuario.salvar(usuarioPedroIsolani);        
        
        Usuario usuarioAdilsonVahldick = new Usuario();
        usuarioAdilsonVahldick.setNome("Adilson Vahldick");
        usuarioAdilsonVahldick.setNick("Adilson");
        usuarioAdilsonVahldick.setSenha("1234");
        usuarioAdilsonVahldick.setEmail("adilsonvahldick@gmail.com");
        usuarioAdilsonVahldick.setAdministrador(true);
        cUsuario.salvar(usuarioAdilsonVahldick);        
        //FIM DA INCLUSÃO DOS USUÁRIOS           

        //INICIO DA INCLUSAO DO PROJETO DE INICIAÇÃO CIENTÍFICA
        Projeto projetoFerramentaObjetoAprendizagem = new Projeto();
        projetoFerramentaObjetoAprendizagem.setTitulo("Projeto de Desenvolvimento de uma Ferramenta para Gerenciamento do Processo de Desenvolvimento de Objetos de Aprendizagem.");
        projetoFerramentaObjetoAprendizagem.setDataInicio(new java.util.Date());
        projetoFerramentaObjetoAprendizagem.setDescricao("O projeto consiste em um projeto de Iniciação Científica.");
        projetoFerramentaObjetoAprendizagem.setCoordenador(usuarioAdilsonVahldick);
        
        //INICIO DA INCLUSAO DO PROJETO SOL MAIOR
        Projeto projetoSolMaior = new Projeto();
        projetoSolMaior.setTitulo("Projeto Sol Maior.");
        projetoSolMaior.setDataInicio(new java.util.Date());
        projetoSolMaior.setDescricao("O projeto consiste em um projeto de extensão que pretende realizar eventos musicais na UDESC Ibirama e no município com o intuito de aproximar os acadêmicos e a comunidade da música enquanto modo de expressão e arte.");
        projetoSolMaior.setCoordenador(usuarioPabloSchoeffel);
        
        //INICIO DA INCLUSAO DO PROJETO INCLUSÃO DIGITAL PARA IDOSOS
        Projeto projetoInclusaoDigitalParaIdosos = new Projeto();
        projetoInclusaoDigitalParaIdosos.setTitulo("Projeto de Inclusão Digital para Idosos.");
        projetoInclusaoDigitalParaIdosos.setDataInicio(new java.util.Date());
        projetoInclusaoDigitalParaIdosos.setDescricao("O projeto consiste em um projeto de extensão que pretende realizar aulas de informática básica para idosos da comunidade de Ibirama e região.");
        projetoInclusaoDigitalParaIdosos.setCoordenador(usuarioGeraldoVarela);
        
        //INICIO DA INCLUSAO DO PROJETO INCLUSÂO DIGITAL PARA ADOLESCENTES
        Projeto projetoInclusaoDigitalParaAdolescentes = new Projeto();
        projetoInclusaoDigitalParaAdolescentes.setTitulo("Projeto de Inclusão Digital para Adolescentes.");
        projetoInclusaoDigitalParaAdolescentes.setDataInicio(new java.util.Date());
        projetoInclusaoDigitalParaAdolescentes.setDescricao("O projeto consiste em um projeto de extensão que pretende realizar aulas de informática básica para idosos da comunidade de Ibirama e região.");
        projetoInclusaoDigitalParaAdolescentes.setCoordenador(usuarioGeraldoVarela);
        
        //INICIO DA INCLUSAO DO PROJETO APRIMORAR MECANISMOS PARA COORDENACAO EM SISTEMAS MULTIAGENTE ATRAVÉS DO USO DE METÁFORAS DA INTELIÊNCIA DE ENXAMES
        Projeto projetoAprimorarMecanismosParaCoordenacao = new Projeto();
        projetoAprimorarMecanismosParaCoordenacao.setTitulo("Projeto Aprimorar Mecanismos para Coordenação em sistemas Multiagente Através do uso de Metáforas da Inteligência de Enxames.");
        projetoAprimorarMecanismosParaCoordenacao.setDataInicio(new java.util.Date());
        projetoAprimorarMecanismosParaCoordenacao.setDescricao("O projeto consiste em um projeto de Iniciação Científica.");
        projetoAprimorarMecanismosParaCoordenacao.setCoordenador(usuarioFernandoSantos);
        
        //INICIO DA INCLUSAO DO PROJETO MAPEAMENTO DE EMPRESAS DE DESENVOLVIMENTO DE SOFTWARE DO ALTO VALE DO ITAJAÍ
        Projeto projetoMapementoDeEmpresas = new Projeto();
        projetoMapementoDeEmpresas.setTitulo("Projeto Mapeamento de Empresas de Desenvolvimento de Software do Alto Vale do Itajaí.");
        projetoMapementoDeEmpresas.setDataInicio(new java.util.Date());
        projetoMapementoDeEmpresas.setDescricao("O projeto consiste em um projeto de Iniciação Científica.");
        projetoMapementoDeEmpresas.setCoordenador(usuarioPabloSchoeffel);
        
        //MEMBROS PROJETO INICIACAO CIENTIFICA
        MembroEquipe membro1 = new MembroEquipe();
        membro1.setUsuario(usuarioClaudioSouza);
        membro1.setPapelEnum(PapelEnum.AVALIADOR);
        membro1.setProjeto(projetoFerramentaObjetoAprendizagem);

        MembroEquipe membro2 = new MembroEquipe();
        membro2.setUsuario(usuarioFabioCorrea);
        membro2.setPapelEnum(PapelEnum.DESIGNER);
        membro2.setProjeto(projetoFerramentaObjetoAprendizagem);
        
        MembroEquipe membro3 = new MembroEquipe();
        membro3.setUsuario(usuarioPedroIsolani);
        membro3.setPapelEnum(PapelEnum.PROGRAMADOR);
        membro3.setProjeto(projetoFerramentaObjetoAprendizagem);
        
        MembroEquipe membro4 = new MembroEquipe();
        membro4.setUsuario(usuarioTulioMaravilha);
        membro4.setPapelEnum(PapelEnum.PROFESSOR_CONTEUDISTA);
        membro4.setProjeto(projetoFerramentaObjetoAprendizagem);

        List<MembroEquipe> membrosProjetoFerramentaObjetoAprendizagem = new ArrayList<MembroEquipe>();
        membrosProjetoFerramentaObjetoAprendizagem.add(membro1);
        membrosProjetoFerramentaObjetoAprendizagem.add(membro2);
        membrosProjetoFerramentaObjetoAprendizagem.add(membro3);
        membrosProjetoFerramentaObjetoAprendizagem.add(membro4);
        
        projetoFerramentaObjetoAprendizagem.setEquipe(membrosProjetoFerramentaObjetoAprendizagem);
        
        //MEMBROS PROJETO SOL MAIOR
        MembroEquipe membro5 = new MembroEquipe();
        membro5.setUsuario(usuarioDanielLucas);
        membro5.setPapelEnum(PapelEnum.DESIGNER);
        membro5.setProjeto(projetoSolMaior);
        
        MembroEquipe membro6 = new MembroEquipe();
        membro6.setUsuario(usuarioAndersonFreitas);
        membro6.setPapelEnum(PapelEnum.PROGRAMADOR);
        membro6.setProjeto(projetoSolMaior);
        
        MembroEquipe membro7 = new MembroEquipe();
        membro7.setUsuario(usuarioJoaoPaulo);
        membro7.setPapelEnum(PapelEnum.PROFESSOR_CONTEUDISTA);
        membro7.setProjeto(projetoSolMaior);        

        List<MembroEquipe> membrosProjetoSolMaior = new ArrayList<MembroEquipe>();
        membrosProjetoSolMaior.add(membro5);
        membrosProjetoSolMaior.add(membro6);
        membrosProjetoSolMaior.add(membro7);
        
        projetoSolMaior.setEquipe(membrosProjetoSolMaior);
        
        //MEMBROS PROJETO INCLUSAO DIGITAL PARA IDOSOS
        MembroEquipe membro8 = new MembroEquipe();
        membro8.setUsuario(usuarioDaniloSimoni);
        membro8.setPapelEnum(PapelEnum.PROFESSOR_CONTEUDISTA);
        membro8.setProjeto(projetoInclusaoDigitalParaIdosos);
        
        MembroEquipe membro9 = new MembroEquipe();
        membro9.setUsuario(usuarioJuliaPires);
        membro9.setPapelEnum(PapelEnum.AVALIADOR);
        membro9.setProjeto(projetoInclusaoDigitalParaIdosos);        

        List<MembroEquipe> membrosProjetoInclusaoDigitalParaIdosos = new ArrayList<MembroEquipe>();
        membrosProjetoInclusaoDigitalParaIdosos.add(membro8);
        membrosProjetoInclusaoDigitalParaIdosos.add(membro9);           
        
        projetoInclusaoDigitalParaIdosos.setEquipe(membrosProjetoInclusaoDigitalParaIdosos);
        
        //MEMBROS PROJETO INCLUSAO DIGITAL PARA ADOLESCENTES
        MembroEquipe membro10 = new MembroEquipe();
        membro10.setUsuario(usuarioDaniloSimoni);
        membro10.setPapelEnum(PapelEnum.PROFESSOR_CONTEUDISTA);
        membro10.setProjeto(projetoInclusaoDigitalParaAdolescentes);
        
        MembroEquipe membro11 = new MembroEquipe();
        membro11.setUsuario(usuarioGabrielaFigueredo);
        membro11.setPapelEnum(PapelEnum.DESIGNER);
        membro11.setProjeto(projetoInclusaoDigitalParaAdolescentes);        

        List<MembroEquipe> membrosProjetoInclusaoDigitalParaAdolescentes = new ArrayList<MembroEquipe>();
        membrosProjetoInclusaoDigitalParaAdolescentes.add(membro10);
        membrosProjetoInclusaoDigitalParaAdolescentes.add(membro11);   
        
        projetoInclusaoDigitalParaAdolescentes.setEquipe(membrosProjetoInclusaoDigitalParaAdolescentes);
        
        //MEMBROS PROJETO APRIMORAR MECANISMOS PARA COORDENACAO
        MembroEquipe membro12 = new MembroEquipe();
        membro12.setUsuario(usuarioRafaelDeLima);
        membro12.setPapelEnum(PapelEnum.DESIGNER);
        membro12.setProjeto(projetoAprimorarMecanismosParaCoordenacao);
        
        MembroEquipe membro13 = new MembroEquipe();
        membro13.setUsuario(usuarioGabrielJacobsen);
        membro13.setPapelEnum(PapelEnum.PROGRAMADOR);
        membro13.setProjeto(projetoAprimorarMecanismosParaCoordenacao);
        
        MembroEquipe membro14 = new MembroEquipe();
        membro14.setUsuario(usuarioRomarioSilva);
        membro14.setPapelEnum(PapelEnum.PROFESSOR_CONTEUDISTA);
        membro14.setProjeto(projetoAprimorarMecanismosParaCoordenacao);

        List<MembroEquipe> membrosProjetoAprimorarMecanismosParaCoordenacao = new ArrayList<MembroEquipe>();
        membrosProjetoAprimorarMecanismosParaCoordenacao.add(membro12);
        membrosProjetoAprimorarMecanismosParaCoordenacao.add(membro13);
        membrosProjetoAprimorarMecanismosParaCoordenacao.add(membro14);
        
        projetoAprimorarMecanismosParaCoordenacao.setEquipe(membrosProjetoAprimorarMecanismosParaCoordenacao);
        
        //MEMBROS PROJETO MAPEAMENTO DE EMPRESAS
        MembroEquipe membro15 = new MembroEquipe();
        membro15.setUsuario(usuarioCharlesFerrari);
        membro15.setPapelEnum(PapelEnum.PROGRAMADOR);
        membro15.setProjeto(projetoMapementoDeEmpresas);
        
        MembroEquipe membro16 = new MembroEquipe();
        membro16.setUsuario(usuarioJoaoPaulo);
        membro16.setPapelEnum(PapelEnum.DESIGNER);
        membro16.setProjeto(projetoMapementoDeEmpresas);
        
        MembroEquipe membro17 = new MembroEquipe();
        membro17.setUsuario(usuarioFabioCorrea);
        membro17.setPapelEnum(PapelEnum.PROFESSOR_CONTEUDISTA);
        membro17.setProjeto(projetoMapementoDeEmpresas);

        List<MembroEquipe> membrosProjetoMapeamentoDeEmpresas = new ArrayList<MembroEquipe>();
        membrosProjetoMapeamentoDeEmpresas.add(membro15);
        membrosProjetoMapeamentoDeEmpresas.add(membro16);
        membrosProjetoMapeamentoDeEmpresas.add(membro17);
        
        projetoMapementoDeEmpresas.setEquipe(membrosProjetoMapeamentoDeEmpresas);
        
        //OBJETIVOS PROJETO FERRAMENTA OBJETO APRENDIZAGEM
        List<Objetivo> objetivos1 = new ArrayList<Objetivo>();                                

        //CONTEUDOS PROJETO FERRAMENTA OBJETO APRENDIZAGEM
        List<Conteudo> conteudos1 = new ArrayList<Conteudo>();                
        
        
        //UNIDADES PROJETO FERRAMENTA OBJETO APRENDIZAGEM
        Unidade unidade1 = new Unidade();
        unidade1.setNome("Gerenciamento do projeto Loman");
        unidade1.setDescricao("Todo o projeto deve ser implementado");
        unidade1.setProjeto(projetoFerramentaObjetoAprendizagem);
        unidade1.setDuracao(75);
        unidade1.setObjetivos(objetivos1);
        unidade1.setConteudos(conteudos1); 
        
        Conteudo conteudo1 = new Conteudo();
        conteudo1.setDescricao("Biblioteca Primefaces 3.0");
        conteudo1.setUnidade(unidade1);
        
        Conteudo conteudo2 = new Conteudo();
        conteudo2.setDescricao("Servidor Apache Tomcat 7");
        conteudo2.setUnidade(unidade1);
        
        Conteudo conteudo3 = new Conteudo();
        conteudo3.setDescricao("Hibernate JPA");
        conteudo3.setUnidade(unidade1);
        
        Conteudo conteudo4 = new Conteudo();
        conteudo4.setDescricao("Linguagem Java");
        conteudo4.setUnidade(unidade1);
        
        conteudos1.add(conteudo1);
        conteudos1.add(conteudo2);
        conteudos1.add(conteudo3);
        conteudos1.add(conteudo4);
                        
        Objetivo objetivo1 = new Objetivo();        
        objetivo1.setDescricao("Concluir projeto até fim do ano de 2011");
        objetivo1.setUnidade(unidade1);
        
        Objetivo objetivo2 = new Objetivo();        
        objetivo2.setDescricao("Constuir ferramenta eficaz");
        objetivo2.setUnidade(unidade1);
        
        Objetivo objetivo3 = new Objetivo();        
        objetivo3.setDescricao("Testar implementações");
        objetivo3.setUnidade(unidade1);
        
        objetivos1.add(objetivo1);
        objetivos1.add(objetivo2);
        objetivos1.add(objetivo3);               
        
        Roteiro roteiro1 = new Roteiro();        
        unidade1.setRoteiro(roteiro1);
        roteiro1.setUnidade(unidade1);
        
        Unidade unidade2 = new Unidade();
        unidade2.setNome("Gerenciamento de Custos");
        unidade2.setDescricao("Deve ser especificado todos os gastos do projeto.");        
        unidade2.setProjeto(projetoFerramentaObjetoAprendizagem);
        unidade2.setDuracao(360);                
        
        Roteiro roteiro2 = new Roteiro();
        roteiro2.setUnidade(unidade2);
        unidade2.setRoteiro(roteiro2);
        
        Unidade unidade3 = new Unidade();
        unidade3.setNome("Gerenciamento de Recursos");
        unidade3.setDescricao("Deve ser especificado todos os recursos do projeto.");        
        unidade3.setProjeto(projetoFerramentaObjetoAprendizagem);
        unidade3.setDuracao(360);                
        
        Roteiro roteiro3 = new Roteiro();
        roteiro3.setUnidade(unidade3);
        unidade3.setRoteiro(roteiro3);
        
        Unidade unidade4 = new Unidade();
        unidade4.setNome("Gerenciamento de Cronograma");
        unidade4.setDescricao("Deve ser especificado todas as atividades e datas do projeto.");        
        unidade4.setProjeto(projetoFerramentaObjetoAprendizagem);
        unidade4.setDuracao(360);                
        
        Roteiro roteiro4 = new Roteiro();
        roteiro4.setUnidade(unidade4);
        unidade4.setRoteiro(roteiro4); 
        
        //UNIDADES PROJETO SOL MAIOR
        Unidade unidade5 = new Unidade();
        unidade5.setNome("Gerenciamento do Projeto Sol Maior");
        unidade5.setDescricao("Todo o projeto deve ser implementado");        
        unidade5.setProjeto(projetoSolMaior);
        unidade5.setDuracao(75);                                
        
        Roteiro roteiro5 = new Roteiro();        
        unidade5.setRoteiro(roteiro5);
        roteiro5.setUnidade(unidade5);
        
        Unidade unidade6 = new Unidade();
        unidade6.setNome("Gerenciamento de Instrumentos Musicais");
        unidade6.setDescricao("Deve ser especificado todos os instrumentos musicais do projeto.");        
        unidade6.setProjeto(projetoSolMaior);
        unidade6.setDuracao(360);                
        
        Roteiro roteiro6 = new Roteiro();
        roteiro6.setUnidade(unidade6);
        unidade6.setRoteiro(roteiro6);
        
        Unidade unidade7 = new Unidade();
        unidade7.setNome("Gerenciamento de Recursos");
        unidade7.setDescricao("Deve ser especificado todos os recursos projeto.");        
        unidade7.setProjeto(projetoSolMaior);
        unidade7.setDuracao(360);                
        
        Roteiro roteiro7 = new Roteiro();
        roteiro7.setUnidade(unidade7);
        unidade7.setRoteiro(roteiro7);
        
        Unidade unidade8 = new Unidade();
        unidade8.setNome("Gerenciamento de Apresentações");
        unidade8.setDescricao("Deve ser especificado todas as apresentações musicais.");        
        unidade8.setProjeto(projetoSolMaior);
        unidade8.setDuracao(360);                
        
        Roteiro roteiro8 = new Roteiro();
        roteiro8.setUnidade(unidade8);
        unidade8.setRoteiro(roteiro8);
        
        //UNIDADES PROJETO INCLUSAO DIGITAL PARA IDOSOS
        Unidade unidade9 = new Unidade();
        unidade9.setNome("Gerenciamento de Alunos");
        unidade9.setDescricao("Deve ser alocado alunos nos laboratórios.");        
        unidade9.setProjeto(projetoInclusaoDigitalParaIdosos);
        unidade9.setDuracao(75);                     
        
        Roteiro roteiro9 = new Roteiro();        
        unidade9.setRoteiro(roteiro9);
        roteiro9.setUnidade(unidade9);
        
        //UNIDADES PROJETO INCLUSAO DIGITAL PARA ADOLESCENTES
        Unidade unidade10 = new Unidade();
        unidade10.setNome("Gerenciamento de Professores");
        unidade10.setDescricao("Deve ser alocado alunos nos laboratórios.");        
        unidade10.setProjeto(projetoInclusaoDigitalParaAdolescentes);
        unidade10.setDuracao(75);                    
        
        Roteiro roteiro10 = new Roteiro();        
        unidade10.setRoteiro(roteiro10);
        roteiro10.setUnidade(unidade10);
        
        //UNIDADES PROJETO APRIMORAR MECANISMOS PARA COORDENACAO        
        Unidade unidade11 = new Unidade();
        unidade11.setNome("Gerenciamento do Mecanismos para coordenação.");
        unidade11.setDescricao("Deve ser realizada uma pesquisa sobre os mecanismos para coordenação.");        
        unidade11.setProjeto(projetoAprimorarMecanismosParaCoordenacao);
        unidade11.setDuracao(75);                      
        
        Roteiro roteiro11 = new Roteiro();        
        unidade11.setRoteiro(roteiro11);
        roteiro11.setUnidade(unidade11);
        
        //UNIDADES PROJETO MAPEMENTO DE EMPRESAS
        Unidade unidade12 = new Unidade();
        unidade12.setNome("Gerenciamento de empresas do Alto Vale do Itajaí");
        unidade12.setDescricao("Deve ser uma pesquisas com as empresas de software do Alto Vale do Itajaí");        
        unidade12.setProjeto(projetoMapementoDeEmpresas);
        unidade12.setDuracao(75);                   
        
        Roteiro roteiro12 = new Roteiro();        
        unidade12.setRoteiro(roteiro12);
        roteiro12.setUnidade(unidade12);                                                            
        
        //TAREFAS UNIDADE GERENCIAMENTO DO PROJETO LOMAN 
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Imagem ilustrativa");
        tarefa1.setDescricao("A imagem pode ser em qualquer formato");
        tarefa1.setDataEntrega(new java.util.Date());
        tarefa1.setPapelEnum(PapelEnum.DESIGNER);
        tarefa1.setStatus(Status.ABERTA);                                                 
        
        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Ação de botão calcular resultado");
        tarefa2.setDescricao("Deverá ser implementada um botão que calcule o resultado para o problema proposto no slide.");
        tarefa2.setDataEntrega(new java.util.Date());
        tarefa2.setPapelEnum(PapelEnum.PROGRAMADOR);
        tarefa2.setStatus(Status.ABERTA);                       
        
        Tarefa tarefa3 = new Tarefa();
        tarefa3.setTitulo("Tabela ilustrativa");
        tarefa3.setDescricao("Deverá ser ser construída uma tabela com os dados informados no slide");
        tarefa3.setDataEntrega(new java.util.Date());
        tarefa3.setPapelEnum(PapelEnum.DESIGNER);
        tarefa3.setStatus(Status.ABERTA);                                        
        
        Tarefa tarefa4 = new Tarefa();
        tarefa4.setTitulo("Criação do diagrama de classes");
        tarefa4.setDescricao("Deve ser criado um diagrama de classes dos sistema.");
        tarefa4.setDataEntrega(new java.util.Date());
        tarefa4.setPapelEnum(PapelEnum.PROGRAMADOR);
        tarefa4.setStatus(Status.ABERTA);        
        
        //TAREFA CRIAÇÃO DE JANELA INVISÍVEL
        Tarefa tarefa5 = new Tarefa();
        tarefa5.setTitulo("Janela invisível");
        tarefa5.setDescricao("Deve existir uma janela invisível.");
        tarefa5.setDataEntrega(new java.util.Date());
        tarefa5.setPapelEnum(PapelEnum.PROGRAMADOR);
        tarefa5.setStatus(Status.ABERTA);                     
        
        //SLIDES UNIDADE GERENCIAMENTO DE PROJETO LOMAN    
        List<Tarefa> tarefasSlide1 = new ArrayList<Tarefa>();
        List<Tarefa> tarefasSlide2 = new ArrayList<Tarefa>();        
        
        Slide slide1 = new Slide();
        slide1.setTitulo("TITULO SLIDE");
        slide1.setDescricao("DESCRICAO SLIDE");
        slide1.setRoteiro(roteiro1);
        tarefasSlide1.add(tarefa1);
        tarefasSlide1.add(tarefa2);
        tarefasSlide1.add(tarefa3);
        slide1.setTarefas(tarefasSlide1);        
        
        Slide slide2 = new Slide();
        slide2.setTitulo("Subtítulo1");
        slide2.setDescricao("Descrição do slide...");
        slide2.setRoteiro(roteiro1);
        tarefasSlide2.add(tarefa4);
        tarefasSlide2.add(tarefa5);
        slide2.setTarefas(tarefasSlide2);        
        
        Slide slide3 = new Slide();
        slide3.setTitulo("Subtítulo2");
        slide3.setDescricao("Descrição do slide...");
        slide3.setRoteiro(roteiro1);
                
        Slide slide4 = new Slide();
        slide4.setTitulo("Subtítulo4");
        slide4.setDescricao("Descrição do slide...");
        slide4.setRoteiro(roteiro1);
        
        Slide slide5 = new Slide();
        slide5.setTitulo("Subtítulo5");
        slide5.setDescricao("Descrição do slide...");
        slide5.setRoteiro(roteiro1);
        
        Slide slide6 = new Slide();
        slide6.setTitulo("Subtítulo6");
        slide6.setDescricao("Descrição do slide...");
        slide6.setRoteiro(roteiro1);
        
        Slide slide7 = new Slide();
        slide7.setTitulo("Subtítulo7");
        slide7.setDescricao("Descrição do slide...");
        slide7.setRoteiro(roteiro1);
        
        Slide slide8 = new Slide();
        slide8.setTitulo("Subtítulo8");
        slide8.setDescricao("Descrição do slide...");
        slide8.setRoteiro(roteiro1);
        
        Slide slide9 = new Slide();
        slide9.setTitulo("Subtítulo9");
        slide9.setDescricao("Descrição do slide...");
        slide9.setRoteiro(roteiro1);       
        
        tarefa1.setSlide(slide1);
        tarefa1.setRoteiro(slide1.getRoteiro());
        
        tarefa2.setSlide(slide2);
        tarefa2.setRoteiro(slide2.getRoteiro());
        
        tarefa3.setSlide(slide3);
        tarefa3.setRoteiro(slide3.getRoteiro());
        
        tarefa4.setSlide(slide4);
        tarefa4.setRoteiro(slide4.getRoteiro());
        
        tarefa5.setSlide(slide5);
        tarefa5.setRoteiro(slide5.getRoteiro());
        
        List<Tarefa> tarefas1 = new ArrayList<Tarefa>();        
        tarefas1.add(tarefa1);
        tarefas1.add(tarefa2);
        tarefas1.add(tarefa3);
        tarefas1.add(tarefa4);
        tarefas1.add(tarefa5);
        
        List<Slide> slides1 = new ArrayList<Slide>();
        slides1.add(slide1);
        slides1.add(slide2);
        slides1.add(slide3);
        slides1.add(slide4);
        slides1.add(slide5);
        slides1.add(slide6);
        slides1.add(slide7);
        slides1.add(slide8);
        slides1.add(slide9);
                
        //ROTEIRO GERENCIAMENTO DO PROJETO LOMAN        
        roteiro1.setTarefas(tarefas1);
        roteiro1.setSlides(slides1);                                                      
        unidade1.setRoteiro(roteiro1);     
        roteiro1.setUnidade(unidade1);
        
        List<Unidade> unidades1 = new ArrayList<Unidade>();
        unidades1.add(unidade1);
        unidades1.add(unidade2);
        unidades1.add(unidade3);
        unidades1.add(unidade4);
                
        projetoFerramentaObjetoAprendizagem.setUnidades(unidades1);
        
        List<Unidade> unidades2 = new ArrayList<Unidade>();
        unidades2.add(unidade5);
        unidades2.add(unidade6);
        unidades2.add(unidade7);
        unidades2.add(unidade8);
        
        projetoSolMaior.setUnidades(unidades2);
        
        List<Unidade> unidades3 = new ArrayList<Unidade>();
        unidades3.add(unidade9);
        
        projetoInclusaoDigitalParaIdosos.setUnidades(unidades3);
        
        List<Unidade> unidades4 = new ArrayList<Unidade>();
        unidades4.add(unidade10);
        
        projetoInclusaoDigitalParaAdolescentes.setUnidades(unidades4);
        
        List<Unidade> unidades5 = new ArrayList<Unidade>();
        unidades5.add(unidade11);
        
        projetoAprimorarMecanismosParaCoordenacao.setUnidades(unidades5);
        
        List<Unidade> unidades6 = new ArrayList<Unidade>();
        unidades6.add(unidade12);
        
        projetoMapementoDeEmpresas.setUnidades(unidades6);
        
        System.out.println("Titulo projeto " + projetoFerramentaObjetoAprendizagem.getUnidades().toString());
        
        cProjetos.salvar(projetoFerramentaObjetoAprendizagem);
        System.out.println("fim do cadastro do PROJETO FERRAMENTA OBJETO APRENDIZAGEM!");
        //FIM DA INCLUSAO DO PROJETO FERRAMENTA OBJETO APRENDIZAGEM
        
        cProjetos.salvar(projetoAprimorarMecanismosParaCoordenacao);
        System.out.println("fim do cadastro do PROJETO APRIMORAR MECANISMOS PARA COORDENACAO!");
        cProjetos.salvar(projetoInclusaoDigitalParaAdolescentes);
        System.out.println("fim do cadastro do PROJETO INCLUSAO DIGITAL PARA ADOLESCENTES!");
        cProjetos.salvar(projetoInclusaoDigitalParaIdosos);
        System.out.println("fim do cadastro do PROJETO INCLUSAO DIGIITAL PARA IDOSOS!");
        cProjetos.salvar(projetoMapementoDeEmpresas);
        System.out.println("fim do cadastro do PROJETO MAPEAMENTO DE EMPRESAS!");
        cProjetos.salvar(projetoSolMaior);
        System.out.println("fim do cadastro do PROJETO SOL MAIOR!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Popular Tabela");

        jButton1.setFont(new java.awt.Font("Arial", 1, 22));
        jButton1.setText("Popular Tabela");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            criarBD();
            adicionarRegistros();
            JOptionPane.showMessageDialog(null, "Acabou");

        } catch (Exception ex) {
            Logger.getLogger(PopularTabela.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    e.getMessage();
                }
                try {
                    new PopularTabela().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(PopularTabela.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
