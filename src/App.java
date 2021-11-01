import javax.swing.*;
import java.io.IOException;

public class App {
  public static void main(String[] args) throws IOException, DadosPessoaisIncompletosException, RendimentoInvalidoException {
    final Republica republica = new Republica();

    int opcao;
    do {
      String strOpcao = JOptionPane.showInputDialog(
              "Escolha uma opção: \n"
                      + "1 - Cadastrar Aluno\n"
                      + "2 - Cadastrar Despesa\n"
                      + "3 - Cadastrar Categoria\n"
                      + "4 - Editar Aluno\n"
                      + "5 - Editar Despesa\n"
                      + "6 - Remover Aluno\n"
                      + "7 - Remover Despesa\n"
                      + "8 - Calcular despesas de forma igualitária\n"
                      + "9 - Calcular despesas de forma proporcional\n"
                      + "0 - Sair do programa"
      );

      try {
        opcao = Integer.parseInt(strOpcao);
      } catch (NumberFormatException e) {
        opcao = 0;
      }

      switch (opcao) {
        case 1:
          republica.cadastrarAluno();
          break;

        case 2:
          republica.cadastrarDespesas();
          break;

        case 3:
          republica.cadastrarCategoria();
          break;

        case 4:
          republica.editarAluno();
          break;

        case 5:
          republica.editarDespesa();
          break;

        case 6:
          republica.removerAluno();
          break;

        case 7:
          republica.removerDespesa();
          break;

        case 8:
          republica.calcularDespesasIgualitarias();
          break;

        case 9:
          republica.calcularDespesasProporcionais();
          break;

        case 0:
          // sair do programa
          break;

        default:
          // Opcao invalida
          break;
      }
    } while (opcao != 0);

  }
}
