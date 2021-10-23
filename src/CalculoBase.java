import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CalculoBase {
    protected ArrayList<Despesa> despesas;
    protected ArrayList<Aluno> alunos;
    private static final File alunosFile = new File("src/files/alunos.txt");

    private List<String> lerLinhasDoArquivo(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    protected void lerAlunos() throws IOException {
        alunos = new ArrayList<>();
        var linhasDoArquivo = lerLinhasDoArquivo(alunosFile);
        for (var linha : linhasDoArquivo) {
            var linhaSeparada = linha.split(";");

            String nome = linhaSeparada[0];
            String email = linhaSeparada[1];
            Double rendimento = Double.parseDouble(linhaSeparada[2]);

            Aluno aluno = new Aluno(nome, email, rendimento);
            alunos.add(aluno);
        }
    }

    protected void lerDespesas(Integer mes, Integer ano) throws IOException {
        despesas = new ArrayList<>();

        String arquivoDespesasPath = String.format("src/files/despesas_%d_%d.txt", mes, ano);
        File arquivoDespesas = new File(arquivoDespesasPath);
        List<String> linhasDoArquivo = lerLinhasDoArquivo(arquivoDespesas);
        for (String linha : linhasDoArquivo) {
            String[] linhaSeparada = linha.split(";");
            String[] categoriaESubcategoria = linhaSeparada[1].split(",");
            ArrayList<SubCategoria> subcategorias = new ArrayList<>();

            String descricaoCategoria = categoriaESubcategoria[0];
            String descricaoDespesa = linhaSeparada[0];
            Double valor = Double.parseDouble(linhaSeparada[2]);
            if (categoriaESubcategoria.length > 1) {
                SubCategoria subCategoria = new SubCategoria();
                subCategoria.setDescrição(categoriaESubcategoria[1]);
                subcategorias.add(subCategoria);
            }
            Categoria categoria = new Categoria();
            categoria.setSubcategorias(subcategorias);
            categoria.setDescrição(descricaoCategoria);

            Despesa despesa = new Despesa(valor, descricaoDespesa, categoria);
            despesas.add(despesa);
        }
    }

    public void calcular(Integer mes, Integer ano) throws IOException{}
}
