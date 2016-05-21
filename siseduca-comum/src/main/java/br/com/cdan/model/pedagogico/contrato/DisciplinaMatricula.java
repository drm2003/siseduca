package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DisciplinaMatricula")
public class DisciplinaMatricula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 120)
	@Column(name = "nome", nullable = false, length = 120)
	private String nome;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 10)
	@Column(name = "sigla", nullable = false, length = 10)
	private String sigla;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_matricula")
	private Matricula matricula;

	@Column(name = "id_tipoDeCurso")
	private Long tipoDeCurso;

	@Column(name = "id_tipoDeDisciplina")
	private Long tipoDeDisciplina;

	@Column(name = "id_matrizCurricular")
	private Long matrizCurricular;

	@Column(name = "codigoINEP")
	private String codigoINEP;

	@Column(name = "valorHoraAula")
	private BigDecimal valorHoraAula;

	@Temporal(TemporalType.TIME)
	@Column(name = "cargaHoraria")
	private Date cargaHoraria;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "aproveitamento")
	private Aproveitamento aproveitamento;

	@Column(name = "opcional")
	private Boolean opcional;

	@NotNull
	@Column(name = "compartilhado")
	private Boolean compartilhado;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getTipoDeCurso() {
		return tipoDeCurso;
	}

	public void setTipoDeCurso(Long tipoDeCurso) {
		this.tipoDeCurso = tipoDeCurso;
	}

	public Long getTipoDeDisciplina() {
		return tipoDeDisciplina;
	}

	public void setTipoDeDisciplina(Long tipoDeDisciplina) {
		this.tipoDeDisciplina = tipoDeDisciplina;
	}

	public Long getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(Long matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public String getCodigoINEP() {
		return codigoINEP;
	}

	public void setCodigoINEP(String codigoINEP) {
		this.codigoINEP = codigoINEP;
	}

	public BigDecimal getValorHoraAula() {
		return valorHoraAula;
	}

	public void setValorHoraAula(BigDecimal valorHoraAula) {
		this.valorHoraAula = valorHoraAula;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public Date getCargaHoraria() {
		return cargaHoraria;
	}

	public Aproveitamento getAproveitamento() {
		return aproveitamento;
	}

	public Boolean getOpcional() {
		return opcional;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public void setCargaHoraria(Date cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public void setAproveitamento(Aproveitamento aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public void setOpcional(Boolean opcional) {
		this.opcional = opcional;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
