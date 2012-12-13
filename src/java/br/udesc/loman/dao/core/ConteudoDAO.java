package br.udesc.loman.dao.core;

import br.udesc.dao.core.DAO;
import br.udesc.loman.modelo.Conteudo;
import br.udesc.loman.modelo.Relacao;
import br.udesc.loman.modelo.Unidade;
import java.util.List;

public interface ConteudoDAO extends DAO<Conteudo> {

    List<Conteudo> buscaConteudosPorUnidade(Unidade unidade);
    
    List<Conteudo> buscarConteudosMapa(Unidade unidade);
    
    List<Conteudo> buscarConteudosDisponiveis(Unidade unidade);
    
    List<Conteudo> buscarConteudosRelacao(Relacao relacao);
    
    Conteudo buscaConteudoPorChave(long id);
}
