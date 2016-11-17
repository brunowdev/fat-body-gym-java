package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;

public abstract class AbstractEntidadeEntity implements Serializable {

	private static final long serialVersionUID = 8517208593127592387L;

	public abstract Long getId();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

}
