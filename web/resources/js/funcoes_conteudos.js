function adicionarConteudos(){
    for (var i=0; i<this.listaConteudosId.length; i++){        
        this.adicionarNodo(this.listaConteudosId[i], this.listaConteudosDescricao[i],1,'rect');
    }
}

function adicionarConteudo(idNodo, descricaoNodo) {    
    mapaConceitual.adicionarConteudo(idNodo, descricaoNodo);  
    adicionarNodo(idNodo, descricaoNodo, 1, 'rect');
    network.addNodes(nodesTable);
    network.nodesTable = links.Network.tableToArray(nodesTable);     
    network.redraw();
    dialogConteudos.hide();    
}

function excluirConteudoComRelacao(){
    mapaConceitual.excluirConteudoComRelacao(nodesTable.D[sel[0].row].c[3].v);
}

function excluirConteudoSemRelacao(){
    mapaConceitual.excluirConteudoSemRelacao(nodesTable.D[sel[0].row].c[3].v);
}  