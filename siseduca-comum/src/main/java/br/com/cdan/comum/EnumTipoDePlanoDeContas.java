package br.com.cdan.comum;

public enum EnumTipoDePlanoDeContas {
	ENTRADA("Entrada"), SAIDA("Saída");

	private final String valor;

	private EnumTipoDePlanoDeContas(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
