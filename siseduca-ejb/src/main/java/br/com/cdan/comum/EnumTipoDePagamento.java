package br.com.cdan.comum;

public enum EnumTipoDePagamento {
	HORISTA("Horista"), MENSALISTA("Mensalista");

	private final String valor;

	private EnumTipoDePagamento(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
