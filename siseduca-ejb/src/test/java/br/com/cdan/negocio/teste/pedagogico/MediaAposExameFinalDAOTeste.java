package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.MediaAposExameFinal;
import br.com.cdan.negocio.pedagogico.MediaAposExameFinalDao;
import br.com.cdan.negocio.pedagogico.factory.MediaAposExameFinalFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MediaAposExameFinalDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MediaAposExameFinalDAOTeste.class);
	MediaAposExameFinalDao dao;

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
		dao = new MediaAposExameFinalDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MediaAposExameFinal a = criaMediaAposExameFinal();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MediaAposExameFinal consulta = dao.find(MediaAposExameFinal.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MediaAposExameFinal a = criaMediaAposExameFinal();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MediaAposExameFinal consulta = dao.find(MediaAposExameFinal.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MediaAposExameFinal a = criaMediaAposExameFinal();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MediaAposExameFinal consulta = dao.find(MediaAposExameFinal.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MediaAposExameFinal.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MediaAposExameFinal a1 = criaMediaAposExameFinal();
		dao.persist(a1);
		MediaAposExameFinal a2 = criaMediaAposExameFinal();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MediaAposExameFinal a";
		TypedQuery<MediaAposExameFinal> query = dao.getEntityManager().createQuery(sql, MediaAposExameFinal.class);
		//
		List<MediaAposExameFinal> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MediaAposExameFinal a = criaMediaAposExameFinal();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private MediaAposExameFinal criaMediaAposExameFinal() {
		return MediaAposExameFinalFabricaTest.getInstance().criaMediaAposExameFinal(getEntityManager());
	}
}