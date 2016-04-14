package br.com.cdan.comum;

public enum EnumTipoAquisicao {
	COMPRA("Compra"), DOACAO("Doação"), EMPRESTIMO("Empréstimo"), TROCA("Troca"), VENDA("Venda");

	private final String valor;

	private EnumTipoAquisicao(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
