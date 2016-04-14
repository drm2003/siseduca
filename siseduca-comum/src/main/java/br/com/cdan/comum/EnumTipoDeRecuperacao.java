package br.com.cdan.comum;

public enum EnumTipoDeRecuperacao {
	SEMRECUPERACAO("Sem Recuperação"), RECUPERACAOACADAPERIODO(
			"Recuperação a cada período"), RECUPERACAOACADAMODULO(
			"Recuperação a cada módulo"), OUTRA("Outra");

	private final String valor;

	private EnumTipoDeRecuperacao(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
