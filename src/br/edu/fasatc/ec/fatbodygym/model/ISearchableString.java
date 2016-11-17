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
	 * Utilizar a sequ�ncia mais perform�tica.
	 *
	 * @return
	 */
	public String[] getSearchableFields();

	/**
	 * M�todo que efetua uma consulta nos campos mapeados com searchable.
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
	 * M�todo que efetua uma consulta nos campos mapeados com searchable.
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
