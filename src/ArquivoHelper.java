import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArquivoHelper {
    private static final File alunosFile = new File("src/files/alunos.txt");

    private static List<String> lerLinhasDoArquivo(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    private static File getArquivosDeDespesasPath(Integer mes, Integer ano) throws IOException {
        String arquivoDespesasPath = String.format("src/files/despesas_%d_%d.txt", mes, ano);
        File file = new File(arquivoDespesasPath);
        file.createNewFile();
        
        return file;
    }

    public static ArrayList<Despesa> lerDespesas(Integer mes, Integer ano) throws IOException {
        ArrayList<Despesa> despesas = new ArrayList<>();

        File arquivoDespesas = getArquivosDeDespesasPath(mes, ano);
        List<String> linhasDoArquivo = lerLinhasDoArquivo(arquivoDespesas);
        for (String linha : linhasDoArquivo) {
            String[] linhaSeparada = linha.split(";");
            String[] categoriaESubcategoria = linhaSeparada[1].split(",");
            String subcategorias = new String();

            String descricaoCategoria = categoriaESubcategoria[0];
            String descricaoDespesa = linhaSeparada[0];
            Double valor = Double.parseDouble(linhaSeparada[2]);
            if (categoriaESubcategoria.length > 1) {
                SubCategoria subCategoria = new SubCategoria();
                subCategoria.setDescricao(categoriaESubcategoria[1]);
                subcategorias = subCategoria.getDescricao();
            }
            Categoria categoria = new Categoria();
            categoria.setSubcategorias(subcategorias);
            categoria.setDescricao(descricaoCategoria);

            Despesa despesa = new Despesa(valor, descricaoDespesa, categoria, subcategorias);
            despesas.add(despesa);
        }

        return despesas;
    }

    public static ArrayList<Aluno> lerAlunos() throws IOException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        var linhasDoArquivo = lerLinhasDoArquivo(alunosFile);
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            String nome = linhaSeparada[0];
            String email = linhaSeparada[1];
            Double rendimento = Double.parseDouble(linhaSeparada[2]);

            Aluno aluno = new Aluno(nome, email, rendimento);
            alunos.add(aluno);
        }

        return alunos;
    }

    public static ArrayList<Aluno> editarCadastroAluno(String _aluno) throws IOException, DadosPessoaisIncompletosException, RendimentoInvalidoException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        var linhasDoArquivo = lerLinhasDoArquivo(alunosFile);
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            String nome;
            String email;
            double rendimento;

            if (Objects.equals(linhaSeparada[0], _aluno)) {
                nome = Aluno.checarNomeEmBranco(JOptionPane.showInputDialog("Informe o nome do Aluno: "));

                email = Aluno.checarEmailEmBranco(JOptionPane.showInputDialog("Informe o e-mail do Aluno: "));

                String strRendimento = Aluno.rendimentoInvalido(
                        JOptionPane.showInputDialog("Informe o rendimento do Aluno: R$ ")
                );
                rendimento = Double.parseDouble(strRendimento);
            } else {
                nome = linhaSeparada[0];
                email = linhaSeparada[1];
                rendimento = Double.parseDouble(linhaSeparada[2]);
            }

            Aluno aluno = new Aluno(nome, email, rendimento);
            alunos.add(aluno);
        }

        return alunos;
    }

    public static boolean alunoExiste(String _aluno) throws IOException {
        var linhasDoArquivo = lerLinhasDoArquivo(alunosFile);
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            if (Objects.equals(linhaSeparada[0], _aluno)) {
                return true;
            }
        }
        return false;
    }

    public static boolean alunoRepetido(String _aluno) throws IOException {
        var linhasDoArquivo = lerLinhasDoArquivo(alunosFile);
        int count = 0;
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            if (Objects.equals(linhaSeparada[0], _aluno)) count++;
            if (count == 2) return true;
        }
        return false;
    }

    public static ArrayList<Aluno> removerAluno(String _aluno) throws IOException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        var linhasDoArquivo = lerLinhasDoArquivo(alunosFile);
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            if (!Objects.equals(linhaSeparada[0], _aluno)) {
                String nome = linhaSeparada[0];
                String email = linhaSeparada[1];
                Double rendimento = Double.parseDouble(linhaSeparada[2]);
                Aluno aluno = new Aluno(nome, email, rendimento);
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public static void salvarAlunos(ArrayList<Aluno> alunos) throws IOException {
        StringBuilder linhasDoArquivo = new StringBuilder();

        for (var aluno : alunos) {
            linhasDoArquivo.append(String.format("%s;%s;%.2f\n", aluno.getNome(), aluno.getEmail(), aluno.getRendimento()));
        }
        Files.write(alunosFile.toPath(), linhasDoArquivo.toString().getBytes());
    }

    // MIGHT BE REFACTORED
    public static boolean despesaExiste(String _despesa, Integer _mes, Integer _ano) throws IOException {
        var linhasDoArquivo = lerLinhasDoArquivo(getArquivosDeDespesasPath(_mes, _ano));
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            if (Objects.equals(linhaSeparada[0], _despesa)) {
                return true;
            }
        }
        return false;
    }

    public static void salvarDespesas(ArrayList<Despesa> despesas, Integer mes, Integer ano) throws IOException {
        StringBuilder linhasDoArquivo = new StringBuilder();

        for (var despesa : despesas) {
            String subCategoria = despesa.getSubcategoria();
            // var subCategorias = despesa.getCategoria().getSubcategorias();

            //if (subCategorias != null) {
            //    subCategoria = subCategorias.getDescricao();
            //}
            linhasDoArquivo.append(String.format("%s;%s,%s;%.2f\n", despesa.getDescricao(),
                    despesa.getCategoria().getDescricao(), subCategoria, despesa.getValor()));
        }

        File arquivoDeDespesas = getArquivosDeDespesasPath(mes, ano);
        Files.write(arquivoDeDespesas.toPath(), linhasDoArquivo.toString().getBytes());
    }
}
