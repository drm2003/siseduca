package br.com.cdan.model.clientefornecedor;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TipoRelacaoClienteFornecedor")
public class TipoRelacaoClienteFornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 150, min = 3)
	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@NotNull
	@Column(name = "compartilhado")
	private Boolean compartilhado;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tiposRelacoesClienteFornecedor")
	private Set<ClienteFornecedor> clienteFornecedor;

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

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<ClienteFornecedor> getClienteFornecedor() {
		return clienteFornecedor;
	}

	public void setClienteFornecedor(Set<ClienteFornecedor> clienteFornecedor) {
		this.clienteFornecedor = clienteFornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((compartilhado == null) ? 0 : compartilhado.hashCode());
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
		TipoRelacaoClienteFornecedor other = (TipoRelacaoClienteFornecedor) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (compartilhado == null) {
			if (other.compartilhado != null)
				return false;
		} else if (!compartilhado.equals(other.compartilhado))
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
		return "TipoRelacaoClienteFornecedor [id=" + id + ", descricao=" + descricao + ", compartilhado="
				+ compartilhado + ", ativo=" + ativo + "]";
	}
}
