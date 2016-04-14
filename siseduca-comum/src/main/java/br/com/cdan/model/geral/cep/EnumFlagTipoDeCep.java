package br.com.cdan.model.geral.cep;

public enum EnumFlagTipoDeCep {
	ZERO(0, "cep comum"), SEIS(6, "cep especial");

	private int valor;
	private String descricao;

	private EnumFlagTipoDeCep(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
}
