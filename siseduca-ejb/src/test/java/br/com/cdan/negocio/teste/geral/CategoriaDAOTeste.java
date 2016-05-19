package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Categoria;
import br.com.cdan.negocio.geral.CategoriaDao;
import br.com.cdan.negocio.geral.factory.CategoriaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CategoriaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CategoriaDAOTeste.class);
	CategoriaDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistência utilizando o JUnit é
	 * preciso ter acesso ao cont\exto de persistência fornecido pelo JPA.
	 * <c> Deste modo, antes da execução dos testes fornecemos este acesso é
	 * camada de persistência por meio de uma instância
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new CategoriaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Categoria a = criaCategoria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Categoria consulta = dao.find(Categoria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Categoria a = criaCategoria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Categoria consulta = dao.find(Categoria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Categoria a = criaCategoria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Categoria consulta = dao.find(Categoria.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Categoria.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Categoria a1 = criaCategoria();
		dao.persist(a1);
		Categoria a2 = criaCategoria();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Categoria a";
		Query query = dao.getEntityManager().createQuery(sql, Categoria.class);
		//
		@SuppressWarnings("unchecked")
		List<Categoria> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Categoria a = criaCategoria();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Categoria a WHERE a.descricao = :descricao";
		TypedQuery<Categoria> query = dao.getEntityManager().createQuery(sql, Categoria.class);
		query.setParameter("descricao", a.getDescricao());
		Categoria consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Categoria a = criaCategoria();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Categoria criaCategoria() {
		return CategoriaFabricaTest.getInstance().criaCategoria();
	}
}