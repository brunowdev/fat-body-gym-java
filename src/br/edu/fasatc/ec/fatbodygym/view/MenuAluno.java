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
            System.out.println("\n===== MENU =====");
            System.out.println("1. Opcao 1");
            System.out.println("2. Opcao 2");
            System.out.println("3. Opcao 3");
            System.out.println("0. Sair");
            System.out.print("Informe a opcao desejada: > ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            if (opcao < getOpcaoMinima() || opcao > getOpcaoMaxima()) {
                System.out.println("\nOpcao invalida!!!\n");
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
        return 5;
    }

}
