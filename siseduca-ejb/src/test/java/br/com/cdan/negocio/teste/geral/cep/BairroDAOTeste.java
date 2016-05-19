package br.com.cdan.negocio.teste.geral.cep;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.cep.Bairro;
import br.com.cdan.negocio.geral.BairroDao;
import br.com.cdan.negocio.geral.cep.factory.BairroFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class BairroDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(BairroDAOTeste.class);
	BairroDao dao;

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
		dao = new BairroDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Bairro a = criaBairro();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Bairro consulta = dao.find(Bairro.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Bairro a = criaBairro();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Bairro consulta = dao.find(Bairro.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Bairro a = criaBairro();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Bairro consulta = dao.find(Bairro.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Bairro.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Bairro a1 = criaBairro();
		dao.persist(a1);
		Bairro a2 = criaBairro();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Bairro a";
		Query query = dao.getEntityManager().createQuery(sql, Bairro.class);
		//
		@SuppressWarnings("unchecked")
		List<Bairro> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Bairro a = criaBairro();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Bairro a WHERE a.descricao = :descricao";
		TypedQuery<Bairro> query = dao.getEntityManager().createQuery(sql, Bairro.class);
		query.setParameter("descricao", a.getDescricao());
		Bairro consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Bairro a = criaBairro();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Bairro criaBairro() {
		return BairroFabricaTest.getInstance().criaBairro(getEntityManager());
	}
}