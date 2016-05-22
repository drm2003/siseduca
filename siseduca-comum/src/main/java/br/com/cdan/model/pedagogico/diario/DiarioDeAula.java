package br.com.cdan.model.pedagogico.diario;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "DiarioDeAula")
public class DiarioDeAula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "DIARIODEAULA_id_turma", referencedColumnName = "id_turma", nullable = false),
			@JoinColumn(name = "DIARIODEAULA_id_disciplina", referencedColumnName = "id_disciplina", nullable = false) })
	private Turma_Disciplina turma_Disciplina;

	@OneToMany(mappedBy = "diarioDeAula")
	private Set<Aluno> alunos;

	@ManyToMany
	@JoinTable(name = "disciplinaMatricula_diarioDeAula", joinColumns = @JoinColumn(name = "id_disciplinaMatricula"), inverseJoinColumns = @JoinColumn(name = "id_diarioDeAula"))
	private Set<DisciplinaMatricula> disciplinaMatricula;

	@ManyToOne
	@JoinColumn(name = "controleDeFrequencia")
	private ControleDeFrequencia controleDeFrequencia;

	@ManyToOne
	@JoinColumn(name = "controleDeConteudo")
	private ControleDeConteudo controleDeConteudo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "diarioDeAula_diaDaSemanaAula", joinColumns = @JoinColumn(name = "id_diarioDeAula"), inverseJoinColumns = @JoinColumn(name = "id_diaDaSemanaAula"))
	private Set<DiaDaSemanaAula> diasDaSemanaAula;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor")
	private Funcionario professor;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma_Disciplina getTurma_Disciplina() {
		return turma_Disciplina;
	}

	public void setTurma_Disciplina(Turma_Disciplina turma_Disciplina) {
		this.turma_Disciplina = turma_Disciplina;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<DisciplinaMatricula> getDisciplinaMatricula() {
		return disciplinaMatricula;
	}

	public void setDisciplinaMatricula(Set<DisciplinaMatricula> disciplinaMatricula) {
		this.disciplinaMatricula = disciplinaMatricula;
	}

	public ControleDeFrequencia getControleDeFrequencia() {
		return controleDeFrequencia;
	}

	public void setControleDeFrequencia(ControleDeFrequencia controleDeFrequencia) {
		this.controleDeFrequencia = controleDeFrequencia;
	}

	public ControleDeConteudo getControleDeConteudo() {
		return controleDeConteudo;
	}

	public void setControleDeConteudo(ControleDeConteudo controleDeConteudo) {
		this.controleDeConteudo = controleDeConteudo;
	}

	public Set<DiaDaSemanaAula> getDiasDaSemanaAula() {
		return diasDaSemanaAula;
	}

	public void setDiasDaSemanaAula(Set<DiaDaSemanaAula> diasDaSemanaAula) {
		this.diasDaSemanaAula = diasDaSemanaAula;
	}

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor = professor;
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
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((diasDaSemanaAula == null) ? 0 : diasDaSemanaAula.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((turma_Disciplina == null) ? 0 : turma_Disciplina.hashCode());
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
		DiarioDeAula other = (DiarioDeAula) obj;
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		} else if (!alunos.equals(other.alunos))
			return false;
		if (diasDaSemanaAula == null) {
			if (other.diasDaSemanaAula != null)
				return false;
		} else if (!diasDaSemanaAula.equals(other.diasDaSemanaAula))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (turma_Disciplina == null) {
			if (other.turma_Disciplina != null)
				return false;
		} else if (!turma_Disciplina.equals(other.turma_Disciplina))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DiarioDeAula [id=" + id + ", turma_Disciplina=" + turma_Disciplina + ", alunos=" + alunos
				+ ", disciplinaMatricula=" + disciplinaMatricula + ", controleDeFrequencia=" + controleDeFrequencia
				+ ", controleDeConteudo=" + controleDeConteudo + ", diasDaSemanaAula=" + diasDaSemanaAula
				+ ", professor=" + professor + "]";
	}
}