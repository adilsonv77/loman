package br.udesc.loman.web.converters;

import br.udesc.loman.modelo.Usuario;
import br.udesc.loman.web.LoManListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int index = Integer.parseInt(value);
            return LoManListener.getDAOFactory().getUsuarioDAO().ler(index);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Usuario d = (Usuario)value;
        return "" + d.getId();
    }

}
