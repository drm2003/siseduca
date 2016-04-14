/*
 * Created on 11/04/2016
 *
 */
package br.com.cdan.util.excecao;

/**
 *
 */
public class ExcecaoUtil extends ExcecaoSISEDUCA {
	private static final long serialVersionUID = 3256726177828778035L;

	public ExcecaoUtil() {
		super();
	}

	public ExcecaoUtil(String message) {
		super(message);
	}

	public ExcecaoUtil(Throwable cause) {
		super(cause);
	}

	public ExcecaoUtil(String message, Throwable cause) {
		super(message, cause);
	}
}
