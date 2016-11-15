package br.edu.fasatc.ec.fatbodygym.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_USUARIOS)
public class Usuario extends AbstractEntidadeEntity {

	private static final long serialVersionUID = -429947733530672916L;

	@NotNull(message = "O id � requerido para persistir um objeto.")
	private Long id;

	@Length(max = 120, message = "O tamanho m�ximo do e-mail � de 120 caracteres.")
	@Email(message = "Este e-mail n�o � v�lido.")
	private String email;

	@Length(min = 4, max = 20, message = "A senha deve possuir entre 4 e 20 caracteres.")
	private String senha;

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
