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
}
