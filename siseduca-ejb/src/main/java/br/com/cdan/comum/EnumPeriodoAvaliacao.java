package br.com.cdan.comum;

public enum EnumPeriodoAvaliacao {
	MODULO("Módulo"), BIMESTRE("Bimestre"), TRIMESTRE("Trimestre"), SEMESTRE(
			"Semestre");

	private final String valor;

	private EnumPeriodoAvaliacao(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
