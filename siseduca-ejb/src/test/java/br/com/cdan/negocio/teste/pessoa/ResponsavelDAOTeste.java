package br.com.cdan.negocio.teste.pessoa;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.pedagogico.pessoa.ResponsavelDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.ResponsavelFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ResponsavelDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ResponsavelDAOTeste.class);
	ResponsavelDao dao;

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
		dao = new ResponsavelDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Responsavel a = criaResponsavel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Responsavel consulta = dao.find(Responsavel.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Responsavel a = criaResponsavel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setLoginPortal("loginPortal");
		a.setObservacoes("observacoes");
		dao.merge(a);
		Responsavel consulta = dao.find(Responsavel.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Responsavel a = criaResponsavel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Responsavel consulta = dao.find(Responsavel.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Responsavel.class, a.getId()));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultar_todos() {
		Responsavel a1 = criaResponsavel();
		dao.persist(a1);
		Responsavel a2 = criaResponsavel();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Responsavel a";
		Query query = dao.getEntityManager().createQuery(sql, Responsavel.class);
		//
		List<Responsavel> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Responsavel a = criaResponsavel();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Responsavel criaResponsavel() {
		return ResponsavelFabricaTest.getInstance().criaResponsavel(getEntityManager());
	}
}