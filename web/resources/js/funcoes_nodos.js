function adicionarNodo(idNodo, descricaoNodo, tipoNodo, estiloNodo) {     
    nodesTable.addRow([id, estiloNodo, descricaoNodo, idNodo, tipoNodo]);    
    id++;
}
      
function onselect() {                   
    this.sel = network.getSelection();    
    habilitarDesabilitarBotoes();
    if (this.sel.length == 0){                        
        this.sel = undefined;                  
    } else {              
        if (this.origem != null){            
            //Se o nodo for conteúdo
            if (nodesTable.D[this.sel[0].row].c[4].v == 1){                
                mapaConceitual.adicionarRelacao(nodesTable.D[this.sel[0].row].c[3].v, nodesTable.D[this.origem[0].row].c[3].v);                
            } else {
                mapaConceitual.adicionarAresta(nodesTable.D[this.sel[0].row].c[3].v, nodesTable.D[this.origem[0].row].c[3].v);
            }   
            draw();
            origem = null;
        }                
    }
}

function editarNodo(){
    if (this.sel != undefined){
        //Se o nodo for Conteúdo
        if (nodesTable.D[sel[0].row].c[4].v == 2){
            //Se o nodo for Relação
            mapaConceitual.setarRelacao(nodesTable.D[sel[0].row].c[3].v, nodesTable.D[sel[0].row].c[2].v);
            dialogEditarRelacao.show();                    
            return true;
        } else {
            return false;
        }
    } else {
        alert('Nennhum nodo selecionado!');
        return false;
    }
}

function excluirNodo(){
    //Se tiver nodo selecionado
    if (this.sel != undefined){
        //Se o nodo for conteúdo
        if (nodesTable.D[sel[0].row].c[4].v == 1){
            var cont = 0;           
            for (var i=0; i<listaArestaConteudoId.length; i++){
                if (nodesTable.D[sel[0].row].c[3].v == listaArestaConteudoId[i]){
                    cont++;
                }
            }
            if (cont > 0){
                dialogConfirmDeleteConteudoComRelacao.show();
            } else{
                dialogConfirmDeleteConteudoSemRelacao.show();
            }
        } else {
            //Se o nodo for relação            
            mapaConceitual.setarRelacao(nodesTable.D[sel[0].row].c[3].v, nodesTable.D[sel[0].row].c[2].v);
            dialogConfirmDeleteRelacao.show();
        }
    }           
}    