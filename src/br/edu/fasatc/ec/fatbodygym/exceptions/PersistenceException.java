package br.edu.fasatc.ec.fatbodygym.exceptions;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = -7682692577415323652L;

	private static final String message = "O ocorreu um erro ao %s a entidade \"%s\".";

	public PersistenceException() {

	}

	public PersistenceException(String acao, Class classe, Exception e) {
		super(String.format(message, "persistir", classe.getSimpleName(), e));
	}

	public PersistenceException(String acao, String classe, Exception e) {
		super(String.format(message, "persistir", classe), e);
	}

}
