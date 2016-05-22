package br.com.cdan.model.financeiro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TIPI")
public class TIPI implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "tipi")
	private NfeLayout nfeLayout;

	@NotNull
	@Column(name = "codigoReceita", nullable = false, unique = true)
	private Long codigoReceita;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 250)
	@Column(name = "descricao", length = 250, nullable = false, unique = true)
	private String descricao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NfeLayout getNfeLayout() {
		return nfeLayout;
	}

	public void setNfeLayout(NfeLayout nfeLayout) {
		this.nfeLayout = nfeLayout;
	}

	public Long getCodigoReceita() {
		return codigoReceita;
	}

	public void setCodigoReceita(Long codigoReceita) {
		this.codigoReceita = codigoReceita;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		result = prime * result + ((codigoReceita == null) ? 0 : codigoReceita.hashCode());
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
		TIPI other = (TIPI) obj;
		if (codigoReceita == null) {
			if (other.codigoReceita != null)
				return false;
		} else if (!codigoReceita.equals(other.codigoReceita))
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
		return "TIPI [nfeLayout=" + nfeLayout + ", codigoReceita=" + codigoReceita + ", descricao=" + descricao + "]";
	}
}
