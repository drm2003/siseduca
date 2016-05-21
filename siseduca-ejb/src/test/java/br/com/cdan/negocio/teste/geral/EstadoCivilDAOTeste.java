package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.EstadoCivil;
import br.com.cdan.negocio.geral.EstadoCivilDao;
import br.com.cdan.negocio.geral.factory.EstadoCivilFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EstadoCivilDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EstadoCivilDAOTeste.class);
	EstadoCivilDao dao;

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
		dao = new EstadoCivilDao(getEntityManager());
	}

	@Test
	public void inserir() {
		EstadoCivil a = criaEstadoCivil();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		EstadoCivil consulta = dao.find(EstadoCivil.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		EstadoCivil a = criaEstadoCivil();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		EstadoCivil consulta = dao.find(EstadoCivil.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		EstadoCivil a = criaEstadoCivil();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		EstadoCivil consulta = dao.find(EstadoCivil.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(EstadoCivil.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		EstadoCivil a1 = criaEstadoCivil();
		dao.persist(a1);
		EstadoCivil a2 = criaEstadoCivil();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM EstadoCivil a";
		Query query = dao.getEntityManager().createQuery(sql, EstadoCivil.class);
		//
		@SuppressWarnings("unchecked")
		List<EstadoCivil> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		EstadoCivil a = criaEstadoCivil();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		EstadoCivil a = criaEstadoCivil();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		EstadoCivil a = criaEstadoCivil();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		EstadoCivil a = criaEstadoCivil();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		EstadoCivil a = criaEstadoCivil();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private EstadoCivil criaEstadoCivil() {
		return EstadoCivilFabricaTest.getInstance().criaEstadoCivil();
	}
}