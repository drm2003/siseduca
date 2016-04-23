package br.com.cdan.model.pedagogico.curso;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.model.pedagogico.SituacaoDaTurma;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "Turma")
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "turma", fetch = FetchType.LAZY, targetEntity = Turma_Disciplina.class)
	private Set<Turma_Disciplina> turma_Disciplina;

	@Column(name = "nome", nullable = false, length = 200)
	private String nome;

	@Column(name = "sigla", nullable = false, length = 20)
	private String sigla;

	@Column(name = "codigo")
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Funcionario professor;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicio")
	private Calendar dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataTermino")
	private Calendar dataTermino;

	@ManyToOne
	@JoinColumn(name = "id_situacaoDaTurma")
	private SituacaoDaTurma situacaoDaTurma;

	@ManyToOne
	@JoinColumn(name = "id_matrizCurricular")
	private MatrizCurricular matrizCurricular;

	@ManyToOne
	@JoinColumn(name = "id_calendarioPadrao")
	private CalendarioLetivo calendarioPadrao;

	@Column(name = "salaDeAulaPadrao")
	private String salaDeAulaPadrao;

	@ManyToOne
	@JoinColumn(name = "id_horarioDeAula")
	private HorarioDeAula horarioDeAula;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "turma")
	private Set<Ocorrencia> ocorrencias;

	@ManyToMany(mappedBy = "turmas", fetch = FetchType.LAZY)
	private Set<ContaAReceber> contasAReceber;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Turma_Disciplina> getTurma_Disciplina() {
		return turma_Disciplina;
	}

	public void setTurma_Disciplina(Set<Turma_Disciplina> turma_Disciplina) {
		this.turma_Disciplina = turma_Disciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor = professor;
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

	public SituacaoDaTurma getSituacaoDaTurma() {
		return situacaoDaTurma;
	}

	public void setSituacaoDaTurma(SituacaoDaTurma situacaoDaTurma) {
		this.situacaoDaTurma = situacaoDaTurma;
	}

	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public CalendarioLetivo getCalendarioPadrao() {
		return calendarioPadrao;
	}

	public void setCalendarioPadrao(CalendarioLetivo calendarioPadrao) {
		this.calendarioPadrao = calendarioPadrao;
	}

	public String getSalaDeAulaPadrao() {
		return salaDeAulaPadrao;
	}

	public void setSalaDeAulaPadrao(String salaDeAulaPadrao) {
		this.salaDeAulaPadrao = salaDeAulaPadrao;
	}

	public HorarioDeAula getHorarioDeAula() {
		return horarioDeAula;
	}

	public void setHorarioDeAula(HorarioDeAula horarioDeAula) {
		this.horarioDeAula = horarioDeAula;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(Set<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Set<ContaAReceber> getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Set<ContaAReceber> contasAReceber) {
		this.contasAReceber = contasAReceber;
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Turma other = (Turma) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turma [turma_Disciplina=" + turma_Disciplina + ", nome=" + nome + ", sigla=" + sigla + ", codigo="
				+ codigo + ", curso=" + curso + ", situacaoDaTurma=" + situacaoDaTurma + "]";
	}
}
