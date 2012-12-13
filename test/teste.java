
import java.util.regex.Pattern;

public class teste {

    public static void main(String[] args) {
        String nomeArquivo = new String("Pajd77..sdkjaing.uins.jpg");

        String[] g = nomeArquivo.split(Pattern.quote("."));

        System.out.println(g.length);
        System.out.println(g[0]);
        System.out.println(g[1]);
        System.out.println("%%%");
        nomeArquivo = "";

        for (int i = 0; i < g.length; i++) {
            if (i == g.length - 1) {
                g[i] = "(Pedro)" + "." + g[i];
            }
            nomeArquivo = nomeArquivo + g[i];
        }
        
        System.out.println(g[0]);
        System.out.println(g[1]);
        System.out.println(nomeArquivo);
    }
}
