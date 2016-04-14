package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Disciplina_MatrizCurricularPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long disciplina;

	private Long matrizCurricular;

	public Disciplina_MatrizCurricularPK() {
	}

	public Disciplina_MatrizCurricularPK(Long disciplina, Long matrizCurricular) {
		this.disciplina = disciplina;
		this.matrizCurricular = matrizCurricular;
	}

	public Long getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Long disciplina) {
		this.disciplina = disciplina;
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
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
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
		Disciplina_MatrizCurricularPK other = (Disciplina_MatrizCurricularPK) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (matrizCurricular == null) {
			if (other.matrizCurricular != null)
				return false;
		} else if (!matrizCurricular.equals(other.matrizCurricular))
			return false;
		return true;
	}
}
