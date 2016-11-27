package br.edu.fasatc.ec.fatbodygym.persistence;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.exceptions.EntidadeNaoEncontradaException;
import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;
import br.edu.fasatc.ec.fatbodygym.model.SearchableString;

public class ReadWriteLocalFile<T extends AbstractEntidadeEntity & SearchableString> {

	private static final String CAMPO_ID = "id";

	private final String tabela;

	/**
	 * Construtor para capturar os dados de uma tabela.
	 *
	 * @param urlTable
	 */
	public ReadWriteLocalFile(String urlTable) {

		Objects.requireNonNull(urlTable);

		this.tabela = urlTable;
	}

	/**
	 * Carrega as entidades já persistidas
	 *
	 * @return
	 * @throws ReadFileException
	 */
	@SuppressWarnings("unchecked")
	private List<T> readPersistedEntities() throws ReadFileException {

		final List<T> entities = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(tabela); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			try {

				while (true) {
					entities.add((T) objectInputStream.readObject());
				}

			} catch (final EOFException e) {
			}

		} catch (final FileNotFoundException fnf) {
		} catch (final Exception e) {
			throw new ReadFileException(null, e);
		}

		return entities;

	}

	/**
	 * Persiste uma nova entidade ou atualiza uma já existente
	 *
	 * @param entity
	 * @return
	 * @throws WriteFileException
	 * @throws ReadFileException
	 */
	public T merge(T entity) throws WriteFileException, ReadFileException {

		final List<T> persisted = readPersistedEntities();

		try (FileOutputStream fileOutputStream = new FileOutputStream(tabela); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

			if (entity.getId() != null) {
				final int index = persisted.indexOf(entity);
				persisted.set(index, entity);
			} else {
				setId(entity, persisted);
				persisted.add(entity);
			}

			for (final T entityToPersist : persisted) {
				objectOutputStream.writeObject(entityToPersist);
			}

		} catch (final Exception e) {
			throw new WriteFileException(entity.getClass(), e);
		}

		return entity;
	}

	/**
	 * Remove um registro de uma tabela.
	 *
	 * @param entity
	 * @throws WriteFileException
	 * @throws ReadFileException
	 */
	public void remove(T entity) throws WriteFileException, ReadFileException {

		final Long idEntity = entity.getId();

		if (idEntity == null) {
			throw new IllegalStateException("A entidade não está persistida, portanto não pode ser removida.");
		}

		final List<T> persisted = readPersistedEntities();

		try (FileOutputStream fileOutputStream = new FileOutputStream(tabela); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

			final int index = persisted.indexOf(entity);
			persisted.remove(index);

			for (final T entityToPersist : persisted) {
				objectOutputStream.writeObject(entityToPersist);
			}

		} catch (final Exception e) {
			throw new WriteFileException(entity.getClass(), e);
		}

	}

	/**
	 * Retorna um registro de uma tabela.
	 *
	 * @param entity
	 * @return
	 * @throws EntidadeNaoEncontradaException
	 * @throws ReadFileException
	 */
	public T findOne(T entity) throws EntidadeNaoEncontradaException, ReadFileException {

		if (entity == null || entity.getId() == null) {
			throw new IllegalStateException("O id para busca não pode ser vazio.");
		}

		final List<T> persisted = readPersistedEntities();

		final int index = persisted.indexOf(entity);

		if (index == -1) {
			throw new EntidadeNaoEncontradaException("Não foi encontrado registro com id \"" + entity.getId() + "\"");
		}

		return persisted.get(persisted.indexOf(entity));
	}

	/**
	 * Busca uma entidade pelos campos de String
	 *
	 * @param query
	 * @return
	 * @throws ReadFileException
	 * @throws EntidadeNaoEncontradaException
	 */
	public T findByStringFields(String query) throws ReadFileException, EntidadeNaoEncontradaException {

		if (query == null) {
			throw new IllegalStateException("A query para busca não pode ser vazia.");
		}

		final List<T> persisted = readPersistedEntities();

		T entityMatch = null;

		for (final T t : persisted) {

			if (t.strictMatch(query) || t.containsMatch(query)) {
				entityMatch = t;
				break;
			}

		}

		if (entityMatch == null) {
			throw new EntidadeNaoEncontradaException("O registro procurado não foi localizado.");
		}

		return entityMatch;
	}

	/**
	 * Retorna todos os registros de uma tabela.
	 *
	 * @return
	 * @throws WriteFileException
	 * @throws ReadFileException
	 */
	public List<T> findAll() throws WriteFileException, ReadFileException {
		return readPersistedEntities();
	}

	/**
	 * Retorna a próxima sequência de id para uma entidade
	 *
	 * @param entities
	 * @return
	 */
	public Long getSequence(List<T> entities) {

		T entidadeLocalizada = null;

		if (entities == null || entities.isEmpty()) {
			return 1L;
		}

		entidadeLocalizada = entities.get(entities.size() - 1);

		return entidadeLocalizada == null ? 1L : (entidadeLocalizada.getId() + 1);

	}

	/**
	 * Método que define a sequência no objeto que será persistido
	 *
	 * @param entity
	 * @param entities
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public void setId(T entity, List<T> entities) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

		Field field = null;

		try {
			field = entity.getClass().getDeclaredField(CAMPO_ID);
		} catch (final NoSuchFieldException noSuchFieldException) {
			field = entity.getClass().getSuperclass().getDeclaredField(CAMPO_ID);
		}

		field.setAccessible(true);
		field.set(entity, getSequence(entities));
	}

}
