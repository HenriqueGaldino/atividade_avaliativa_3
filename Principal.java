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

            opt = scanner.nextInt();
            scanner.nextLine(); 
            switch (opt) {
                case 1:
                    try {
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
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar professor. Entrada inválida.");
                        scanner.nextLine(); 
                    }
                    break;

                case 2:
                    try {
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
                        System.out.println("Digite o ID do professor: ");
                        int idProf = scanner.nextInt();
                        scanner.nextLine();

                        // Busca manual do professor pelo ID
                        Professor profResponsavel = null;
                        for (Professor prof : professores) {
                            if (prof.getId() == idProf) {
                                profResponsavel = prof;
                                break;
                            }
                        }
                        if (profResponsavel != null) {
                            Curso curso = new Curso(idCurso, nomeCurso, cargaHoraria, profResponsavel);
                            cursos.add(curso);
                            System.out.println("Curso cadastrado com sucesso!");
                        } else {
                            System.out.println("Professor não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar curso. Entrada inválida.");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    try {
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
                        System.out.println("Digite o ID do curso: ");
                        int idCur = scanner.nextInt();
                        scanner.nextLine();

                        // Busca manual do curso pelo ID
                        Curso cursoEscolhido = null;
                        for (Curso cur : cursos) {
                            if (cur.getId() == idCur) {
                                cursoEscolhido = cur;
                                break;
                            }
                        }
                        if (cursoEscolhido != null) {
                            Aluno aluno = new Aluno(idAluno, nomeAluno, dataNascimento, cpf, cursoEscolhido);
                            alunos.add(aluno);
                            cursoEscolhido.adicionarAluno(aluno);
                            System.out.println("Aluno cadastrado com sucesso!");
                        } else {
                            System.out.println("Curso não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar aluno. Entrada inválida.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    try {
                        for (Professor prof : professores) {
                            long count = 0;
                            for (Curso c : cursos) {
                                if (c.getProfessor().getId() == prof.getId()) {
                                    count++;
                                }
                            }
                            System.out.println("Professor: " + prof.getNome() + ", Departamento: " + prof.getDepartamento() + ", Cursos: " + count);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao listar professores.");
                    }
                    break;

                case 5:
                    try {
                        for (Curso cur : cursos) {
                            System.out.println("Curso: " + cur.getNome() + ", Carga Horária: " + cur.getCargaHoraria() + ", Professor: " + cur.getProfessor().getNome());
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao listar cursos.");
                    }
                    break;

                case 6:
                    try {
                        for (Aluno al : alunos) {
                            System.out.println(
                                    "Aluno: " + al.getNome() + ", Data de Nascimento: " + al.getDataNascimento() + ", CPF: " + al.getCpf() + ", Curso: " + al.getCurso().getNome());
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao listar alunos.");
                    }
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opt != 7);
    }
}