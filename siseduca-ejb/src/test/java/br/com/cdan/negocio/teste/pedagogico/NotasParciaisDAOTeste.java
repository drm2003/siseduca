package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.NotasParciais;
import br.com.cdan.negocio.pedagogico.NotasParciaisDao;
import br.com.cdan.negocio.pedagogico.factory.NotasParciaisFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class NotasParciaisDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(NotasParciaisDAOTeste.class);
	NotasParciaisDao dao;

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
		dao = new NotasParciaisDao(getEntityManager());
	}

	@Test
	public void inserir() {
		NotasParciais a = criaNotasParciais();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		NotasParciais consulta = dao.find(NotasParciais.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		NotasParciais a = criaNotasParciais();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		NotasParciais consulta = dao.find(NotasParciais.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		NotasParciais a = criaNotasParciais();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		NotasParciais consulta = dao.find(NotasParciais.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(NotasParciais.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		NotasParciais a1 = criaNotasParciais();
		dao.persist(a1);
		NotasParciais a2 = criaNotasParciais();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM NotasParciais a";
		TypedQuery<NotasParciais> query = dao.getEntityManager().createQuery(sql, NotasParciais.class);
		//
		List<NotasParciais> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		NotasParciais a = criaNotasParciais();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private NotasParciais criaNotasParciais() {
		return NotasParciaisFabricaTest.getInstance().criaNotasParciais(getEntityManager());
	}
}