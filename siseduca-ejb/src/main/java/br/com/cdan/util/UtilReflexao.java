/*
 * Created on 11/04/2016
 *
 *Utilit�rios para trabalhar com a API Reflection do Java.
 */
package br.com.cdan.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import br.com.cdan.util.excecao.ExcecaoUtil;

/**
 * Classe utilit�ria para manipular classes e obejtos por reflex�o.
 *
 */
@SuppressWarnings("rawtypes")
public class UtilReflexao {
	/**
	 * Retorna o valor do atributo separado por seus v�rios n�veis. Por
	 * exemplo: aluno.titular.nome; titulo.conta.agencia;
	 * 
	 * @param objeto
	 *            Objeto do qual ser� extra�do o valor
	 * @param atributo
	 *            Atributo do objeto do qual ser� extra�do o valor
	 * @return Valor do atributo
	 */
	public static Object getValorDoAtributoComposto(Object objeto, String atributo) {
		Object valorDoAtributo = objeto;
		StringTokenizer token = new StringTokenizer(atributo, ".");
		while (token.hasMoreTokens()) {
			if (valorDoAtributo == null) {
				return null;
			}
			valorDoAtributo = getValorDoAtributo(valorDoAtributo, token.nextToken());
		}
		return valorDoAtributo;
	}

	private static final String getNomeBaseDoMetodo(String nomeDoAtributo) {
		String nomeBaseDoMetodo = nomeDoAtributo.substring(0, 1).toUpperCase()
				+ nomeDoAtributo.substring(1, nomeDoAtributo.length());
		return nomeBaseDoMetodo;
	}

	protected static final String getNomeDoMetodoGet(String nomeDoAtributo) {
		return "get" + getNomeBaseDoMetodo(nomeDoAtributo);
	}

	private static final String getNomeDoMetodoSet(String nomeDoAtributo) {
		return "set" + getNomeBaseDoMetodo(nomeDoAtributo);
	}

	/**
	 * Cria uma instancia de uma classe
	 * 
	 * @param nomeDaClasse
	 *            nome classe a ser instanciada
	 * @return instancia da classe
	 */
	public static final Object criaInstancia(String nomeDaClasse) {
		return criaInstancia(getClasse(nomeDaClasse));
	}

	/**
	 * Cria uma instancia de uma classe
	 * 
	 * @param <T>
	 *            tipo da classe
	 * @param classe
	 *            classe a ser instanciada
	 * @return instancia da classe
	 */
	public static final <T extends Object> T criaInstancia(Class<T> classe) {
		try {
			return classe.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cria uma instancia de uma classe (com o construtor recebendo par�metros
	 * 
	 * @param <T>
	 *            tipo da classe
	 * @param classe
	 *            classe a ser instanciada
	 * @param parametros
	 *            parametros do construtor da classe
	 * @return instancia da classe
	 */
	public static final <T extends Object> T criaInstancia(Class<T> classe, Object... parametros) {
		try {
			Class[] tipos = new Class[parametros.length];
			for (int i = 0; i < parametros.length; i++) {
				tipos[i] = parametros[i].getClass();
			}
			Constructor<T> construtor = classe.getConstructor(tipos);
			return construtor.newInstance(parametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final Object criaInstancia(String nomeDaClasse, Object... parametro) throws ClassNotFoundException {
		Class<?> classe = Class.forName(nomeDaClasse);
		return criaInstancia(classe, parametro);
	}

	/**
	 * Recupera a classe a partir do nome da classe
	 * 
	 * @param nomeDaClasse
	 *            nome da classe
	 * @return Class
	 */
	public static final Class<?> getClasse(String nomeDaClasse) {
		try {
			return Class.forName(nomeDaClasse);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Recupera as classes dos par�metros
	 * 
	 * @param parametros
	 *            parametros a ser recuperados os tipos dos parametros (class)
	 * @return tipos de classe dos parametros
	 */
	public static final Class[] getClassesDosParametros(Object[] parametros) {
		if (parametros == null) {
			return null;
		}
		Class[] classes = new Class[parametros.length];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = parametros[i].getClass();
		}
		return classes;
	}

	/**
	 * Execura um m�todo est�tico de uma classe
	 * 
	 * @param nomeDaClasse
	 *            classe a ser executado o m�todo est�tico
	 * @param nomeDoMetodo
	 *            nome do m�todo a ser executado
	 * @param parametros
	 *            parametros do m�todo
	 * @return retorno do m�todo executado
	 */
	@SuppressWarnings("unchecked")
	public static final Object executaMetodoEstatico(String nomeDaClasse, String nomeDoMetodo, Object[] parametros) {
		Class classe = getClasse(nomeDaClasse);
		try {
			Method metodo = classe.getMethod(nomeDoMetodo, getClassesDosParametros(parametros));
			return metodo.invoke(classe, parametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Execura um m�todo de uma classe
	 * 
	 * @param nomeDaClasse
	 *            classe a ser executado o m�todo
	 * @param nomeDoMetodo
	 *            nome do m�todo a ser executado
	 * @param parametros
	 *            parametros do m�todo
	 * @return retorno do m�todo executado
	 */
	public static final Object executaMetodo(String nomeDaClasse, String nomeDoMetodo, Object... parametros) {
		Object objeto = criaInstancia(nomeDaClasse);
		try {
			Method metodo = objeto.getClass().getMethod(nomeDoMetodo, getClassesDosParametros(parametros));
			return metodo.invoke(objeto, parametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Execura um m�todo est�tico de uma classe
	 * 
	 * @param objeto
	 *            objeto a ser executado o m�todo est�tico
	 * @param nomeDoMetodo
	 *            nome do m�todo a ser executado
	 * @param parametros
	 *            parametros do m�todo
	 * @return retorno do m�todo executado
	 */
	public static final Object executaMetodo(Object objeto, String nomeDoMetodo, Object... parametros) {
		Method metodo = getMetodo(objeto, nomeDoMetodo, parametros);
		try {
			return metodo.invoke(objeto, parametros);
		} catch (Exception e) {
			throw new ExcecaoUtil(e);
		}
	}

	private static final Method getMetodo(Object objeto, String nomeDoMetodo, Object[] parametros) {
		Class[] tiposDosParametros = getTiposDosParametros(parametros);
		try {
			return objeto.getClass().getMethod(nomeDoMetodo, tiposDosParametros);
		} catch (Exception e) {
			throw new ExcecaoUtil(e);
		}
	}

	private static Class[] getTiposDosParametros(Object[] parametros) {
		Class[] tiposDosParametros = new Class[parametros.length];
		for (int i = 0; i < parametros.length; i++) {
			tiposDosParametros[i] = parametros[i].getClass();
		}
		return tiposDosParametros;
	}

	/**
	 * Seta o valor de um atributo em um objeto
	 * 
	 * @param objetoDestino
	 *            objeto no qual ser� setado o valor
	 * @param nomeDoCampo
	 *            nome do campo que receber� o valor
	 * @param valor
	 *            valor a ser setado.
	 */
	public static final void setValorDoAtributo(Object objetoDestino, String nomeDoCampo, Object valor) {
		String nomeDoMetodo = getNomeDoMetodoSet(nomeDoCampo);
		Class<?> classe = objetoDestino.getClass();
		Method[] metodos = classe.getMethods();
		Method metodo = null;
		for (Method m : metodos) {
			if (m.getName().equals(nomeDoMetodo)) {
				Class<?>[] parametros = m.getParameterTypes();
				for (Class<?> p : parametros) {
					try {
						p.cast(valor);
						metodo = m;
						break;
					} catch (ClassCastException e) {
						// tenta pr�ximo m�todo set
					}
				}
			}
		}
		if (metodo == null) {
			throw new RuntimeException("M�todo " + nomeDoMetodo + " n�o encontrado na classe " + classe);
		}
		Object[] parametros = { valor };
		executaMetodo(objetoDestino, metodo, parametros);
	}

	/**
	 * Recupera o valor de um atributo
	 * 
	 * @param objeto
	 *            objeto a ser recuperado o valor do atributo
	 * @param nomeDoCampo
	 *            nome do atributo a ser recuperado
	 * @return valor do atributo
	 */
	public static final Object getValorDoAtributo(Object objeto, String nomeDoCampo) {
		Method metodo = getMetodoGet(objeto, nomeDoCampo);
		return executaMetodo(objeto, metodo, null);
	}

	/**
	 * Recupera o descritor do m�todo <code>get()</code> para um campo no
	 * objeto indicado.
	 * 
	 * @param objeto
	 *            objeto a ser recuperado o m�todo get.
	 * @param nomeDoCampo
	 *            nome do campo que ser� recuperado
	 * @return m�todo para ser executado por reflex�o.
	 */
	public static final Method getMetodoGet(Object objeto, String nomeDoCampo) {
		return criaMetodoGetDoAtributo(objeto, nomeDoCampo);
	}

	/**
	 * Recupera o descritor do m�todo <code>get()</code> para um campo no
	 * objeto indicado.
	 * 
	 * @param classe
	 *            classe a ser recuperada o m�todo get.
	 * @param nomeDoAtributo
	 *            nome do campo que ser� recuperado
	 * @return m�todo para ser executado por reflex�o.
	 */
	public static final Method getMetodoGet(Class<?> classe, String nomeDoAtributo) {
		try {
			return classe.getMethod("get" + nomeDoAtributo.substring(0, 1).toUpperCase() + nomeDoAtributo.substring(1));
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private static final Method criaMetodoGetDoAtributo(Object objeto, String nomeDoCampo) {
		return criaMetodo(objeto.getClass(), getNomeDoMetodoGet(nomeDoCampo), null);
	}

	private static final Object executaMetodo(Object objetoDestino, Method metodo, Object[] parametros) {
		try {
			return metodo.invoke(objetoDestino, parametros);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retorna o descritor do m�todo <code>set()</code> para um campo no
	 * objeto indicado.
	 * 
	 * @param objetoDestino
	 *            O objeto de onde extrair o m�todo
	 * @param nomeDoCampo
	 *            O nome do campo para o qual se deseja obter o m�todo
	 * @return O descritor do m�todo <code>set()</code> para o campo no
	 *         objeto.
	 */
	public static final Method getMetodoSet(Object objetoDestino, String nomeDoCampo) {
		Class classeDoParametro = descobreClasseDoParametro(objetoDestino, nomeDoCampo);
		Method metodo = criaMetodoSetDoAtributo(objetoDestino, nomeDoCampo, classeDoParametro);
		return metodo;
	}

	private static Class descobreClasseDoParametro(Object objetoDestino, String nomeDoCampo) {
		Class classeDoParametro = getMetodoGet(objetoDestino, nomeDoCampo).getReturnType();
		if (classeDoParametro == null) {
			classeDoParametro = objetoDestino.getClass();
		}
		return classeDoParametro;
	}

	private static final Method criaMetodoSetDoAtributo(Object objetoDestino, String nomeDoCampo,
			Class classeDoParametro) {
		Class[] classesDosParametros = { classeDoParametro };
		Method metodo = criaMetodo(objetoDestino.getClass(), getNomeDoMetodoSet(nomeDoCampo), classesDosParametros);
		return metodo;
	}

	private static final Method criaMetodo(Class<?> classeDoObjetoDestino, String nomeDoMetodo,
			Class[] classesDosParametros) {
		Method metodo;
		try {
			metodo = classeDoObjetoDestino.getMethod(nomeDoMetodo, classesDosParametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return metodo;
	}

	/**
	 * Verifica se existe o m�todo <code>get()</code> para um atributo
	 * 
	 * @param objeto
	 *            objeto que ser� verificado se existe o m�todo
	 *            <code>get()</code>.
	 * @param nomeDoAtributo
	 *            nome do atributo a ser verificado
	 * @return true, se existir; false se n�o existir
	 */
	public static boolean existeGet(Object objeto, String nomeDoAtributo) {
		if (nomeDoAtributo != null && nomeDoAtributo.length() > 0) {
			try {
				objeto.getClass().getMethod(getNomeDoMetodoGet(nomeDoAtributo));
				return true;
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Verifica se existe o m�todo <code>set()</code> para o campo indicado.
	 * 
	 * @param objeto
	 *            O objeto no qual ser� verificada a exist�ncia do m�todo
	 * @param nomeDoCampo
	 *            O nome do campo para o qual ser� verificada a exist�ncia
	 *            do m�todo
	 * @return <code>true</code> se existir um m�todo <code>set()</code>
	 *         v�lido para o campo indicado; caso contr�rio, retorna
	 *         <code>false</code> .
	 */
	public static boolean existeSet(Object objeto, String nomeDoCampo) {
		if (nomeDoCampo != null) {
			try {
				getMetodoSet(objeto, nomeDoCampo);
				return true;
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (RuntimeException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Copia todos os atributos do objeto de origem para o objeto de destino.
	 * 
	 * @param origem
	 * @param destino
	 * 
	 * @see #copiaAtributos(Object, Object, String...)
	 */
	public static <T> void copiaAtributos(T origem, T destino) {
		copiaAtributos(origem, destino, (String[]) null);
	}

	/**
	 * Copia os atributos do objeto de origem para o objeto de destino.
	 * 
	 * @param origem
	 *            o objeto que tem os atributos que ser�o copiados.
	 * @param destino
	 *            o objeto que ter� seus atributos definidos.
	 * @param ignorarAtributo
	 *            atributo(s) que n�o deve(m) ser copiado(s).
	 */
	public static <T> void copiaAtributos(T origem, T destino, String... ignorarAtributo) {
		Class<?> classeDaOrigem = UtilJPA.getClasseOriginal(origem);
		Class<?> classeDoDestino = UtilJPA.getClasseOriginal(destino);
		Method[] metodos = classeDaOrigem.getMethods();
		boolean ignorar = false;
		for (Method m : metodos) {
			Class<?> returnType = m.getReturnType();
			String nomeBase = null;
			if (m.getName().startsWith("get")) {
				nomeBase = m.getName().substring(3);
			} else if (m.getName().startsWith("is")) {
				nomeBase = m.getName().substring(2);
			} else {
				continue;
			}
			ignorar = false;
			if (ignorarAtributo != null && ignorarAtributo.length > 0) {
				for (String excluir : ignorarAtributo) {
					if (getNomeBaseDoMetodo(nomeBase).equals(getNomeBaseDoMetodo(excluir))) {
						ignorar = true;
						break;
					}
				}
			}
			if (!ignorar) {
				String nomeDoSet = "set" + nomeBase;
				try {
					Method set = classeDoDestino.getMethod(nomeDoSet, returnType);
					Object valor = m.invoke(origem);
					set.invoke(destino, valor);
				} catch (SecurityException e) {
					throw new RuntimeException(e);
				} catch (NoSuchMethodException e) {
					// Se n�o encontrou o m�todo set, ignora o erro e
					// continua
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}