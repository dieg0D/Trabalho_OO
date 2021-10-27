import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Republica {
    private ArrayList<Aluno> alunos;
    private ArrayList<Despesa> despesas;
    private ArrayList<Categoria> categorias;
    private ArrayList<SubCategoria> subCategorias;

    public Republica() throws IOException {
        try {
            this.alunos = ArquivoHelper.lerAlunos();
        } catch (IOException e) {
            this.alunos = new ArrayList<Aluno>();
        }
        this.despesas = new ArrayList<Despesa>();
        this.categorias = new ArrayList<Categoria>();
        this.subCategorias = new ArrayList<SubCategoria>();
    }

    public void cadastrarAluno() throws IOException {
        String nome = Aluno.checarNomeEmBranco(JOptionPane.showInputDialog("Informe o nome do Aluno: "));
        if (!ArquivoHelper.alunoExiste(nome)) {
            String email = Aluno.checarEmailEmBranco(JOptionPane.showInputDialog("Informe o e-mail do Aluno: "));

            String strRendimento = Aluno.rendimentoInvalido(
                    JOptionPane.showInputDialog("Informe o rendimento do Aluno: R$ ")
            );
            double rendimento = Double.parseDouble(strRendimento);

            Aluno novoAluno = new Aluno(nome, email, rendimento);
            alunos.add(novoAluno);

            ArquivoHelper.salvarAlunos(alunos);
        } else {
            JOptionPane.showMessageDialog(null, "Esse aluno já está cadastrado");
        }
    }

    public void editarAluno() throws IOException, DadosPessoaisIncompletosException, RendimentoInvalidoException {
        String alunoEditar = JOptionPane.showInputDialog("Informe o nome do Aluno que gostaria de editar: ");
        ArrayList<Aluno> alunos_copy = alunos;

        if (ArquivoHelper.alunoExiste(alunoEditar)) {
            alunos = ArquivoHelper.editarCadastroAluno(alunoEditar);
            ArquivoHelper.salvarAlunos(alunos);
            if (ArquivoHelper.alunoRepetido(alunoEditar)) {
                JOptionPane.showMessageDialog(null, "Esse aluno já está cadastrado");
                ArquivoHelper.salvarAlunos(alunos_copy);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Esse aluno não existe");
        }
    }

    public void removerAluno() throws IOException {
        String alunoRemover = JOptionPane.showInputDialog("Informe o nome do Aluno que gostaria de remover: ");

        if (ArquivoHelper.alunoExiste(alunoRemover)) {
            alunos = ArquivoHelper.removerAluno(alunoRemover);
            ArquivoHelper.salvarAlunos(alunos);
        } else {
            JOptionPane.showMessageDialog(null, "Esse aluno não existe");
        }
    }

    public void cadastrarDespesas() {
        String strValor = JOptionPane.showInputDialog("Informe o valor da Despesa: R$ ");
        Double valor = Double.parseDouble(strValor);
        String descricao = JOptionPane.showInputDialog("Informe a descrição da Despesa: ");
        String categoria = JOptionPane.showInputDialog("Informe a categoria: ");

    }

    public void cadastrarCategoria() {
        String descricao = JOptionPane.showInputDialog("Informe a descrição da Categoria: ");
    }

    public void cadastrarSubCategoria() {
        String descricao = JOptionPane.showInputDialog("Informe a descrição da SubCategoria: ");

    }

    public void calcularDespesasIgualitarias() {
        try {
            String mesStr = JOptionPane.showInputDialog("Informe o mes da despesa(1-12): ");
            Integer mes = Integer.parseInt(mesStr);
            String anoStr = JOptionPane.showInputDialog("Informe o ano da despesa: ");
            Integer ano = Integer.parseInt(anoStr);
            CalculoIgualitario calculoIgualitario = new CalculoIgualitario();
            calculoIgualitario.calcular(mes, ano);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ano/mes inserido não é válido");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível achar uma despesa no intervalo de ano/mes dado");
        }

    }

    public void calcularDespesasProporcionais() {
        JOptionPane.showMessageDialog(null, "Resultado: ");
    }
}