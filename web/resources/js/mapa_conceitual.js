// Método chamado quando a API esta carregada.
function draw() {
    this.id = 0;
    // Opções do Mapa
    options = {
        'width': '820px',
        'height': '450px',
        'stabilize': false
    };
    
    //Desabilitando botões
    habilitarDesabilitarBotoes();
    //**

    // Instanciando o grafico de objetos.
    network = new links.Network(document.getElementById('mynetwork'));
            
    // Identificando as colunas da tabela de nodos            
    nodesTable = new google.visualization.DataTable();
    nodesTable.addColumn('number', 'id');
    nodesTable.addColumn('string', 'style');
    nodesTable.addColumn('string', 'text');
    nodesTable.addColumn('number', 'idbanco');
    nodesTable.addColumn('number', 'tipo');
    
    // Identificando as colunas da tabela de arestas
    linksTable = new google.visualization.DataTable();
    linksTable.addColumn('number', 'from');
    linksTable.addColumn('number', 'to');
    linksTable.addColumn('string', 'color');
    linksTable.addColumn('number', 'width');
    linksTable.addColumn('number', 'length');
    linksTable.addColumn('string', 'style');

    mapaConceitual.init();

    carregaConteudos();
    carregaRelacoes();   
    adicionarConteudos();
    adicionarRelacoes();  
                
    // Adicionando eventos
    google.visualization.events.addListener(network, 'select', onselect);

    // Desenhando o gráfico com as opções
    network.draw(nodesTable, options); 
    
    ligarRelacoes();      
}

function carregaConteudos(){
    mapaConceitual.listarConteudosId({
        callback:function(res) { 
            this.listaConteudosId = res;
        }
    });
    
    mapaConceitual.listarConteudosDescricao({
        callback:function(res) { 
            this.listaConteudosDescricao = res;
        }
    });
}

function carregaRelacoes(){
    mapaConceitual.listarArestasConteudosId({
        callback:function(res) { 
            this.listaArestaConteudoId = res;
        }
    });
    
    mapaConceitual.listarArestasRelacoesId({
        callback:function(res) { 
            this.listaArestaRelacaoId = res;
        }
    });
    
    mapaConceitual.listarRelacoesId({
        callback:function(res) { 
            this.listaRelacoesId = res;
        }
    });
    
    mapaConceitual.listarRelacoesDescricao({
        callback:function(res) { 
            this.listaRelacoesDescricao = res;
        }
    });
}

function relacionar() {         
    if (this.origem == null){
        this.origem = network.getSelection();
        alert('Selecione o nodo de destino');
        habilitarDesabilitarBotoes();
    }                                                                                                                                                                                                            
}

function cancelar(){
    origem = null;       
    habilitarDesabilitarBotoes();
}

function habilitarDesabilitarBotoes(){
    if (this.sel == undefined){
        var relacao = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:btn_relacao");
        relacao.disabled = "disabled";
        var editar = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:btn_editar");
        editar.disabled = "disabled";
        var excluir = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:btn_excluir");
        excluir.disabled = "disabled";
    } else {
        relacao = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:btn_relacao");
        relacao.disabled = "";
        editar = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:btn_editar");
        editar.disabled = "";
        excluir = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:btn_excluir");
        excluir.disabled = "";
    }
}