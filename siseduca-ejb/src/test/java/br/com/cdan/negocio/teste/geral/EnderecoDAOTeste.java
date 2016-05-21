package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.negocio.geral.EnderecoDao;
import br.com.cdan.negocio.geral.factory.EnderecoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EnderecoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EnderecoDAOTeste.class);
	EnderecoDao dao;

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
		dao = new EnderecoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Endereco a = criaEndereco();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Endereco consulta = dao.find(Endereco.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Endereco a = criaEndereco();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setLogradouro("logradouro" + Math.random() * 10000);
		a.setComplemento("complemento");
		dao.merge(a);
		Endereco consulta = dao.find(Endereco.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Endereco a = criaEndereco();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Endereco consulta = dao.find(Endereco.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Endereco.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Endereco a1 = criaEndereco();
		dao.persist(a1);
		Endereco a2 = criaEndereco();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Endereco a";
		Query query = dao.getEntityManager().createQuery(sql, Endereco.class);
		//
		@SuppressWarnings("unchecked")
		List<Endereco> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_logradouro_vazio() {
		Endereco a = criaEndereco();
		a.setLogradouro("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_logradouro_nulo() {
		Endereco a = criaEndereco();
		a.setLogradouro(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Endereco a = criaEndereco();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Endereco criaEndereco() {
		return EnderecoFabricaTest.getInstance().criaEndereco(getEntityManager());
	}
}