package br.udesc.loman.web.converters;

import br.udesc.loman.modelo.Objetivo;
import br.udesc.loman.web.LoManListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Objetivo.class, value="objetivoConverter")
public class ObjetivoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int index = Integer.parseInt(value);
            return LoManListener.getDAOFactory().getObjetivoDAO().ler(index);
        } catch (Exception ex) {
            Logger.getLogger(ObjetivoConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Objetivo o = (Objetivo)value;
        return "" + o.getId();
    }

}
