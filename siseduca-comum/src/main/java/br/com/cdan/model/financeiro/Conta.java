package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cdan.model.empresa.Empresa;

@Entity
@Table(name = "Conta")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "titular", length = 60, nullable = false, unique = true)
	private String titular;

	@Column(name = "numero", nullable = false)
	private String numero;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_banco")
	private Banco banco;

	@ManyToMany
	@JoinTable(name = "conta_empresa", joinColumns = @JoinColumn(name = "id_conta"), inverseJoinColumns = @JoinColumn(name = "id_empresa"))
	private Set<Empresa> empresas;

	@Column(name = "encerrada")
	private Boolean encerrada;

	@OneToMany(mappedBy = "conta")
	private Set<ContasAReceber> contasAReceber;

	@OneToMany(mappedBy = "conta")
	private Set<Caixa> caixas;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Boolean getEncerrada() {
		return encerrada;
	}

	public void setEncerrada(Boolean encerrada) {
		this.encerrada = encerrada;
	}

	public Set<ContasAReceber> getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Set<ContasAReceber> contasAReceber) {
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
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
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
		Conta other = (Conta) obj;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [titular=" + titular + ", numero=" + numero + ", banco=" + banco + "]";
	}

}