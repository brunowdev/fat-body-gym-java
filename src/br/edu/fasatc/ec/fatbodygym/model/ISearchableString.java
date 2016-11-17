package br.edu.fasatc.ec.fatbodygym.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface para permitir busca na entidade por mais de um campo.
 *
 * @author BRUNO-PC
 *
 */
public interface ISearchableString {

	/**
	 * Utilizar a sequência mais performática.
	 *
	 * @return
	 */
	public String[] getSearchableFields();

	/**
	 * Método que efetua uma consulta nos campos mapeados com searchable.
	 *
	 * @param query
	 * @return
	 */
	public default boolean strictMatch(String query) {

		final String[] values = getSearchableFields();

		final List<String> valid = Arrays.asList(values).stream().filter(value -> value != null).collect(Collectors.toList());

		for (final String field : valid) {
			if (field.equals(query)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Método que efetua uma consulta nos campos mapeados com searchable.
	 *
	 * @param query
	 * @return
	 */
	public default boolean containsMatch(String query) {

		final String[] values = getSearchableFields();

		final List<String> valid = Arrays.asList(values).stream().filter(value -> value != null).collect(Collectors.toList());

		for (final String field : valid) {
			if (field.contains(query)) {
				return true;
			}
		}

		return false;
	}

}
