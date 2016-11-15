package br.edu.fasatc.ec.fatbodygym.exceptions;

public class EntidadeNaoEncontradaException extends Exception {

	private static final long serialVersionUID = -1518423232148330195L;

	public EntidadeNaoEncontradaException(String message, Exception e) {
		super(String.format(message, e));
	}

}
