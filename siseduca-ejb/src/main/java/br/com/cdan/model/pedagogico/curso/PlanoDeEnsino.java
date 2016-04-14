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
@Table(name = "PlanoDeEnsino")
public class PlanoDeEnsino implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "planoDeEnsino")
	private Disciplina_MatrizCurricular disciplina_MatrizCurricular;

	@Column(name = "ementa")
	private String ementa;

	@Column(name = "objetivosGerais")
	private String objetivosGerais;

	@Column(name = "objetivosEspecificos")
	private String objetivosEspecificos;

	@Column(name = "interdisciplinariedade")
	private String interdisciplinariedade;

	@Column(name = "contribuicaoParaOEgresso")
	private String contribuicaoParaOEgresso;

	@Column(name = "contribuicaoParaAInstituicao")
	private String contribuicaoParaAInstituicao;

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

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getObjetivosGerais() {
		return objetivosGerais;
	}

	public void setObjetivosGerais(String objetivosGerais) {
		this.objetivosGerais = objetivosGerais;
	}

	public String getObjetivosEspecificos() {
		return objetivosEspecificos;
	}

	public void setObjetivosEspecificos(String objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}

	public String getInterdisciplinariedade() {
		return interdisciplinariedade;
	}

	public void setInterdisciplinariedade(String interdisciplinariedade) {
		this.interdisciplinariedade = interdisciplinariedade;
	}

	public String getContribuicaoParaOEgresso() {
		return contribuicaoParaOEgresso;
	}

	public void setContribuicaoParaOEgresso(String contribuicaoParaOEgresso) {
		this.contribuicaoParaOEgresso = contribuicaoParaOEgresso;
	}

	public String getContribuicaoParaAInstituicao() {
		return contribuicaoParaAInstituicao;
	}

	public void setContribuicaoParaAInstituicao(
			String contribuicaoParaAInstituicao) {
		this.contribuicaoParaAInstituicao = contribuicaoParaAInstituicao;
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
		result = prime * result + ((ementa == null) ? 0 : ementa.hashCode());
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
		PlanoDeEnsino other = (PlanoDeEnsino) obj;
		if (disciplina_MatrizCurricular == null) {
			if (other.disciplina_MatrizCurricular != null)
				return false;
		} else if (!disciplina_MatrizCurricular
				.equals(other.disciplina_MatrizCurricular))
			return false;
		if (ementa == null) {
			if (other.ementa != null)
				return false;
		} else if (!ementa.equals(other.ementa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanoDeEnsino [disciplina_MatrizCurricular="
				+ disciplina_MatrizCurricular + ", ementa=" + ementa
				+ ", objetivosGerais=" + objetivosGerais
				+ ", objetivosEspecificos=" + objetivosEspecificos
				+ ", interdisciplinariedade=" + interdisciplinariedade
				+ ", contribuicaoParaOEgresso=" + contribuicaoParaOEgresso
				+ ", contribuicaoParaAInstituicao="
				+ contribuicaoParaAInstituicao + "]";
	}
}
