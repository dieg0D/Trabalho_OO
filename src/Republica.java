import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Republica {
    private ArrayList<Aluno> alunos;
    private ArrayList<Despesa> despesas;
    private ArrayList<Categoria> categorias;

    public Republica() throws IOException {
        try {
            this.alunos = ArquivoHelper.lerAlunos();
        } catch (IOException e) {
            this.alunos = new ArrayList<Aluno>();
        }
        this.despesas = new ArrayList<Despesa>();
        this.categorias = new ArrayList<Categoria>();
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

    public void cadastrarDespesas() throws IOException{
        Integer mes = Integer.parseInt(JOptionPane.showInputDialog("Informe o mês da Despesa: "));
        Integer ano = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano da Despesa: "));
        String descricao = Despesa.checarDescricaoValida(JOptionPane.showInputDialog("Informe a descrição da Despesa: "));
        if (!ArquivoHelper.despesaExiste(descricao, mes, ano)) {
            ArrayList<String> categoriasList = new ArrayList<String>();
            for(var cat : categorias) {
                categoriasList.add(cat.getDescricao());
            }

            String[] opcoesCategorias = new String[categoriasList.size()];
            opcoesCategorias = categoriasList.toArray(opcoesCategorias);
            String categoria = Despesa.checarCategoriaValida((String) JOptionPane.showInputDialog(null, "Selecione a Categoria", "Cadastro de Despesa", JOptionPane.QUESTION_MESSAGE, null, opcoesCategorias, ""));

            String[] opcoesSubcategorias = null;
            Categoria categoriaSelecionada = null;

            for (var cat : categorias) {
                if(cat.getDescricao().equals(categoria)) {
                    opcoesSubcategorias = cat.getSubcategorias().split(",");
                    categoriaSelecionada = cat;
                }
            }
            String subcategoria = Despesa.checarCategoriaValida((String) JOptionPane.showInputDialog(null, "Selecione a Subcategoria", "Cadastro de Despesa", JOptionPane.QUESTION_MESSAGE, null, opcoesSubcategorias, ""));

            String strValor = Despesa.checarValorValido(JOptionPane.showInputDialog("Informe o valor da Despesa: R$ "));
            Double valor = Double.parseDouble(strValor);



            Despesa novaDespesa = new Despesa(valor, descricao, categoriaSelecionada, subcategoria);
            despesas.add(novaDespesa);

            ArquivoHelper.salvarDespesas(despesas, mes, ano);
        } else {
            JOptionPane.showMessageDialog(null, "Essa despesa já está cadastrada");
        }

    }

    public void editarDespesa() {

    }

    public void removerDespesa() {

    }

    public void cadastrarCategoria() {
        String descricao = JOptionPane.showInputDialog("Informe a descrição da Categoria: ");
        Categoria novaCategoria = new Categoria();
        novaCategoria.setDescricao(descricao);

        categorias.add(novaCategoria);

        String subcategoria = "";
        String comSubcategoria = "";
        while (!comSubcategoria.equals("s") && !comSubcategoria.equals("S") && !comSubcategoria.equals("n") && !comSubcategoria.equals("N")) {
            comSubcategoria = JOptionPane.showInputDialog("Deseja atrelar subcategorias? (s/n)");
        }
        if( comSubcategoria.equals("s") || comSubcategoria.equals("S") ) { subcategoria = JOptionPane.showInputDialog("Informe a subcategoria: "); }
        novaCategoria.setSubcategorias(subcategoria);

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
        try {
            String mesStr = JOptionPane.showInputDialog("Informe o mes da despesa(1-12): ");
            Integer mes = Integer.parseInt(mesStr);
            String anoStr = JOptionPane.showInputDialog("Informe o ano da despesa: ");
            Integer ano = Integer.parseInt(anoStr);
            CalculoProporcional calculoProporcional = new CalculoProporcional();
            calculoProporcional.calcular(mes, ano);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ano/mes inserido não é válido");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível achar uma despesa no intervalo de ano/mes dado");
        }

    }
}
