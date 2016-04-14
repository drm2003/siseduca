package br.com.cdan.comum;

public enum EnumOcorrenciaAlunoTurma {
	ALUNO("Aluno"), TURMA("Turma");

	private final String valor;

	private EnumOcorrenciaAlunoTurma(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
