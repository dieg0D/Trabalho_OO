import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CalculoIgualitario extends CalculoBase {
    private ArrayList<Aluno> alunos;
    private ArrayList<Despesa> despesas;

    private void mostrarDistruibuicao(Double distribuicao) {
        StringBuilder mensagem = new StringBuilder("Resultado:\n");

        for (var aluno : alunos) {
            var distribuicaoAluno = String.format("    %s: R$ %.2f\n", aluno.getNome(), distribuicao);
            mensagem.append(distribuicaoAluno);
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }

    @Override
    public void calcular(Integer mes, Integer ano) throws IOException {
        alunos = ArquivoHelper.lerAlunos();
        despesas = ArquivoHelper.lerDespesas(mes, ano);

        var numeroDeAlunos = alunos.size();
        Double somaDeDespesas = 0D;
        for (var despesa : despesas) {
            somaDeDespesas += despesa.getValor();
        }

        Double distribuicao = somaDeDespesas / numeroDeAlunos;
        mostrarDistruibuicao(distribuicao);
    }
}
