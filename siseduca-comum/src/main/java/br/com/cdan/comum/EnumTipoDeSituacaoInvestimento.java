package br.com.cdan.comum;

public enum EnumTipoDeSituacaoInvestimento {
	PENDENTE("Pendente"), QUITADA("Quitada"), CANCELADA("Cancelada");

	private final String valor;

	private EnumTipoDeSituacaoInvestimento(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
