package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Setor;
import br.com.cdan.negocio.biblioteca.SetorDao;
import br.com.cdan.negocio.biblioteca.factory.SetorFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SetorDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SetorDAOTeste.class);
	SetorDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new SetorDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Setor a = criaSetor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Setor consulta = dao.find(Setor.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Setor a = criaSetor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Setor consulta = dao.find(Setor.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Setor a = criaSetor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Setor consulta = dao.find(Setor.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Setor.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Setor a1 = criaSetor();
		dao.persist(a1);
		Setor a2 = criaSetor();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Setor a";
		Query query = dao.getEntityManager().createQuery(sql, Setor.class);
		//
		@SuppressWarnings("unchecked")
		List<Setor> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Setor a = criaSetor();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Setor a WHERE a.descricao = :descricao";
		TypedQuery<Setor> query = dao.getEntityManager().createQuery(sql, Setor.class);
		query.setParameter("descricao", a.getDescricao());
		Setor consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Setor a = criaSetor();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Setor a = criaSetor();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Setor a = criaSetor();
		a.setDescricao(criarStringDinamicaPorTamanho(356));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Setor a = criaSetor();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Setor a = criaSetor();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Setor criaSetor() {
		return SetorFabricaTest.getInstance().criaSetor();
	}
}