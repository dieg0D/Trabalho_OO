import java.util.Objects;

public class ExceptionHandler {
    public static void rendimentoInvalido(String strRendimento) throws RendimentoInvalidoException {
        double rendimento;
        try {
            rendimento = Double.parseDouble(strRendimento);
        } catch (NumberFormatException e) {
            throw new RendimentoInvalidoException("O valor do rendimento deve ser um número e suas casas decimais separadas por um ponto, se necessário");
        }

        if (rendimento < 0) {
            throw new RendimentoInvalidoException("O valor do rendimento não pode ser um número negativo");
        }
    }

    public static void checarCampoEmBranco(String dado) throws DadosPessoaisIncompletosException {
        if (Objects.equals(dado, "")) {
            throw new DadosPessoaisIncompletosException("Dados em branco não são permitidos no cadastro de alunos");
        }
    }

    // DESPESAS EXCEPTION HANDLERS
    public static void checarDescricaoDespesaEmBranco(String dado) throws DescricaoNaoInformadaException {
        if (Objects.equals(dado, "")) {
            throw new DescricaoNaoInformadaException("É necessário fornecer uma descrição (nome) para a despesa a ser cadastrada");
        }
    }

    public static void checarCategoriaOuSubcategoriaDespesaEmBranco(String dado) throws CategoriaNaoInformadaException {
        if (Objects.equals(dado, "")) {
            throw new CategoriaNaoInformadaException("É necessário selecionar uma categoria ou uma subcaterogia para a despesa a ser cadastrada");
        }
    }

    public static void checarValorDespesaEmBrancoOuInvalido(String strValor) throws ValorNaoInformadoException {
        double valor;
        try {
            valor = Double.parseDouble(strValor);
        } catch (NumberFormatException e) {
            throw new ValorNaoInformadoException("O valor da despesa deve ser um número e suas casas decimais separadas por um ponto, se necessário");
        }

        if (valor < 0) {
            throw new ValorNaoInformadoException("O valor da despesa não pode ser um número negativo");
        }
    }
}
