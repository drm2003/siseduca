package br.com.cdan.negocio.teste.estoque;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.estoque.Item_Empresa;
import br.com.cdan.negocio.estoque.Item_EmpresaDao;
import br.com.cdan.negocio.estoque.factory.Item_EmpresaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class Item_EmpresaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(Item_EmpresaDAOTeste.class);
	Item_EmpresaDao dao;

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
		dao = new Item_EmpresaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Item_Empresa a = criaItem_Empresa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Item_Empresa consulta = dao.find(Item_Empresa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Item_Empresa a = criaItem_Empresa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Item_Empresa consulta = dao.find(Item_Empresa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Item_Empresa a = criaItem_Empresa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Item_Empresa consulta = dao.find(Item_Empresa.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Item_Empresa.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Item_Empresa a1 = criaItem_Empresa();
		dao.persist(a1);
		Item_Empresa a2 = criaItem_Empresa();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Item_Empresa a";
		Query query = dao.getEntityManager().createQuery(sql, Item_Empresa.class);
		//
		@SuppressWarnings("unchecked")
		List<Item_Empresa> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	/**
	 * 
	 */
	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_utiliza_nulo() {
		Item_Empresa a = criaItem_Empresa();
		a.setUtiliza(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		Item_Empresa a = criaItem_Empresa();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Item_Empresa criaItem_Empresa() {
		return Item_EmpresaFabricaTest.getInstance().criaItem_Empresa(getEntityManager());
	}
}