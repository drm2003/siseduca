package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.pedagogico.SituacaoDoAlunoNaTurma;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;
import br.com.cdan.model.pessoa.Aluno;

@Entity
@Table(name = "Matricula")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "matricula")
	private Set<DisciplinaMatricula> disciplinas;

	@NotNull
	@NotEmpty
	@Column(name = "numeroContrato", nullable = false, unique = true)
	private String numeroContrato;

	@Column(name = "dataInicio")
	private Calendar dataInicio;

	@Column(name = "dataTermino")
	private Calendar dataTermino;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "situacaoDoContrato")
	private SituacaoDoContrato situacaoDoContrato;

	@ManyToOne(fetch = FetchType.EAGER)
	private TipoDeContrato tipoDeContrato;

	@ManyToOne
	@JoinColumn(name = "situacaoDoAlunoNaTurma")
	private SituacaoDoAlunoNaTurma situacaoDoAlunoNaTurma;

	@ManyToOne
	@JoinColumn(name = "investimento")
	private Investimento investimento;

	@Column(name = "turma")
	private Long turma;

	@Column(name = "matrizCurricular")
	private Long matrizCurricular;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(value = {
			@JoinColumn(name = "MATRICULA_id_turma", referencedColumnName = "id_turma", nullable = false),
			@JoinColumn(name = "MATRICULA_id_disciplina", referencedColumnName = "id_disciplina", nullable = false) })
	private Turma_Disciplina turma_disciplina;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Set<DisciplinaMatricula> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<DisciplinaMatricula> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

	public SituacaoDoContrato getSituacaoDoContrato() {
		return situacaoDoContrato;
	}

	public void setSituacaoDoContrato(SituacaoDoContrato situacaoDoContrato) {
		this.situacaoDoContrato = situacaoDoContrato;
	}

	public TipoDeContrato getTipoDeContrato() {
		return tipoDeContrato;
	}

	public void setTipoDeContrato(TipoDeContrato tipoDeContrato) {
		this.tipoDeContrato = tipoDeContrato;
	}

	public SituacaoDoAlunoNaTurma getSituacaoDoAlunoNaTurma() {
		return situacaoDoAlunoNaTurma;
	}

	public void setSituacaoDoAlunoNaTurma(SituacaoDoAlunoNaTurma situacaoDoAlunoNaTurma) {
		this.situacaoDoAlunoNaTurma = situacaoDoAlunoNaTurma;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	public Long getTurma() {
		return turma;
	}

	public void setTurma(Long turma) {
		this.turma = turma;
	}

	public Long getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(Long matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
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
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
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
		Matricula other = (Matricula) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (disciplinas == null) {
			if (other.disciplinas != null)
				return false;
		} else if (!disciplinas.equals(other.disciplinas))
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
		return "Matricula [id=" + id + ", aluno=" + aluno + ", disciplinas=" + disciplinas + ", numeroContrato="
				+ numeroContrato + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino
				+ ", situacaoDoContrato=" + situacaoDoContrato + ", tipoDeContrato=" + tipoDeContrato
				+ ", situacaoDoAlunoNaTurma=" + situacaoDoAlunoNaTurma + ", investimento=" + investimento + ", turma="
				+ turma + ", matrizCurricular=" + matrizCurricular + ", turma_disciplina=" + turma_disciplina
				+ ", ativo=" + ativo + "]";
	}
}