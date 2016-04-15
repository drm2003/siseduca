package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "Aproveitamento")
public class Aproveitamento implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nomeEstabelecimento")
	private String nomeEstabelecimento;

	@Column(name = "siglaEstabelecimento")
	private String siglaEstabelecimento;

	@OneToOne
	@JoinColumn(name = "cepEstabelecimento")
	private CEP cepEstabelecimento;

	@OneToMany(mappedBy = "aproveitamento", fetch = FetchType.LAZY)
	private Set<Telefone> telefoneEstabelecimento;

	@OneToOne
	@JoinColumn(name = "enderecoEstabelecimento")
	private Endereco enderecoEstabelecimento;

	@Column(name = "nomeCurso")
	private String nomeCurso;

	@Column(name = "nomeDisciplina")
	private String nomeDisciplina;

	@Temporal(TemporalType.TIME)
	@Column(name = "cargaHoraria")
	private Calendar cargaHoraria;

	@Column(name = "semestreAno")
	private String semestreAno;

	@Column(name = "ementa")
	private String ementa;

	@Min(value = 0)
	@Max(value = 10)
	@Column(name = "nota")
	private BigDecimal nota;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coordenador")
	private Funcionario coordenador;

	@Column(name = "parecerCoordenador")
	private String parecerCoordenador;

	@Column(name = "confirmaCredito")
	private Boolean confirmaCredito;

	@Column(name = "necessarioExameProficiencia")
	private Boolean necessarioExameProficiencia;

	@Min(value = 0)
	@Max(value = 10)
	@Column(name = "notaExameProficiencia")
	private BigDecimal notaExameProficiencia;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

	public String getSiglaEstabelecimento() {
		return siglaEstabelecimento;
	}

	public void setSiglaEstabelecimento(String siglaEstabelecimento) {
		this.siglaEstabelecimento = siglaEstabelecimento;
	}

	public CEP getCepEstabelecimento() {
		return cepEstabelecimento;
	}

	public void setCepEstabelecimento(CEP cepEstabelecimento) {
		this.cepEstabelecimento = cepEstabelecimento;
	}

	public Set<Telefone> getTelefoneEstabelecimento() {
		return telefoneEstabelecimento;
	}

	public void setTelefoneEstabelecimento(Set<Telefone> telefoneEstabelecimento) {
		this.telefoneEstabelecimento = telefoneEstabelecimento;
	}

	public Endereco getEnderecoEstabelecimento() {
		return enderecoEstabelecimento;
	}

	public void setEnderecoEstabelecimento(Endereco enderecoEstabelecimento) {
		this.enderecoEstabelecimento = enderecoEstabelecimento;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public Calendar getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Calendar cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getSemestreAno() {
		return semestreAno;
	}

	public void setSemestreAno(String semestreAno) {
		this.semestreAno = semestreAno;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public Funcionario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Funcionario coordenador) {
		this.coordenador = coordenador;
	}

	public String getParecerCoordenador() {
		return parecerCoordenador;
	}

	public void setParecerCoordenador(String parecerCoordenador) {
		this.parecerCoordenador = parecerCoordenador;
	}

	public Boolean getConfirmaCredito() {
		return confirmaCredito;
	}

	public void setConfirmaCredito(Boolean confirmaCredito) {
		this.confirmaCredito = confirmaCredito;
	}

	public Boolean getNecessarioExameProficiencia() {
		return necessarioExameProficiencia;
	}

	public void setNecessarioExameProficiencia(Boolean necessarioExameProficiencia) {
		this.necessarioExameProficiencia = necessarioExameProficiencia;
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public BigDecimal getNotaExameProficiencia() {
		return notaExameProficiencia;
	}

	public void setNotaExameProficiencia(BigDecimal notaExameProficiencia) {
		this.notaExameProficiencia = notaExameProficiencia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
