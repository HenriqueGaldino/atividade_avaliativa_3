import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int opt = 0;
        Scanner scanner = new Scanner(System.in);

        // Listas para armazenar professores, cursos e alunos
        ArrayList<Professor> professores = new ArrayList<>();
        ArrayList<Curso> cursos = new ArrayList<>();
        ArrayList<Aluno> alunos = new ArrayList<>();

        do {
            System.out.println("Opções: ");
            System.out.println("[1] - Cadastrar Professor");
            System.out.println("[2] - Cadastrar Curso");
            System.out.println("[3] - Cadastrar Aluno");
            System.out.println("[4] - Listar Professores");
            System.out.println("[5] - Listar Cursos");
            System.out.println("[6] - Listar Alunos");
            System.out.println("[7] - Sair");
            // Le a opção do usuario e trata possíveis exceções
               try {
                opt = scanner.nextInt();
                scanner.nextLine();  
            } catch (Exception e) {
                System.out.println("Opção inválida");
                scanner.nextLine();  
                continue;
            }

            // Casdastrar Professor 
            switch (opt) {
                case 1:
                    System.out.println("Digite o ID do professor: ");
                    int idProfessor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do professor: ");
                    String nomeProfessor = scanner.nextLine();
                    System.out.println("Digite o departamento do professor: ");
                    String departamento = scanner.nextLine();
                    Professor professor = new Professor(idProfessor, nomeProfessor, departamento);
                    professores.add(professor);
                    System.out.println("Professor cadastrado com sucesso!");
                    break;
                 // Cadastrar Curso   
                case 2:
                    System.out.println("Digite o ID do curso: ");
                    int idCurso = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do curso: ");
                    String nomeCurso = scanner.nextLine();
                    System.out.println("Digite a carga horária do curso: ");
                    int cargaHoraria = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Selecione o ID do professor responsável: ");
                    for (Professor prof : professores) {
                        System.out.println("ID: " + prof.getId() + ", Nome: " + prof.getNome());
                    }
                    int idProf = scanner.nextInt();
                    scanner.nextLine();
                    Professor profResponsavel = professores.stream()
                            .filter(p -> p.getId() == idProf)
                            .findFirst()
                            .orElse(null);
                    // Se o professor for encontrado cria o curso
                    if (profResponsavel != null) {
                        Curso curso = new Curso(idCurso, nomeCurso, cargaHoraria, profResponsavel);
                        cursos.add(curso);
                        System.out.println("Curso cadastrado com sucesso!");
                    } else {
                        System.out.println("Professor não encontrado.");
                    }
                    break;

                    //Cadastar Aluno
                case 3:
                    System.out.println("Digite o ID do aluno: ");
                    int idAluno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do aluno: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.println("Digite a data de nascimento do aluno: ");
                    String dataNascimento = scanner.nextLine();
                    System.out.println("Digite o CPF do aluno: ");
                    String cpf = scanner.nextLine();

                    System.out.println("Selecione o ID do curso: ");
                    for (Curso cur : cursos) {
                        System.out.println("ID: " + cur.getId() + ", Nome: " + cur.getNome());
                    }
                    int idCur = scanner.nextInt();
                    scanner.nextLine();
                    Curso cursoEscolhido = cursos.stream() // Busca o curso correspondente ao ID fornecido pelo usuario
                            .filter(c -> c.getId() == idCur) 
                            .findFirst()
                            .orElse(null);

                    if (cursoEscolhido != null) {// Verifica se o curso foi encontrado
                        Aluno aluno = new Aluno(idAluno, nomeAluno, dataNascimento, cpf, cursoEscolhido);
                        alunos.add(aluno);
                        cursoEscolhido.adicionarAluno(aluno);
                        System.out.println("Aluno cadastrado com sucesso!");
                    } else {
                        System.out.println("Curso não encontrado.");
                    }
                    break;
                    
                    //Listar Professor 
                    case 4:
                    for (Professor prof : professores) {
                        long count = cursos.stream().filter(c -> c.getProfessor().getId() == prof.getId()).count();
                        System.out.println("Professor: " + prof.getNome() + ", Departamento: " + prof.getDepartamento() + ", Cursos: " + count);
                    }
                    break;

                    //Listar Curso
                case 5:
                    for (Curso cur : cursos) {
                        System.out.println("Curso: " + cur.getNome() + ", Carga Horária: " + cur.getCargaHoraria() + ", Professor: " + cur.getProfessor().getNome());
                    }
                    break;

                    //Listar Aluno
                case 6:
                    for (Aluno al : alunos) {
                        System.out.println("Aluno: " + al.getNome() + ", Data de Nascimento: " + al.getDataNascimento() + ", CPF: " + al.getCpf() + ", Curso: " + al.getCurso().getNome());
                    }
                    break;

                    //Opção para sair
                case 7:
                    System.out.println("Saindo...");
                    break;

                    //Opeção invalida
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;

            }

        } while (opt != 7);

        scanner.close();
    }
}

