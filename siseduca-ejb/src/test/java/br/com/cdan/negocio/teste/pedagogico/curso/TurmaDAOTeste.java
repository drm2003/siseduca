package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.negocio.pedagogico.curso.TurmaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TurmaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TurmaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TurmaDAOTeste.class);
	TurmaDao dao;

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
		dao = new TurmaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Turma a = criaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Turma consulta = dao.find(Turma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Turma a = criaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Turma consulta = dao.find(Turma.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Turma a = criaTurma();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Turma consulta = dao.find(Turma.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Turma.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Turma a1 = criaTurma();
		dao.persist(a1);
		Turma a2 = criaTurma();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Turma a";
		TypedQuery<Turma> query = dao.getEntityManager().createQuery(sql, Turma.class);
		//
		List<Turma> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Turma a = criaTurma();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Turma a = criaTurma();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Turma a = criaTurma();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Turma a = criaTurma();
		a.setNome(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		Turma a = criaTurma();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Turma a WHERE a.nome = :nome";
		TypedQuery<Turma> query = dao.getEntityManager().createQuery(sql, Turma.class);
		query.setParameter("nome", a.getNome());
		//
		Turma m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_nulo() {
		Turma a = criaTurma();
		a.setSigla(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_vazio() {
		Turma a = criaTurma();
		a.setSigla("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_menor_que_tamanho_minimo_permitido() {
		Turma a = criaTurma();
		a.setSigla(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_maior_que_tamanho_maximo_permitido() {
		Turma a = criaTurma();
		a.setSigla(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_sigla() {
		Turma a = criaTurma();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Turma a WHERE a.sigla = :sigla";
		TypedQuery<Turma> query = dao.getEntityManager().createQuery(sql, Turma.class);
		query.setParameter("sigla", a.getSigla());
		//
		Turma m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_codigo_nulo() {
		Turma a = criaTurma();
		a.setCodigo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Turma a = criaTurma();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Turma criaTurma() {
		return TurmaFabricaTest.getInstance().criaTurma(getEntityManager());
	}
}