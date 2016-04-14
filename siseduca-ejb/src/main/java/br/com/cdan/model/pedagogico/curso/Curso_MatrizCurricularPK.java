package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Curso_MatrizCurricularPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long curso;

	private Long matrizCurricular;

	public Curso_MatrizCurricularPK() {
	}

	public Curso_MatrizCurricularPK(Long curso, Long matrizCurricular) {
		this.curso = curso;
		this.matrizCurricular = matrizCurricular;
	}

	public Long getCurso() {
		return curso;
	}

	public void setCurso(Long curso) {
		this.curso = curso;
	}

	public Long getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(Long matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((matrizCurricular == null) ? 0 : matrizCurricular.hashCode());
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
		Curso_MatrizCurricularPK other = (Curso_MatrizCurricularPK) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (matrizCurricular == null) {
			if (other.matrizCurricular != null)
				return false;
		} else if (!matrizCurricular.equals(other.matrizCurricular))
			return false;
		return true;
	}
}
