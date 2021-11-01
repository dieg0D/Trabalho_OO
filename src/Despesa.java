import javax.swing.*;
import java.util.Objects;
public class Despesa {

    // Atributos da Classe Despesa
    private Double valor;
    private String descricao;
    private Categoria categoria;
    private String subcategoria;

    // Método Construtor
    public Despesa(Double valor, String descricao, Categoria categoria, String subcategoria) { 
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }

    // Getters
    public Double getValor(){ return valor; }

    public String getDescricao(){ return descricao; }

    public Categoria getCategoria() { return categoria; }

    public String getSubcategoria() { return categoria.getSubcategorias(); }

    // Setters
    public void setValor(Double valor) { this.valor = valor; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
        this.categoria.setSubcategorias(subcategoria);
    }


    public static String checarDescricaoValida(String descricao) {
        boolean flag = true;
        do {
            try {
                ExceptionHandler.checarDescricaoDespesaEmBranco(descricao);
                flag = false;
            } catch (DescricaoNaoInformadaException e) {
                JOptionPane.showMessageDialog(null, e);
                descricao = JOptionPane.showInputDialog("Informe o valor da Despesa: R$ ");
            }
        } while (flag);
        return descricao;
    }

    public static String checarCategoriaValida(String categoria) {
        boolean flag = true;
        do {
            try {
                ExceptionHandler.checarCategoriaOuSubcategoriaDespesaEmBranco(categoria);
                flag = false;
            } catch (CategoriaNaoInformadaException e) {
                JOptionPane.showMessageDialog(null, e);
                categoria = JOptionPane.showInputDialog("Informe a categoria: ");
            }
        } while (flag);
        return categoria;
    }

    // public static String checarSubcategoriaValida(String subcategoria) {
    //     boolean flag = true;
    //     do {
    //         try {
    //             ExceptionHandler.checarCategoriaOuSubcategoriaDespesaEmBranco(subcategoria);
    //             flag = false;
    //         } catch (CategoriaNaoInformadaException e) {
    //             JOptionPane.showMessageDialog(null, e);
    //             subcategoria = JOptionPane.showInputDialog("Informe a categoria: ");
    //         }
    //     } while (flag);
    //     return subcategoria;
    // }

    public static String checarValorValido(String strValor) {
        boolean flag = true;
        do {
            try {
                ExceptionHandler.checarValorDespesaEmBrancoOuInvalido(strValor);
                flag = false;
            } catch (ValorNaoInformadoException e) {
                JOptionPane.showMessageDialog(null, e);
                strValor = JOptionPane.showInputDialog("Informe o valor da Despesa: R$ ");
            }
        } while (flag);
        return strValor;
    }

}




