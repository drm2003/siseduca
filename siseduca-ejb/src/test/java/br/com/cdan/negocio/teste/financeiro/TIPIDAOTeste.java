package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.TIPI;
import br.com.cdan.negocio.financeiro.TIPIDao;
import br.com.cdan.negocio.financeiro.factory.TIPIFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TIPIDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TIPIDAOTeste.class);
	TIPIDao dao;

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
		dao = new TIPIDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TIPI a = criaTIPI();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TIPI consulta = dao.find(TIPI.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TIPI a = criaTIPI();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		TIPI consulta = dao.find(TIPI.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TIPI a = criaTIPI();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TIPI consulta = dao.find(TIPI.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TIPI.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TIPI a1 = criaTIPI();
		dao.persist(a1);
		TIPI a2 = criaTIPI();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TIPI a";
		Query query = dao.getEntityManager().createQuery(sql, TIPI.class);
		//
		@SuppressWarnings("unchecked")
		List<TIPI> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TIPI a = criaTIPI();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TIPI a WHERE a.descricao = :descricao";
		TypedQuery<TIPI> query = dao.getEntityManager().createQuery(sql, TIPI.class);
		query.setParameter("descricao", a.getDescricao());
		TIPI consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TIPI a = criaTIPI();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TIPI a = criaTIPI();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TIPI a = criaTIPI();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TIPI a = criaTIPI();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_codigoReceita_nulo() {
		TIPI a = criaTIPI();
		a.setCodigoReceita(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TIPI a = criaTIPI();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TIPI criaTIPI() {
		return TIPIFabricaTest.getInstance().criaTIPI(getEntityManager());
	}
}