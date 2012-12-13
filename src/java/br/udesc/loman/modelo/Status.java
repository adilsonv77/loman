package br.udesc.loman.modelo;

public enum Status {

    ABERTA("Aberta"),
    COM_PROBLEMAS("Com problemas"),
    EM_ANDAMENTO("Em andamento"),
    PRONTA_PARA_REVISAO("Pronta para revisão"),
    CONCLUIDA("Concluida");
    
    private String nome;

    private Status(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}