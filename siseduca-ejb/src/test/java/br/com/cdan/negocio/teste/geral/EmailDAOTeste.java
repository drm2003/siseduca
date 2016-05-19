package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Email;
import br.com.cdan.negocio.geral.EmailDao;
import br.com.cdan.negocio.geral.factory.EmailFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EmailDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EmailDAOTeste.class);
	EmailDao dao;

	/**
	 * <c> Ao criar um teste da camada de persist�ncia utilizando o JUnit �
	 * preciso ter acesso ao cont\exto de persist�ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execu��o dos testes fornecemos este acesso �
	 * camada de persist�ncia por meio de uma inst�ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new EmailDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Email a = criaEmail();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Email consulta = dao.find(Email.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		Email a = criaEmail();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Email consulta = dao.find(Email.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		Email a = criaEmail();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Email consulta = dao.find(Email.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Email.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Email a1 = criaEmail();
		dao.persist(a1);
		Email a2 = criaEmail();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Email a";
		Query query = dao.getEntityManager().createQuery(sql, Email.class);
		//
		@SuppressWarnings("unchecked")
		List<Email> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Email a = criaEmail();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Email a WHERE a.descricao = :descricao";
		TypedQuery<Email> query = dao.getEntityManager().createQuery(sql, Email.class);
		query.setParameter("descricao", a.getDescricao());
		Email consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Email a = criaEmail();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Email criaEmail() {
		return EmailFabricaTest.getInstance().criaEmail(getEntityManager());
	}
}