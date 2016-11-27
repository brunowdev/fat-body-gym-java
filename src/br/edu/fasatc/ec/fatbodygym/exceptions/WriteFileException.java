package br.edu.fasatc.ec.fatbodygym.exceptions;

import br.edu.fasatc.ec.fatbodygym.model.AbstractEntidadeEntity;

public class WriteFileException extends PersistenciaException {

    private static final long serialVersionUID = -3712808954455220847L;

    public WriteFileException(Class<? extends AbstractEntidadeEntity> classe, Exception e) {
        super("persistir", classe.getSimpleName(), e);
    }

}
