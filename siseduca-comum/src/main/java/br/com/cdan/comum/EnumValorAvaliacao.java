package br.com.cdan.comum;

public enum EnumValorAvaliacao {
	zeroAdez("0 a 10"), zeroAcem("0 a 100");

	private final String valor;

	private EnumValorAvaliacao(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
