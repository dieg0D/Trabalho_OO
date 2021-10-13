import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Republica {
    private ArrayList<Aluno> alunos;
    private ArrayList<Despesas> despesas;
    private ArrayList<Categoria> categorias;
    private ArrayList<SubCategoria> subCategoria;

    public Republica(){
        this.alunos = new ArrayList<Aluno>();
        this.despesas = new ArrayList<Despesas>();
        this.categorias = new  ArrayList<Categoria>();
        this.subCategorias = new ArrayList<SubCategoria>();
    }

    public void cadastrarAluno() {
        String nome = JOptionPane.showInputDialog("Informe o nome do Aluno: ");
        String email = JOptionPane.showInputDialog("Informe o e-mail do Aluno: ");
        String strRendimento = JOptionPane.showInputDialog("Informe o rendimento do Aluno: R$ ");
        Double rendimento = Double.parseDouble(strRendimento);

        Aluno novoAluno = new Aluno();

        novoAluno.setNome(nome);
        novoAluno.setEmail(email);
        novoAluno.setRendimento(rendimento);

        alunos.add(novoAluno);
        return;
    }

    public void cadastrarDespesas() {
        String strValor = JOptionPane.showInputDialog("Informe o valor da Despesa: R$ ");
        Double valor = Double.parseDouble(strValor);
        String descricao = JOptionPane.showInputDialog("Informe a descrição da Despesa: ");
        String categoria = JOptionPane.showInputDialog("Informe a categoria: ");

        return;
    }

    public void cadastrarCategoria() {
        String descricao = JOptionPane.showInputDialog("Informe a descrição da Categoria: ");
        return;
    }

    public void cadastrarSubCategoria() {
        String descricao = JOptionPane.showInputDialog("Informe a descrição da SubCategoria: ");

        return;
    }

    public void calcularDespesasIgualitarias() {
        return;
    }

    public void calcularDespesasProporcionais() {
        return;
    }
}