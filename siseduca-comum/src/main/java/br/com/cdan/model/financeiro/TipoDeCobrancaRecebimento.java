package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TipoDeCobrancaRecebimento")
public class TipoDeCobrancaRecebimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@OneToMany(mappedBy = "tipoDeCobranca", fetch = FetchType.EAGER)
	private Set<ContaAReceber> contasAReceber;

	@OneToMany(mappedBy = "tipoDeMovimentacao", fetch = FetchType.EAGER)
	private Set<Caixa> caixas;

	@NotNull
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

	public Set<ContaAReceber> getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Set<ContaAReceber> contasAReceber) {
		this.contasAReceber = contasAReceber;
	}

	public Set<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(Set<Caixa> caixas) {
		this.caixas = caixas;
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
		TipoDeCobrancaRecebimento other = (TipoDeCobrancaRecebimento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TiposPagamentoProfessorHorista [descricao=" + descricao;
	}
}
