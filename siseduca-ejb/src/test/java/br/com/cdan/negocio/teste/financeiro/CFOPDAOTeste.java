package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.CFOP;
import br.com.cdan.negocio.financeiro.CFOPDao;
import br.com.cdan.negocio.financeiro.factory.CFOPFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CFOPDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CFOPDAOTeste.class);
	CFOPDao dao;

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
		dao = new CFOPDao(getEntityManager());
	}

	@Test
	public void inserir() {
		CFOP a = criaCFOP();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CFOP consulta = dao.find(CFOP.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		CFOP a = criaCFOP();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		CFOP consulta = dao.find(CFOP.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		CFOP a = criaCFOP();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CFOP consulta = dao.find(CFOP.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(CFOP.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		CFOP a1 = criaCFOP();
		dao.persist(a1);
		CFOP a2 = criaCFOP();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM CFOP a";
		Query query = dao.getEntityManager().createQuery(sql, CFOP.class);
		//
		@SuppressWarnings("unchecked")
		List<CFOP> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		CFOP a = criaCFOP();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM CFOP a WHERE a.descricao = :descricao";
		TypedQuery<CFOP> query = dao.getEntityManager().createQuery(sql, CFOP.class);
		query.setParameter("descricao", a.getDescricao());
		CFOP consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		CFOP a = criaCFOP();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		CFOP a = criaCFOP();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		CFOP a = criaCFOP();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		CFOP a = criaCFOP();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		CFOP a = criaCFOP();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private CFOP criaCFOP() {
		return CFOPFabricaTest.getInstance().criaCFOP(getEntityManager());
	}
}