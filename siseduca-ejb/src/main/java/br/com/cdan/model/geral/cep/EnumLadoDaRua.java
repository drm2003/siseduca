package br.com.cdan.model.geral.cep;

public enum EnumLadoDaRua {
	UM(1, "indica todo lado par"), DOIS(2, "indica lado par limitado"), QUINZE(
			15, "abrange lado par"), DEZESSEIS(16, "indica todo lado impar"), TRINTAEDOIS(
			32, "indica lado impar limitado"), DUZENTOSEQUARENTA(240,
			"abrange lado impar"), DEZESSETE(17, "abrange todo o logradouro"), TRINTAEQUATRO(
			34, "indica CEP limitado"), TRINTAECINCO(35,
			"indica CEP SEM NUMERO");

	private int codigo;
	private String descricao;

	private EnumLadoDaRua(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
