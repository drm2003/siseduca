package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Cargo;
import br.com.cdan.negocio.geral.CargoDao;
import br.com.cdan.negocio.geral.factory.CargoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CargoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CargoDAOTeste.class);
	CargoDao dao;

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
		dao = new CargoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Cargo a = criaCargo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Cargo consulta = dao.find(Cargo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Cargo a = criaCargo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Cargo consulta = dao.find(Cargo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Cargo a = criaCargo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Cargo consulta = dao.find(Cargo.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Cargo.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Cargo a1 = criaCargo();
		dao.persist(a1);
		Cargo a2 = criaCargo();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Cargo a";
		Query query = dao.getEntityManager().createQuery(sql, Cargo.class);
		//
		@SuppressWarnings("unchecked")
		List<Cargo> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Cargo a = criaCargo();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Cargo a WHERE a.descricao = :descricao";
		TypedQuery<Cargo> query = dao.getEntityManager().createQuery(sql, Cargo.class);
		query.setParameter("descricao", a.getDescricao());
		Cargo consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Cargo a = criaCargo();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Cargo criaCargo() {
		return CargoFabricaTest.getInstance().criaCargo();
	}
}