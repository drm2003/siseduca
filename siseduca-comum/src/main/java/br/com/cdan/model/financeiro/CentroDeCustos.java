package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CentroDeCustos")
public class CentroDeCustos implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 160)
	@Column(name = "nome", length = 160, nullable = false, unique = true)
	private String nome;

	@OneToMany(mappedBy = "centroDeCustos")
	private Set<PlanoDeContas_CentroDeCustos> planoDeContas_CentroDeCustos;

	@NotNull
	@Column(name = "compartilhado")
	private Boolean compartilhado;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Set<PlanoDeContas_CentroDeCustos> getPlanoDeContas_CentroDeCustos() {
		return planoDeContas_CentroDeCustos;
	}

	public void setPlanoDeContas_CentroDeCustos(Set<PlanoDeContas_CentroDeCustos> planoDeContas_CentroDeCustos) {
		this.planoDeContas_CentroDeCustos = planoDeContas_CentroDeCustos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((planoDeContas_CentroDeCustos == null) ? 0 : planoDeContas_CentroDeCustos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentroDeCustos other = (CentroDeCustos) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (planoDeContas_CentroDeCustos == null) {
			if (other.planoDeContas_CentroDeCustos != null)
				return false;
		} else if (!planoDeContas_CentroDeCustos.equals(other.planoDeContas_CentroDeCustos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CentroDeCustos [id=" + id + ", planoDeContas_CentroDeCustos=" + planoDeContas_CentroDeCustos + ", nome="
				+ nome + ", compartilhado=" + compartilhado + ", ativo=" + ativo + "]";
	}
}
