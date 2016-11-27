package br.edu.fasatc.ec.fatbodygym.relatorios;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.fasatc.ec.fatbodygym.model.Aluno;
import br.edu.fasatc.ec.fatbodygym.model.Exercicio;
import br.edu.fasatc.ec.fatbodygym.model.Treino;
import br.edu.fasatc.ec.fatbodygym.model.comparators.AlunoComparator;
import br.edu.fasatc.ec.fatbodygym.utils.ComparadorValorMap;

public class Relatorios {

	/**
	 * Gera um relatório dos exercícios mais praticados, ordenados dos mais para
	 * os menos praticados.
	 *
	 * @param path
	 * @param treinos
	 * @throws FileNotFoundException
	 */
	public static void relatorioExercicioMaisPraticados(String path, List<Treino> treinos) throws FileNotFoundException {
		final String formato = "%s\t\t\t%s\t\t%n";

		try (Formatter formatter = new Formatter(path)) {

			formatter.format("%s\t\t\t%s\t\t\t%n", "Exercício", "Total");

			final Map<String, Integer> exerciciosPraticados = new HashMap<>();

			for (final Treino treino : treinos) {

				for (final Exercicio exercicio : treino.getExercicios()) {

					final String exercicioKey = exercicio.getNome();

					if (!exerciciosPraticados.containsKey(exercicioKey)) {
						exerciciosPraticados.put(exercicioKey, 1);
					} else {
						exerciciosPraticados.put(exercicioKey, exerciciosPraticados.get(exercicioKey).intValue() + 1);
					}

				}

			}

			final TreeMap<String, Integer> ordenados = new TreeMap<>(new ComparadorValorMap(exerciciosPraticados));
			ordenados.putAll(exerciciosPraticados);

			for (final String key : ordenados.keySet()) {
				formatter.format(formato, key, exerciciosPraticados.get(key));
			}

			System.out.println("Relatório gerado com sucesso!");
			System.out.println("Caminho: " + path);
		} catch (final Exception e) {
			System.out.println("Ocorreu um erro ao gerar relatório." + e.getMessage());
		}

	}

	/**
	 * Gera um relatório dos alunos com mais treinos, ordenados dos que possuem
	 * mais treinos para os que tem menos.
	 *
	 * @param path
	 * @param treinos
	 * @throws FileNotFoundException
	 */
	public static void relatorioAlunosMaisAtivos(String path, List<Treino> treinos) throws FileNotFoundException {
		final String formato = "%s\t\t\t%s\t\t%n";

		try (Formatter formatter = new Formatter(path)) {

			formatter.format("%s\t\t\t%s\t\t\t%n", "Aluno", "Total");

			final Map<String, Integer> alunosComTreinos = new HashMap<>();

			for (final Treino treino : treinos) {

				final String alunoKey = treino.getAluno().getNome();

				if (!alunosComTreinos.containsKey(alunoKey)) {
					alunosComTreinos.put(alunoKey, 1);
				} else {
					alunosComTreinos.put(alunoKey, alunosComTreinos.get(alunoKey).intValue() + 1);
				}

			}

			final TreeMap<String, Integer> ordenados = new TreeMap<>(new ComparadorValorMap(alunosComTreinos));
			ordenados.putAll(alunosComTreinos);

			for (final String key : ordenados.keySet()) {
				formatter.format(formato, key, alunosComTreinos.get(key));
			}

			System.out.println("Relatório gerado com sucesso!");
			System.out.println("Caminho: " + path);
		} catch (final Exception e) {
			System.out.println("Ocorreu um erro ao gerar relatório." + e.getMessage());
		}

	}

	/**
	 * Gera um relatório dos alunos ordenados por nome ASC
	 *
	 * @param path
	 * @param alunos
	 * @throws FileNotFoundException
	 */
	public static void relatorioAlunos(String path, List<Aluno> alunos) throws FileNotFoundException {
		final String formato = "%s\t\t%s\t\t%s\t\t%-21s\t\t%n";
		final SimpleDateFormat formataData = new SimpleDateFormat("dd-mm-yyyy");

		try (Formatter formatter = new Formatter(path)) {

			formatter.format("%s\t\t%s\t\t%s\t\t%-21s\t\t%n", "Código", "Nome", "CPF", "   Data nascimento");

			final AlunoComparator comparator = new AlunoComparator();
			Collections.sort(alunos, comparator);

			for (final Aluno aluno : alunos) {
				System.out.println(aluno.getNome());
				formatter.format(formato, aluno.getId(), aluno.getNome(), aluno.getCpf(), formataData.format(aluno.getDataNascimento()));
			}

			System.out.println("Relatório gerado com sucesso!");
			System.out.println("Caminho: " + path);
		} catch (final Exception e) {
			System.out.println("Ocorreu um erro ao gerar relatório." + e.getMessage());
		}

	}

}