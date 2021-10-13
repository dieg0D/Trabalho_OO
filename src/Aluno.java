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
}
