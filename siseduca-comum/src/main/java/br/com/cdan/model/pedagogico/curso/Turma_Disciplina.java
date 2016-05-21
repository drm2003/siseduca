package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "Turma_Disciplina")
public class Turma_Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private Turma_DisciplinaPK id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_turma", nullable = false)
	private Turma turma;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_disciplina", nullable = false)
	private Disciplina disciplina;

	@OneToMany(mappedBy = "turma_disciplina")
	private Set<Matricula> matriculas;

	@OneToOne
	@JoinColumn(name = "diaDaSemanaAula")
	private DiaDaSemanaAula diaDaSemanaAula;

	@Column(name = "salaDeAula")
	private String salaDeAula;

	@Column(name = "ano")
	private Long ano;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicio")
	private Calendar dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataTermino")
	private Calendar dataTermino;

	@OneToOne
	@JoinColumn(name = "professor")
	private Funcionario professor;

	@OneToOne
	@JoinColumn(name = "funcionario")
	private Funcionario funcionario;

	@OneToMany(mappedBy = "turma_Disciplina")
	private Set<Aluno> aluno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipoDeInvestimento")
	private TipoDeInvestimento tipoDeInvestimento;

	@OneToOne(mappedBy = "turma_Disciplina")
	private DiarioDeAula diarioDeAula;

	@Column(name = "ativo")
	private Boolean ativo;

	public Turma_DisciplinaPK getId() {
		return id;
	}

	public void setId(Turma_DisciplinaPK id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public DiaDaSemanaAula getDiaDaSemanaAula() {
		return diaDaSemanaAula;
	}

	public void setDiaDaSemanaAula(DiaDaSemanaAula diaDaSemanaAula) {
		this.diaDaSemanaAula = diaDaSemanaAula;
	}

	public String getSalaDeAula() {
		return salaDeAula;
	}

	public void setSalaDeAula(String salaDeAula) {
		this.salaDeAula = salaDeAula;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
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

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor = professor;
	}

	public TipoDeInvestimento getTipoDeInvestimento() {
		return tipoDeInvestimento;
	}

	public void setTipoDeInvestimento(TipoDeInvestimento tipoDeInvestimento) {
		this.tipoDeInvestimento = tipoDeInvestimento;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public DiarioDeAula getDiarioDeAula() {
		return diarioDeAula;
	}

	public void setDiarioDeAula(DiarioDeAula diarioDeAula) {
		this.diarioDeAula = diarioDeAula;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Set<Aluno> getAluno() {
		return aluno;
	}

	public void setAluno(Set<Aluno> aluno) {
		this.aluno = aluno;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
