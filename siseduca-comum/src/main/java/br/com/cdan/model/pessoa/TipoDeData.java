package br.com.cdan.model.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipoDeData")
public class TipoDeData implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@OneToOne(mappedBy = "tipoDeData")
	private FeriadoEvento feriadoEvento;

	@Column(name = "cor")
	private String cor;

	@Column(name = "temAula")
	private Boolean temAula;

	@Column(name = "ativo")
	private Boolean ativo;

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

	public FeriadoEvento getFeriadoEvento() {
		return feriadoEvento;
	}

	public void setFeriadoEvento(FeriadoEvento feriadoEvento) {
		this.feriadoEvento = feriadoEvento;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Boolean getTemAula() {
		return temAula;
	}

	public void setTemAula(Boolean temAula) {
		this.temAula = temAula;
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
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((feriadoEvento == null) ? 0 : feriadoEvento.hashCode());
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
		TipoDeData other = (TipoDeData) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (feriadoEvento == null) {
			if (other.feriadoEvento != null)
				return false;
		} else if (!feriadoEvento.equals(other.feriadoEvento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoDeData [descricao=" + descricao + ", feriadoEvento="
				+ feriadoEvento + ", ativo=" + ativo + "]";
	}
}
