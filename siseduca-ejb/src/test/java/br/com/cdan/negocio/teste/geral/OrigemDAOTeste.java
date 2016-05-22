package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Origem;
import br.com.cdan.negocio.biblioteca.OrigemDao;
import br.com.cdan.negocio.geral.factory.OrigemFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class OrigemDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(OrigemDAOTeste.class);
	OrigemDao dao;

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
		dao = new OrigemDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Origem a = criaOrigem();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Origem consulta = dao.find(Origem.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Origem a = criaOrigem();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		Origem consulta = dao.find(Origem.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Origem a = criaOrigem();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Origem consulta = dao.find(Origem.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Origem.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Origem a1 = criaOrigem();
		dao.persist(a1);
		Origem a2 = criaOrigem();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Origem a";
		Query query = dao.getEntityManager().createQuery(sql, Origem.class);
		//
		@SuppressWarnings("unchecked")
		List<Origem> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Origem a = criaOrigem();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Origem a = criaOrigem();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Origem a = criaOrigem();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Origem a = criaOrigem();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Origem a = criaOrigem();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Origem a = criaOrigem();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Origem criaOrigem() {
		return OrigemFabricaTest.getInstance().criaOrigem();
	}
}