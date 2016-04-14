package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Metodologia")
public class Metodologia implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "metodologia")
	private Disciplina_MatrizCurricular disciplina_MatrizCurricular;

	@Column(name = "tecnicasDeEnsino")
	private String tecnicasDeEnsino;

	@Column(name = "recursosDidaticos")
	private String recursosDidaticos;

	@Column(name = "criteriosDeAvaliacao")
	private String criteriosDeAvaliacao;

	@Column(name = "atividadesPraticas")
	private String atividadesPraticas;

	@Column(name = "bibliografiaBasica")
	private String bibliografiaBasica;

	@Column(name = "bibliografiaComplementar")
	private String bibliografiaComplementar;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Disciplina_MatrizCurricular getDisciplina_MatrizCurricular() {
		return disciplina_MatrizCurricular;
	}

	public void setDisciplina_MatrizCurricular(
			Disciplina_MatrizCurricular disciplina_MatrizCurricular) {
		this.disciplina_MatrizCurricular = disciplina_MatrizCurricular;
	}

	public String getTecnicasDeEnsino() {
		return tecnicasDeEnsino;
	}

	public void setTecnicasDeEnsino(String tecnicasDeEnsino) {
		this.tecnicasDeEnsino = tecnicasDeEnsino;
	}

	public String getRecursosDidaticos() {
		return recursosDidaticos;
	}

	public void setRecursosDidaticos(String recursosDidaticos) {
		this.recursosDidaticos = recursosDidaticos;
	}

	public String getCriteriosDeAvaliacao() {
		return criteriosDeAvaliacao;
	}

	public void setCriteriosDeAvaliacao(String criteriosDeAvaliacao) {
		this.criteriosDeAvaliacao = criteriosDeAvaliacao;
	}

	public String getAtividadesPraticas() {
		return atividadesPraticas;
	}

	public void setAtividadesPraticas(String atividadesPraticas) {
		this.atividadesPraticas = atividadesPraticas;
	}

	public String getBibliografiaBasica() {
		return bibliografiaBasica;
	}

	public void setBibliografiaBasica(String bibliografiaBasica) {
		this.bibliografiaBasica = bibliografiaBasica;
	}

	public String getBibliografiaComplementar() {
		return bibliografiaComplementar;
	}

	public void setBibliografiaComplementar(String bibliografiaComplementar) {
		this.bibliografiaComplementar = bibliografiaComplementar;
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
		result = prime
				* result
				+ ((disciplina_MatrizCurricular == null) ? 0
						: disciplina_MatrizCurricular.hashCode());
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
		Metodologia other = (Metodologia) obj;
		if (disciplina_MatrizCurricular == null) {
			if (other.disciplina_MatrizCurricular != null)
				return false;
		} else if (!disciplina_MatrizCurricular
				.equals(other.disciplina_MatrizCurricular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Metodologia [disciplina_MatrizCurricular="
				+ disciplina_MatrizCurricular + ", tecnicasDeEnsino="
				+ tecnicasDeEnsino + ", recursosDidaticos=" + recursosDidaticos
				+ ", criteriosDeAvaliacao=" + criteriosDeAvaliacao
				+ ", atividadesPraticas=" + atividadesPraticas
				+ ", bibliografiaBasica=" + bibliografiaBasica
				+ ", bibliografiaComplementar=" + bibliografiaComplementar
				+ "]";
	}
}
