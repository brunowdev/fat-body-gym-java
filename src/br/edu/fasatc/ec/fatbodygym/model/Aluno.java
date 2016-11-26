package br.edu.fasatc.ec.fatbodygym.model;

import java.math.BigDecimal;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_ALUNOS)
public final class Aluno extends Pessoa implements SearchableString {

	private static final long serialVersionUID = 7793253166397502975L;

	public Aluno(Long id) {
		setId(id);
	}

	public Aluno() {
	}

	private BigDecimal peso;

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@Override
	public String[] getSearchableFields() {
		return new String[] { getCpf(), getNome(), getRg() };
	}

}
