package br.com.cdan.model.geral.cep;

public enum EnumChaveCidade {
	C("C", "cidade possui cep unico"), D("D", "distrito possui cep unico"), L(
			"L", "distrito possui cep por logradouro"), NOVES("99999",
			"cidade com cep por logradouro");

	private String valor;
	private String descricao;

	private EnumChaveCidade(String valorOpcao, String descricao) {
		this.valor = valorOpcao;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
}
