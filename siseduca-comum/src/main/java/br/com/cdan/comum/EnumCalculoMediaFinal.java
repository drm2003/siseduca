																																																																																																																																																													package br.com.cdan.comum;

public enum EnumCalculoMediaFinal {
	MEDIAAUTOMATICA("Calcular média entre as notas"), SOMARNOTAS(
			"Somar as notas"), MEDIAMANUAL("Média é informada manualmente");

	private final String valor;

	private EnumCalculoMediaFinal(String valorOpcao) {
		valor = valorOpcao;
	}
																								
	public String getValor() {
		return valor;
	}
}
