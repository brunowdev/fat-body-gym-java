package br.edu.fasatc.ec.fatbodygym.relatorios;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.fasatc.ec.fatbodygym.model.Exercicio;
import br.edu.fasatc.ec.fatbodygym.model.Treino;
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

}