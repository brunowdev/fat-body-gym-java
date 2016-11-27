package br.edu.fasatc.ec.fatbodygym.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Menu base para os menus da aplicação
 *
 * @author BRUNO-PC
 */
public abstract class AbstractBaseMenu {

	public abstract int getOpcaoMinima();

	public abstract int getOpcaoMaxima();

	protected Scanner scanner;

	public AbstractBaseMenu(Scanner scanner) {

		if (scanner == null) {
			throw new NullPointerException("Scanner inválido.");
		}

		this.scanner = scanner;
	}

	public abstract int selecionarOpcao();

	public String lerTexto() {
		final String texto = this.scanner.nextLine();
		return texto;
	}

	public Integer lerInteiro() {
		final Integer integer = this.scanner.nextInt();
		scanner.nextLine();
		return integer;
	}

	public Long lerLong() {
		final Long longg = this.scanner.nextLong();
		scanner.nextLine();
		return longg;
	}

	public BigDecimal lerBigdecimal() {
		final BigDecimal bigDecimal = this.scanner.nextBigDecimal();
		scanner.nextLine();
		return bigDecimal;
	}

	public Date lerData(boolean lerAteSerValido) {

		Date data = null;
		final boolean valorValido = false;

		while (!valorValido) {

			try {

				final String dateFormat = "dd/MM/yyyy";
				data = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());
				return data;
			} catch (final Exception e) {
				if (!lerAteSerValido) {
					return data;
				}
				System.out.println("Valor inválido! Informe uma data no formato dd/mm/aaaa");
			}

		}

	}

}
