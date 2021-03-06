import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArquivoHelper {
    private static final File alunosFile = new File("src/files/alunos.txt");

    private static List<String> lerLinhasDoArquivo(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    private static File getArquivosDeDespesasPath(Integer mes, Integer ano) throws IOException {
        String arquivoDespesasName = String.format("src/files/despesas_%d_%d.txt", mes, ano);
        Path arquivoDespesasPath = Paths.get(arquivoDespesasName);

        if(!Files.exists(arquivoDespesasPath)){
            arquivoDespesasPath = Files.createFile(arquivoDespesasPath);
        }
        
        return arquivoDespesasPath.toFile();
    }

    private static String getDoubleAsString(Double value) {
        return value.toString().replace(",", ".");
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
                nome = Aluno.checarNomeEmBranco(JOptionPane.showInputDialog("Informe o novo nome do Aluno: "));

                if (ArquivoHelper.alunoExiste(nome) && !Objects.equals(nome, _aluno)) {
                    JOptionPane.showMessageDialog(null, "Esse aluno j?? est?? cadastrado");
                    nome = linhaSeparada[0];
                    email = linhaSeparada[1];
                    rendimento = Double.parseDouble(linhaSeparada[2]);
                } else {
                    email = Aluno.checarEmailEmBranco(JOptionPane.showInputDialog("Informe o novo e-mail do Aluno: "));

                    String strRendimento = Aluno.rendimentoInvalido(
                            JOptionPane.showInputDialog("Informe o novo rendimento do Aluno: R$ ")
                    );
                    rendimento = Double.parseDouble(strRendimento);
                }

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
            linhasDoArquivo.append(String.format("%s;%s;%s\n", aluno.getNome(), aluno.getEmail(), getDoubleAsString(aluno.getRendimento())));
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

    public static ArrayList<Despesa> editarCadastroDespesa(String _despesa, Integer _mes, Integer _ano, ArrayList<Categoria> categorias) throws IOException, DescricaoNaoInformadaException, CategoriaNaoInformadaException, ValorNaoInformadoException {
        ArrayList<Despesa> despesas = new ArrayList<>();
        var linhasDoArquivo = lerLinhasDoArquivo(getArquivosDeDespesasPath(_mes, _ano));
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            String descricao = "";
            Categoria categoria = new Categoria();
            Double valor = 0.0;

            if (Objects.equals(linhaSeparada[0], _despesa)) {
                descricao = Despesa.checarDescricaoValida(JOptionPane.showInputDialog("Informe a nova descri????o da despesa: "));

                if (ArquivoHelper.despesaExiste(descricao, _mes, _ano) && !Objects.equals(descricao, _despesa)) {
                    JOptionPane.showMessageDialog(null, "Essa despesa j?? est?? cadastrada");
                    descricao = linhaSeparada[0];
                    var categoriaSeparada = linhaSeparada[1].split(",");
                    categoria.setDescricao(categoriaSeparada[0]);
                    if(!categoriaSeparada[1].isEmpty()){
                        categoria.setSubcategorias(categoriaSeparada[1]);
                    }
                    valor = Double.parseDouble(linhaSeparada[2]);
                } else {
                    ArrayList<String> categoriasList = new ArrayList<String>();
                    for(var cat : categorias) {
                        categoriasList.add(cat.getDescricao());
                    }

                    String[] opcoesCategorias = new String[categoriasList.size()];
                    opcoesCategorias = categoriasList.toArray(opcoesCategorias);
                    String categoriaNome = Despesa.checarCategoriaValida((String) JOptionPane.showInputDialog(null, "Selecione a nova Categoria", "Edi????o de Despesa", JOptionPane.QUESTION_MESSAGE, null, opcoesCategorias, ""));

                    String[] opcoesSubcategorias = null;
                    Categoria categoriaSelecionada = null;

                    for (var cat : categorias) {
                        if(cat.getDescricao().equals(categoriaNome)) {
                            opcoesSubcategorias = cat.getSubcategorias().split(",");
                            categoriaSelecionada = cat;
                        }
                    }

                    String subcategoria = "";

                    if(opcoesSubcategorias.length > 0 && !opcoesSubcategorias[0].equals("")) {
                        subcategoria = (String) JOptionPane.showInputDialog(null, "Selecione a nova Subcategoria", "Edi????o de Despesa", JOptionPane.QUESTION_MESSAGE, null, opcoesSubcategorias, "");
                    }

                    String strValor = Despesa.checarValorValido(
                            JOptionPane.showInputDialog("Informe o novo valor da despesa: R$ ")
                    );
                    valor = Double.parseDouble(strValor);
                    
                    Despesa despesa = new Despesa(valor, descricao, categoriaSelecionada, subcategoria);
                    despesas.add(despesa);
                }
            }
        }

        despesas.removeIf(desp -> (desp.getDescricao().equals(_despesa)));

        return despesas;
    }

    public static void salvarDespesas(ArrayList<Despesa> despesas, Integer mes, Integer ano) throws IOException {
        StringBuilder linhasDoArquivo = new StringBuilder();

        for (var despesa : despesas) {
            if(despesa.getCategoria().getSubcategorias().isEmpty()){
                linhasDoArquivo.append(String.format(
                    "%s;%s;%s\n",
                    despesa.getDescricao(),
                    despesa.getCategoria().getDescricao(),
                    getDoubleAsString(despesa.getValor())
                ));
            } else {
                linhasDoArquivo.append(String.format(
                    "%s;%s,%s;%s\n",
                    despesa.getDescricao(),
                    despesa.getCategoria().getDescricao(),
                    despesa.getSubcategoria(),
                    getDoubleAsString(despesa.getValor())
                ));
            }
        }

        File arquivoDeDespesas = getArquivosDeDespesasPath(mes, ano);
        Files.write(arquivoDeDespesas.toPath(), linhasDoArquivo.toString().getBytes(), StandardOpenOption.APPEND);
    }

    public static ArrayList<Despesa> removerDespesa(String _despesa, Integer _mes, Integer _ano) throws IOException {
        ArrayList<Despesa> despesas = new ArrayList<>();
        var linhasDoArquivo = lerLinhasDoArquivo(getArquivosDeDespesasPath(_mes, _ano));
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            if (!Objects.equals(linhaSeparada[0], _despesa)) {
                String descricao = linhaSeparada[0];
                Categoria categoria = new Categoria();
                var categoriaSeparada = linhaSeparada[1].split(",");
                categoria.setDescricao(categoriaSeparada[0]);
                if(categoriaSeparada.length > 1){
                    categoria.setSubcategorias(categoriaSeparada[1]);
                }
                Double valor = Double.parseDouble(linhaSeparada[2]);
                Despesa despesa = new Despesa(valor, descricao, categoria, categoria.getSubcategorias());
                despesas.add(despesa);
            }
        }
        return despesas;
    }
}
