package br.edu.fasatc.ec.fatbodygym.view;

import java.util.Scanner;

public class MenuInstrutor extends AbstractBaseMenu {

	public MenuInstrutor(Scanner scanner) {
		super(scanner);
	}

	@Override
	public int selecionarOpcao() {
		int opcao;
		do {
			System.out.println("\n=================================== MENU ===================================");

			System.out.println("1. Cadastrar exercício");
			System.out.println("2. Editar exercício");
			System.out.println("3. Consultar exercício (pesquisa textual)");
			System.out.println("4. Consultar exercício (pesquisa código)");
			System.out.println("5. Listar exercícios");

			System.out.println("5. Cadastrar aluno");
			System.out.println("6. Editar aluno");
			System.out.println("7. Consultar aluno (CPF, nome ou RG)");
			System.out.println("8. Consultar aluno (pesquisa código)");
			System.out.println("9. Listar alunos");

			System.out.println("10. Cadastrar usuário");
			System.out.println("11. Editar usuário");
			System.out.println("12. Consultar usuário (e-mail)");
			System.out.println("13. Consultar usuário (pesquisa código)");
			System.out.println("14. Listar usuários");

			System.out.println("15. Cadastrar instrutor");
			System.out.println("16. Editar instrutor");
			System.out.println("17. Consultar instrutor (CPF, nome ou RG)");
			System.out.println("18. Consultar instrutor (pesquisa código)");
			System.out.println("19. Listar instrutores");
			System.out.println("\n=================================== RELATÓRIOS ===================================");
			System.out.println("20. Treinos mais praticados (dos mais praticados para os menos praticados)");
			System.out.println("21. Alunos mais ativos (dos mais ativos para os menos ativos)");
			System.out.println("22. Alunos ordenados por nome");

			System.out.println("0. Sair");
			System.out.print("Informe a opcao desejada: > ");
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
		return 22;
	}

}
