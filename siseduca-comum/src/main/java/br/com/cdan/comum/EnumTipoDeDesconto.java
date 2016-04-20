package br.com.cdan.comum;

public enum EnumTipoDeDesconto {
	BOLSA("Bolsa"), CONlVENIO("Convênio");

	private final String valor;

	private EnumTipoDeDesconto(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
