package br.edu.fasatc.ec.fatbodygym.model;

import java.util.Date;

public abstract class Pessoa extends AbstractEntidadeEntity {

	private static final long serialVersionUID = 3128570043988823792L;

	private Long id;
	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private Boolean ativo;

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
