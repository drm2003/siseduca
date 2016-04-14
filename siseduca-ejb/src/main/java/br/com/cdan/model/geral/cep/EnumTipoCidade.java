package br.com.cdan.model.geral.cep;

public enum EnumTipoCidade {
	M("Municipio"), D("Distrito"), P("povoado"), R("Região Administrativa");

	private final String valor;

	private EnumTipoCidade(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
