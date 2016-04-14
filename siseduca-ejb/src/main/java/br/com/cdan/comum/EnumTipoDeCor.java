package br.com.cdan.comum;

public enum EnumTipoDeCor {
	BRANCO("Branco"), PARDO("Pardo"), NEGRO("Negro"), AMARELO("Amarelo"), NAODECLARADO(
			"Não declarado");

	private final String valor;

	private EnumTipoDeCor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
