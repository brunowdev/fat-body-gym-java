package br.edu.fasatc.ec.fatbodygym.view;

import java.awt.Frame;
import java.util.Scanner;

import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.view.login.LoginGUI;

public class MenuApp {

	private static Scanner scanner;
	private static Usuario usuarioLogado;

	static {

		try {
			scanner = new Scanner(System.in);
		} catch (final Exception exc) {
			sair();
		}

	}

	public static void main(String... args) {

		final Frame jf = new Frame();
		final LoginGUI login = new LoginGUI(jf, true);
		login.setVisible(true);
		exibir();

	}

	public static void exibir() {

		retornaMenu(usuarioLogado);
		sair();

	}

	private static AbstractBaseMenu retornaMenu(Usuario usuario) {
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

	public static void definirUsuarioLogado(Usuario usuario) {
		usuarioLogado = usuario;
	}

}
