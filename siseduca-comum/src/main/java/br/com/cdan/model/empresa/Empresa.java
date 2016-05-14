package br.com.cdan.model.empresa;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.model.acesso.Permissao_Empresa;
import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.model.biblioteca.TipoDeObra;
import br.com.cdan.model.estoque.Item_Empresa;
import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.OperadoraCartao;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pessoa.Responsavel;

@Entity
@Table(name = "Empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 60, nullable = false, unique = true)
	private String nome;

	@Column(name = "sigla")
	private String sigla;

	@Column(name = "razaoSocial")
	private String razaoSocial;

	@Column(name = "cnpj", nullable = false, unique = true, length = 14)
	private String cnpj;

	@Column(name = "inscricaoMunicipal")
	private String inscricaoMunicipal;

	@Column(name = "inscricaoEstadual")
	private String inscricaoEstadual;

	@Column(name = "optanteSimplesNacional")
	private Boolean optanteSimplesNacional;

	@OneToMany(mappedBy = "empresa")
	private Set<Endereco> enderecos;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
	private Set<CEP> cep;

	@Column(name = "codigoArea")
	private String codigoArea;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<Telefone> telefone;

	@Column(name = "fax")
	private String fax;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<Email> email;

	@Column(name = "home")
	private String homePage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoDeObra")
	private TipoDeObra tipoDeObra;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<Bolsa> bolsas;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<OperadoraCartao> operadoraCartao;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<TipoDeServico> tiposDeServicos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa", targetEntity = Item_Empresa.class)
	private Set<Item_Empresa> item_Empresa;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
	private Set<Permissao_Empresa> permissao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa", fetch = FetchType.LAZY)
	private Set<Responsavel> responsaveis;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dadosEmpresaConcedente.empresa")
	private Set<EstagioMonografia> estagioMonografia;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<ContaAReceber> contasAReceber;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<ContaAPagar> contasAPagar;

	@ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
	private Set<Obra> obras;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Boolean getOptanteSimplesNacional() {
		return optanteSimplesNacional;
	}

	public void setOptanteSimplesNacional(Boolean optanteSimplesNacional) {
		this.optanteSimplesNacional = optanteSimplesNacional;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<CEP> getCep() {
		return cep;
	}

	public void setCep(Set<CEP> cep) {
		this.cep = cep;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public Set<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(Set<Telefone> telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Set<Email> getEmail() {
		return email;
	}

	public void setEmail(Set<Email> email) {
		this.email = email;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public TipoDeObra getTipoDeObra() {
		return tipoDeObra;
	}

	public void setTipoDeObra(TipoDeObra tipoDeObra) {
		this.tipoDeObra = tipoDeObra;
	}

	public Set<Bolsa> getBolsas() {
		return bolsas;
	}

	public void setBolsas(Set<Bolsa> bolsas) {
		this.bolsas = bolsas;
	}

	public Set<OperadoraCartao> getOperadoraCartao() {
		return operadoraCartao;
	}

	public void setOperadoraCartao(Set<OperadoraCartao> operadoraCartao) {
		this.operadoraCartao = operadoraCartao;
	}

	public Set<TipoDeServico> getTiposDeServicos() {
		return tiposDeServicos;
	}

	public void setTiposDeServicos(Set<TipoDeServico> tiposDeServicos) {
		this.tiposDeServicos = tiposDeServicos;
	}

	public Set<Permissao_Empresa> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<Permissao_Empresa> permissao) {
		this.permissao = permissao;
	}

	public Set<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Set<Item_Empresa> getItem_Empresa() {
		return item_Empresa;
	}

	public void setItem_Empresa(Set<Item_Empresa> item_Empresa) {
		this.item_Empresa = item_Empresa;
	}

	public Set<EstagioMonografia> getEstagioMonografia() {
		return estagioMonografia;
	}

	public void setEstagioMonografia(Set<EstagioMonografia> estagioMonografia) {
		this.estagioMonografia = estagioMonografia;
	}

	public Set<ContaAReceber> getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Set<ContaAReceber> contasAReceber) {
		this.contasAReceber = contasAReceber;
	}

	public Set<ContaAPagar> getContasAPagar() {
		return contasAPagar;
	}

	public void setContasAPagar(Set<ContaAPagar> contasAPagar) {
		this.contasAPagar = contasAPagar;
	}

	public Set<Obra> getObras() {
		return obras;
	}

	public void setObras(Set<Obra> obras) {
		this.obras = obras;
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
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [nome=" + nome + ", sigla=" + sigla + ", cnpj=" + cnpj + "]";
	}
}