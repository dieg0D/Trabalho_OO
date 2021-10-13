
public class Despesa {

    // Atributos da Classe Despesa
    private Double valor;
    private String descricao;
    private Categoria categoria;

    // Método Construtor
    public Despesa(Double valor, String descricao, Categoria categoria) { 
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    // Getters
    public Double getValor(){ return valor; }

    public String getDescricao(){ return descricao; }

    public Categoria getCategoria() { return categoria; }

    // Setters
    public void setValor(Double valor) { this.valor = valor; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

}




