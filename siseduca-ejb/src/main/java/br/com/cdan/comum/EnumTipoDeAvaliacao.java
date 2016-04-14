package br.com.cdan.comum;

public enum EnumTipoDeAvaliacao {
	NOTAS("Notas"), CONCEITOS("Conceitos"), DISTRIBUICAO(
			"Distribuição de Pontos");

	private final String valor;

	private EnumTipoDeAvaliacao(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
