package br.edu.fasatc.ec.fatbodygym.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_USUARIOS)
public class Usuario extends AbstractEntidadeEntity implements SearchableString {

	private static final long serialVersionUID = -429947733530672916L;

	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	@NotNull(message = "O id é requerido para persistir um objeto.")
	private Long id;

	@Length(max = 120, message = "O tamanho máximo do e-mail é de 120 caracteres.")
	@Email(message = "Este e-mail não é válido.")
	private String email;

	@Length(min = 4, max = 20, message = "A senha deve possuir entre 4 e 20 caracteres.")
	private String senha;

	private Aluno aluno;

	private Instrutor instrutor;

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

	public Aluno getAluno() {
		return aluno;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
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

	public void hidePersonalFields() {
		this.id = Long.MAX_VALUE;
		this.senha = "*******";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String[] getSearchableFields() {
		return new String[] { getEmail() };
	}

	/**
	 * A busca pelo usuário deverá ser exata para verificar se o usuário já
	 * existe. Se for necessário, pode ser criada uma outra implementação no
	 * repository.
	 */
	@Override
	public boolean containsMatch(String query) {
		return false;
	}

}
