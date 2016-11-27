package br.edu.fasatc.ec.fatbodygym.view;

import java.util.Scanner;

/**
 * Representa o menu que será exibido ao cadastrar um novo usuário.
 *
 * @author BRUNO-PC
 *
 */
public class MenuInstrutor extends AbstractBaseMenu {

	public MenuInstrutor(Scanner scanner) {
		super(scanner);
	}

	@Override
	public int selecionarOpcao() {
		int opcao;
		do {
			System.out.println("\n=================================== MENU ===================================");

			System.out.println("1. Cadastrar aluno");
			System.out.println("2. Editar aluno");
			System.out.println("3. Consultar aluno (CPF, nome ou RG)");
			System.out.println("4. Consultar aluno (pesquisa código)");
			System.out.println("5. Listar alunos");
			System.out.println("6. Remover aluno");

			System.out.println("7. Cadastrar exercício");
			System.out.println("8. Editar exercício");
			System.out.println("9. Consultar exercício (pesquisa textual)");
			System.out.println("10. Consultar exercício (pesquisa código)");
			System.out.println("11. Listar exercícios");
			System.out.println("12. Remover exercício");

			System.out.println("13. Cadastrar usuário");
			System.out.println("14. Editar usuário");
			System.out.println("15. Consultar usuário (e-mail)");
			System.out.println("16. Consultar usuário (pesquisa código)");
			System.out.println("17. Listar usuários");
			System.out.println("18. Remover usuários");

			System.out.println("19. Cadastrar instrutor");
			System.out.println("20. Editar instrutor");
			System.out.println("21. Consultar instrutor (CPF, nome ou RG)");
			System.out.println("22. Consultar instrutor (pesquisa código)");
			System.out.println("23. Listar instrutores");
			System.out.println("24. Remover instrutor");
			System.out.println("\n=================================== RELATÓRIOS ===================================");
			System.out.println("25. Exercícios mais praticados (dos mais praticados para os menos praticados)");
			System.out.println("26. Alunos mais ativos (dos mais ativos para os menos ativos)");
			System.out.println("27. Alunos ordenados por nome");

			System.out.println("0. Sair");
			System.out.print("Informe a opção desejada: > ");
			opcao = scanner.nextInt();
			scanner.nextLine();
			if (opcao < getOpcaoMinima() || opcao > getOpcaoMaxima()) {
				System.out.println("\nOpcao inválida!!!\n");
			}
		} while (opcao < getOpcaoMinima() || opcao > getOpcaoMaxima());
		return opcao;
	}

	@Override
	public int getOpcaoMinima() {
		return 0;
	}

	@Override
	public int getOpcaoMaxima() {
		return 27;
	}

}
