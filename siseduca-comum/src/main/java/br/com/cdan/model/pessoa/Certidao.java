package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.comum.EnumTipoCertidao;
import br.com.cdan.model.geral.Cidade;

@Entity
@Table(name = "Certidao")
public class Certidao implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "certidao")
	private Pessoa pessoa;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoCertidao")
	private EnumTipoCertidao tipoCertidao;

	@Column(name = "numeroMatricula")
	private String numeroMatricula;

	@Column(name = "numeroTermo")
	private String numeroTermo;

	@Column(name = "folha")
	private String folha;

	@Column(name = "livro")
	private String livro;

	@Column(name = "dataEmissao")
	@Temporal(TemporalType.DATE)
	private Calendar dataEmissao;

	@Column(name = "cartorio")
	private String cartorio;

	@Column(name = "municipioCartorio")
	private Cidade municipioCartorio;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public EnumTipoCertidao getTipoCertidao() {
		return tipoCertidao;
	}

	public void setTipoCertidao(EnumTipoCertidao tipoCertidao) {
		this.tipoCertidao = tipoCertidao;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public String getNumeroTermo() {
		return numeroTermo;
	}

	public void setNumeroTermo(String numeroTermo) {
		this.numeroTermo = numeroTermo;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public Calendar getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Calendar dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getCartorio() {
		return cartorio;
	}

	public void setCartorio(String cartorio) {
		this.cartorio = cartorio;
	}

	public Cidade getMunicipioCartorio() {
		return municipioCartorio;
	}

	public void setMunicipioCartorio(Cidade municipioCartorio) {
		this.municipioCartorio = municipioCartorio;
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
		result = prime * result + ((folha == null) ? 0 : folha.hashCode());
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		result = prime * result
				+ ((numeroMatricula == null) ? 0 : numeroMatricula.hashCode());
		result = prime * result
				+ ((numeroTermo == null) ? 0 : numeroTermo.hashCode());
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
		Certidao other = (Certidao) obj;
		if (folha == null) {
			if (other.folha != null)
				return false;
		} else if (!folha.equals(other.folha))
			return false;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		if (numeroMatricula == null) {
			if (other.numeroMatricula != null)
				return false;
		} else if (!numeroMatricula.equals(other.numeroMatricula))
			return false;
		if (numeroTermo == null) {
			if (other.numeroTermo != null)
				return false;
		} else if (!numeroTermo.equals(other.numeroTermo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Certidao [pessoa=" + pessoa + ", tipoCertidao=" + tipoCertidao
				+ ", numeroMatricula=" + numeroMatricula + ", numeroTermo="
				+ numeroTermo + ", folha=" + folha + ", livro=" + livro
				+ ", dataEmissao=" + dataEmissao + ", cartorio=" + cartorio
				+ ", municipioCartorio=" + municipioCartorio + "]";
	}
}
