package br.edu.fasatc.ec.fatbodygym.view;

import java.util.Scanner;

public class MenuAluno extends AbstractBaseMenu {

	public MenuAluno(Scanner scanner) {
		super(scanner);
	}

	@Override
	public int selecionarOpcao() {
		int opcao;
		do {
			System.out.println("\n=================================== MENU ===================================");
			System.out.println("1. Cadastrar um treino");
			System.out.println("2. Editar um treino");
			System.out.println("3. Consultar treino (pesquisa textual por exercício)");
			System.out.println("4. Consultar treino (pesquisa por código do exercício)");
			System.out.println("5. Listar meus Treinos");
			System.out.println("6. Remover treino");
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
		return 6;
	}

}
