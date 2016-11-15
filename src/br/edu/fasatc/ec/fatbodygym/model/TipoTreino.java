package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;

public enum TipoTreino implements EnumDescription, EnumInteger, Serializable {

	FUNCIONAL(1, "Funcional"), HIPERTROFIA(2, "Hipertrofia muscular"), FORCA_MUSCULAR(3, "For�a muscular"), POTENCIA_MUSCULAR(4, "Pot�ncia muscular"), RESISTENCIA_MUSCULAR(5,
			"Resist�ncia muscular");

	private TipoTreino(Integer integer, String description) {
		this.integer = integer;
		this.description = description;
	}

	private final Integer integer;
	private final String description;

	@Override
	public Integer getInteger() {
		return integer;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
