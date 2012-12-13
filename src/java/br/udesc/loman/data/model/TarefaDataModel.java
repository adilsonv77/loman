package br.udesc.loman.data.model;

import br.udesc.loman.modelo.Tarefa;
import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel;  
  
@SuppressWarnings("unchecked")
public class TarefaDataModel extends ListDataModel<Tarefa> implements SelectableDataModel<Tarefa> {    
  
    public TarefaDataModel() {  
    }  
  
    public TarefaDataModel(List<Tarefa> data) {  
        super(data);  
    }  
      
    @Override  
    public Tarefa getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Tarefa> tarefas = (List<Tarefa>) getWrappedData();  
          
        for(Tarefa tarefa : tarefas) {  
            if(tarefa.getTitulo().equals(rowKey))  
                return tarefa;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Tarefa tarefa) {  
        return tarefa.getTitulo();  
    }  
}  
