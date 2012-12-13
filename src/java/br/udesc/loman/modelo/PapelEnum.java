package br.udesc.loman.modelo;

public enum PapelEnum {

    AVALIADOR("Avaliador"),
    DESIGNER("Designer"),
    PROGRAMADOR("Programador"),
    PROFESSOR_CONTEUDISTA("Professor Conteudista"),
    COORDENADOR("Coordenador");
    
    private String nome;

    private PapelEnum(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}