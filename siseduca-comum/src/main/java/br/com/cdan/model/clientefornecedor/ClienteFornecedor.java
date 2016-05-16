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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.comum.EnumTipoDePessoa;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Pessoa;

@Entity
@Table(name = "ClienteFornecedor")
public class ClienteFornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 150, min = 3)
	@Column(name = "nome", length = 150, nullable = false, unique = true)
	private String nome;

	@Size(max = 250, min = 3)
	@Column(name = "razaoSocial", length = 250)
	private String razaoSocial;

	@NotNull
	@Column(name = "tipoDePessoa")
	private EnumTipoDePessoa tipoDePessoa;

	@OneToOne
	@JoinColumn(name = "pessoa")
	private Pessoa pessoa;

	@OneToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@OneToMany(mappedBy = "clienteFornecedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Endereco> enderecos;

	@OneToMany(mappedBy = "clienteFornecedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Telefone> telefones;

	@OneToMany(mappedBy = "clienteFornecedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Email> emails;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ClienteFornecedor_Banco", joinColumns = @JoinColumn(name = "id_clienteFornecedor"), inverseJoinColumns = @JoinColumn(name = "id_banco"))
	private Set<Banco> bancos;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ClienteFornecedor_TipoRelacao", joinColumns = @JoinColumn(name = "id_ClienteFornecedor"), inverseJoinColumns = @JoinColumn(name = "id_tipoRelacao"))
	private Set<TipoRelacaoClienteFornecedor> tiposRelacoesClienteFornecedor;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ClienteFornecedor_Follow", joinColumns = @JoinColumn(name = "id_ClienteFornecedor"), inverseJoinColumns = @JoinColumn(name = "id_follow"))
	private Set<Follow> follows;

	@Column(name = "observacao")
	private String observacao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public EnumTipoDePessoa getTipoDePessoa() {
		return tipoDePessoa;
	}

	public void setTipoDePessoa(EnumTipoDePessoa tipoDePessoa) {
		this.tipoDePessoa = tipoDePessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Set<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(Set<Banco> bancos) {
		this.bancos = bancos;
	}

	public Set<Follow> getFollows() {
		return follows;
	}

	public void setFollows(Set<Follow> follows) {
		this.follows = follows;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<TipoRelacaoClienteFornecedor> getTiposRelacoesClienteFornecedor() {
		return tiposRelacoesClienteFornecedor;
	}

	public void setTiposRelacoesClienteFornecedor(Set<TipoRelacaoClienteFornecedor> tiposRelacoesClienteFornecedor) {
		this.tiposRelacoesClienteFornecedor = tiposRelacoesClienteFornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((tipoDePessoa == null) ? 0 : tipoDePessoa.hashCode());
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
		ClienteFornecedor other = (ClienteFornecedor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (tipoDePessoa != other.tipoDePessoa)
			return false;
		return true;
	}
}
