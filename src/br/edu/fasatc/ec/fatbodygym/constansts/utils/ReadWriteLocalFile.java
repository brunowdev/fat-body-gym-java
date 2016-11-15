package br.edu.fasatc.ec.fatbodygym.constansts.utils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Objects;

import br.edu.fasatc.ec.fatbodygym.exceptions.EntidadeNaoEncontradaException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;

@SuppressWarnings("unchecked")
public class ReadWriteLocalFile<T extends AbstractEntidadeEntity> {

	private final String tabela;

	public ReadWriteLocalFile(String urlTable) {

		Objects.requireNonNull(urlTable);

		this.tabela = urlTable;
	}

	public T merge(T entity) throws WriteFileException {

		try (FileOutputStream fileOutputStream = new FileOutputStream(tabela); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {

			if (entity.getId() == null) {
				setId(entity);
			}

			objectOutputStream.writeObject(entity);

		} catch (final Exception e) {
			throw new WriteFileException(entity.getClass(), e);
		}

		return entity;
	}

	public void remove(T entity) throws WriteFileException {

		final Long idEntity = entity.getId();

		if (idEntity == null) {
			throw new IllegalStateException("A entidade não está persistida, portanto não pode ser removida.");
		}

		final T entidadeLocalizada = null;

		try (FileInputStream fileInputStream = new FileInputStream(new File(tabela)); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {

		} catch (final Exception e) {
			throw new WriteFileException(entity.getClass(), e);
		}

	}

	private T localizarEntidade(T entidade) throws EntidadeNaoEncontradaException {

		final Long idEntity = entidade.getId();

		if (idEntity == null) {
			throw new IllegalStateException("A entidade não está persistida, portanto não pode ser removida.");
		}

		T entidadeLocalizada = null;

		try (FileInputStream fileInputStream = new FileInputStream(new File(tabela)); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {

			try {

				while (true) {

					final T persistedEntity = (T) objectInputStream.readObject();

					if (persistedEntity.getId() == idEntity) {
						entidadeLocalizada = persistedEntity;
						break;
					}

				}

			} catch (final EOFException eof) {
			}

		} catch (final Exception e) {
		}

		if (entidadeLocalizada == null) {
			throw new EntidadeNaoEncontradaException("A entidade com id " + idEntity + " não foi encontrada.", null);
		}

		return entidadeLocalizada;

	}

	/**
	 * Retorna a próxima sequência de id para uma entidade
	 *
	 * @return
	 */
	public synchronized Long getSequence() {

		T entidadeLocalizada = null;

		try (FileInputStream fileInputStream = new FileInputStream(tabela); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {

			try {

				while (true) {
					final T persistedEntity = (T) objectInputStream.readObject();
					entidadeLocalizada = persistedEntity;
				}

			} catch (final EOFException eof) {
			}

		} catch (final Exception e) {
			System.out.println("Ocorreu um erro ao obter a sequência." + e);
		}

		return entidadeLocalizada == null ? 1L : (entidadeLocalizada.getId() + 1);

	}

	/**
	 * Método que define a sequência no objeto que será persistido
	 *
	 * @param entity
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public void setId(T entity) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		final Field field = entity.getClass().getDeclaredField("id");
		field.setAccessible(true);
		field.set(entity, getSequence());
	}

}
