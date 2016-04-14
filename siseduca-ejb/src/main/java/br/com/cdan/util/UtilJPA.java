package br.com.cdan.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

public class UtilJPA {
	/**
	 * <b>Objetivo:</b> Este m�todo carregar� os dados das entidades
	 * passadas como par�metro e que ainda n�o foram carregadas por causa do
	 * LazyInitializer do JPA. � possivel carregar o objeto em diversos
	 * n�veis de relacionamento. O par�metro 'mapeamentoRelacao' deve ser
	 * formatado na forma que cada objeto seja separado por pontos(.).
	 * 
	 * <p>
	 * <b>Exemplo:</b>
	 * 
	 * <p>
	 * Suponhamos que a classe Advogado tenha uma Pessoa e essa Pessoa tenha uma
	 * Profiss�o. Caso seja necess�rio carregar o objeto Lazy de
	 * Profiss�o, a entrada e a sa�da ser�o as seguintes:
	 * 
	 * <p>
	 * <b> Entrada: </b> 'advogado.pessoa.profissao'
	 * <p>
	 * <b>Sa�da:</b> Lista de String: ['getAdvogado', 'getPessoa',
	 * 'getProfissao'].
	 * 
	 * @param mapeamentoRelacao
	 * @param listaDePojos
	 */
	public static void carregaLazyAgora(String mapeamentoRelacao, List<?> listaDePojos) {
		if (isNullOrEmpty(listaDePojos)) {
			return;
		}
		// Transformo o mapeamentoRelacao(separado por pontos) em uma lista,
		// onde cada pojo est� em uma posi��o.
		List<String> listaNomeMetodosMapeados = criaListaDeNomesDosMetodosMapeados(mapeamentoRelacao);
		//
		// Recupero o primeiro pojo da lista para poder recuperar o m�todo que
		// ser� utilizado no carregamento do lazy.
		Object primeiroObjeto = getObjetoParaCarregarLazy(listaDePojos.get(0), listaNomeMetodosMapeados);
		//
		// Com o primeiro pojo em m�os, busco o m�todo que ser� utilizado
		// para carregar o lazy.
		// Note que irei recuperar o m�todo correto uma �nica vez.
		Method metodoParaCarregarLazy = getMetodoParaCarregarLazy(getClasseOriginal(primeiroObjeto));
		//
		// Se n�o encotrar nenhum m�todo para carregar o lazy, finalizo o
		// m�todo aqui.
		if (metodoParaCarregarLazy == null) {
			return;
		}
		//
		// Executa o m�todo encontrado em cada pojo da lista.
		// Isso carregar� o lazy de cada pojo.
		for (Object pojo : listaDePojos) {
			try {
				metodoParaCarregarLazy.invoke(getObjetoParaCarregarLazy(pojo, listaNomeMetodosMapeados));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Sobrecarga do m�todo {@link #carregaLazyAgora(String, List)}. Nesta
	 * vers�o � poss�vel passar apenas uma entidade ou entidades separados
	 * por v�rgulas.
	 * 
	 * @param mapeamentoRelacao
	 * @param pojo
	 */
	public static void carregaLazyAgora(String mapeamentoRelacao, Object... pojo) {
		if (pojo == null) {
			return;
		}
		carregaLazyAgora(mapeamentoRelacao, asList(pojo));
	}

	/**
	 * Sobrecarga do m�todo {@link #carregaLazyAgora(String, List)}. Nesta
	 * vers�o n�o � necess�rio especificar o 'mapeamentoRelacao' da
	 * rela��o.
	 * 
	 * @param listaDePojos
	 */
	public static void carregaLazyAgora(List<?> listaDePojos) {
		carregaLazyAgora(null, listaDePojos);
	}

	/**
	 * Sobrecarga do m�todo {@link #carregaLazyAgora(String, List)}. Nesta
	 * vers�o � poss�vel passar apenas uma entidade ou entidades separados
	 * por v�rgulas. Tamb�m n�o � necess�rio especificar o
	 * 'mapeamentoRelacao' da rela��o.
	 * 
	 * @param listaDePojos
	 */
	public static void carregaLazyAgora(Object... listaDePojos) {
		carregaLazyAgora(null, listaDePojos);
	}

	/**
	 * </br>
	 * Objetivo: Transforma o mapeamento da rela��o de objetos em nomes de
	 * m�todos no formato "getter and setter".
	 * 
	 * O par�metro 'mapeamentoRelacao' deve ser formatado em que cada objeto
	 * seja separado por pontos(.).
	 * 
	 * <p>
	 * <b>Exemplo:</b>
	 * 
	 * <p>
	 * Suponhamos que a classe Advogado tenha uma Pessoa e essa Pessoa tenha uma
	 * Profiss�o. Caso seja necess�rio carregar o objeto Lazy de
	 * Profiss�o, a entrada e a sa�da ser�o as seguintes:
	 * 
	 * <p>
	 * <b> Entrada: </b> 'advogado.pessoa.profissao'
	 * <p>
	 * <b>Sa�da:</b> Lista de String: ['getAdvogado', 'getPessoa',
	 * 'getProfissao'].
	 * 
	 * @param mapeamentoRelacao
	 * @return listaMetodosRelacao
	 */
	private static List<String> criaListaDeNomesDosMetodosMapeados(String mapeamentoRelacao) {
		if (isNullOrEmpty(mapeamentoRelacao)) {
			return null;
		}
		String[] arrayMapeamentoRelacao = mapeamentoRelacao.split("\\.");
		List<String> listaMetodosRelacao = new ArrayList<>(arrayMapeamentoRelacao.length - 1);
		for (int i = 1; i < arrayMapeamentoRelacao.length; i++) {
			listaMetodosRelacao.add(UtilReflexao.getNomeDoMetodoGet(arrayMapeamentoRelacao[i]));
		}
		return listaMetodosRelacao;
	}

	/**
	 * Retorna o m�todo da classe de acordo com a sequ�ncia de lista de
	 * chamada dos m�todos.
	 * 
	 * @param classe
	 * @return metodoEncontrado
	 */
	private static Method getMetodoParaCarregarLazy(Class<?> classe) {
		for (Method metodo : classe.getMethods()) {
			if (isMetodoGetter(metodo) && metodo.isAnnotationPresent(javax.persistence.Column.class)
					&& !metodo.isAnnotationPresent(javax.persistence.Id.class)) {
				return metodo;
			}
		}
		return null;
	}

	/**
	 * Retorna o objeto de acordo com a sequ�ncia de lista de chamada dos
	 * m�todos.
	 * 
	 * @param pojo
	 * @param nomeDosMetodos
	 * @return pojoEncontrado
	 */
	private static Object getObjetoParaCarregarLazy(Object pojo, List<String> nomeDosMetodos) {
		if (nomeDosMetodos == null || nomeDosMetodos.isEmpty()) {
			return pojo;
		}
		for (String nomeDoMetodo : nomeDosMetodos) {
			pojo = UtilReflexao.executaMetodo(pojo, nomeDoMetodo);
		}
		return pojo;
	}

	/**
	 * Este m�todo verifica se o par�metro passado � um m�todo Getter.
	 * Por conven��o, � verificado se o nome do m�todo � iniciado com
	 * 'get' ou 'is', e se n�o tem nenhum par�metro e se n�o retorna void.
	 */
	private static boolean isMetodoGetter(Method metodo) {
		return ((metodo.getName().startsWith("get") || metodo.getName().startsWith("is"))
				&& metodo.getParameterTypes().length == 0 && !metodo.getReturnType().equals(void.class));
	}

	private static boolean isNullOrEmpty(List<?> lista) {
		return lista == null || lista.isEmpty();
	}

	private static boolean isNullOrEmpty(String valor) {
		return valor == null || valor.isEmpty();
	}

	@SafeVarargs
	public static <T extends Object> List<T> asList(T... pojos) {
		if (pojos != null) {
			List<T> lista = new ArrayList<>(pojos.length);
			for (T objeto : pojos) {
				if (objeto != null) {
					lista.add(objeto);
				}
			}
			return lista;
		} else {
			return null;
		}
	}

	/**
	 * Se o objeto ainda n�o estiver inicializado, a classe desse objeto
	 * ser� um proxy. Nesse caso, utiliza-se este m�todo para obter a classe
	 * original.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClasseOriginal(T objeto) {
		if (objeto instanceof HibernateProxy) {
			return HibernateProxyHelper.getClassWithoutInitializingProxy(objeto);
		} else {
			return (Class<T>) objeto.getClass();
		}
	}

	public static Object getValorDoId(EntityManager entityManager, Object entidade) {
		return entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entidade);
	}

	public static <S> SingularAttribute<? super S, ?> getAtributoId(EntityManager entityManager,
			Class<S> classeDaEntidade) {
		EntityType<S> entityType = entityManager.getMetamodel().entity(classeDaEntidade);
		SingularAttribute<? super S, ?> atributoId = entityType.getId(entityType.getIdType().getJavaType());
		return atributoId;
	}

	public static <S> SingularAttribute<? super S, ?> getAtributoId(EntityManager entityManager, S entidade) {
		return getAtributoId(entityManager, UtilJPA.getClasseOriginal(entidade));
	}

	public static <X> Set<SingularAttribute<? super X, ?>> getSingularAttributes(EntityManager entityManager,
			Class<X> classeDaEntidade) {
		EntityType<X> entityType = entityManager.getMetamodel().entity(classeDaEntidade);
		return entityType.getSingularAttributes();
	}

	public static <X> Set<SingularAttribute<? super X, ?>> getSingularAttributes(EntityManager entityManager,
			X entidade) {
		return getSingularAttributes(entityManager, UtilJPA.getClasseOriginal(entidade));
	}
}