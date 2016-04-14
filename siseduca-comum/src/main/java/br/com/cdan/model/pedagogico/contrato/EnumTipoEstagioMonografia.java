package br.com.cdan.model.pedagogico.contrato;

public enum EnumTipoEstagioMonografia {
	ESTAGIO("Estágio"), MONOGRAFIA("Monografia");

	private final String valor;

	private EnumTipoEstagioMonografia(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
