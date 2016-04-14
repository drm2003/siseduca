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
	 * <b>Objetivo:</b> Este mï¿½todo carregarï¿½ os dados das entidades
	 * passadas como parï¿½metro e que ainda nï¿½o foram carregadas por causa do
	 * LazyInitializer do JPA. ï¿½ possivel carregar o objeto em diversos
	 * nï¿½veis de relacionamento. O parï¿½metro 'mapeamentoRelacao' deve ser
	 * formatado na forma que cada objeto seja separado por pontos(.).
	 * 
	 * <p>
	 * <b>Exemplo:</b>
	 * 
	 * <p>
	 * Suponhamos que a classe Advogado tenha uma Pessoa e essa Pessoa tenha uma
	 * Profissï¿½o. Caso seja necessï¿½rio carregar o objeto Lazy de
	 * Profissï¿½o, a entrada e a saï¿½da serï¿½o as seguintes:
	 * 
	 * <p>
	 * <b> Entrada: </b> 'advogado.pessoa.profissao'
	 * <p>
	 * <b>Saï¿½da:</b> Lista de String: ['getAdvogado', 'getPessoa',
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
		// onde cada pojo está em uma posição.
		List<String> listaNomeMetodosMapeados = criaListaDeNomesDosMetodosMapeados(mapeamentoRelacao);
		//
		// Recupero o primeiro pojo da lista para poder recuperar o mï¿½todo que
		// será utilizado no carregamento do lazy.
		Object primeiroObjeto = getObjetoParaCarregarLazy(listaDePojos.get(0), listaNomeMetodosMapeados);
		//
		// Com o primeiro pojo em mï¿½os, busco o método que será utilizado
		// para carregar o lazy.
		// Note que irei recuperar o método correto uma única vez.
		Method metodoParaCarregarLazy = getMetodoParaCarregarLazy(getClasseOriginal(primeiroObjeto));
		//
		// Se nï¿½o encotrar nenhum mï¿½todo para carregar o lazy, finalizo o
		// mï¿½todo aqui.
		if (metodoParaCarregarLazy == null) {
			return;
		}
		//
		// Executa o mï¿½todo encontrado em cada pojo da lista.
		// Isso carregarï¿½ o lazy de cada pojo.
		for (Object pojo : listaDePojos) {
			try {
				metodoParaCarregarLazy.invoke(getObjetoParaCarregarLazy(pojo, listaNomeMetodosMapeados));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Sobrecarga do mï¿½todo {@link #carregaLazyAgora(String, List)}. Nesta
	 * versï¿½o ï¿½ possï¿½vel passar apenas uma entidade ou entidades separados
	 * por vï¿½rgulas.
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
	 * Sobrecarga do mï¿½todo {@link #carregaLazyAgora(String, List)}. Nesta
	 * versï¿½o nï¿½o ï¿½ necessï¿½rio especificar o 'mapeamentoRelacao' da
	 * relaï¿½ï¿½o.
	 * 
	 * @param listaDePojos
	 */
	public static void carregaLazyAgora(List<?> listaDePojos) {
		carregaLazyAgora(null, listaDePojos);
	}

	/**
	 * Sobrecarga do mï¿½todo {@link #carregaLazyAgora(String, List)}. Nesta
	 * versï¿½o ï¿½ possï¿½vel passar apenas uma entidade ou entidades separados
	 * por vï¿½rgulas. Tambï¿½m nï¿½o ï¿½ necessï¿½rio especificar o
	 * 'mapeamentoRelacao' da relaï¿½ï¿½o.
	 * 
	 * @param listaDePojos
	 */
	public static void carregaLazyAgora(Object... listaDePojos) {
		carregaLazyAgora(null, listaDePojos);
	}

	/**
	 * </br>
	 * Objetivo: Transforma o mapeamento da relaï¿½ï¿½o de objetos em nomes de
	 * mï¿½todos no formato "getter and setter".
	 * 
	 * O parï¿½metro 'mapeamentoRelacao' deve ser formatado em que cada objeto
	 * seja separado por pontos(.).
	 * 
	 * <p>
	 * <b>Exemplo:</b>
	 * 
	 * <p>
	 * Suponhamos que a classe Advogado tenha uma Pessoa e essa Pessoa tenha uma
	 * Profissï¿½o. Caso seja necessï¿½rio carregar o objeto Lazy de
	 * Profissï¿½o, a entrada e a saï¿½da serï¿½o as seguintes:
	 * 
	 * <p>
	 * <b> Entrada: </b> 'advogado.pessoa.profissao'
	 * <p>
	 * <b>Saï¿½da:</b> Lista de String: ['getAdvogado', 'getPessoa',
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
	 * Retorna o mï¿½todo da classe de acordo com a sequï¿½ncia de lista de
	 * chamada dos mï¿½todos.
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
	 * Retorna o objeto de acordo com a sequï¿½ncia de lista de chamada dos
	 * mï¿½todos.
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
	 * Este mï¿½todo verifica se o parï¿½metro passado ï¿½ um mï¿½todo Getter.
	 * Por convenï¿½ï¿½o, ï¿½ verificado se o nome do mï¿½todo ï¿½ iniciado com
	 * 'get' ou 'is', e se nï¿½o tem nenhum parï¿½metro e se nï¿½o retorna void.
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
	 * Se o objeto ainda nï¿½o estiver inicializado, a classe desse objeto
	 * serï¿½ um proxy. Nesse caso, utiliza-se este mï¿½todo para obter a classe
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