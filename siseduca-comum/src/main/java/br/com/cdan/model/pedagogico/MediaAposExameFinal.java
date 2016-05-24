package br.com.cdan.model.pedagogico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.comum.EnumTipoMediaAposExameFinal;

@Entity
@Table(name = "MediaAposExameFinal")
public class MediaAposExameFinal implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoMediaAposExameFinal")
	private EnumTipoMediaAposExameFinal enumTipoMediaAposExameFinal;

	@Column(name = "desconsiderarANotaDeExameFinal")
	private Boolean desconsiderarANotaDeExameFinal;

	@Column(name = "considerarANotaDeExameFinal")
	private Boolean considerarANotaDeExameFinal;

	@OneToOne(mappedBy = "mediaAposExameFinal")
	private ExameFinal exameFinal;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumTipoMediaAposExameFinal getEnumTipoMediaAposExameFinal() {
		return enumTipoMediaAposExameFinal;
	}

	public void setEnumTipoMediaAposExameFinal(EnumTipoMediaAposExameFinal enumTipoMediaAposExameFinal) {
		this.enumTipoMediaAposExameFinal = enumTipoMediaAposExameFinal;
	}

	public Boolean getDesconsiderarANotaDeExameFinal() {
		return desconsiderarANotaDeExameFinal;
	}

	public void setDesconsiderarANotaDeExameFinal(Boolean desconsiderarANotaDeExameFinal) {
		this.desconsiderarANotaDeExameFinal = desconsiderarANotaDeExameFinal;
	}

	public Boolean getConsiderarANotaDeExameFinal() {
		return considerarANotaDeExameFinal;
	}

	public void setConsiderarANotaDeExameFinal(Boolean considerarANotaDeExameFinal) {
		this.considerarANotaDeExameFinal = considerarANotaDeExameFinal;
	}

	public ExameFinal getExameFinal() {
		return exameFinal;
	}

	public void setExameFinal(ExameFinal exameFinal) {
		this.exameFinal = exameFinal;
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
		result = prime * result + ((enumTipoMediaAposExameFinal == null) ? 0 : enumTipoMediaAposExameFinal.hashCode());
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
		MediaAposExameFinal other = (MediaAposExameFinal) obj;
		if (enumTipoMediaAposExameFinal != other.enumTipoMediaAposExameFinal)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediaAposExameFinal [enumTipoMediaAposExameFinal=" + enumTipoMediaAposExameFinal
				+ ", desconsiderarANotaDeExameFinal=" + desconsiderarANotaDeExameFinal
				+ ", considerarANotaDeExameFinal=" + considerarANotaDeExameFinal + ", exameFinal=" + exameFinal + "]";
	}
}
