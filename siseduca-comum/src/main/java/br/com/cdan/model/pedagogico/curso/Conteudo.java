package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Conteudo")
public class Conteudo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "CONTEUDO_id_disciplina", referencedColumnName = "id_disciplina", nullable = false),
			@JoinColumn(name = "CONTEUDO_id_matrizCurricular", referencedColumnName = "id_matrizCurricular", nullable = false) })
	private Disciplina_MatrizCurricular disciplina_MatrizCurricular;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "cargaHoraria")
	private Long cargaHoraria;

	@Column(name = "cargaHorariaEstagio")
	private Long cargaHorariaEstagio;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Disciplina_MatrizCurricular getDisciplina_MatrizCurricular() {
		return disciplina_MatrizCurricular;
	}

	public void setDisciplina_MatrizCurricular(Disciplina_MatrizCurricular disciplina_MatrizCurricular) {
		this.disciplina_MatrizCurricular = disciplina_MatrizCurricular;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Long getCargaHorariaEstagio() {
		return cargaHorariaEstagio;
	}

	public void setCargaHorariaEstagio(Long cargaHorariaEstagio) {
		this.cargaHorariaEstagio = cargaHorariaEstagio;
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((disciplina_MatrizCurricular == null) ? 0 : disciplina_MatrizCurricular.hashCode());
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
		Conteudo other = (Conteudo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (disciplina_MatrizCurricular == null) {
			if (other.disciplina_MatrizCurricular != null)
				return false;
		} else if (!disciplina_MatrizCurricular.equals(other.disciplina_MatrizCurricular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conteudo [disciplina_MatrizCurricular=" + disciplina_MatrizCurricular + ", descricao=" + descricao
				+ ", cargaHoraria=" + cargaHoraria + ", cargaHorariaEstagio=" + cargaHorariaEstagio + "]";
	}
}
