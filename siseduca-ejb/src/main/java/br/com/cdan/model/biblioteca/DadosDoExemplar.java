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
}
