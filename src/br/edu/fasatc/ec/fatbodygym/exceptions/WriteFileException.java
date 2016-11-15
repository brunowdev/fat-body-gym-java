package br.edu.fasatc.ec.fatbodygym.exceptions;

public class WriteFileException extends PersistenceException {

	private static final long serialVersionUID = -3712808954455220847L;

	public WriteFileException(Class classe, Exception e) {
		super("persistir", classe.getSimpleName(), e);
	}

}
