package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.OperadoraCartao;
import br.com.cdan.negocio.financeiro.OperadoraCartaoDao;
import br.com.cdan.negocio.financeiro.factory.OperadoraCartaoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class OperadoraCartaoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(OperadoraCartaoDAOTeste.class);
	OperadoraCartaoDao dao;

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
		dao = new OperadoraCartaoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		OperadoraCartao a = criaOperadoraCartao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		OperadoraCartao consulta = dao.find(OperadoraCartao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		OperadoraCartao a = criaOperadoraCartao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		OperadoraCartao consulta = dao.find(OperadoraCartao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		OperadoraCartao a = criaOperadoraCartao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		OperadoraCartao consulta = dao.find(OperadoraCartao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(OperadoraCartao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		OperadoraCartao a1 = criaOperadoraCartao();
		dao.persist(a1);
		OperadoraCartao a2 = criaOperadoraCartao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM OperadoraCartao a";
		Query query = dao.getEntityManager().createQuery(sql, OperadoraCartao.class);
		//
		@SuppressWarnings("unchecked")
		List<OperadoraCartao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		OperadoraCartao a = criaOperadoraCartao();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM OperadoraCartao a WHERE a.nome = :nome";
		TypedQuery<OperadoraCartao> query = dao.getEntityManager().createQuery(sql, OperadoraCartao.class);
		query.setParameter("nome", a.getNome());
		OperadoraCartao consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		OperadoraCartao a = criaOperadoraCartao();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		OperadoraCartao a = criaOperadoraCartao();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		OperadoraCartao a = criaOperadoraCartao();
		a.setNome(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		OperadoraCartao a = criaOperadoraCartao();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		OperadoraCartao a = criaOperadoraCartao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private OperadoraCartao criaOperadoraCartao() {
		return OperadoraCartaoFabricaTest.getInstance().criaOperadoraCartao();
	}
}