package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.model.pedagogico.CalendarioLetivo;

@Entity
@Table(name = "FeriadoEvento")
public class FeriadoEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoDeData")
	private TipoDeData tipoDeData;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicio")
	private Calendar dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFinal")
	private Calendar dataFinal;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_CalendarioLetivo")
	private CalendarioLetivo calendarioLetivo;

	@Column(name = "ativo")
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

	public TipoDeData getTipoDeData() {
		return tipoDeData;
	}

	public void setTipoDeData(TipoDeData tipoDeData) {
		this.tipoDeData = tipoDeData;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public CalendarioLetivo getCalendarioLetivo() {
		return calendarioLetivo;
	}

	public void setCalendarioLetivo(CalendarioLetivo calendarioLetivo) {
		this.calendarioLetivo = calendarioLetivo;
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
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((tipoDeData == null) ? 0 : tipoDeData.hashCode());
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
		FeriadoEvento other = (FeriadoEvento) obj;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipoDeData == null) {
			if (other.tipoDeData != null)
				return false;
		} else if (!tipoDeData.equals(other.tipoDeData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FeriadoEvento [descricao=" + descricao + ", tipoDeData="
				+ tipoDeData + ", dataInicio=" + dataInicio + ", dataFinal="
				+ dataFinal + ", calendarioLetivo=" + calendarioLetivo + "]";
	}
}
