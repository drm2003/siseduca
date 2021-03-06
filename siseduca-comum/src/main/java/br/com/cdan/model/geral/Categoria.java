package br.com.cdan.model.geral;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.pedagogico.curso.Investimento;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private Set<TipoDeServico> tiposDeServico;

	@OneToOne(mappedBy = "categoria", fetch = FetchType.LAZY)
	private Investimento investimento;

	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private Set<Caixa> caixas;

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

	public Set<TipoDeServico> getTiposDeServico() {
		return tiposDeServico;
	}

	public void setTiposDeServico(Set<TipoDeServico> tiposDeServico) {
		this.tiposDeServico = tiposDeServico;
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
		Categoria other = (Categoria) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [descricao=" + descricao + ", tipoDeServico=" + tiposDeServico.toString() + "]";
	}
}
