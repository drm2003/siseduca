package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.comum.EnumCalculoMediaFinal;
import br.com.cdan.comum.EnumPeriodoAvaliacao;
import br.com.cdan.comum.EnumTipoDeAvaliacao;
import br.com.cdan.comum.EnumValorAvaliacao;

@Entity
@Table(name = "SistemaDeAvaliacao")
public class SistemaDeAvaliacao implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "nome", length = 150, nullable = false, unique = true)
	private String nome;

	@NotNull
	@Column(name = "padrao")
	private Boolean padrao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDeAvaliacao")
	private EnumTipoDeAvaliacao enumTipoDeAvaliacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "valorAvaliacao")
	private EnumValorAvaliacao enumValorAvaliacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "periodoAvaliacao")
	private EnumPeriodoAvaliacao enumPeriodoAvaliacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "calculoMediaFinal")
	private EnumCalculoMediaFinal enumCalculoMediaFinal;

	@Column(name = "mediaMinima")
	private BigDecimal mediaMinima;

	@Column(name = "pesoDaMedia")
	private BigDecimal pesoDaMedia;

	@Column(name = "frequenciaMinima")
	private BigDecimal frequenciaMinima;

	@Column(name = "pesoPontos")
	private BigDecimal pesoPontos;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	@OneToOne
	@JoinColumn(name = "notasParciais")
	private NotasParciais notasParciais;

	@OneToOne
	@JoinColumn(name = "recuperacao")
	private Recuperacao recuperacao;

	@OneToOne
	@JoinColumn(name = "exameFinal")
	private ExameFinal exameFinal;

	@Column(name = "sintese")
	private String sintese;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getPadrao() {
		return padrao;
	}

	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}

	public EnumTipoDeAvaliacao getEnumTipoDeAvaliacao() {
		return enumTipoDeAvaliacao;
	}

	public void setEnumTipoDeAvaliacao(EnumTipoDeAvaliacao enumTipoDeAvaliacao) {
		this.enumTipoDeAvaliacao = enumTipoDeAvaliacao;
	}

	public EnumValorAvaliacao getEnumValorAvaliacao() {
		return enumValorAvaliacao;
	}

	public void setEnumValorAvaliacao(EnumValorAvaliacao enumValorAvaliacao) {
		this.enumValorAvaliacao = enumValorAvaliacao;
	}

	public EnumPeriodoAvaliacao getEnumPeriodoAvaliacao() {
		return enumPeriodoAvaliacao;
	}

	public void setEnumPeriodoAvaliacao(EnumPeriodoAvaliacao enumPeriodoAvaliacao) {
		this.enumPeriodoAvaliacao = enumPeriodoAvaliacao;
	}

	public EnumCalculoMediaFinal getEnumCalculoMediaFinal() {
		return enumCalculoMediaFinal;
	}

	public void setEnumCalculoMediaFinal(EnumCalculoMediaFinal enumCalculoMediaFinal) {
		this.enumCalculoMediaFinal = enumCalculoMediaFinal;
	}

	public BigDecimal getMediaMinima() {
		return mediaMinima;
	}

	public void setMediaMinima(BigDecimal mediaMinima) {
		this.mediaMinima = mediaMinima;
	}

	public BigDecimal getPesoDaMedia() {
		return pesoDaMedia;
	}

	public void setPesoDaMedia(BigDecimal pesoDaMedia) {
		this.pesoDaMedia = pesoDaMedia;
	}

	public BigDecimal getFrequenciaMinima() {
		return frequenciaMinima;
	}

	public void setFrequenciaMinima(BigDecimal frequenciaMinima) {
		this.frequenciaMinima = frequenciaMinima;
	}

	public BigDecimal getPesoPontos() {
		return pesoPontos;
	}

	public void setPesoPontos(BigDecimal pesoPontos) {
		this.pesoPontos = pesoPontos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public NotasParciais getNotasParciais() {
		return notasParciais;
	}

	public void setNotasParciais(NotasParciais notasParciais) {
		this.notasParciais = notasParciais;
	}

	public Recuperacao getRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(Recuperacao recuperacao) {
		this.recuperacao = recuperacao;
	}

	public ExameFinal getExameFinal() {
		return exameFinal;
	}

	public void setExameFinal(ExameFinal exameFinal) {
		this.exameFinal = exameFinal;
	}

	public String getSintese() {
		return sintese;
	}

	public void setSintese(String sintese) {
		this.sintese = sintese;
	}
}
