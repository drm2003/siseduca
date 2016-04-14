package br.com.cdan.comum;

public enum EnumTipoMediaAposExameFinal {
	CALCULARAMEDIA("Calcular média entre a média anual e a nota do Exame Final"), MANTERAMAIORNOTA(
			"Manter a maior nota entre a média anual e a nota do Exame Final"), SOMARMEDIAANUAL(
			"Somar a média anual e a nota do Exame Final"), MANTERNOTAEXAMEFINAL(
			"Manter a nota do Exame Final");

	private final String valor;

	private EnumTipoMediaAposExameFinal(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
