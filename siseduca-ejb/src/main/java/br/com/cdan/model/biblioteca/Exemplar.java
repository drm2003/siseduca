package br.com.cdan.model.biblioteca;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Exemplar")
public class Exemplar implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obra")
	private Obra obra;

	@Column(name = "numeroDoExemplar")
	private Long numeroDoExemplar;

	@Column(name = "ano")
	private Long ano;

	@Column(name = "dataAquisicao")
	private Calendar dataAquisicao;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "edicao")
	private Long edicao;

	@Column(name = "volume")
	private Long volume;

	@Embedded
	private DadosDoExemplar dadosDoExemplar;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Long getNumeroDoExemplar() {
		return numeroDoExemplar;
	}

	public void setNumeroDoExemplar(Long numeroDoExemplar) {
		this.numeroDoExemplar = numeroDoExemplar;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Calendar getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Calendar dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getEdicao() {
		return edicao;
	}

	public void setEdicao(Long edicao) {
		this.edicao = edicao;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public DadosDoExemplar getDadosDoExemplar() {
		return dadosDoExemplar;
	}

	public void setDadosDoExemplar(DadosDoExemplar dadosDoExemplar) {
		this.dadosDoExemplar = dadosDoExemplar;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
