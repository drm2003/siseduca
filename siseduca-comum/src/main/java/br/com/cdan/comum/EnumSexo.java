package br.com.cdan.comum;

public enum EnumSexo {
	M("Masculino"), F("Feminino");
	
	private final String valor;

	private EnumSexo(String valor) {
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}
}
