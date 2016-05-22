package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Metodologia;
import br.com.cdan.negocio.pedagogico.curso.MetodologiaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.MetodologiaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MetodologiaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MetodologiaDAOTeste.class);
	MetodologiaDao dao;

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
		dao = new MetodologiaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Metodologia a = criaMetodologia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Metodologia consulta = dao.find(Metodologia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Metodologia a = criaMetodologia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Metodologia consulta = dao.find(Metodologia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Metodologia a = criaMetodologia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Metodologia consulta = dao.find(Metodologia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Metodologia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Metodologia a1 = criaMetodologia();
		dao.persist(a1);
		Metodologia a2 = criaMetodologia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Metodologia a";
		TypedQuery<Metodologia> query = dao.getEntityManager().createQuery(sql, Metodologia.class);
		//
		List<Metodologia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Metodologia a = criaMetodologia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Metodologia criaMetodologia() {
		return MetodologiaFabricaTest.getInstance().criaMetodologia(getEntityManager());
	}
}