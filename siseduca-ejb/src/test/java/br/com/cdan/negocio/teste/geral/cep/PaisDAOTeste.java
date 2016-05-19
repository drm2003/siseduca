package br.com.cdan.negocio.teste.geral.cep;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.cep.Pais;
import br.com.cdan.negocio.geral.PaisDao;
import br.com.cdan.negocio.geral.cep.factory.PaisFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class PaisDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(PaisDAOTeste.class);
	PaisDao dao;

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
		dao = new PaisDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Pais a = criaPais();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Pais consulta = dao.find(Pais.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Pais a = criaPais();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Pais consulta = dao.find(Pais.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Pais a = criaPais();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Pais consulta = dao.find(Pais.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Pais.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Pais a1 = criaPais();
		dao.persist(a1);
		Pais a2 = criaPais();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Pais a";
		Query query = dao.getEntityManager().createQuery(sql, Pais.class);
		//
		@SuppressWarnings("unchecked")
		List<Pais> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Pais a = criaPais();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Pais a WHERE a.descricao = :descricao";
		TypedQuery<Pais> query = dao.getEntityManager().createQuery(sql, Pais.class);
		query.setParameter("descricao", a.getDescricao());
		Pais consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Pais a = criaPais();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Pais criaPais() {
		return PaisFabricaTest.getInstance().criaPais();
	}
}