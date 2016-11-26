package br.edu.fasatc.ec.fatbodygym.persistence;

import java.util.List;

import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;
import br.edu.fasatc.ec.fatbodygym.exceptions.EntidadeNaoEncontradaException;
import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;
import br.edu.fasatc.ec.fatbodygym.model.SearchableString;

public abstract class BaseReposity<T extends AbstractEntidadeEntity & SearchableString, PK> extends InnerClassHandler<T> {

	/**
	 * Captura a tabela de acordo com a anotação na classe.
	 */
	private final String tabela = type.getDeclaredAnnotation(LocalFileAsTable.class).tableName();

	public T merge(T entity) throws WriteFileException, ReadFileException {
		return readWriteLocalFile().merge(entity);
	}

	public void remove(T entity) throws WriteFileException, ReadFileException {
		readWriteLocalFile().remove(entity);
	}

	public T findById(T entity) throws ReadFileException, EntidadeNaoEncontradaException {
		return readWriteLocalFile().findOne(entity);
	}

	public T findByStringFields(String query) throws ReadFileException, EntidadeNaoEncontradaException {
		return readWriteLocalFile().findByStringFields(query);
	}

	public List<T> findAll() throws WriteFileException, ReadFileException {
		return readWriteLocalFile().findAll();
	}

	private ReadWriteLocalFile<T> readWriteLocalFile() {
		return new ReadWriteLocalFile<>(tabela);
	}

}
