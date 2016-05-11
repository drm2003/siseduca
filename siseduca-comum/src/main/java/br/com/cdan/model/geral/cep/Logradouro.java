package br.com.cdan.model.geral.cep;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Logradouro")
public class Logradouro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CEP", nullable = false)
	private String cep;

	@Enumerated(EnumType.STRING)
	@Column(name = "Chave", length = 5, nullable = false, unique = true)
	private EnumChaveCidade chave;

	@Column(name = "UF", length = 2, nullable = false, unique = true)
	private String UF;

	@Column(name = "Tipo", length = 15, nullable = false)
	private String tipoLogradouro;

	@Column(name = "Titulo", length = 15, nullable = false)
	private String tituloLogradouro;

	@Column(name = "Preposicao", length = 15, nullable = false)
	private String preposicaoInicialLogradouro;

	@Column(name = "Nome", length = 60, nullable = false)
	private String nome;

	@Column(name = "Bairro1", length = 30, nullable = false)
	private String bairroInicioLogradouro;

	@Column(name = "Bairro2", length = 30, nullable = false)
	private String bairroFinalLogradouro;

	@Column(name = "LiminPar", length = 8, nullable = false)
	private String limiteInferiorLadoPar;

	@Column(name = "Liminfimpa", length = 8, nullable = false)
	private String limiteInferiorLadoImpar;

	@Column(name = "Limsuppar", length = 8, nullable = false)
	private String limiteSuperiorLadoPar;

	@Column(name = "Limsupimpa", length = 8, nullable = false)
	private String limiteSuperiorLadoImpar;

	@Enumerated(EnumType.STRING)
	@Column(name = "Flag", length = 1, nullable = false)
	private EnumFlagTipoDeCep flagTipoDeCep;

	@Enumerated(EnumType.STRING)
	@Column(name = "Lados", length = 1, nullable = false)
	private EnumLadoDaRua ladoDaRua;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EnumChaveCidade getChave() {
		return chave;
	}

	public void setChave(EnumChaveCidade chave) {
		this.chave = chave;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getTituloLogradouro() {
		return tituloLogradouro;
	}

	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}

	public String getPreposicaoInicialLogradouro() {
		return preposicaoInicialLogradouro;
	}

	public void setPreposicaoInicialLogradouro(String preposicaoInicialLogradouro) {
		this.preposicaoInicialLogradouro = preposicaoInicialLogradouro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBairroInicioLogradouro() {
		return bairroInicioLogradouro;
	}

	public void setBairroInicioLogradouro(String bairroInicioLogradouro) {
		this.bairroInicioLogradouro = bairroInicioLogradouro;
	}

	public String getBairroFinalLogradouro() {
		return bairroFinalLogradouro;
	}

	public void setBairroFinalLogradouro(String bairroFinalLogradouro) {
		this.bairroFinalLogradouro = bairroFinalLogradouro;
	}

	public String getLimiteInferiorLadoPar() {
		return limiteInferiorLadoPar;
	}

	public void setLimiteInferiorLadoPar(String limiteInferiorLadoPar) {
		this.limiteInferiorLadoPar = limiteInferiorLadoPar;
	}

	public String getLimiteInferiorLadoImpar() {
		return limiteInferiorLadoImpar;
	}

	public void setLimiteInferiorLadoImpar(String limiteInferiorLadoImpar) {
		this.limiteInferiorLadoImpar = limiteInferiorLadoImpar;
	}

	public String getLimiteSuperiorLadoPar() {
		return limiteSuperiorLadoPar;
	}

	public void setLimiteSuperiorLadoPar(String limiteSuperiorLadoPar) {
		this.limiteSuperiorLadoPar = limiteSuperiorLadoPar;
	}

	public String getLimiteSuperiorLadoImpar() {
		return limiteSuperiorLadoImpar;
	}

	public void setLimiteSuperiorLadoImpar(String limiteSuperiorLadoImpar) {
		this.limiteSuperiorLadoImpar = limiteSuperiorLadoImpar;
	}

	public EnumFlagTipoDeCep getFlagTipoDeCep() {
		return flagTipoDeCep;
	}

	public void setFlagTipoDeCep(EnumFlagTipoDeCep flagTipoDeCep) {
		this.flagTipoDeCep = flagTipoDeCep;
	}

	public EnumLadoDaRua getLadoDaRua() {
		return ladoDaRua;
	}

	public void setLadoDaRua(EnumLadoDaRua ladoDaRua) {
		this.ladoDaRua = ladoDaRua;
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
		result = prime * result + ((UF == null) ? 0 : UF.hashCode());
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
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
		Logradouro other = (Logradouro) obj;
		if (UF == null) {
			if (other.UF != null)
				return false;
		} else if (!UF.equals(other.UF))
			return false;
		if (chave != other.chave)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Logradouros [cep=" + cep + ", chave=" + chave + ", UF=" + UF + ", tipoLogradouro=" + tipoLogradouro
				+ ", tituloLogradouro=" + tituloLogradouro + ", preposicaoInicialLogradouro="
				+ preposicaoInicialLogradouro + ", nome=" + nome + ", bairroInicioLogradouro=" + bairroInicioLogradouro
				+ ", bairroFinalLogradouro=" + bairroFinalLogradouro + "]";
	}
}