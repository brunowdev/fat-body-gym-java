package br.edu.fasatc.ec.fatbodygym.constansts.utils;

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

public class ReadWriteLocalFile<T extends AbstractEntidadeEntity> {

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
	 * Carrega as entidades j� persistidas
	 *
	 * @return
	 * @throws ReadFileException
	 */
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
	 * Persiste uma nova entidade ou atualiza uma j� existente
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
			throw new IllegalStateException("A entidade n�o est� persistida, portanto n�o pode ser removida.");
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
			throw new IllegalStateException("O id para busca n�o pode ser vazio.");
		}

		final List<T> persisted = readPersistedEntities();

		final int index = persisted.indexOf(entity);

		if (index == -1) {
			throw new EntidadeNaoEncontradaException("N�o foi encontrado registro com id \"" + entity.getId() + "\"");
		}

		return persisted.get(persisted.indexOf(entity));
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
	 * Retorna a pr�xima sequ�ncia de id para uma entidade
	 *
	 * @return
	 */
	public synchronized Long getSequence(List<T> entities) {

		T entidadeLocalizada = null;

		if (entities == null || entities.size() == 0) {
			return 1L;
		}

		entidadeLocalizada = entities.get(entities.size() - 1);

		return entidadeLocalizada == null ? 1L : (entidadeLocalizada.getId() + 1);

	}

	/**
	 * M�todo que define a sequ�ncia no objeto que ser� persistido
	 *
	 * @param entity
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public void setId(T entity, List<T> entities) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		final Field field = entity.getClass().getDeclaredField("id");
		field.setAccessible(true);
		field.set(entity, getSequence(entities));
	}

}
