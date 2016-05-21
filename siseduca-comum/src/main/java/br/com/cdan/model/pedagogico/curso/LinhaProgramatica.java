package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LinhaProgramatica")
public class LinhaProgramatica implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumns(/* foreignKey = @ForeignKey(name = "FK_linhaProgramatica"), */value = {
			@JoinColumn(name = "LINHAPROGRAMATICA_id_disciplina", referencedColumnName = "id_disciplina", nullable = false),
			@JoinColumn(name = "LINHAPROGRAMATICA_id_matrizCurricular", referencedColumnName = "id_matrizCurricular", nullable = false) })
	private Disciplina_MatrizCurricular disciplina_MatrizCurricular;

	@Column(name = "aula")
	private Long aula;

	@Column(name = "unidade")
	private String unidade;

	@Column(name = "subunidade")
	private String subunidade;

	@Column(name = "assunto")
	private String assunto;

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

	public void setDisciplina_MatrizCurricular(Disciplina_MatrizCurricular disciplina_MatrizCurricular) {
		this.disciplina_MatrizCurricular = disciplina_MatrizCurricular;
	}

	public Long getAula() {
		return aula;
	}

	public void setAula(Long aula) {
		this.aula = aula;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getSubunidade() {
		return subunidade;
	}

	public void setSubunidade(String subunidade) {
		this.subunidade = subunidade;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
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
		result = prime * result + ((disciplina_MatrizCurricular == null) ? 0 : disciplina_MatrizCurricular.hashCode());
		result = prime * result + ((subunidade == null) ? 0 : subunidade.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		LinhaProgramatica other = (LinhaProgramatica) obj;
		if (disciplina_MatrizCurricular == null) {
			if (other.disciplina_MatrizCurricular != null)
				return false;
		} else if (!disciplina_MatrizCurricular.equals(other.disciplina_MatrizCurricular))
			return false;
		if (subunidade == null) {
			if (other.subunidade != null)
				return false;
		} else if (!subunidade.equals(other.subunidade))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LinhaProgramatica [disciplina_MatrizCurricular=" + disciplina_MatrizCurricular + ", aula=" + aula
				+ ", unidade=" + unidade + ", subunidade=" + subunidade + ", assunto=" + assunto + "]";
	}
}
