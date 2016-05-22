package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.negocio.pedagogico.curso.CursoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.CursoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CursoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CursoDAOTeste.class);
	CursoDao dao;

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
		dao = new CursoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Curso a = criaCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Curso consulta = dao.find(Curso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Curso a = criaCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Curso consulta = dao.find(Curso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Curso a = criaCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Curso consulta = dao.find(Curso.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Curso.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Curso a1 = criaCurso();
		dao.persist(a1);
		Curso a2 = criaCurso();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Curso a";
		TypedQuery<Curso> query = dao.getEntityManager().createQuery(sql, Curso.class);
		//
		List<Curso> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Curso a = criaCurso();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Curso a = criaCurso();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Curso a = criaCurso();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Curso a = criaCurso();
		a.setNome(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		Curso a = criaCurso();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Curso a WHERE a.nome = :nome";
		TypedQuery<Curso> query = dao.getEntityManager().createQuery(sql, Curso.class);
		query.setParameter("nome", a.getNome());
		//
		Curso m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_nulo() {
		Curso a = criaCurso();
		a.setSigla(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_vazio() {
		Curso a = criaCurso();
		a.setSigla("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_menor_que_tamanho_minimo_permitido() {
		Curso a = criaCurso();
		a.setSigla(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_maior_que_tamanho_maximo_permitido() {
		Curso a = criaCurso();
		a.setSigla(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_sigla() {
		Curso a = criaCurso();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Curso a WHERE a.sigla = :sigla";
		TypedQuery<Curso> query = dao.getEntityManager().createQuery(sql, Curso.class);
		query.setParameter("sigla", a.getSigla());
		//
		Curso m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Curso a = criaCurso();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Curso a = criaCurso();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Curso criaCurso() {
		return CursoFabricaTest.getInstance().criaCurso(getEntityManager());
	}
}