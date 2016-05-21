package br.com.cdan.negocio.teste.pessoa;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.negocio.pedagogico.pessoa.InteressadoDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.InteressadoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class InteressadoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(InteressadoDAOTeste.class);
	InteressadoDao dao;

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
		dao = new InteressadoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Interessado a = criaInteressado();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Interessado consulta = dao.find(Interessado.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Interessado a = criaInteressado();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setOutraEscolaDoAluno("outraEscolaDoAluno");
		dao.merge(a);
		Interessado consulta = dao.find(Interessado.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Interessado a = criaInteressado();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Interessado consulta = dao.find(Interessado.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Interessado.class, a.getId()));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultar_todos() {
		Interessado a1 = criaInteressado();
		dao.persist(a1);
		Interessado a2 = criaInteressado();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Interessado a";
		Query query = dao.getEntityManager().createQuery(sql, Interessado.class);
		//
		List<Interessado> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Interessado a = criaInteressado();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Interessado criaInteressado() {
		return InteressadoFabricaTest.getInstance().criaInteressado(getEntityManager());
	}
}