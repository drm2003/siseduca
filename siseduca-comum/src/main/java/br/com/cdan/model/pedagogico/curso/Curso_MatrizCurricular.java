package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Curso_MatrizCurricular")
public class Curso_MatrizCurricular implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private Curso_MatrizCurricularPK id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_curso", nullable = false)
	private Curso curso;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_matrizCurricular", nullable = false)
	private MatrizCurricular matrizCurricular;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataValidade", nullable = false)
	private Calendar dataValidade;

	@Column(name = "ativo")
	private Boolean ativo;

	public Curso_MatrizCurricularPK getId() {
		return id;
	}

	public void setId(Curso_MatrizCurricularPK id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Curso_MatrizCurricular other = (Curso_MatrizCurricular) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso_MatrizCurricular [id=" + id + ", curso=" + curso + ", matrizCurricular=" + matrizCurricular
				+ ", dataValidade=" + dataValidade + "]";
	}

}
