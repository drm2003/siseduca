package br.com.cdan.model.biblioteca;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.cdan.comum.EnumTipoAquisicao;

@Embeddable
public class DadosDoExemplar {
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoAquisicao")
	private EnumTipoAquisicao tipoAquisicao;

	@Column(name = "ISBN")
	private String ISBN;

	@Column(name = "ISSN")
	private String ISSN;

	@Column(name = "codigoDeBarras")
	private Long codigoDeBarras;

	@Column(name = "tombo")
	private Long tombo;

	@Column(name = "localizacao")
	private String localizacao;

	@Column(name = "emprestar")
	private Boolean emprestar;

	@Column(name = "reservar")
	private Boolean reservar;

	@Column(name = "renovar")
	private Boolean renovar;

	@Column(name = "observacoes")
	private String observacoes;

	@Column(name = "ativo")
	private Boolean ativo;

	public EnumTipoAquisicao getTipoAquisicao() {
		return tipoAquisicao;
	}

	public void setTipoAquisicao(EnumTipoAquisicao tipoAquisicao) {
		this.tipoAquisicao = tipoAquisicao;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	public Long getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Long codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Long getTombo() {
		return tombo;
	}

	public void setTombo(Long tombo) {
		this.tombo = tombo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Boolean getEmprestar() {
		return emprestar;
	}

	public void setEmprestar(Boolean emprestar) {
		this.emprestar = emprestar;
	}

	public Boolean getReservar() {
		return reservar;
	}

	public void setReservar(Boolean reservar) {
		this.reservar = reservar;
	}

	public Boolean getRenovar() {
		return renovar;
	}

	public void setRenovar(Boolean renovar) {
		this.renovar = renovar;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		result = prime * result + ((ISSN == null) ? 0 : ISSN.hashCode());
		result = prime * result + ((codigoDeBarras == null) ? 0 : codigoDeBarras.hashCode());
		result = prime * result + ((tombo == null) ? 0 : tombo.hashCode());
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
		DadosDoExemplar other = (DadosDoExemplar) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		if (ISSN == null) {
			if (other.ISSN != null)
				return false;
		} else if (!ISSN.equals(other.ISSN))
			return false;
		if (codigoDeBarras == null) {
			if (other.codigoDeBarras != null)
				return false;
		} else if (!codigoDeBarras.equals(other.codigoDeBarras))
			return false;
		if (tombo == null) {
			if (other.tombo != null)
				return false;
		} else if (!tombo.equals(other.tombo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DadosDoExemplar [tipoAquisicao=" + tipoAquisicao + ", ISBN=" + ISBN + ", ISSN=" + ISSN
				+ ", codigoDeBarras=" + codigoDeBarras + ", tombo=" + tombo + ", localizacao=" + localizacao
				+ ", emprestar=" + emprestar + ", reservar=" + reservar + ", renovar=" + renovar + ", observacoes="
				+ observacoes + ", ativo=" + ativo + "]";
	}
}
