package br.com.cdan.comum;

public enum EnumTipoDePessoa {
	F("Física"), J("Jurídica");

	private final String valor;

	private EnumTipoDePessoa(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
