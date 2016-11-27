package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;

public enum TipoTreino implements EnumDescription, EnumInteger, Serializable {

	FUNCIONAL(1, "Funcional"), HIPERTROFIA(2, "Hipertrofia muscular"), FORCA_MUSCULAR(3, "Força muscular"), POTENCIA_MUSCULAR(4, "Potência muscular"), RESISTENCIA_MUSCULAR(5,
			"Resistência muscular");

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

	public static TipoTreino fromInteger(Integer integer) {

		switch (integer) {
		case 1:
			return FUNCIONAL;
		case 2:
			return HIPERTROFIA;
		case 3:
			return FORCA_MUSCULAR;
		case 4:
			return POTENCIA_MUSCULAR;
		case 5:
			return RESISTENCIA_MUSCULAR;

		default:
			return null;
		}

	}

}
