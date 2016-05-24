package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.ExameFinal;
import br.com.cdan.negocio.pedagogico.ExameFinalDao;
import br.com.cdan.negocio.pedagogico.factory.ExameFinalFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ExameFinalDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ExameFinalDAOTeste.class);
	ExameFinalDao dao;

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
		dao = new ExameFinalDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ExameFinal a = criaExameFinal();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ExameFinal consulta = dao.find(ExameFinal.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ExameFinal a = criaExameFinal();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		ExameFinal consulta = dao.find(ExameFinal.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ExameFinal a = criaExameFinal();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ExameFinal consulta = dao.find(ExameFinal.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ExameFinal.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ExameFinal a1 = criaExameFinal();
		dao.persist(a1);
		ExameFinal a2 = criaExameFinal();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ExameFinal a";
		TypedQuery<ExameFinal> query = dao.getEntityManager().createQuery(sql, ExameFinal.class);
		//
		List<ExameFinal> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ExameFinal a = criaExameFinal();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private ExameFinal criaExameFinal() {
		return ExameFinalFabricaTest.getInstance().criaExameFinal(getEntityManager());
	}
}