package br.com.cdan.comum;

public enum EnumDiaDaSemana {
	SEGUNDA("Segunda-feira"), TERCA("Terça-feira"), QUARTA("Quarta-feira"), QUINTA(
			"Quinta-feira"), SEXTA("Sexta-feira"), SABADO("Sábado"), DOMINGO(
			"Domingo");

	private final String valor;

	private EnumDiaDaSemana(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
