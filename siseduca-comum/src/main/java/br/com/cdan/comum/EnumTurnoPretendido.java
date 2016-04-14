package br.com.cdan.comum;

public enum EnumTurnoPretendido {
	MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite");

	private final String valor;

	private EnumTurnoPretendido(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
