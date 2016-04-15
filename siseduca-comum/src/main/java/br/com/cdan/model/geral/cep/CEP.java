package br.com.cdan.model.geral.cep;

import java.io.Serializable;
import java.util.Set;

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

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.EstadoUF;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;

@Entity
@Table(name = "CEP")
public class CEP implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "cep", fetch = FetchType.LAZY)
	private Set<Endereco> endereco;

	@OneToOne
	@JoinColumn(name = "UF")
	private EstadoUF UF;

	@Enumerated(EnumType.STRING)
	@Column(name = "Chave", length = 5, nullable = false, unique = true)
	private EnumChaveCidade chave;

	@Column(name = "Cepmin", length = 8, nullable = false, unique = true)
	private String cepmin;

	@Column(name = "CepMax", length = 8, nullable = false, unique = true)
	private String cepMax;

	@Column(name = "Cod_cidade", length = 5, nullable = false, unique = true)
	private String cod_cidade;

	@Enumerated(EnumType.STRING)
	@Column(name = "Tipo_Cidade", length = 1, nullable = false, unique = true)
	private EnumTipoCidade tipoCidade;

	@Column(name = "Cod_Cidade_Sub", length = 5, nullable = false, unique = true)
	private String codCidadeSub;

	@Column(name = "Cod_Mun", length = 7, nullable = false, unique = true)
	private String codMunicicio;

	@OneToOne(mappedBy = "cepEstabelecimento")
	private Aproveitamento aproveitamento;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}

	public EstadoUF getEstadoUF() {
		return UF;
	}

	public void setEstadoUF(EstadoUF uF) {
		UF = uF;
	}

	public EnumChaveCidade getChave() {
		return chave;
	}

	public void setChave(EnumChaveCidade chave) {
		this.chave = chave;
	}

	public String getCepmin() {
		return cepmin;
	}

	public void setCepmin(String cepmin) {
		this.cepmin = cepmin;
	}

	public String getCepMax() {
		return cepMax;
	}

	public void setCepMax(String cepMax) {
		this.cepMax = cepMax;
	}

	public String getCod_cidade() {
		return cod_cidade;
	}

	public void setCod_cidade(String cod_cidade) {
		this.cod_cidade = cod_cidade;
	}

	public EnumTipoCidade getTipoCidade() {
		return tipoCidade;
	}

	public void setTipoCidade(EnumTipoCidade tipoCidade) {
		this.tipoCidade = tipoCidade;
	}

	public String getCodCidadeSub() {
		return codCidadeSub;
	}

	public void setCodCidadeSub(String codCidadeSub) {
		this.codCidadeSub = codCidadeSub;
	}

	public String getCodMunicicio() {
		return codMunicicio;
	}

	public void setCodMunicicio(String codMunicicio) {
		this.codMunicicio = codMunicicio;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UF == null) ? 0 : UF.hashCode());
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((tipoCidade == null) ? 0 : tipoCidade.hashCode());
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
		CEP other = (CEP) obj;
		if (UF == null) {
			if (other.UF != null)
				return false;
		} else if (!UF.equals(other.UF))
			return false;
		if (chave != other.chave)
			return false;
		if (tipoCidade != other.tipoCidade)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CEP [endereco=" + endereco + ", UF=" + UF + ", chave=" + chave + ", cepmin=" + cepmin + ", cepMax="
				+ cepMax + ", cod_cidade=" + cod_cidade + ", tipoCidade=" + tipoCidade + ", codCidadeSub="
				+ codCidadeSub + ", codMunicicio=" + codMunicicio + "]";
	}

}
