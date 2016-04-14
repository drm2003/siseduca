package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.Disciplina;

@Entity
@Table(name = "TipoDeCurso")
public class TipoDeCurso implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@ManyToMany(mappedBy = "tipoDeCurso", fetch = FetchType.LAZY)
	private Set<TipoDeServico> tipoDeServico;

	@Column(name = "reconhecidoPeloMec")
	private Boolean reconhecidoPeloMec;

	@Column(name = "temMatrizCurricular")
	private Boolean temMatrizCurricular;

	@OneToMany(mappedBy = "tipoDeCurso")
	private Set<SeriePadrao> seriesPadrao;

	@OneToMany(mappedBy = "tipoDeCurso")
	private Set<Disciplina> disciplina;

	@OneToOne(mappedBy = "tipoDeCurso")
	private Curso curso;

	@Column(name = "ativo")
	private Boolean ativo;

	@Column(name = "compartilhado")
	private Boolean compartilhado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getReconhecidoPeloMec() {
		return reconhecidoPeloMec;
	}

	public void setReconhecidoPeloMec(Boolean reconhecidoPeloMec) {
		this.reconhecidoPeloMec = reconhecidoPeloMec;
	}

	public Boolean getTemMatrizCurricular() {
		return temMatrizCurricular;
	}

	public void setTemMatrizCurricular(Boolean temMatrizCurricular) {
		this.temMatrizCurricular = temMatrizCurricular;
	}

	public Set<SeriePadrao> getSeriesPadrao() {
		return seriesPadrao;
	}

	public void setSeriesPadrao(Set<SeriePadrao> seriesPadrao) {
		this.seriesPadrao = seriesPadrao;
	}

	public Set<TipoDeServico> getTipoDeServico() {
		return tipoDeServico;
	}

	public void setTipoDeServico(Set<TipoDeServico> tipoDeServico) {
		this.tipoDeServico = tipoDeServico;
	}

	public Set<Disciplina> getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Set<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		TipoDeCurso other = (TipoDeCurso) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoDeCurso [descricao=" + descricao + ", reconhecidoPeloMec="
				+ reconhecidoPeloMec + ", temMatrizCurricular="
				+ temMatrizCurricular + ", seriesPadrao=" + seriesPadrao + "]";
	}
}
