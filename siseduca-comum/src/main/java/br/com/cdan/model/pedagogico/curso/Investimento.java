package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.comum.EnumTipoDeSituacaoInvestimento;
import br.com.cdan.model.estoque.Item;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.geral.Categoria;
import br.com.cdan.model.pedagogico.contrato.Matricula;

@Entity
@Table(name = "Investimento")
public class Investimento implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_tipoDeInvestimento")
	private TipoDeInvestimento tipoDeInvestimento;

	@Column(name = "descricaoPlano", unique = true, nullable = false)
	private String descricaoPlano;

	@Column(name = "numeroDeParcelas")
	private Long numeroDeParcelas;

	@Column(name = "valorDaParcela")
	private BigDecimal valorDaParcela;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataInicial")
	private Calendar dataInicial;

	@OneToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@OneToMany(mappedBy = "investimento")
	private Set<Matricula> matriculas;

	@Column(name = "considerarMesAtual")
	private Boolean considerarMesAtual;

	@Column(name = "valorDaPrimeiraParcelaDiferenciada")
	private Boolean valorDaPrimeiraParcelaDiferenciada;

	@Column(name = "valorPrimeiraParcelaDiferenciado")
	private BigDecimal valorPrimeiraParcelaDiferenciado;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataPrimeiraParcela")
	private Calendar dataDiferenciadaPrimeiraParcela;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item")
	private Item item;

	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "tipoDeSituacao")
	private EnumTipoDeSituacaoInvestimento tipoDeSituacao;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "primeiraParcela")
	private ContaAReceber contaAReceberPrimeiraParcela;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "investimento")
	private ContaAReceber contaAReceber;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDeInvestimento getTipoDeInvestimento() {
		return tipoDeInvestimento;
	}

	public void setTipoDeInvestimento(TipoDeInvestimento tipoDeInvestimento) {
		this.tipoDeInvestimento = tipoDeInvestimento;
	}

	public String getDescricaoPlano() {
		return descricaoPlano;
	}

	public void setDescricaoPlano(String descricaoPlano) {
		this.descricaoPlano = descricaoPlano;
	}

	public Long getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Long numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Boolean getConsiderarMesAtual() {
		return considerarMesAtual;
	}

	public void setConsiderarMesAtual(Boolean considerarMesAtual) {
		this.considerarMesAtual = considerarMesAtual;
	}

	public Boolean getValorDaPrimeiraParcelaDiferenciada() {
		return valorDaPrimeiraParcelaDiferenciada;
	}

	public void setValorDaPrimeiraParcelaDiferenciada(Boolean valorDaPrimeiraParcelaDiferenciada) {
		this.valorDaPrimeiraParcelaDiferenciada = valorDaPrimeiraParcelaDiferenciada;
	}

	public BigDecimal getValorDaParcela() {
		return valorDaParcela;
	}

	public void setValorDaParcela(BigDecimal valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}

	public BigDecimal getValorPrimeiraParcelaDiferenciado() {
		return valorPrimeiraParcelaDiferenciado;
	}

	public void setValorPrimeiraParcelaDiferenciado(BigDecimal valorPrimeiraParcelaDiferenciado) {
		this.valorPrimeiraParcelaDiferenciado = valorPrimeiraParcelaDiferenciado;
	}

	public Calendar getDataDiferenciadaPrimeiraParcela() {
		return dataDiferenciadaPrimeiraParcela;
	}

	public void setDataDiferenciadaPrimeiraParcela(Calendar dataDiferenciadaPrimeiraParcela) {
		this.dataDiferenciadaPrimeiraParcela = dataDiferenciadaPrimeiraParcela;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ContaAReceber getContaAReceber() {
		return contaAReceber;
	}

	public void setContaAReceber(ContaAReceber contaAReceber) {
		this.contaAReceber = contaAReceber;
	}

	public EnumTipoDeSituacaoInvestimento getTipoDeSituacao() {
		return tipoDeSituacao;
	}

	public void setTipoDeSituacao(EnumTipoDeSituacaoInvestimento tipoDeSituacao) {
		this.tipoDeSituacao = tipoDeSituacao;
	}

	public ContaAReceber getContaAReceberPrimeiraParcela() {
		return contaAReceberPrimeiraParcela;
	}

	public void setContaAReceberPrimeiraParcela(ContaAReceber contaAReceberPrimeiraParcela) {
		this.contaAReceberPrimeiraParcela = contaAReceberPrimeiraParcela;
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
		result = prime * result + ((descricaoPlano == null) ? 0 : descricaoPlano.hashCode());
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
		Investimento other = (Investimento) obj;
		if (descricaoPlano == null) {
			if (other.descricaoPlano != null)
				return false;
		} else if (!descricaoPlano.equals(other.descricaoPlano))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Investimento [descricaoPlano=" + descricaoPlano + ", numeroDeParcelas=" + numeroDeParcelas
				+ ", dataInicial=" + dataInicial + "]";
	}
}
