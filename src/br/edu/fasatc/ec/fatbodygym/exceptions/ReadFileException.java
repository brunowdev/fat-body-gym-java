package br.edu.fasatc.ec.fatbodygym.exceptions;

import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;

public class ReadFileException extends PersistenciaException {

    private static final long serialVersionUID = 1813044456255215385L;

    public ReadFileException(Class<? extends AbstractEntidadeEntity> classe, Exception e) {
        super("recuperar", classe.getSimpleName(), e);
    }

}
