package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.CST;
import br.com.cdan.negocio.financeiro.CSTDao;
import br.com.cdan.negocio.financeiro.factory.CSTFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CSTDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CSTDAOTeste.class);
	CSTDao dao;

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
		dao = new CSTDao(getEntityManager());
	}

	@Test
	public void inserir() {
		CST a = criaCST();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CST consulta = dao.find(CST.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		CST a = criaCST();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		CST consulta = dao.find(CST.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		CST a = criaCST();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CST consulta = dao.find(CST.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(CST.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		CST a1 = criaCST();
		dao.persist(a1);
		CST a2 = criaCST();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM CST a";
		Query query = dao.getEntityManager().createQuery(sql, CST.class);
		//
		@SuppressWarnings("unchecked")
		List<CST> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		CST a = criaCST();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM CST a WHERE a.descricao = :descricao";
		TypedQuery<CST> query = dao.getEntityManager().createQuery(sql, CST.class);
		query.setParameter("descricao", a.getDescricao());
		CST consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		CST a = criaCST();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		CST a = criaCST();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		CST a = criaCST();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		CST a = criaCST();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_codigoDaReceita_nulo() {
		CST a = criaCST();
		a.setCodigoReceita(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		CST a = criaCST();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private CST criaCST() {
		return CSTFabricaTest.getInstance().criaCST(getEntityManager());
	}
}