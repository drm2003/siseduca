package br.com.cdan.comum;

public enum EnumMediaAposRecuperacao {
	MEDIAENTREANOTAEARECUPERACAO("Calcular média entre a nota e a recuperação"), MANTERAMAIORNOTAENTREAMEDIAEARECUPERACAO(
			"Manter	a maior média entre a nota e a recuperação");

	private final String valor;

	private EnumMediaAposRecuperacao(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
