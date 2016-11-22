package br.edu.fasatc.ec.fatbodygym.view;

import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import java.util.Scanner;

public class MenuApp {

    private static Scanner scanner;

    static {

        try {
            scanner = new Scanner(System.in);
        } catch (Exception exc) {
            sair();
        }

    }

    public void exibir(Usuario usuario) {

        escolherOpcaoMenuAluno(retornaMenu(usuario));
        sair();

    }

    private AbstractBaseMenu retornaMenu(Usuario usuario) {
        return (usuario.getAluno() == null && usuario.getInstrutor() == null) ? new MenuAluno(scanner) : new MenuAluno(scanner);
    }

    private void escolherOpcaoMenuAluno(AbstractBaseMenu menu) {

        int opcao = menu.selecionarOpcao();

        while (opcao != 0) {

            switch (opcao) {
                case 1:
                    break;
                case 0:
                    break;
            }
            opcao = menu.selecionarOpcao();
        }

    }

    private void escolherOpcaoMenuInstrutor(AbstractBaseMenu menu) {

        int opcao = menu.selecionarOpcao();

        while (opcao != 0) {

            switch (opcao) {
                case 1:
                    break;
                case 0:
                    break;
            }
            opcao = menu.selecionarOpcao();
        }

    }

    private static void sair() {
        System.out.println("Fechando");
        System.exit(0);
    }

    private static void fecharRecursosEstaticos() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
