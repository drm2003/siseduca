package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.negocio.pedagogico.SistemaDeAvaliacaoDao;
import br.com.cdan.negocio.pedagogico.factory.SistemaDeAvaliacaoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SistemaDeAvaliacaoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SistemaDeAvaliacaoDAOTeste.class);
	SistemaDeAvaliacaoDao dao;

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
		dao = new SistemaDeAvaliacaoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SistemaDeAvaliacao consulta = dao.find(SistemaDeAvaliacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		SistemaDeAvaliacao consulta = dao.find(SistemaDeAvaliacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SistemaDeAvaliacao consulta = dao.find(SistemaDeAvaliacao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SistemaDeAvaliacao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SistemaDeAvaliacao a1 = criaSistemaDeAvaliacao();
		dao.persist(a1);
		SistemaDeAvaliacao a2 = criaSistemaDeAvaliacao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SistemaDeAvaliacao a";
		TypedQuery<SistemaDeAvaliacao> query = dao.getEntityManager().createQuery(sql, SistemaDeAvaliacao.class);
		//
		List<SistemaDeAvaliacao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		a.setNome(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		a.setNome(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		dao.persist(a);
		//
		String sql = "SELECT a FROM SistemaDeAvaliacao a WHERE a.nome = :nome";
		TypedQuery<SistemaDeAvaliacao> query = dao.getEntityManager().createQuery(sql, SistemaDeAvaliacao.class);
		query.setParameter("nome", a.getNome());
		//
		SistemaDeAvaliacao m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SistemaDeAvaliacao a = criaSistemaDeAvaliacao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private SistemaDeAvaliacao criaSistemaDeAvaliacao() {
		return SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacao(getEntityManager());
	}
}