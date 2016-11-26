package br.edu.fasatc.ec.fatbodygym.model;

import java.io.Serializable;

import br.edu.fasatc.ec.fatbodygym.constansts.ErpDatabaseConstants;
import br.edu.fasatc.ec.fatbodygym.constansts.LocalFileAsTable;

@LocalFileAsTable(tableName = ErpDatabaseConstants.TABLE_EXERCICIOS)
public class Exercicio extends AbstractEntidadeEntity implements Serializable, SearchableString {

	private static final long serialVersionUID = 9093881942761085438L;

	private Long id;
	private String nome;
	private Integer series;
	private Integer quantidadeSerie;
	private TipoExercicio tipoExercicio;
	private Boolean ativo;

	public Exercicio() {
	}

	public Exercicio(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getSeries() {
		return series;
	}

	public Integer getQuantidadeSerie() {
		return quantidadeSerie;
	}

	public TipoExercicio getTipoExercicio() {
		return tipoExercicio;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public void setQuantidadeSerie(Integer quantidadeSerie) {
		this.quantidadeSerie = quantidadeSerie;
	}

	public void setTipoExercicio(TipoExercicio tipoExercicio) {
		this.tipoExercicio = tipoExercicio;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		final Exercicio other = (Exercicio) obj;
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
		return new String[] { getNome() };
	}

}
