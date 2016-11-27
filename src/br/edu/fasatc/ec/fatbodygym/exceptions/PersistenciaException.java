package br.edu.fasatc.ec.fatbodygym.exceptions;

import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;

public class PersistenciaException extends Exception {

	private static final long serialVersionUID = -7682692577415323652L;

	private static final String MENSAGEM = "O ocorreu um erro ao %s a entidade \"%s\".";

	public PersistenciaException() {

	}

	public PersistenciaException(String acao, Class<? extends AbstractEntidadeEntity> classe, Exception e) {
		super(String.format(MENSAGEM, acao, classe.getSimpleName(), e));
	}

	public PersistenciaException(String acao, String classe, Exception e) {
		super(String.format(MENSAGEM, acao, classe), e);
	}

}
