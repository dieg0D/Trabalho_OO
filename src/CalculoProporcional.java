import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CalculoProporcional extends CalculoBase {
    private ArrayList<Aluno> alunos;
    private ArrayList<Despesa> despesas;

    @Override
    public void calcular(Integer mes, Integer ano) throws IOException {
        alunos = ArquivoHelper.lerAlunos();
        despesas = ArquivoHelper.lerDespesas(mes, ano);

        Double somaDeDespesas = 0D;
        for (var despesa : despesas) {
            somaDeDespesas += despesa.getValor();
        }

        Double somaDeRendimentos = 0D;
        for (var aluno : alunos) {
            somaDeRendimentos += aluno.getRendimento();
        }

        StringBuilder mensagem = new StringBuilder("Resultado:\n");
        for (var aluno : alunos) {
            Double percentual  =  (aluno.getRendimento() * 100)  / somaDeRendimentos;
            Double distribuicao = (somaDeDespesas * percentual) / 100;
            var distribuicaoAluno = String.format("    %s: R$ %.2f\n", aluno.getNome(), distribuicao);
            mensagem.append(distribuicaoAluno);
        }

        JOptionPane.showMessageDialog(null, mensagem);

    }
}
