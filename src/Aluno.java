import javax.swing.*;
import java.util.Objects;

public class Aluno {
    
    private String nome;
    private String email;
    private Double rendimento;

    public Aluno(String nome, String email, Double rendimento) {
        this.nome = nome;
        this.email = email;
        this.rendimento = rendimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }

    public static String checarNomeEmBranco(String nome) {
        boolean flag = true;
        do {
            try {
                ExceptionHandler.checarCampoEmBranco(nome);
                flag = false;
            } catch (DadosPessoaisIncompletosException e) {
                JOptionPane.showMessageDialog(null, e);
                nome = JOptionPane.showInputDialog("Informe o nome do Aluno: ");
            }
        } while (flag);
        return nome;
    }

    public static String checarEmailEmBranco(String email) {
        boolean flag = true;
        do {
            try {
                ExceptionHandler.checarCampoEmBranco(email);
                flag = false;
            } catch (DadosPessoaisIncompletosException e) {
                JOptionPane.showMessageDialog(null, e);
                email = JOptionPane.showInputDialog("Informe o e-mail do Aluno: ");
            }
        } while (flag);
        return email;
    }

    public static String rendimentoInvalido(String strRendimento) {
        boolean flag = true;
        do {
            try {
                ExceptionHandler.checarCampoEmBranco(strRendimento);
                ExceptionHandler.rendimentoInvalido(strRendimento);
                flag = false;
            } catch (RendimentoInvalidoException | DadosPessoaisIncompletosException e) {
                JOptionPane.showMessageDialog(null, e);
                strRendimento = JOptionPane.showInputDialog("Informe o rendimento do Aluno: R$ ");
            }
        } while (flag);
        return strRendimento;
    }
}
