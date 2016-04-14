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

import org.hibernate.validator.constraints.NotBlank;
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
	private Set<DisciplinaMatricula> disciplina;

	@NotNull
	@NotBlank
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
			@JoinColumn(name = "MATRICULA_id_turma", referencedColumnName = "id_turma", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "MATRICULA_id_disciplina", referencedColumnName = "id_disciplina", nullable = false, insertable = false, updatable = false) })
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

	public Set<DisciplinaMatricula> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Set<DisciplinaMatricula> disciplina) {
		this.disciplina = disciplina;
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
}