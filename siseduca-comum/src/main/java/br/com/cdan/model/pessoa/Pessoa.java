package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.comum.EnumSexo;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.EstadoCivil;

@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	@Column(name = "profissaoFormacao")
	private String profissaoFormacao;

	@Column(name = "empresaLocalDeTrabalho")
	private String empresaLocalDeTrabalho;

	@Column(name = "estadoCivil")
	private EstadoCivil estadoCivil;

	@Column(name = "cidadeNatal")
	private Cidade cidadeNatal;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private EnumSexo sexo;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classificacao")
	private Classificacao classificacao;

	@Column(name = "rg")
	private String rg;

	@Column(name = "orgaoExpedidorRg")
	private String orgaoExpedidorRg;

	@Column(name = "dataExpedicaoRg")
	@Temporal(TemporalType.DATE)
	private Calendar dataExpedicaoRg;

	@Column(name = "tituloEleitor")
	private String tituloEleitor;

	@Column(name = "zonaTituloEleitor")
	private String zonaTituloEleitor;

	@Column(name = "secaoTituloEleitor")
	private String secaoTituloEleitor;

	@Column(name = "dataEmissaoTituloEleitor")
	private String dataEmissaoTituloEleitor;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "documentoMilitar")
	private String documentoMilitar;

	@Column(name = "numeroDocumentoMilitar")
	private String numeroDocumentoMilitar;

	@Column(name = "numeroPassaporte")
	private String numeroPassaporte;

	@OneToOne
	@JoinColumn(name = "certidaoNascimentoCasamento")
	private Certidao certidao;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private Set<AnexoDocumentos> anexoDocumentos;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private Set<Endereco> endereco;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private Set<Email> email;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private Set<Funcionario> funcionarios;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aluno aluno;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cnh")
	private CarteiraHabilitacao cnh;

	@OneToOne(mappedBy = "pessoa", targetEntity = Responsavel.class, fetch = FetchType.EAGER)
	private Responsavel responsavel;

	@OneToOne
	@JoinColumn(name = "id_carteiraHabilitacao")
	private CarteiraHabilitacao carteiraHabilitacao;

	@Column(name = "permitirEmprestimoBiblioteca")
	private Boolean permitirEmprestimoBiblioteca;

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

	public String getProfissaoFormacao() {
		return profissaoFormacao;
	}

	public void setProfissaoFormacao(String profissaoFormacao) {
		this.profissaoFormacao = profissaoFormacao;
	}

	public String getEmpresaLocalDeTrabalho() {
		return empresaLocalDeTrabalho;
	}

	public void setEmpresaLocalDeTrabalho(String empresaLocalDeTrabalho) {
		this.empresaLocalDeTrabalho = empresaLocalDeTrabalho;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Cidade getCidadeNatal() {
		return cidadeNatal;
	}

	public void setCidadeNatal(Cidade cidadeNatal) {
		this.cidadeNatal = cidadeNatal;
	}

	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidorRg() {
		return orgaoExpedidorRg;
	}

	public void setOrgaoExpedidorRg(String orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Calendar getDataExpedicaoRg() {
		return dataExpedicaoRg;
	}

	public void setDataExpedicaoRg(Calendar dataExpedicaoRg) {
		this.dataExpedicaoRg = dataExpedicaoRg;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getZonaTituloEleitor() {
		return zonaTituloEleitor;
	}

	public void setZonaTituloEleitor(String zonaTituloEleitor) {
		this.zonaTituloEleitor = zonaTituloEleitor;
	}

	public String getSecaoTituloEleitor() {
		return secaoTituloEleitor;
	}

	public void setSecaoTituloEleitor(String secaoTituloEleitor) {
		this.secaoTituloEleitor = secaoTituloEleitor;
	}

	public String getDataEmissaoTituloEleitor() {
		return dataEmissaoTituloEleitor;
	}

	public void setDataEmissaoTituloEleitor(String dataEmissaoTituloEleitor) {
		this.dataEmissaoTituloEleitor = dataEmissaoTituloEleitor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDocumentoMilitar() {
		return documentoMilitar;
	}

	public void setDocumentoMilitar(String documentoMilitar) {
		this.documentoMilitar = documentoMilitar;
	}

	public String getNumeroDocumentoMilitar() {
		return numeroDocumentoMilitar;
	}

	public void setNumeroDocumentoMilitar(String numeroDocumentoMilitar) {
		this.numeroDocumentoMilitar = numeroDocumentoMilitar;
	}

	public String getNumeroPassaporte() {
		return numeroPassaporte;
	}

	public void setNumeroPassaporte(String numeroPassaporte) {
		this.numeroPassaporte = numeroPassaporte;
	}

	public Certidao getCertidao() {
		return certidao;
	}

	public void setCertidao(Certidao certidao) {
		this.certidao = certidao;
	}

	public Set<AnexoDocumentos> getAnexoDocumentos() {
		return anexoDocumentos;
	}

	public void setAnexoDocumentos(Set<AnexoDocumentos> anexoDocumentos) {
		this.anexoDocumentos = anexoDocumentos;
	}

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Set<Email> getEmail() {
		return email;
	}

	public void setEmail(Set<Email> email) {
		this.email = email;
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public CarteiraHabilitacao getCnh() {
		return cnh;
	}

	public void setCnh(CarteiraHabilitacao cnh) {
		this.cnh = cnh;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Boolean getPermitirEmprestimoBiblioteca() {
		return permitirEmprestimoBiblioteca;
	}

	public void setPermitirEmprestimoBiblioteca(
			Boolean permitirEmprestimoBiblioteca) {
		this.permitirEmprestimoBiblioteca = permitirEmprestimoBiblioteca;
	}

	public CarteiraHabilitacao getCarteiraHabilitacao() {
		return carteiraHabilitacao;
	}

	public void setCarteiraHabilitacao(CarteiraHabilitacao carteiraHabilitacao) {
		this.carteiraHabilitacao = carteiraHabilitacao;
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
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((cnh == null) ? 0 : cnh.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (cnh == null) {
			if (other.cnh != null)
				return false;
		} else if (!cnh.equals(other.cnh))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", profissaoFormacao=" + profissaoFormacao
				+ ", empresaLocalDeTrabalho=" + empresaLocalDeTrabalho
				+ ", estadoCivil=" + estadoCivil + ", cidadeNatal="
				+ cidadeNatal + ", sexo=" + sexo + ", rg=" + rg
				+ ", orgaoExpedidorRg=" + orgaoExpedidorRg
				+ ", dataExpedicaoRg=" + dataExpedicaoRg + ", tituloEleitor="
				+ tituloEleitor + ", zonaTituloEleitor=" + zonaTituloEleitor
				+ ", secaoTituloEleitor=" + secaoTituloEleitor
				+ ", dataEmissaoTituloEleitor=" + dataEmissaoTituloEleitor
				+ ", cpf=" + cpf + ", documentoMilitar=" + documentoMilitar
				+ ", numeroDocumentoMilitar=" + numeroDocumentoMilitar
				+ ", numeroPassaporte=" + numeroPassaporte
				+ ", certidaoNascimentoCasamento=" + certidao + ", endereco="
				+ endereco + ", email=" + email + ", funcionarios="
				+ funcionarios + ", aluno=" + aluno + ", cnh=" + cnh
				+ ", responsavel=" + responsavel + "]";
	}
}
