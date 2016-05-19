package br.com.cdan.negocio.teste.geral.cep;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.negocio.geral.cep.CEPDao;
import br.com.cdan.negocio.geral.cep.factory.CEPFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CEPDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CEPDAOTeste.class);
	CEPDao dao;

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
		dao = new CEPDao(getEntityManager());
	}

	@Test
	public void inserir() {
		CEP a = criaCEP();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CEP consulta = dao.find(CEP.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		CEP a = criaCEP();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setCodigo("");
		dao.merge(a);
		CEP consulta = dao.find(CEP.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		CEP a = criaCEP();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CEP consulta = dao.find(CEP.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(CEP.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		CEP a1 = criaCEP();
		dao.persist(a1);
		CEP a2 = criaCEP();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM CEP a";
		Query query = dao.getEntityManager().createQuery(sql, CEP.class);
		//
		@SuppressWarnings("unchecked")
		List<CEP> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_codigo() {
		CEP a = criaCEP();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM CEP a WHERE a.codigo = :codigo";
		TypedQuery<CEP> query = dao.getEntityManager().createQuery(sql, CEP.class);
		query.setParameter("codigo", a.getCodigo());
		CEP consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		CEP a = criaCEP();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private CEP criaCEP() {
		return CEPFabricaTest.getInstance().criaCEP(getEntityManager());
	}
}