package br.edu.fasatc.ec.fatbodygym.view;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.Aluno;
import br.edu.fasatc.ec.fatbodygym.model.Exercicio;
import br.edu.fasatc.ec.fatbodygym.model.TipoExercicio;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.AlunoRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.ExercicioRepository;
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

		try {
			final Frame jf = new Frame();
			final LoginGUI login = new LoginGUI(jf, true);
			login.setVisible(true);
			exibir();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a execução do programa.\n\nErro: " + e.getMessage());
			e.printStackTrace();
			sair();
		}

	}

	public static void exibir() throws WriteFileException, ReadFileException {
		final AbstractBaseMenu menu = retornaMenu(usuarioLogado);
		invokeMenu(menu);
		sair();
	}

	private static AbstractBaseMenu retornaMenu(Usuario usuario) {
		return (usuario.getAluno() == null && usuario.getInstrutor() == null) ? new MenuInstrutor(scanner) : new MenuAluno(scanner);
	}

	private static void invokeMenu(AbstractBaseMenu menu) throws WriteFileException, ReadFileException {

		if (menu instanceof MenuAluno) {
			escolherOpcaoMenuAluno(menu);
		} else {
			escolherOpcaoMenuInstrutor(menu);
		}

	}

	private static void escolherOpcaoMenuAluno(AbstractBaseMenu menu) {

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

	private static void escolherOpcaoMenuInstrutor(AbstractBaseMenu menu) throws WriteFileException, ReadFileException {

		int opcao = menu.selecionarOpcao();

		final AlunoRepository alunoRepository = new AlunoRepository();
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		while (opcao != 0) {

			switch (opcao) {

			case 1:
				alunoRepository.merge(lerAluno(menu, null));
				break;
			case 2:
				final Aluno aluno = localizarAlunoParaEditar(menu);
				alunoRepository.merge(lerAluno(menu, aluno));
				break;
			case 3:
				localizarAlunoPorTexto(menu);
				break;
			case 4:
				localizarAlunoPorCodigo(menu);
				break;
			case 5:
				listarAlunos(menu);
				break;
			case 6:
				exercicioRepository.merge(lerExercicio(menu, null));
				break;
			case 7:
				final Exercicio exercicio = localizarExercicioParaEditar(menu);
				exercicioRepository.merge(lerExercicio(menu, exercicio));
				break;
			case 8:
				localizarExercicioPorTexto(menu);
				break;
			case 9:
				localizarExercicioPorCodigo(menu);
				break;
			case 10:
				listarExercicios(menu);
				break;

			case 0:
				break;
			}
			opcao = menu.selecionarOpcao();
		}

	}

	/**
	 * Método que cadastra um novo aluno ou altera um já existente.
	 *
	 * @param menu
	 * @param aluno
	 * @return
	 */
	private static Aluno lerAluno(AbstractBaseMenu menu, Aluno aluno) {

		final Aluno alunoParaSalvar = aluno == null ? new Aluno() : aluno;

		System.out.println((aluno == null ? ("Cadastrando") : ("Alterando")) + " aluno");
		System.out.println("Nome: \n > ");
		alunoParaSalvar.setNome(menu.lerTexto());
		System.out.println("CPF: \n > ");
		alunoParaSalvar.setCpf(menu.lerTexto());
		System.out.println("RG: \n > ");
		alunoParaSalvar.setRg(menu.lerTexto());
		System.out.println("Data nascimento: (dd/mm/aaaa)\n > ");
		alunoParaSalvar.setDataNascimento(menu.lerData(true));
		System.out.println("Peso: (kg)\n > ");
		alunoParaSalvar.setPeso(menu.lerBigdecimal());
		System.out.println("Aluno " + (aluno == null ? ("cadastrado") : ("alterado")) + " com sucesso!");

		return alunoParaSalvar;
	}

	private static Aluno localizarAlunoParaEditar(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				aluno = alunoRepository.findById(new Aluno(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			}
		}

		return aluno;
	}

	private static void localizarAlunoPorTexto(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o CPF, nome ou RG para buscar: ");
				final String texto = menu.lerTexto();
				aluno = alunoRepository.findByStringFields(texto);
				imprimirAluno(aluno);
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			}
		}

	}

	private static void localizarAlunoPorCodigo(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				aluno = alunoRepository.findById(new Aluno(id));
				imprimirAluno(aluno);
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno não encontrado!");
			}
		}

	}

	private static void listarAlunos(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final AlunoRepository alunoRepository = new AlunoRepository();

		System.out.println("Listando alunos: ");
		List<Aluno> alunos = new ArrayList<>();
		alunos = alunoRepository.findAll();
		alunos.stream().forEach(aluno -> imprimirAluno(aluno));

	}

	private static void imprimirAluno(Aluno aluno) {

		System.out.println("\n\n");
		System.out.println("Código: " + aluno.getId());
		System.out.println("Nome: " + aluno.getNome());
		System.out.println("CPF: " + aluno.getCpf());
		System.out.println("RG: " + aluno.getRg());
		System.out.println("Data nascimento: " + aluno.getDataNascimento());
		System.out.println("Peso: " + aluno.getPeso());

	}

	/**
	 * Método que cadastra um novo Exercício ou altera um já existente.
	 *
	 * @param menu
	 * @param aluno
	 * @return
	 */
	private static Exercicio lerExercicio(AbstractBaseMenu menu, Exercicio exercicio) {

		final Exercicio exercicioParaSalvar = exercicio == null ? new Exercicio() : exercicio;

		System.out.println(exercicio == null ? ("Cadastrando") : ("Alterando") + " exercício");
		System.out.println("Nome: \n > ");
		exercicioParaSalvar.setNome(menu.lerTexto());
		System.out.println("Séries: \n > ");
		exercicioParaSalvar.setSeries(menu.lerInteiro());
		System.out.println("Quantidade série: \n > ");
		exercicioParaSalvar.setQuantidadeSerie(menu.lerInteiro());
		System.out.println("Tipo: \n > ");
		exercicioParaSalvar.setTipoExercicio(TipoExercicio.fromInteger(menu.lerInteiro()));
		System.out.println("Exercício " + exercicio == null ? ("cadastrado") : ("alterado") + " com sucesso!");

		return exercicioParaSalvar;
	}

	private static Exercicio localizarExercicioParaEditar(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;
		while (exercicio == null) {

			try {
				System.out.println("Informe o código para alterar: ");
				exercicio = exercicioRepository.findById(new Exercicio(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exercício não encontrado!");
			}
		}

		return exercicio;
	}

	private static void localizarExercicioPorTexto(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;

		while (exercicio == null) {

			try {
				System.out.println("Informe o nome para buscar: ");
				final String texto = menu.lerTexto();
				exercicio = exercicioRepository.findByStringFields(texto);
				imprimirExercicio(exercicio);
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exercício não encontrado!");
			}
		}

	}

	private static void localizarExercicioPorCodigo(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;

		while (exercicio == null) {

			try {
				System.out.println("Informe o código para buscar: ");
				final Long id = menu.lerLong();
				exercicio = exercicioRepository.findById(new Exercicio(id));
				imprimirExercicio(exercicio);
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exercício não encontrado!");
			}
		}

	}

	private static void listarExercicios(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		System.out.println("Listando exercícios: ");
		List<Exercicio> exercicios = new ArrayList<>();
		exercicios = exercicioRepository.findAll();
		exercicios.stream().forEach(exercicio -> imprimirExercicio(exercicio));

	}

	private static void imprimirExercicio(Exercicio exercicio) {

		System.out.println("\n\n");
		System.out.println("Código: " + exercicio.getId());
		System.out.println("Nome: " + exercicio.getNome());
		System.out.println("Séries: " + exercicio.getSeries());
		System.out.println("Tipo exercício: " + exercicio.getTipoExercicio().toString());

	}

	private static void sair() {
		System.out.println("Fechando");
		fecharRecursosEstaticos();
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
