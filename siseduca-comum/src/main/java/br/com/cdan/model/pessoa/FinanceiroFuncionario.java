package br.com.cdan.model.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.financeiro.Banco;

@Entity
@Table(name = "FinanceiroFuncionario")
public class FinanceiroFuncionario implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "financeiroFuncionario", fetch = FetchType.EAGER)
	private Funcionario funcionario;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "banco")
	private Banco banco;

	@Column(name = "agencia")
	private String agencia;

	@Column(name = "conta")
	private String conta;

	@Column(name = "codigoDoCliente")
	private String codigoDoCliente;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCodigoDoCliente() {
		return codigoDoCliente;
	}

	public void setCodigoDoCliente(String codigoDoCliente) {
		this.codigoDoCliente = codigoDoCliente;
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
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result
				+ ((codigoDoCliente == null) ? 0 : codigoDoCliente.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result
				+ ((funcionario == null) ? 0 : funcionario.hashCode());
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
		FinanceiroFuncionario other = (FinanceiroFuncionario) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (codigoDoCliente == null) {
			if (other.codigoDoCliente != null)
				return false;
		} else if (!codigoDoCliente.equals(other.codigoDoCliente))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinanceiroFuncionario [id=" + id + ", funcionario="
				+ funcionario + ", banco=" + banco + ", agencia=" + agencia
				+ ", conta=" + conta + ", codigoDoCliente=" + codigoDoCliente
				+ "]";
	}
}
