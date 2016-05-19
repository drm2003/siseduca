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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

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

	@NotNull
	@NotEmpty
	@Size(max = 250, min = 3)
	@Column(name = "descricao", unique = true, nullable = false)
	private String descricao;

	@Column(name = "edicao")
	private Long edicao;

	@Column(name = "volume")
	private Long volume;

	@Embedded
	private DadosDoExemplar dadosDoExemplar;

	@NotNull
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((dadosDoExemplar == null) ? 0 : dadosDoExemplar.hashCode());
		result = prime * result + ((dataAquisicao == null) ? 0 : dataAquisicao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((edicao == null) ? 0 : edicao.hashCode());
		result = prime * result + ((numeroDoExemplar == null) ? 0 : numeroDoExemplar.hashCode());
		result = prime * result + ((obra == null) ? 0 : obra.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		Exemplar other = (Exemplar) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (dadosDoExemplar == null) {
			if (other.dadosDoExemplar != null)
				return false;
		} else if (!dadosDoExemplar.equals(other.dadosDoExemplar))
			return false;
		if (dataAquisicao == null) {
			if (other.dataAquisicao != null)
				return false;
		} else if (!dataAquisicao.equals(other.dataAquisicao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (edicao == null) {
			if (other.edicao != null)
				return false;
		} else if (!edicao.equals(other.edicao))
			return false;
		if (numeroDoExemplar == null) {
			if (other.numeroDoExemplar != null)
				return false;
		} else if (!numeroDoExemplar.equals(other.numeroDoExemplar))
			return false;
		if (obra == null) {
			if (other.obra != null)
				return false;
		} else if (!obra.equals(other.obra))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exemplar [id=" + id + ", obra=" + obra + ", numeroDoExemplar=" + numeroDoExemplar + ", ano=" + ano
				+ ", dataAquisicao=" + dataAquisicao + ", descricao=" + descricao + ", edicao=" + edicao + ", volume="
				+ volume + ", dadosDoExemplar=" + dadosDoExemplar + ", ativo=" + ativo + "]";
	}
}