function adicionarRelacoes(){    
    for(var i=0; i<this.listaRelacoesId.length; i++){        
        adicionarNodo(this.listaRelacoesId[i], this.listaRelacoesDescricao[i],2,'text');
    }
}

function ligarRelacoes(){    
    var tempCont;
    var tempRel;
    //Percorrendo a lista de arestas
    for (var i=0; i<this.listaArestaConteudoId.length; i++){
        //Percorrendo a tabela de nodos
        for (var j=0; j<nodesTable.D.length; j++){
            //Se TIPO = Conteudo && ID for Igual
            if ((nodesTable.D[j].c[4].v == 1) && (nodesTable.D[j].c[3].v == this.listaArestaConteudoId[i])){
                //ID do nodo DO Conteudo
                tempCont = nodesTable.D[j].c[0].v; 
                //Percorrendo a tabela de nodos
                for (var k=0; k<nodesTable.D.length; k++){
                    //Se TIPO = Relacao && for Igual
                    if ((nodesTable.D[k].c[4].v == 2) && (nodesTable.D[k].c[3].v == this.listaArestaRelacaoId[i])){ 
                        tempRel = nodesTable.D[k].c[0].v;                        
                        linksTable.addRow([tempCont, tempRel, 'black', 2, 120, 'arrow']);                        
                        network.addLinks(linksTable);
                    }
                }
            }
        }
    }                
}

function alterarRelacao() {   
    var descricao = document.getElementById("tabUnidadeSlideTarefa:formMapaConceitual:edt_relacao");    
    mapaConceitual.alterarRelacao(descricao.value);
    draw();
}

function excluirRelacao(){
    var nodoExclusao = sel[0].row;
    this.sel = undefined;
    mapaConceitual.excluirRelacao();
    nodesTable.D.splice(nodoExclusao, 1);
    network.nodesTable.splice(nodoExclusao, 1);
    var node = network.nodes[nodoExclusao];
    network.nodes.splice(nodoExclusao, 1);
    for (var i=network.links.length-1; i >= 0; i--)
        if (network.links[i].from.id == node.id || network.links[i].to.id == node.id)
            network.links.splice(i, 1);    
    network.redraw();
}    