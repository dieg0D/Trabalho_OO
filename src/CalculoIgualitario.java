import javax.swing.*;
import java.io.IOException;

public class CalculoIgualitario extends CalculoBase {
    private void mostrarDistruibuicao(Double distribuicao) {
        StringBuilder mensagem = new StringBuilder("Resultado:\n");

        for (var aluno : alunos) {
            var distribuicaoAluno = String.format("%s: %.2f\n", aluno.getNome(), distribuicao);
            mensagem.append(distribuicaoAluno);
        }

        JOptionPane.showMessageDialog(null, mensagem);
    }

    @Override
    public void calcular(Integer mes, Integer ano) throws IOException {
        lerAlunos();
        lerDespesas(mes, ano);

        var numeroDeAlunos = alunos.size();
        Double somaDeDespesas = 0D;
        for (var despesa : despesas) {
            somaDeDespesas += despesa.getValor();
        }

        Double distribuicao = somaDeDespesas / numeroDeAlunos;
        mostrarDistruibuicao(distribuicao);
    }
}
