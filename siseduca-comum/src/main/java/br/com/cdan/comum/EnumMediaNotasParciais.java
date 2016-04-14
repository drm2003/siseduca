package br.com.cdan.comum;

public enum EnumMediaNotasParciais {
	MEDIAA("Calcular média entre as notas"), SOMARNOTAS(
			"Somar as notas");

	private final String valor;

	private EnumMediaNotasParciais(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
