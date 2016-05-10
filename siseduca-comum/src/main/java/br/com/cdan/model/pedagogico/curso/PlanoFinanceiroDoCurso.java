package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PlanoFinanceiroDoCurso")
public class PlanoFinanceiroDoCurso implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso")
	private Curso curso;

	@Column(name = "modulo")
	private Long modulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "planoPadrao")
	private Boolean planoPadrao;

	@Column(name = "ativo")
	private Boolean ativo;

	@OneToMany
	private Set<Investimento> investimentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Long getModulo() {
		return modulo;
	}

	public void setModulo(Long modulo) {
		this.modulo = modulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getPlanoPadrao() {
		return planoPadrao;
	}

	public void setPlanoPadrao(Boolean planoPadrao) {
		this.planoPadrao = planoPadrao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Investimento> getInvestimentos() {
		return investimentos;
	}

	public void setInvestimentos(Set<Investimento> investimentos) {
		this.investimentos = investimentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((investimentos == null) ? 0 : investimentos.hashCode());
		result = prime * result + ((modulo == null) ? 0 : modulo.hashCode());
		result = prime * result + ((planoPadrao == null) ? 0 : planoPadrao.hashCode());
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
		PlanoFinanceiroDoCurso other = (PlanoFinanceiroDoCurso) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (investimentos == null) {
			if (other.investimentos != null)
				return false;
		} else if (!investimentos.equals(other.investimentos))
			return false;
		if (modulo == null) {
			if (other.modulo != null)
				return false;
		} else if (!modulo.equals(other.modulo))
			return false;
		if (planoPadrao == null) {
			if (other.planoPadrao != null)
				return false;
		} else if (!planoPadrao.equals(other.planoPadrao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanoFinanceiroDoCurso [id=" + id + ", curso=" + curso + ", modulo=" + modulo + ", descricao="
				+ descricao + ", planoPadrao=" + planoPadrao + ", ativo=" + ativo + ", investimento=" + investimentos
				+ "]";
	}
}
