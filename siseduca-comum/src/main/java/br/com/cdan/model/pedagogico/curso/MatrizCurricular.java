package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.pedagogico.TipoDeCurso;

@Entity
@Table(name = "MatrizCurricular")
public class MatrizCurricular implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Column(name = "quantidadeModulo")
	private Long quantidadeModulo;

	@OneToMany(mappedBy = "disciplinaDependente", fetch = FetchType.LAZY)
	private Set<Disciplina> disciplinasDependentes;

	@Column(name = "utilizaDisciplinasSequenciais")
	private Boolean utilizaDisciplinasSequenciais;

	@Column(name = "utilizarSimuladoParaComporMedia")
	private Boolean utilizarSimuladoParaComporMedia;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipoDeCurso")
	private TipoDeCurso tipoDeCurso;

	@OneToMany(mappedBy = "disciplina", targetEntity = Disciplina_MatrizCurricular.class)
	private Set<Disciplina_MatrizCurricular> disciplina_MatrizCurricular;

	@OneToMany(mappedBy = "matrizCurricular", fetch = FetchType.LAZY, targetEntity = Curso_MatrizCurricular.class)
	private Set<Curso_MatrizCurricular> curso_MatrizCurricular;

	@OneToMany(mappedBy = "matrizCurricular")
	private Set<Turma> turmas;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuantidadeModulo() {
		return quantidadeModulo;
	}

	public void setQuantidadeModulo(Long quantidadeModulo) {
		this.quantidadeModulo = quantidadeModulo;
	}

	public Boolean getUtilizaDisciplinasSequenciais() {
		return utilizaDisciplinasSequenciais;
	}

	public void setUtilizaDisciplinasSequenciais(Boolean utilizaDisciplinasSequenciais) {
		this.utilizaDisciplinasSequenciais = utilizaDisciplinasSequenciais;
	}

	public Boolean getUtilizarSimuladoParaComporMedia() {
		return utilizarSimuladoParaComporMedia;
	}

	public void setUtilizarSimuladoParaComporMedia(Boolean utilizarSimuladoParaComporMedia) {
		this.utilizarSimuladoParaComporMedia = utilizarSimuladoParaComporMedia;
	}

	public TipoDeCurso getTipoDeCurso() {
		return tipoDeCurso;
	}

	public void setTipoDeCurso(TipoDeCurso tipoDeCurso) {
		this.tipoDeCurso = tipoDeCurso;
	}

	public Set<Disciplina_MatrizCurricular> getDisciplina_MatrizCurricular() {
		return disciplina_MatrizCurricular;
	}

	public void setDisciplina_MatrizCurricular(Set<Disciplina_MatrizCurricular> disciplina_MatrizCurricular) {
		this.disciplina_MatrizCurricular = disciplina_MatrizCurricular;
	}

	public Set<Curso_MatrizCurricular> getCurso_MatrizCurricular() {
		return curso_MatrizCurricular;
	}

	public void setCurso_MatrizCurricular(Set<Curso_MatrizCurricular> curso_MatrizCurricular) {
		this.curso_MatrizCurricular = curso_MatrizCurricular;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((quantidadeModulo == null) ? 0 : quantidadeModulo.hashCode());
		result = prime * result + ((tipoDeCurso == null) ? 0 : tipoDeCurso.hashCode());
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
		MatrizCurricular other = (MatrizCurricular) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidadeModulo == null) {
			if (other.quantidadeModulo != null)
				return false;
		} else if (!quantidadeModulo.equals(other.quantidadeModulo))
			return false;
		if (tipoDeCurso == null) {
			if (other.tipoDeCurso != null)
				return false;
		} else if (!tipoDeCurso.equals(other.tipoDeCurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatrizCurricular [nome=" + nome + ", quantidadeModulo=" + quantidadeModulo
				+ ", utilizaDisciplinasSequenciais=" + utilizaDisciplinasSequenciais
				+ ", utilizarSimuladoParaComporMedia=" + utilizarSimuladoParaComporMedia + ", tipoDeCurso="
				+ tipoDeCurso + ", disciplina_matrizCurricular=" + disciplina_MatrizCurricular + "]";
	}
}
