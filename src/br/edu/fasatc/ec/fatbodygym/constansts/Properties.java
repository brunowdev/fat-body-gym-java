package br.edu.fasatc.ec.fatbodygym.constansts;

import javax.resource.spi.IllegalStateException;

import br.edu.fasatc.ec.fatbodygym.constansts.utils.ReadSystemProperties;

public class Properties {

	public static class Persistence {

		public static ReadSystemProperties proprieades;

		static {

			try {
				proprieades = ReadSystemProperties.create("database.properties");
			} catch (final IllegalStateException e) {
				e.printStackTrace();
			}

		}

		private static final String TABLE_EXTENSION = "persistence.table.extension";

		public static String getTableExtension() {
			return Persistence.proprieades.readPropertieOrNull(TABLE_EXTENSION, ".dat");

		}

	}

}
