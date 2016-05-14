package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.negocio.biblioteca.ObraDao;
import br.com.cdan.negocio.biblioteca.factory.ObraFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ObraDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ObraDAOTeste.class);
	ObraDao dao;

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
		dao = new ObraDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Obra a = criaObra();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Obra consulta = dao.find(Obra.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Obra a = criaObra();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		dao.merge(a);
		Obra consulta = dao.find(Obra.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Obra a = criaObra();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Obra consulta = dao.find(Obra.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Obra.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Obra a1 = criaObra();
		dao.persist(a1);
		Obra a2 = criaObra();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Obra a";
		Query query = dao.getEntityManager().createQuery(sql, Obra.class);
		//
		@SuppressWarnings("unchecked")
		List<Obra> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		Obra a = criaObra();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Obra a WHERE a.nome = :nome";
		TypedQuery<Obra> query = dao.getEntityManager().createQuery(sql, Obra.class);
		query.setParameter("nome", a.getNome());
		Obra consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Obra a = criaObra();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Obra a = criaObra();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Obra a = criaObra();
		a.setNome(criarStringDinamicaPorTamanho(356));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Obra a = criaObra();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void subtitulo_nao_pode_maior_que_tamanho_maximo_permitido() {
		Obra a = criaObra();
		a.setSubtitulo(criarStringDinamicaPorTamanho(356));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void subtitulo_nao_pode_menor_que_tamanho_minimo_permitido() {
		Obra a = criaObra();
		a.setSubtitulo(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void subtitulo_pode_ser_nulo() {
		Obra a = criaObra();
		a.setSubtitulo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void tipoDeObra_nao_deve_permitir_Nulo() {
		Obra a = criaObra();
		a.setTipoDeObra(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Obra a = criaObra();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Obra criaObra() {
		return ObraFabricaTest.getInstance().criaObra(getEntityManager());
	}
}