package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NfeLayout")
public class NfeLayout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 60, nullable = false, unique = true)
	private String descricao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_origemMercadoria", nullable = false)
	private OrigemMercadoria origemMercadoria;

	@OneToOne
	@JoinColumn(name = "id_modalidadeBaseICMS")
	private ModalidadeBaseICMS modalidadeBaseICMS;

	@OneToOne
	@JoinColumn(name = "id_cfop", nullable = false)
	private CFOP cfop;

	@OneToOne
	@JoinColumn(name = "id_cst")
	private CST cst;

	@OneToOne
	@JoinColumn(name = "id_tipi")
	private TIPI tipi;

	@Column(name = "icms")
	private BigDecimal icms;

	@Column(name = "icmsST")
	private BigDecimal icmsST;

	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OrigemMercadoria getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(OrigemMercadoria origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public ModalidadeBaseICMS getModalidadeBaseICMS() {
		return modalidadeBaseICMS;
	}

	public void setModalidadeBaseICMS(ModalidadeBaseICMS modalidadeBaseICMS) {
		this.modalidadeBaseICMS = modalidadeBaseICMS;
	}

	public CFOP getCfop() {
		return cfop;
	}

	public void setCfop(CFOP cfop) {
		this.cfop = cfop;
	}

	public CST getCst() {
		return cst;
	}

	public void setCst(CST cst) {
		this.cst = cst;
	}

	public TIPI getTipi() {
		return tipi;
	}

	public void setTipi(TIPI tipi) {
		this.tipi = tipi;
	}

	public BigDecimal getIcms() {
		return icms;
	}

	public void setIcms(BigDecimal icms) {
		this.icms = icms;
	}

	public BigDecimal getIcmsST() {
		return icmsST;
	}

	public void setIcmsST(BigDecimal icmsST) {
		this.icmsST = icmsST;
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		NfeLayout other = (NfeLayout) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NfeLayout [descricao=" + descricao + ", origem da mercadoria=" + origemMercadoria
				+ ", modalidadebaseICMS=" + modalidadeBaseICMS + ", cfop=" + cfop + ", cst=" + cst + ", tipi=" + tipi
				+ ", icms=" + icms + ", icmsST=" + icmsST + "]";
	}
}
