package br.edu.fasatc.ec.fatbodygym.persistence;

import java.util.List;
import java.util.Optional;

import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;
import br.edu.fasatc.ec.fatbodygym.constansts.utils.ReadWriteLocalFile;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;

public abstract class BaseReposity<T extends AbstractEntidadeEntity, PK> extends InnerClassHandler<T> {

	/**
	 * Captura a tabela de acordo com a anotação na classe.
	 */
	private final String tabela = type.getDeclaredAnnotation(LocalFileAsTable.class).tableName();

	public T merge(T entity) throws WriteFileException {

		final ReadWriteLocalFile<T> writeFile = new ReadWriteLocalFile<>(tabela);

		writeFile.merge(entity);

		return entity;
	}

	public Optional<T> remove() {

		return null;
	}

	public Optional<T> findById(PK id) {
		return null;
	}

	public Optional<List<T>> findAll() {
		return null;
	}

}
