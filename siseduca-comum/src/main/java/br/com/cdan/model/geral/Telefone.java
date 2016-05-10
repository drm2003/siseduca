package br.com.cdan.model.geral;

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
import javax.persistence.Table;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.model.pessoa.Responsavel;

@Entity
@Table(name = "Telefone")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero", nullable = false)
	private String numero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipoDeCelular")
	private TipoDeCelular tipoDeCelular;

	@ManyToMany
	@JoinTable(name = "telefone_empresa", joinColumns = @JoinColumn(name = "id_telefone"), inverseJoinColumns = @JoinColumn(name = "id_empresa"))
	private Set<Empresa> empresas;

	@Column(name = "telefonePrincipal")
	private Boolean telefonePrincipal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Editora")
	private Editora editora;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_banco")
	private Banco banco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aproveitamento")
	private Aproveitamento aproveitamento;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public TipoDeCelular getTipoDeCelular() {
		return tipoDeCelular;
	}

	public void setTipoDeCelular(TipoDeCelular tipoDeCelular) {
		this.tipoDeCelular = tipoDeCelular;
	}

	public Boolean getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(Boolean telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Aproveitamento getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(Aproveitamento aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
