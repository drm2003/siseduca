package br.com.cdan.model.pedagogico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.comum.EnumMediaAposRecuperacao;

@Entity
@Table(name = "MediaAposRecuperacao")
public class MediaAposRecuperacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "mediaAposRecuperacao")
	private EnumMediaAposRecuperacao EnumMediaAposRecuperacao;

	@Column(name = "desconsiderarNotaRecuperacaoMenor")
	private Boolean desconsiderarNotaRecuperacaoMenor;

	@Column(name = "desconsiderarNotaRecuperacaoAposAMedia")
	private Boolean desconsiderarNotaRecuperacaoAposAMedia;

	@Column(name = "desconsiderarNotaMenorQueSete")
	private Boolean desconsiderarNotaMenorQueSete;

	@OneToOne(mappedBy = "mediaAposRecuperacao", fetch = FetchType.EAGER)
	private Recuperacao recuperacao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumMediaAposRecuperacao getEnumMediaAposRecuperacao() {
		return EnumMediaAposRecuperacao;
	}

	public void setEnumMediaAposRecuperacao(EnumMediaAposRecuperacao enumMediaAposRecuperacao) {
		EnumMediaAposRecuperacao = enumMediaAposRecuperacao;
	}

	public Boolean getDesconsiderarNotaRecuperacaoMenor() {
		return desconsiderarNotaRecuperacaoMenor;
	}

	public void setDesconsiderarNotaRecuperacaoMenor(Boolean desconsiderarNotaRecuperacaoMenor) {
		this.desconsiderarNotaRecuperacaoMenor = desconsiderarNotaRecuperacaoMenor;
	}

	public Boolean getDesconsiderarNotaRecuperacaoAposAMedia() {
		return desconsiderarNotaRecuperacaoAposAMedia;
	}

	public void setDesconsiderarNotaRecuperacaoAposAMedia(Boolean desconsiderarNotaRecuperacaoAposAMedia) {
		this.desconsiderarNotaRecuperacaoAposAMedia = desconsiderarNotaRecuperacaoAposAMedia;
	}

	public Boolean getDesconsiderarNotaMenorQueSete() {
		return desconsiderarNotaMenorQueSete;
	}

	public void setDesconsiderarNotaMenorQueSete(Boolean desconsiderarNotaMenorQueSete) {
		this.desconsiderarNotaMenorQueSete = desconsiderarNotaMenorQueSete;
	}

	public Recuperacao getRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(Recuperacao recuperacao) {
		this.recuperacao = recuperacao;
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
		result = prime * result + ((EnumMediaAposRecuperacao == null) ? 0 : EnumMediaAposRecuperacao.hashCode());
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
		MediaAposRecuperacao other = (MediaAposRecuperacao) obj;
		if (EnumMediaAposRecuperacao != other.EnumMediaAposRecuperacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediaAposRecuperacao [EnumMediaAposRecuperacao=" + EnumMediaAposRecuperacao
				+ ", desconsiderarNotaRecuperacaoMenor=" + desconsiderarNotaRecuperacaoMenor
				+ ", desconsiderarNotaRecuperacaoAposAMedia=" + desconsiderarNotaRecuperacaoAposAMedia
				+ ", desconsiderarNotaMenorQueSete=" + desconsiderarNotaMenorQueSete + ", recuperacao=" + recuperacao
				+ "]";
	}
}
