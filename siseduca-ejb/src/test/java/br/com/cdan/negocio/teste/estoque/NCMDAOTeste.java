package br.com.cdan.negocio.teste.estoque;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.estoque.NCM;
import br.com.cdan.negocio.estoque.NCMDao;
import br.com.cdan.negocio.estoque.factory.NCMFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class NCMDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(NCMDAOTeste.class);
	NCMDao dao;

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
		dao = new NCMDao(getEntityManager());
	}

	@Test
	public void inserir() {
		NCM a = criaNCM();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		NCM consulta = dao.find(NCM.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		NCM a = criaNCM();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		NCM consulta = dao.find(NCM.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		NCM a = criaNCM();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		NCM consulta = dao.find(NCM.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(NCM.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		NCM a1 = criaNCM();
		dao.persist(a1);
		NCM a2 = criaNCM();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM NCM a";
		Query query = dao.getEntityManager().createQuery(sql, NCM.class);
		//
		@SuppressWarnings("unchecked")
		List<NCM> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		NCM a = criaNCM();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		NCM a = criaNCM();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		NCM a = criaNCM();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		NCM a = criaNCM();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private NCM criaNCM() {
		return NCMFabricaTest.getInstance().criaNCM();
	}
}