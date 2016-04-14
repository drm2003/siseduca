package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cdan.comum.EnumOcorrenciaAlunoTurma;
import br.com.cdan.model.pedagogico.TipoDeOcorrencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "Ocorrencia")
public class Ocorrencia implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "ocorrenciaAlunoTurma")
	private EnumOcorrenciaAlunoTurma ocorrenciaAlunoTurma;

	@Column(name = "curso")
	private String curso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@Column(name = "data")
	private Calendar data;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipoDeOcorrencia")
	private TipoDeOcorrencia tipoDeOcorrencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@Column(name = "periodo")
	private String periodo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumOcorrenciaAlunoTurma getOcorrenciaAlunoTurma() {
		return ocorrenciaAlunoTurma;
	}

	public void setOcorrenciaAlunoTurma(EnumOcorrenciaAlunoTurma ocorrenciaAlunoTurma) {
		this.ocorrenciaAlunoTurma = ocorrenciaAlunoTurma;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public TipoDeOcorrencia getTipoDeOcorrencia() {
		return tipoDeOcorrencia;
	}

	public void setTipoDeOcorrencia(TipoDeOcorrencia tipoDeOcorrencia) {
		this.tipoDeOcorrencia = tipoDeOcorrencia;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((ocorrenciaAlunoTurma == null) ? 0 : ocorrenciaAlunoTurma.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result + ((tipoDeOcorrencia == null) ? 0 : tipoDeOcorrencia.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (ocorrenciaAlunoTurma != other.ocorrenciaAlunoTurma)
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (tipoDeOcorrencia == null) {
			if (other.tipoDeOcorrencia != null)
				return false;
		} else if (!tipoDeOcorrencia.equals(other.tipoDeOcorrencia))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", ocorrenciaAlunoTurma=" + ocorrenciaAlunoTurma + ", curso=" + curso
				+ ", turma=" + turma + ", aluno=" + aluno + ", data=" + data + ", tipoDeOcorrencia=" + tipoDeOcorrencia
				+ ", funcionario=" + funcionario + ", periodo=" + periodo + "]";
	}
}