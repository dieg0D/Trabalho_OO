import javax.swing.JOptionPane;

public class App {
  public static void main(String[] args) {
    final Republica republica = new Republica();

    int opcao;
    do {
      String strOpcao = JOptionPane.showInputDialog(
        "Escolha uma opção: \n"
        + "1 - Cadastrar Aluno\n"
        + "2 - Cadastrar Despesa\n"
        + "3 - Cadastrar Categoria\n"
        + "4 - Cadastrar SubCategoria\n"
        + "5 - Calcular despesas de forma igualitária\n"
        + "6 - Calcular despesas de forma proporcional\n"
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
          republica.cadastrarSubCategoria();
          break;

        case 5:
          republica.calcularDespesasIgualitarias();
          break;

        case 6:
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