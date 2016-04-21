package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

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
import javax.persistence.Table;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;
import br.com.cdan.model.geral.Categoria;

@Entity
@Table(name = "Caixa")
public class Caixa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private EnumTipoDePlanoDeContas tipo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta")
	private Conta conta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoDeMovimentacao")
	private TipoDeCobrancaRecebimento tipoDeMovimentacao;

	@Column(name = "dataDeLancamento")
	private Calendar dataDeLancamento;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "documento")
	private String documento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	private Categoria categoria;

	@Column(name = "complementoPlanoDeContas")
	private String complementoPlanoDeContas;

	@Column(name = "observacoes")
	private String observacoes;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumTipoDePlanoDeContas getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoDePlanoDeContas tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoDeCobrancaRecebimento getTipoDeMovimentacao() {
		return tipoDeMovimentacao;
	}

	public void setTipoDeMovimentacao(TipoDeCobrancaRecebimento tipoDeMovimentacao) {
		this.tipoDeMovimentacao = tipoDeMovimentacao;
	}

	public Calendar getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(Calendar dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getComplementoPlanoDeContas() {
		return complementoPlanoDeContas;
	}

	public void setComplementoPlanoDeContas(String complementoPlanoDeContas) {
		this.complementoPlanoDeContas = complementoPlanoDeContas;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((tipoDeMovimentacao == null) ? 0 : tipoDeMovimentacao.hashCode());
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
		Caixa other = (Caixa) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipoDeMovimentacao == null) {
			if (other.tipoDeMovimentacao != null)
				return false;
		} else if (!tipoDeMovimentacao.equals(other.tipoDeMovimentacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Caixa [id=" + id + ", tipo=" + tipo + ", conta=" + conta + ", tipoDeMovimentacao=" + tipoDeMovimentacao
				+ ", dataDeLancamento=" + dataDeLancamento + ", valor=" + valor + ", documento=" + documento
				+ ", categoria=" + categoria + ", complementoPlanoDeContas=" + complementoPlanoDeContas
				+ ", observacoes=" + observacoes + "]";
	}
}
