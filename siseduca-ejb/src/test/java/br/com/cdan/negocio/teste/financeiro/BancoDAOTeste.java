package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.negocio.financeiro.BancoDao;
import br.com.cdan.negocio.financeiro.factory.BancoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class BancoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(BancoDAOTeste.class);
	BancoDao dao;

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
		dao = new BancoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Banco a = criaBanco();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Banco consulta = dao.find(Banco.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Banco a = criaBanco();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		dao.merge(a);
		Banco consulta = dao.find(Banco.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Banco a = criaBanco();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Banco consulta = dao.find(Banco.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Banco.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Banco a1 = criaBanco();
		dao.persist(a1);
		Banco a2 = criaBanco();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Banco a";
		Query query = dao.getEntityManager().createQuery(sql, Banco.class);
		//
		@SuppressWarnings("unchecked")
		List<Banco> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		Banco a = criaBanco();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Banco a WHERE a.nome = :nome";
		TypedQuery<Banco> query = dao.getEntityManager().createQuery(sql, Banco.class);
		query.setParameter("nome", a.getNome());
		Banco consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Banco a = criaBanco();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Banco a = criaBanco();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Banco a = criaBanco();
		a.setNome(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Banco a = criaBanco();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Banco a = criaBanco();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Banco criaBanco() {
		return BancoFabricaTest.getInstance().criaBanco();
	}
}