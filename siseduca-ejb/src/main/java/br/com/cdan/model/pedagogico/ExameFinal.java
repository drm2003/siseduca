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

	@Override
	public String toString() {
		return "ExameFinal [mediaExameFinal=" + mediaExameFinal + ", pesoExameFinal=" + pesoExameFinal
				+ ", valorMediaAposExameFinal=" + valorMediaAposExameFinal + ", usarConselhoDeClasse="
				+ usarConselhoDeClasse + "]";
	}
}
