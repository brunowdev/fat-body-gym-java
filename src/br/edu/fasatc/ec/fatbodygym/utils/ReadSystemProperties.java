package br.edu.fasatc.ec.fatbodygym.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Atualmente esta classe não é utilizada.
 *
 * @author BRUNO-PC
 *
 */
public class ReadSystemProperties {

	private final Properties properties;

	private ReadSystemProperties(String url) throws IllegalStateException {
		this.properties = new Properties();

		try (FileInputStream fis = new FileInputStream(new File(url))) {
			this.properties.load(fis);
		} catch (final Exception e) {
			throw new IllegalStateException("Ocorreu um erro ao ler um arquivo de propriedades.", e);
		}

	}

	public static ReadSystemProperties create(String url) throws IllegalStateException {

		Objects.requireNonNull(url);

		return new ReadSystemProperties(url);
	}

	/**
	 * Recupera uma propriedade em um arquivo de configuração ou retorna um
	 * valor padrão.
	 *
	 * @param propertie
	 * @param orValue
	 * @return
	 */
	public String readPropertieOrNull(String propertie, String orValue) {
		return Optional.of(properties.getProperty(propertie)).orElse(orValue);
	}

}
