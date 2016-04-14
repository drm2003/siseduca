package br.com.cdan.comum;

public enum EnumEspecieDesconto {
	PERCENTUAL("Percentual"), VALOR("Valor");

	private final String valor;
	
	private EnumEspecieDesconto(String valor){
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}
}
