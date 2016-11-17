package br.edu.fasatc.ec.fatbodygym.model;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_INSTRUTORES)
public final class Instrutor extends Pessoa implements ISearchableString {

	private static final long serialVersionUID = -5022466853513308191L;

	@Override
	public String[] getSearchableFields() {
		return new String[] { getCpf(), getNome(), getRg() };
	}

}
