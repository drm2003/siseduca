package br.com.cdan.negocio.teste.pedagogico.diario;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.diario.ControleDeConteudo;
import br.com.cdan.negocio.pedagogico.diario.ControleDeConteudoDao;
import br.com.cdan.negocio.pedagogico.diario.factory.ControleDeConteudoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ControleDeFrequenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ControleDeFrequenciaDAOTeste.class);
	ControleDeConteudoDao dao;

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
		dao = new ControleDeConteudoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ControleDeConteudo a = criaControleDeConteudo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ControleDeConteudo consulta = dao.find(ControleDeConteudo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ControleDeConteudo a = criaControleDeConteudo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		ControleDeConteudo consulta = dao.find(ControleDeConteudo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ControleDeConteudo a = criaControleDeConteudo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ControleDeConteudo consulta = dao.find(ControleDeConteudo.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ControleDeConteudo.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ControleDeConteudo a1 = criaControleDeConteudo();
		dao.persist(a1);
		ControleDeConteudo a2 = criaControleDeConteudo();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ControleDeConteudo a";
		TypedQuery<ControleDeConteudo> query = dao.getEntityManager().createQuery(sql, ControleDeConteudo.class);
		//
		List<ControleDeConteudo> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_conteudo_nulo() {
		ControleDeConteudo a = criaControleDeConteudo();
		a.setConteudo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_conteudo_vazio() {
		ControleDeConteudo a = criaControleDeConteudo();
		a.setConteudo("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void conteudo_menor_que_tamanho_minimo_permitido() {
		ControleDeConteudo a = criaControleDeConteudo();
		a.setConteudo(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void conteudo_maior_que_tamanho_maximo_permitido() {
		ControleDeConteudo a = criaControleDeConteudo();
		a.setConteudo(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_conteudo() {
		ControleDeConteudo a = criaControleDeConteudo();
		dao.persist(a);
		//
		String sql = "SELECT a FROM ControleDeConteudo a WHERE a.conteudo = :conteudo";
		TypedQuery<ControleDeConteudo> query = dao.getEntityManager().createQuery(sql, ControleDeConteudo.class);
		query.setParameter("conteudo", a.getConteudo());
		//
		ControleDeConteudo m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		ControleDeConteudo a = criaControleDeConteudo();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private ControleDeConteudo criaControleDeConteudo() {
		return ControleDeConteudoFabricaTest.getInstance().criaControleDeConteudo();
	}
}