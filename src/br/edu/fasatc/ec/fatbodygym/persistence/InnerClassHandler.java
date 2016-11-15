package br.edu.fasatc.ec.fatbodygym.persistence;

import com.google.common.reflect.TypeToken;

/**
 * Esta classe permite que seja identificada a classe em tempo de execução,
 * mesmo trabalhando com Generics.
 *
 * @author BRUNO-PC
 *
 * @param <T>
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class InnerClassHandler<T> {

	private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
	};

	protected final Class<T> type = (Class<T>) typeToken.getType();

}