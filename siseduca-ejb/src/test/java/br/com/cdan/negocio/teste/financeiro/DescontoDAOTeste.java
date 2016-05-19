package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.Desconto;
import br.com.cdan.negocio.financeiro.DescontoDao;
import br.com.cdan.negocio.financeiro.factory.DescontoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DescontoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DescontoDAOTeste.class);
	DescontoDao dao;

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
		dao = new DescontoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Desconto a = criaDesconto();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Desconto consulta = dao.find(Desconto.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Desconto a = criaDesconto();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDiasAntesDoVencimento(Long.parseLong("10"));
		dao.merge(a);
		Desconto consulta = dao.find(Desconto.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Desconto a = criaDesconto();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Desconto consulta = dao.find(Desconto.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Desconto.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Desconto a1 = criaDesconto();
		dao.persist(a1);
		Desconto a2 = criaDesconto();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Desconto a";
		Query query = dao.getEntityManager().createQuery(sql, Desconto.class);
		//
		@SuppressWarnings("unchecked")
		List<Desconto> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Desconto a = criaDesconto();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Desconto criaDesconto() {
		return DescontoFabricaTest.getInstance().criaDesconto(getEntityManager());
	}
}