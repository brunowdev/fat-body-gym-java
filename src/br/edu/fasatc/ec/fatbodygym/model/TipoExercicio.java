package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;

public enum TipoExercicio implements EnumDescription, EnumInteger, Serializable {

	ZUMBA(1, "Zumba"), SPINNING(2, "Spinning"), CROSSFIT(3, "Crossfit"), KETTLEBELLS(4, "Keetlebells"), BOOTCAMP(5, "Bootcamp"), YOGA(6, "Yoga"), MUAY_THAY(7,
			"Muay Thai"), HIPERTROFIA_MUSCULAR(8, "Hipertrofia muscular");

	private TipoExercicio(Integer integer, String description) {
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
