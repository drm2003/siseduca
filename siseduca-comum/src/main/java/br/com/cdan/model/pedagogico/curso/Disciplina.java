package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.math.BigDecimal;
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
import br.com.cdan.model.pedagogico.TipoDeDisciplina;
import br.com.cdan.model.pedagogico.contrato.Dependencia;

@Entity
@Table(name = "Disciplina")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 120)
	@Column(name = "nome", nullable = false, length = 120)
	private String nome;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 10)
	@Column(name = "sigla", nullable = false, length = 10)
	private String sigla;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipoDeCurso")
	private TipoDeCurso tipoDeCurso;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipoDeDisciplina")
	private TipoDeDisciplina tipoDeDisciplina;

	@ManyToOne
	@JoinColumn(name = "id_matrizCurricular")
	private MatrizCurricular matrizCurricular;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_disciplinaDependente")
	private MatrizCurricular disciplinaDependente;

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.LAZY, targetEntity = Disciplina_MatrizCurricular.class)
	private Set<Disciplina_MatrizCurricular> disciplinas_MatrizCurricular;

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.LAZY, targetEntity = Turma_Disciplina.class)
	private Set<Turma_Disciplina> turma_Disciplina;

	@OneToMany(mappedBy = "disciplina", fetch = FetchType.LAZY, targetEntity = Turma_Disciplina.class)
	private Set<Dependencia> dependencias;

	@Column(name = "codigoINEP")
	private String codigoINEP;

	@Column(name = "valorHoraAula")
	private BigDecimal valorHoraAula;

	@NotNull
	@Column(name = "compartilhado")
	private Boolean compartilhado;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public MatrizCurricular getDisciplinaDependente() {
		return disciplinaDependente;
	}

	public void setDisciplinaDependente(MatrizCurricular disciplinaDependente) {
		this.disciplinaDependente = disciplinaDependente;
	}

	public TipoDeCurso getTipoDeCurso() {
		return tipoDeCurso;
	}

	public void setTipoDeCurso(TipoDeCurso tipoDeCurso) {
		this.tipoDeCurso = tipoDeCurso;
	}

	public TipoDeDisciplina getTipoDeDisciplina() {
		return tipoDeDisciplina;
	}

	public void setTipoDeDisciplina(TipoDeDisciplina tipoDeDisciplina) {
		this.tipoDeDisciplina = tipoDeDisciplina;
	}

	public String getCodigoINEP() {
		return codigoINEP;
	}

	public void setCodigoINEP(String codigoINEP) {
		this.codigoINEP = codigoINEP;
	}

	public BigDecimal getValorHoraAula() {
		return valorHoraAula;
	}

	public void setValorHoraAula(BigDecimal valorHoraAula) {
		this.valorHoraAula = valorHoraAula;
	}

	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	public Set<Disciplina_MatrizCurricular> getDisciplinas_MatrizCurricular() {
		return disciplinas_MatrizCurricular;
	}

	public void setDisciplinas_MatrizCurricular(Set<Disciplina_MatrizCurricular> disciplinas_MatrizCurricular) {
		this.disciplinas_MatrizCurricular = disciplinas_MatrizCurricular;
	}

	public Set<Dependencia> getDependencias() {
		return dependencias;
	}

	public void setDependencias(Set<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}

	public Set<Turma_Disciplina> getTurma_Disciplina() {
		return turma_Disciplina;
	}

	public void setTurma_Disciplina(Set<Turma_Disciplina> turma_Disciplina) {
		this.turma_Disciplina = turma_Disciplina;
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
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + ((tipoDeCurso == null) ? 0 : tipoDeCurso.hashCode());
		result = prime * result + ((tipoDeDisciplina == null) ? 0 : tipoDeDisciplina.hashCode());
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
		Disciplina other = (Disciplina) obj;
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
		if (tipoDeCurso == null) {
			if (other.tipoDeCurso != null)
				return false;
		} else if (!tipoDeCurso.equals(other.tipoDeCurso))
			return false;
		if (tipoDeDisciplina == null) {
			if (other.tipoDeDisciplina != null)
				return false;
		} else if (!tipoDeDisciplina.equals(other.tipoDeDisciplina))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina [nome=" + nome + ", sigla=" + sigla + ", tipoDeCurso=" + tipoDeCurso + ", tipoDeDisciplina="
				+ tipoDeDisciplina + ", codigoINEP=" + codigoINEP + ", valorHoraAula=" + valorHoraAula + "]";
	}

}
