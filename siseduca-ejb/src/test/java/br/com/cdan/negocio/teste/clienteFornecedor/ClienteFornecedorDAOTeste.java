package br.com.cdan.negocio.teste.clienteFornecedor;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.clientefornecedor.ClienteFornecedor;
import br.com.cdan.negocio.clientefornecedor.ClienteFornecedorDao;
import br.com.cdan.negocio.clientefornecedor.factory.ClienteFornecedorFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ClienteFornecedorDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ClienteFornecedorDAOTeste.class);
	ClienteFornecedorDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new ClienteFornecedorDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ClienteFornecedor a = criaClienteFornecedor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClienteFornecedor consulta = dao.find(ClienteFornecedor.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ClienteFornecedor a = criaClienteFornecedor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		dao.merge(a);
		ClienteFornecedor consulta = dao.find(ClienteFornecedor.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ClienteFornecedor a = criaClienteFornecedor();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClienteFornecedor consulta = dao.find(ClienteFornecedor.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ClienteFornecedor.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ClienteFornecedor a1 = criaClienteFornecedor();
		dao.persist(a1);
		ClienteFornecedor a2 = criaClienteFornecedor();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ClienteFornecedor a";
		Query query = dao.getEntityManager().createQuery(sql, ClienteFornecedor.class);
		//
		@SuppressWarnings("unchecked")
		List<ClienteFornecedor> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		ClienteFornecedor a = criaClienteFornecedor();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM ClienteFornecedor a WHERE a.nome = :nome";
		TypedQuery<ClienteFornecedor> query = dao.getEntityManager().createQuery(sql, ClienteFornecedor.class);
		query.setParameter("nome", a.getNome());
		ClienteFornecedor consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setNome(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void razaoSocial_menor_que_tamanho_minimo_permitido() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setRazaoSocial(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void razaoSocial_maior_que_tamanho_maximo_permitido() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setRazaoSocial(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_tipoDePessoa_nulo() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setTipoDePessoa(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ClienteFornecedor a = criaClienteFornecedor();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ClienteFornecedor criaClienteFornecedor() {
		return ClienteFornecedorFabricaTest.getInstance().criaClienteFornecedor(getEntityManager());
	}
}