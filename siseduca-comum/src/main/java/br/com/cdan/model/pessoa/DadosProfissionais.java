package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.comum.EnumTipoDePagamento;

@Entity
@Table(name = "DadosProfissionais")
public class DadosProfissionais implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "dadosProfissionais")
	private Funcionario funcionario;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDePagamento")
	private EnumTipoDePagamento tipoDePagamento;

	@Column(name = "aulasNormais")
	private BigDecimal aulasNormais;

	@Column(name = "salario")
	private BigDecimal Salario;

	@Column(name = "INSS")
	private BigDecimal INSS;

	@Column(name = "carteiraProfissional")
	private String carteiraProfissional;

	@Column(name = "dataDeAdmissao")
	@Temporal(TemporalType.DATE)
	private Calendar dataDeAdmissao;

	@Column(name = "dataDeDemissao")
	@Temporal(TemporalType.DATE)
	private Calendar dataDeDemissao;

	@Column(name = "curriculo")
	private String curriculo;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public EnumTipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(EnumTipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	public BigDecimal getAulasNormais() {
		return aulasNormais;
	}

	public void setAulasNormais(BigDecimal aulasNormais) {
		this.aulasNormais = aulasNormais;
	}

	public BigDecimal getSalario() {
		return Salario;
	}

	public void setSalario(BigDecimal salario) {
		Salario = salario;
	}

	public BigDecimal getINSS() {
		return INSS;
	}

	public void setINSS(BigDecimal iNSS) {
		INSS = iNSS;
	}

	public String getCarteiraProfissional() {
		return carteiraProfissional;
	}

	public void setCarteiraProfissional(String carteiraProfissional) {
		this.carteiraProfissional = carteiraProfissional;
	}

	public Calendar getDataDeAdmissao() {
		return dataDeAdmissao;
	}

	public void setDataDeAdmissao(Calendar dataDeAdmissao) {
		this.dataDeAdmissao = dataDeAdmissao;
	}

	public Calendar getDataDeDemissao() {
		return dataDeDemissao;
	}

	public void setDataDeDemissao(Calendar dataDeDemissao) {
		this.dataDeDemissao = dataDeDemissao;
	}

	public String getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
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
		result = prime * result + ((carteiraProfissional == null) ? 0 : carteiraProfissional.hashCode());
		result = prime * result + ((dataDeAdmissao == null) ? 0 : dataDeAdmissao.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
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
		DadosProfissionais other = (DadosProfissionais) obj;
		if (carteiraProfissional == null) {
			if (other.carteiraProfissional != null)
				return false;
		} else if (!carteiraProfissional.equals(other.carteiraProfissional))
			return false;
		if (dataDeAdmissao == null) {
			if (other.dataDeAdmissao != null)
				return false;
		} else if (!dataDeAdmissao.equals(other.dataDeAdmissao))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DadosProfissionais [funcionario=" + funcionario + ", tipoDePagamento=" + tipoDePagamento
				+ ", aulasNormais=" + aulasNormais + ", Salario=" + Salario + ", INSS=" + INSS
				+ ", carteiraProfissional=" + carteiraProfissional + ", dataDeAdmissao=" + dataDeAdmissao
				+ ", dataDeDemissao=" + dataDeDemissao + ", curriculo=" + curriculo + "]";
	}
}
