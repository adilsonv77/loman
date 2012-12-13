package br.udesc.loman.data.model;

import br.udesc.loman.modelo.Slide;
import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel;  
  
@SuppressWarnings("unchecked")
public class SlideDataModel extends ListDataModel<Slide> implements SelectableDataModel<Slide> {    
  
    public SlideDataModel() {  
    }  
  
    public SlideDataModel(List<Slide> data) {  
        super(data);  
    }  
      
    @Override  
    public Slide getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Slide> slides = (List<Slide>) getWrappedData();  
          
        for(Slide slide : slides) {  
            if(slide.getTitulo().equals(rowKey))  
                return slide;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Slide slide) {  
        return slide.getTitulo();  
    }  
}  
