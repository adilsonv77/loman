package br.udesc.loman.data.model;

import br.udesc.loman.modelo.Unidade;
import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel;  
  
@SuppressWarnings("unchecked")
public class UnidadeDataModel extends ListDataModel<Unidade> implements SelectableDataModel<Unidade> {    
  
    public UnidadeDataModel() {  
    }  
  
    public UnidadeDataModel(List<Unidade> data) {  
        super(data);  
    }  
      
    @Override  
    public Unidade getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Unidade> unidades = (List<Unidade>) getWrappedData();  
          
        for(Unidade unidade : unidades) {  
            if(unidade.getNome().equals(rowKey))  
                return unidade;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Unidade unidade) {  
        return unidade.getNome();  
    }
}  
