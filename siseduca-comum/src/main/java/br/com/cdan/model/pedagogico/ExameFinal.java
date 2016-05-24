package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ExameFinal")
public class ExameFinal implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mediaExameFinal")
	private BigDecimal mediaExameFinal;

	@Column(name = "pesoExameFinal")
	private Long pesoExameFinal;

	@Column(name = "ValorMediaAposExameFinal")
	private BigDecimal valorMediaAposExameFinal;

	@Column(name = "usarConselhoDeClasse")
	private Boolean usarConselhoDeClasse;

	@OneToOne
	@JoinColumn(name = "mediaAposExameFinal")
	private MediaAposExameFinal mediaAposExameFinal;

	@OneToOne(mappedBy = "exameFinal")
	private SistemaDeAvaliacao sistemaDeAvaliacao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPesoExameFinal() {
		return pesoExameFinal;
	}

	public void setPesoExameFinal(Long pesoExameFinal) {
		this.pesoExameFinal = pesoExameFinal;
	}

	public BigDecimal getMediaExameFinal() {
		return mediaExameFinal;
	}

	public void setMediaExameFinal(BigDecimal mediaExameFinal) {
		this.mediaExameFinal = mediaExameFinal;
	}

	public BigDecimal getValorMediaAposExameFinal() {
		return valorMediaAposExameFinal;
	}

	public void setValorMediaAposExameFinal(BigDecimal valorMediaAposExameFinal) {
		this.valorMediaAposExameFinal = valorMediaAposExameFinal;
	}

	public Boolean getUsarConselhoDeClasse() {
		return usarConselhoDeClasse;
	}

	public void setUsarConselhoDeClasse(Boolean usarConselhoDeClasse) {
		this.usarConselhoDeClasse = usarConselhoDeClasse;
	}

	public MediaAposExameFinal getMediaAposExameFinal() {
		return mediaAposExameFinal;
	}

	public void setMediaAposExameFinal(MediaAposExameFinal mediaAposExameFinal) {
		this.mediaAposExameFinal = mediaAposExameFinal;
	}

	public SistemaDeAvaliacao getSistemaDeAvaliacao() {
		return sistemaDeAvaliacao;
	}

	public void setSistemaDeAvaliacao(SistemaDeAvaliacao sistemaDeAvaliacao) {
		this.sistemaDeAvaliacao = sistemaDeAvaliacao;
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
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((mediaAposExameFinal == null) ? 0 : mediaAposExameFinal.hashCode());
		result = prime * result + ((mediaExameFinal == null) ? 0 : mediaExameFinal.hashCode());
		result = prime * result + ((pesoExameFinal == null) ? 0 : pesoExameFinal.hashCode());
		result = prime * result + ((sistemaDeAvaliacao == null) ? 0 : sistemaDeAvaliacao.hashCode());
		result = prime * result + ((usarConselhoDeClasse == null) ? 0 : usarConselhoDeClasse.hashCode());
		result = prime * result + ((valorMediaAposExameFinal == null) ? 0 : valorMediaAposExameFinal.hashCode());
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
		ExameFinal other = (ExameFinal) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (mediaAposExameFinal == null) {
			if (other.mediaAposExameFinal != null)
				return false;
		} else if (!mediaAposExameFinal.equals(other.mediaAposExameFinal))
			return false;
		if (mediaExameFinal == null) {
			if (other.mediaExameFinal != null)
				return false;
		} else if (!mediaExameFinal.equals(other.mediaExameFinal))
			return false;
		if (pesoExameFinal == null) {
			if (other.pesoExameFinal != null)
				return false;
		} else if (!pesoExameFinal.equals(other.pesoExameFinal))
			return false;
		if (sistemaDeAvaliacao == null) {
			if (other.sistemaDeAvaliacao != null)
				return false;
		} else if (!sistemaDeAvaliacao.equals(other.sistemaDeAvaliacao))
			return false;
		if (usarConselhoDeClasse == null) {
			if (other.usarConselhoDeClasse != null)
				return false;
		} else if (!usarConselhoDeClasse.equals(other.usarConselhoDeClasse))
			return false;
		if (valorMediaAposExameFinal == null) {
			if (other.valorMediaAposExameFinal != null)
				return false;
		} else if (!valorMediaAposExameFinal.equals(other.valorMediaAposExameFinal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExameFinal [mediaExameFinal=" + mediaExameFinal + ", pesoExameFinal=" + pesoExameFinal
				+ ", valorMediaAposExameFinal=" + valorMediaAposExameFinal + ", usarConselhoDeClasse="
				+ usarConselhoDeClasse + "]";
	}
}
