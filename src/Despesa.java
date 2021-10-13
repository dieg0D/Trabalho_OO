package Despesa;

public class Despesa {

    // Atributos da Classe Aluno
    private Double valor;
    private String descricao;
    private Categoria categoria;

    // Método Construtor
    private Despesa(Double valor, String descricao, Categoria categoria) { // Método Construtor alternativo
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    private Double getValor(){
        return valor;
    }

    private String getDescricao(){
        return descricao;
    }

    private Categoria getCategoria() { return categoria; }


    private void setValor(Double valor) { this.valor = valor; }

    private void setDescricao(String descricao) { this.descricao = descricao; }

    private void setCategoria(String categoria) { this.categoria = categoria; }

}




