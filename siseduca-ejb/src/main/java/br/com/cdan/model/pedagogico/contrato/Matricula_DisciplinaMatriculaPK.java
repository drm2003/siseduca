package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Matricula_DisciplinaMatriculaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long matricula;

	private Long disciplinaMatricula;

	public Matricula_DisciplinaMatriculaPK() {
	}

	public Matricula_DisciplinaMatriculaPK(Long matricula, Long disciplinaMatricula) {
		this.matricula = matricula;
		this.disciplinaMatricula = disciplinaMatricula;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Long getDisciplinaMatricula() {
		return disciplinaMatricula;
	}

	public void setDisciplinaMatricula(Long disciplinaMatricula) {
		this.disciplinaMatricula = disciplinaMatricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplinaMatricula == null) ? 0 : disciplinaMatricula.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Matricula_DisciplinaMatriculaPK other = (Matricula_DisciplinaMatriculaPK) obj;
		if (disciplinaMatricula == null) {
			if (other.disciplinaMatricula != null)
				return false;
		} else if (!disciplinaMatricula.equals(other.disciplinaMatricula))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Matricula_DisciplinaMatriculaPK [matricula=" + matricula + ", disciplinaMatricula="
				+ disciplinaMatricula + "]";
	}
}
