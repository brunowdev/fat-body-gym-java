package br.edu.fasatc.ec.fatbodygym.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Irá ordenar os maps que possuem um texto como chave e um valor quantitativo,
 * geralmente nos relatórios com totalizadores.
 *
 * @author BRUNO-PC
 *
 */
public class ComparadorValorMap implements Comparator<String> {

	private final Map<String, Integer> base;

	public ComparadorValorMap(Map<String, Integer> base) {
		this.base = base;
	}

	@Override
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}

}
