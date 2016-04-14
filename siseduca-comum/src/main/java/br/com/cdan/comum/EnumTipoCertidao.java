package br.com.cdan.comum;

public enum EnumTipoCertidao {
	NASCIMENTO("Nascimento"), CASAMENTO("Casamento");
	
	private final String valor;

	private EnumTipoCertidao(String valor) {
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}
}
