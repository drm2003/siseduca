/*
 * Created on Apr 11, 2016
 *
 */
package br.com.cdan.util.excecao;

public abstract class ExcecaoSISEDUCA extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public ExcecaoSISEDUCA() {
		super();
	}

	public ExcecaoSISEDUCA(String message) {
		super(message);
	}

	public ExcecaoSISEDUCA(Throwable cause) {
		super(cause);
	}

	public ExcecaoSISEDUCA(String message, Throwable cause) {
		super(message, cause);
	}
}