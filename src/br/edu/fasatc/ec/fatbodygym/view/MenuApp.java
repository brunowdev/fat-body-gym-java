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
import br.edu.fasatc.ec.fatbodygym.model.Instrutor;
import br.edu.fasatc.ec.fatbodygym.model.TipoExercicio;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.AlunoRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.ExercicioRepository;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.InstrutorRepository;
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
			JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a execu��o do programa.\n\nErro: " + e.getMessage());
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
		final InstrutorRepository instrutorRepository = new InstrutorRepository();

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

			case 16:
				instrutorRepository.merge(lerInstrutor(menu, null));
				break;
			case 17:
				final Instrutor instrutor = localizarInstrutorParaEditar(menu);
				instrutorRepository.merge(lerInstrutor(menu, instrutor));
				break;
			case 18:
				localizarInstrutorPorTexto(menu);
				break;
			case 19:
				localizarInstrutorPorCodigo(menu);
				break;
			case 20:
				listarInstrutores(menu);
				break;

			case 0:
				break;
			}
			opcao = menu.selecionarOpcao();
		}

	}

	/**
	 * M�todo que cadastra um novo aluno ou altera um j� existente.
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
				System.out.println("Informe o c�digo para alterar: ");
				aluno = alunoRepository.findById(new Aluno(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno n�o encontrado!");
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
				System.out.println("Aluno n�o encontrado!");
			}
		}

	}

	private static void localizarAlunoPorCodigo(AbstractBaseMenu menu) {
		final AlunoRepository alunoRepository = new AlunoRepository();
		Aluno aluno = null;

		while (aluno == null) {

			try {
				System.out.println("Informe o c�digo para buscar: ");
				final Long id = menu.lerLong();
				aluno = alunoRepository.findById(new Aluno(id));
				imprimirAluno(aluno);
			} catch (final Exception e) {
			}

			if (aluno == null) {
				System.out.println("Aluno n�o encontrado!");
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
		System.out.println("C�digo: " + aluno.getId());
		System.out.println("Nome: " + aluno.getNome());
		System.out.println("CPF: " + aluno.getCpf());
		System.out.println("RG: " + aluno.getRg());
		System.out.println("Data nascimento: " + aluno.getDataNascimento());
		System.out.println("Peso: " + aluno.getPeso());

	}

	/**
	 * M�todo que cadastra um novo Exerc�cio ou altera um j� existente.
	 *
	 * @param menu
	 * @param aluno
	 * @return
	 */
	private static Exercicio lerExercicio(AbstractBaseMenu menu, Exercicio exercicio) {

		final Exercicio exercicioParaSalvar = exercicio == null ? new Exercicio() : exercicio;

		System.out.println(exercicio == null ? ("Cadastrando") : ("Alterando") + " exerc�cio");
		System.out.println("Nome: \n > ");
		exercicioParaSalvar.setNome(menu.lerTexto());
		System.out.println("S�ries: \n > ");
		exercicioParaSalvar.setSeries(menu.lerInteiro());
		System.out.println("Quantidade s�rie: \n > ");
		exercicioParaSalvar.setQuantidadeSerie(menu.lerInteiro());
		System.out.println("Tipo: \n > ");
		exercicioParaSalvar.setTipoExercicio(TipoExercicio.fromInteger(menu.lerInteiro()));
		System.out.println("Exerc�cio " + exercicio == null ? ("cadastrado") : ("alterado") + " com sucesso!");

		return exercicioParaSalvar;
	}

	private static Exercicio localizarExercicioParaEditar(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;
		while (exercicio == null) {

			try {
				System.out.println("Informe o c�digo para alterar: ");
				exercicio = exercicioRepository.findById(new Exercicio(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exerc�cio n�o encontrado!");
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
				System.out.println("Exerc�cio n�o encontrado!");
			}
		}

	}

	private static void localizarExercicioPorCodigo(AbstractBaseMenu menu) {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		Exercicio exercicio = null;

		while (exercicio == null) {

			try {
				System.out.println("Informe o c�digo para buscar: ");
				final Long id = menu.lerLong();
				exercicio = exercicioRepository.findById(new Exercicio(id));
				imprimirExercicio(exercicio);
			} catch (final Exception e) {
			}

			if (exercicio == null) {
				System.out.println("Exerc�cio n�o encontrado!");
			}
		}

	}

	private static void listarExercicios(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final ExercicioRepository exercicioRepository = new ExercicioRepository();

		System.out.println("Listando exerc�cios: ");
		List<Exercicio> exercicios = new ArrayList<>();
		exercicios = exercicioRepository.findAll();
		exercicios.stream().forEach(exercicio -> imprimirExercicio(exercicio));

	}

	private static void imprimirExercicio(Exercicio exercicio) {

		System.out.println("\n\n");
		System.out.println("C�digo: " + exercicio.getId());
		System.out.println("Nome: " + exercicio.getNome());
		System.out.println("S�ries: " + exercicio.getSeries());
		System.out.println("Tipo exerc�cio: " + exercicio.getTipoExercicio().toString());

	}

	/******
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */

	/**
	 * M�todo que cadastra um novo instrutor ou altera um j� existente.
	 *
	 * @param menu
	 * @param instrutor
	 * @return
	 */
	private static Instrutor lerInstrutor(AbstractBaseMenu menu, Instrutor instrutor) {

		final Instrutor instrutorParaSalvar = instrutor == null ? new Instrutor() : instrutor;

		System.out.println((instrutor == null ? ("Cadastrando") : ("Alterando")) + " instrutor");
		System.out.println("Nome: \n > ");
		instrutorParaSalvar.setNome(menu.lerTexto());
		System.out.println("CPF: \n > ");
		instrutorParaSalvar.setCpf(menu.lerTexto());
		System.out.println("RG: \n > ");
		instrutorParaSalvar.setRg(menu.lerTexto());
		System.out.println("Data nascimento: (dd/mm/aaaa)\n > ");
		instrutorParaSalvar.setDataNascimento(menu.lerData(true));
		System.out.println("Instrutor " + (instrutor == null ? ("cadastrado") : ("alterado")) + " com sucesso!");

		return instrutorParaSalvar;
	}

	private static Instrutor localizarInstrutorParaEditar(AbstractBaseMenu menu) {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o c�digo para alterar: ");
				instrutor = instrutorRepository.findById(new Instrutor(menu.lerLong()));
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor n�o encontrado!");
			}
		}

		return instrutor;
	}

	private static void localizarInstrutorPorTexto(AbstractBaseMenu menu) {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o CPF, nome ou RG para buscar: ");
				final String texto = menu.lerTexto();
				instrutor = instrutorRepository.findByStringFields(texto);
				imprimirInstrutor(instrutor);
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor n�o encontrado!");
			}
		}

	}

	private static void localizarInstrutorPorCodigo(AbstractBaseMenu menu) {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();
		Instrutor instrutor = null;

		while (instrutor == null) {

			try {
				System.out.println("Informe o c�digo para buscar: ");
				final Long id = menu.lerLong();
				instrutor = instrutorRepository.findById(new Instrutor(id));
				imprimirInstrutor(instrutor);
			} catch (final Exception e) {
			}

			if (instrutor == null) {
				System.out.println("Instrutor n�o encontrado!");
			}
		}

	}

	private static void listarInstrutores(AbstractBaseMenu menu) throws ReadFileException, WriteFileException {
		final InstrutorRepository instrutorRepository = new InstrutorRepository();

		System.out.println("Listando instrutores: ");
		List<Instrutor> instrutores = new ArrayList<>();
		instrutores = instrutorRepository.findAll();
		instrutores.stream().forEach(instrutor -> imprimirInstrutor(instrutor));

	}

	private static void imprimirInstrutor(Instrutor instrutor) {

		System.out.println("\n\n");
		System.out.println("C�digo: " + instrutor.getId());
		System.out.println("Nome: " + instrutor.getNome());
		System.out.println("CPF: " + instrutor.getCpf());
		System.out.println("RG: " + instrutor.getRg());
		System.out.println("Data nascimento: " + instrutor.getDataNascimento());

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
