package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.OperadoraDeCelular;
import br.com.cdan.negocio.geral.OperadoraDeCelularDao;
import br.com.cdan.negocio.geral.factory.OperadoraDeCelularFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class OperadoraDeCelularDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(OperadoraDeCelularDAOTeste.class);
	OperadoraDeCelularDao dao;

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
		dao = new OperadoraDeCelularDao(getEntityManager());
	}

	@Test
	public void inserir() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		OperadoraDeCelular consulta = dao.find(OperadoraDeCelular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		OperadoraDeCelular consulta = dao.find(OperadoraDeCelular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		OperadoraDeCelular consulta = dao.find(OperadoraDeCelular.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(OperadoraDeCelular.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		OperadoraDeCelular a1 = criaOperadoraDeCelular();
		dao.persist(a1);
		OperadoraDeCelular a2 = criaOperadoraDeCelular();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM OperadoraDeCelular a";
		Query query = dao.getEntityManager().createQuery(sql, OperadoraDeCelular.class);
		//
		@SuppressWarnings("unchecked")
		List<OperadoraDeCelular> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		OperadoraDeCelular a = criaOperadoraDeCelular();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private OperadoraDeCelular criaOperadoraDeCelular() {
		return OperadoraDeCelularFabricaTest.getInstance().criaOperadoraDeCelular();
	}
}