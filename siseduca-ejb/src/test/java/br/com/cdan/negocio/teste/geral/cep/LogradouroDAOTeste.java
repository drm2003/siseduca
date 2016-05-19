package br.com.cdan.negocio.teste.geral.cep;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.cep.Logradouro;
import br.com.cdan.negocio.geral.cep.LogradouroDao;
import br.com.cdan.negocio.geral.cep.factory.LogradouroFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class LogradouroDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(LogradouroDAOTeste.class);
	LogradouroDao dao;

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
		dao = new LogradouroDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Logradouro a = criaLogradouro();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Logradouro consulta = dao.find(Logradouro.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Logradouro a = criaLogradouro();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		a.setBairroFinalLogradouro("bairroFinalLogradouro");
		a.setBairroInicioLogradouro("bairroInicioLogradouro");
		dao.merge(a);
		Logradouro consulta = dao.find(Logradouro.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Logradouro a = criaLogradouro();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Logradouro consulta = dao.find(Logradouro.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Logradouro.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Logradouro a1 = criaLogradouro();
		dao.persist(a1);
		Logradouro a2 = criaLogradouro();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Logradouro a";
		Query query = dao.getEntityManager().createQuery(sql, Logradouro.class);
		//
		@SuppressWarnings("unchecked")
		List<Logradouro> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_nao_deve_permitir_vazio() {
		Logradouro a = criaLogradouro();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_nao_deve_permitir_nulo() {
		Logradouro a = criaLogradouro();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test
	public void consultar_por_nome() {
		Logradouro a = criaLogradouro();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Logradouro a WHERE a.nome = :nome";
		TypedQuery<Logradouro> query = dao.getEntityManager().createQuery(sql, Logradouro.class);
		query.setParameter("nome", a.getNome());
		Logradouro consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void cep_nao_deve_permitir_vazio() {
		Logradouro a = criaLogradouro();
		a.setCep("");
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test(expected = ConstraintViolationException.class)
	public void cep_nao_deve_permitir_nulo() {
		Logradouro a = criaLogradouro();
		a.setCep(null);
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test
	public void consultar_por_cep() {
		Logradouro a = criaLogradouro();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Logradouro a WHERE a.cep = :cep";
		TypedQuery<Logradouro> query = dao.getEntityManager().createQuery(sql, Logradouro.class);
		query.setParameter("cep", a.getCep());
		Logradouro consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void tipoLogradouro_nao_deve_permitir_vazio() {
		Logradouro a = criaLogradouro();
		a.setTipoLogradouro("");
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test(expected = ConstraintViolationException.class)
	public void tipoLogradouro_nao_deve_permitir_nulo() {
		Logradouro a = criaLogradouro();
		a.setTipoLogradouro(null);
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test
	public void consultar_por_TipoLogradouro() {
		Logradouro a = criaLogradouro();
		dao.persist(a);
		Assert.assertNotNull(a.getId());
		String sql = "FROM Logradouro a WHERE a.tipoLogradouro = :tipo";
		TypedQuery<Logradouro> query = dao.getEntityManager().createQuery(sql, Logradouro.class);
		query.setParameter("tipo", a.getTipoLogradouro());
		Logradouro consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void uf_nao_deve_permitir_vazio() {
		Logradouro a = criaLogradouro();
		a.setUF("");
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test(expected = ConstraintViolationException.class)
	public void uf_nao_deve_permitir_nulo() {
		Logradouro a = criaLogradouro();
		a.setUF(null);
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test
	public void consultar_por_UF() {
		Logradouro a = criaLogradouro();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Logradouro a WHERE a.UF = :uf";
		TypedQuery<Logradouro> query = dao.getEntityManager().createQuery(sql, Logradouro.class);
		query.setParameter("uf", a.getUF());
		Logradouro consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	/**
	 * 
	 * 
	 * Título Logradouro
	 * 
	 * 
	 */
	@Test(expected = ConstraintViolationException.class)
	public void tituloLogradouro_nao_deve_permitir_vazio() {
		Logradouro a = criaLogradouro();
		a.setTituloLogradouro("");
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test(expected = ConstraintViolationException.class)
	public void tituloLogradouro_nao_deve_permitir_nulo() {
		Logradouro a = criaLogradouro();
		a.setTituloLogradouro(null);
		dao.persist(a);
		Assert.assertNotNull(a);
	}

	@Test
	public void consultar_por_tituloLogradouro() {
		Logradouro a = criaLogradouro();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Logradouro a WHERE a.tituloLogradouro = :tituloLogradouro";
		TypedQuery<Logradouro> query = dao.getEntityManager().createQuery(sql, Logradouro.class);
		query.setParameter("tituloLogradouro", a.getTituloLogradouro());
		Logradouro consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	//

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Logradouro a = criaLogradouro();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Logradouro criaLogradouro() {
		return LogradouroFabricaTest.getInstance().criaLogradouro();
	}
}