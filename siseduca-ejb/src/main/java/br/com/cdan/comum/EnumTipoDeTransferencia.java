package br.com.cdan.comum;

public enum EnumTipoDeTransferencia {
	RECEBIDA("Transferencia recebida"), EXPEDIDA("Transferência Expedida");

	private final String valor;

	private EnumTipoDeTransferencia(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}