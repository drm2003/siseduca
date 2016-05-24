package br.com.cdan.model.pedagogico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.pedagogico.curso.Curso;

@Entity
@Table(name = "SeriePadrao")
public class SeriePadrao implements Serializable {
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
	@Column(name = "codigo", length = 150, nullable = false, unique = true)
	private String codigo;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_tipoDeCurso")
	private TipoDeCurso tipoDeCurso;

	@ManyToOne
	@JoinColumn(name = "id_escolaridade")
	private Escolaridade escolaridade;

	@OneToOne(mappedBy = "serieEquivalente", fetch = FetchType.EAGER)
	private Curso curso;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeCurso getTipoDeCurso() {
		return tipoDeCurso;
	}

	public void setTipoDeCurso(TipoDeCurso tipoDeCurso) {
		this.tipoDeCurso = tipoDeCurso;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		SeriePadrao other = (SeriePadrao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SeriePadrao [codigo=" + codigo + ", descricao=" + descricao + ", tipoDeCurso=" + tipoDeCurso
				+ ", escolaridade=" + escolaridade + "]";
	}
}
