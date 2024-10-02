public class Aluno {
    int id;
    String nome;
    String dataNascimento;
    String cpf;
    Curso curso;

    public Aluno(int id, String nome, String dataNascimento, String cpf, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;  
    }

    public String getCpf() {
        return cpf;
    }

    public Curso getCurso() {
        return curso;
    }
}
