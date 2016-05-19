package br.com.cdan.model.geral;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.model.clientefornecedor.ClienteFornecedor;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.geral.cep.Bairro;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.geral.cep.Cidade;
import br.com.cdan.model.geral.cep.EstadoUF;
import br.com.cdan.model.geral.cep.Pais;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.model.pessoa.Responsavel;

@Entity
@Table(name = "Endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cep")
	private CEP cep;

	@Column(name = "logradouro", nullable = false)
	private String logradouro;

	@Column(name = "numeroRua")
	private String numeroRua;

	@Column(name = "complemento")
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "bairro")
	private Bairro bairro;

	@ManyToOne
	@JoinColumn(name = "cidade")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "estado")
	private EstadoUF estado;

	@ManyToOne
	@JoinColumn(name = "pais")
	private Pais pais;

	@ManyToOne
	@JoinColumn(name = "id_editora")
	private Editora editora;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@Column(name = "enderecoPrincipal")
	private Boolean enderecoPrincipal;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;

	@OneToOne(mappedBy = "enderecoEstabelecimento")
	private Aproveitamento aproveitamento;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clienteFornecedor")
	private ClienteFornecedor clienteFornecedor;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CEP getCep() {
		return cep;
	}

	public void setCep(CEP cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroRua() {
		return numeroRua;
	}

	public void setNumeroRua(String numeroRua) {
		this.numeroRua = numeroRua;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public EstadoUF getEstado() {
		return estado;
	}

	public void setEstado(EstadoUF estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setEnderecoPrincipal(Boolean enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Aproveitamento getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(Aproveitamento aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public ClienteFornecedor getClienteFornecedor() {
		return clienteFornecedor;
	}

	public void setClienteFornecedor(ClienteFornecedor clienteFornecedor) {
		this.clienteFornecedor = clienteFornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numeroRua == null) ? 0 : numeroRua.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numeroRua == null) {
			if (other.numeroRua != null)
				return false;
		} else if (!numeroRua.equals(other.numeroRua))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numeroRua=" + numeroRua + ", bairro=" + bairro + ", cidade="
				+ cidade + ", estado=" + estado + ", pais=" + pais + "]";
	}
}
