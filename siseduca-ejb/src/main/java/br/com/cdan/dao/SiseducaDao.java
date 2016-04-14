package br.com.cdan.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import br.com.cdan.util.PaginacaoSISEDUCA;
import br.com.cdan.util.UtilJPA;

public class SiseducaDao implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 */
	@PersistenceContext(name = "SisEducaPU")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * 
	 * @param clazz
	 *            Tipo de objetos que deverão ser retornados na lista
	 * @param order
	 *            Ordem da lista. Deverá ser passado apenas o nome do campo
	 *            para o ordenamento ascendente ou o nome do campo seguido da
	 *            palavra "desc" para ordenamento descendente. Ex:
	 *            findAll(Usuario.class, "nome", "idade desc")
	 * @return Lista com todos os objetos do tipo informado
	 */
	public <T extends Object> List<T> findAll(Class<T> clazz, String... order) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root = query.from(clazz);
		query.select(root);
		if (order != null && order.length > 0) {
			ArrayList<Order> campos = new ArrayList<Order>();
			for (String o : order) {
				String nomeDoCampo = o.substring(0, o.indexOf(" "));
				String tipoDeOrdenacao = o.substring(o.indexOf(" ") + 1).toLowerCase().trim();
				if ("desc".equals(tipoDeOrdenacao)) {
					campos.add(cb.desc(root.get(o)));
				} else {
					if (!"".equals(tipoDeOrdenacao) && !"asc".equals(tipoDeOrdenacao)) {
						throw new RuntimeException("O tipo de ordenação " + tipoDeOrdenacao + " + não é permitido");
					}
					campos.add(cb.asc(root.get(nomeDoCampo)));
				}
			}
			query.orderBy(campos);
		}
		return getEntityManager().createQuery(query).getResultList();
	}

	public void persist(Object objet) {
		getEntityManager().persist(objet);
	}

	public void merge(Object object) {
		getEntityManager().merge(object);
	}

	public <T extends Object> T find(Class<T> clazz, Object id) {
		return getEntityManager().find(clazz, id);
	}

	public <T> long count(Class<T> clazz) {
		TypedQuery<Long> query = getEntityManager().createQuery("select count(o.id) from " + clazz.getName() + " o",
				Long.class);
		long result = query.getSingleResult();
		return result;
	}

	public void remove(Object obj) {
		if (obj != null) {
			if (getEntityManager().contains(obj)) {
				getEntityManager().remove(obj);
			} else {
				Object valorDoId = UtilJPA.getValorDoId(getEntityManager(), obj);
				Object persist = find(obj.getClass(), valorDoId);
				getEntityManager().remove(persist);
			}
		}
	}

	public <T extends Object> PaginacaoSISEDUCA<T> filter(Class<T> clazz, int first, int pageSize, String sortField,
			String sortType, Map<Object, Object> filters) {
		return filter(clazz, first, pageSize, sortField, sortType, filters, null);
	}

	public <T extends Object> PaginacaoSISEDUCA<T> filter(Class<T> clazz, int first, int pageSize, String sortField,
			String sortType, Map<Object, Object> filters, List<String> fetch) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		// total de objetos que atendem aos filtros informados
		CriteriaQuery<Long> count = cb.createQuery(Long.class);
		Root<T> rootCount = count.from(clazz);
		Expression<Long> expr = cb.count(rootCount);
		count.select(expr);
		count.where(buildPredicates(filters, cb, rootCount));
		Long total = getEntityManager().createQuery(count).getSingleResult();
		// objetos que atendem aos filtros
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root = query.from(clazz);
		// fetch
		if (fetch != null) {
			for (String f : fetch) {
				root.fetch(f);
			}
		}
		// ordena��o
		if (sortField != null) {
			Path<Object> path = getPath(sortField, root, Object.class);
			if (sortType != null && sortType.toLowerCase().equals("desc")) {
				query.orderBy(cb.desc(path));
			} else {
				query.orderBy(cb.asc(path));
			}
		}
		// executa a query para obter os objetos
		query.select(root).where(buildPredicates(filters, cb, root));
		TypedQuery<T> tp = getEntityManager().createQuery(query);
		tp.setFirstResult(first);
		if (first + pageSize > total) {
			pageSize = (int) (total - first);
		}
		tp.setMaxResults(pageSize);
		PaginacaoSISEDUCA<T> paginacao = new PaginacaoSISEDUCA<T>();
		List<T> result = tp.getResultList();
		paginacao.setList(result);
		paginacao.setTotal(total);
		return paginacao;
	}

	private Predicate[] buildPredicates(Map<Object, Object> filters, CriteriaBuilder cb, Root<?> root) {
		ArrayList<Predicate> arr = new ArrayList<Predicate>();
		if (filters != null && filters.size() > 0) {
			Iterator<Entry<Object, Object>> iter = filters.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<Object, Object> e = iter.next();
				Object field = e.getKey();
				Object param = e.getValue();
				if (param == null) {
					// valor nulo
					Path<Object> key = getPath(field, root, Object.class);
					arr.add(cb.and(cb.isNull(key)));
				} else if (param instanceof String) {
					// se o valor for numérico, procura utilizando equals, caso
					// contrário faz um like
					// utilizando o início do texto do banco. Retira todos os
					// caracteres numéricos, se
					// não sobrar nenhum é porque o valor é numérico.
					String value = param.toString();
					if (isNumeric(value)) {
						Path<?> key = getPath(field, root, param.getClass());
						arr.add(cb.and(cb.equal(key, param)));
					} else {
						Path<String> key = getPath(field, root, String.class);
						arr.add(cb.and(cb.like(cb.lower(key), value.toLowerCase() + "%")));
					}
				} else if (param instanceof List) {
					List<?> lista = (List<?>) param;
					if (lista == null || lista.size() == 0) {
						throw new RuntimeException(
								"A lista informada como par�metro n�o pode estar vazia ou ser igual a null");
					}
					Class<?> classeDaLista = recuperaClasseDaLista(lista);
					Path<?> key = getPath(field, root, classeDaLista);
					arr.add(cb.and(cb.in(key)/* .value(lista) */));
				} else {
					Path<?> key = getPath(field, root, param.getClass());
					arr.add(cb.and(cb.equal(key, param)));
				}
			}
		}
		Predicate[] predicates = arr.toArray(new Predicate[arr.size()]);
		return predicates;
	}

	private Class<?> recuperaClasseDaLista(List<?> lista) {
		if (lista.size() == 0 || lista.get(0) == null) {
			return Object.class;
		}
		return lista.get(0).getClass();
	}

	private boolean isNumeric(String value) {
		return value.trim().replaceAll("\\d", "").length() == 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T extends Object> Path<T> getPath(Object field, Root<?> root, Class<T> clazz) {
		if (field instanceof SingularAttribute) {
			return root.get((SingularAttribute) field);
		}
		StringTokenizer token = new StringTokenizer(field.toString(), ".");
		Path<T> path = null;
		while (token.hasMoreTokens()) {
			String f = token.nextToken();
			if (path == null) {
				path = root.get(f);
			} else {
				path = path.get(f);
			}
		}
		return path;
	}

	public <T extends Object> List<T> findBy(Class<T> clazz, SingularAttribute<T, ?> field, Object value) {
		return getEntityManager()
				.createQuery("select o from " + clazz.getName() + " o where o." + field.getName() + " = :param", clazz)
				.setParameter("param", value).getResultList();
	}
}