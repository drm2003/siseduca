package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.TipoDeCelular;
import br.com.cdan.negocio.geral.TipoDeCelularDao;
import br.com.cdan.negocio.geral.factory.TipoDeCelularFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeCelularDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeCelularDAOTeste.class);
	TipoDeCelularDao dao;

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
		dao = new TipoDeCelularDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeCelular a = criaTipoDeCelular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeCelular consulta = dao.find(TipoDeCelular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeCelular a = criaTipoDeCelular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		TipoDeCelular consulta = dao.find(TipoDeCelular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeCelular a = criaTipoDeCelular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeCelular consulta = dao.find(TipoDeCelular.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeCelular.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeCelular a1 = criaTipoDeCelular();
		dao.persist(a1);
		TipoDeCelular a2 = criaTipoDeCelular();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeCelular a";
		TypedQuery<TipoDeCelular> query = dao.getEntityManager().createQuery(sql, TipoDeCelular.class);
		//
		List<TipoDeCelular> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeCelular a = criaTipoDeCelular();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeCelular a = criaTipoDeCelular();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeCelular a = criaTipoDeCelular();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeCelular a = criaTipoDeCelular();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeCelular a = criaTipoDeCelular();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeCelular criaTipoDeCelular() {
		return TipoDeCelularFabricaTest.getInstance().criaTipoDeCelular();
	}
}