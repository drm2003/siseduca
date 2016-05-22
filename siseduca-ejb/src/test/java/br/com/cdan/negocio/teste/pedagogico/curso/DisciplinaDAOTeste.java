package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.negocio.pedagogico.curso.DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.DisciplinaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DisciplinaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DisciplinaDAOTeste.class);
	DisciplinaDao dao;

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
		dao = new DisciplinaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Disciplina a = criaDisciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Disciplina consulta = dao.find(Disciplina.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Disciplina a = criaDisciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Disciplina consulta = dao.find(Disciplina.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Disciplina a = criaDisciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Disciplina consulta = dao.find(Disciplina.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Disciplina.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Disciplina a1 = criaDisciplina();
		dao.persist(a1);
		Disciplina a2 = criaDisciplina();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Disciplina a";
		TypedQuery<Disciplina> query = dao.getEntityManager().createQuery(sql, Disciplina.class);
		//
		List<Disciplina> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Disciplina a = criaDisciplina();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Disciplina a = criaDisciplina();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Disciplina a = criaDisciplina();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Disciplina a = criaDisciplina();
		a.setNome(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		Disciplina a = criaDisciplina();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Disciplina a WHERE a.nome = :nome";
		TypedQuery<Disciplina> query = dao.getEntityManager().createQuery(sql, Disciplina.class);
		query.setParameter("nome", a.getNome());
		//
		Disciplina m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_nulo() {
		Disciplina a = criaDisciplina();
		a.setSigla(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_vazio() {
		Disciplina a = criaDisciplina();
		a.setSigla("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_menor_que_tamanho_minimo_permitido() {
		Disciplina a = criaDisciplina();
		a.setSigla(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_maior_que_tamanho_maximo_permitido() {
		Disciplina a = criaDisciplina();
		a.setSigla(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_sigla() {
		Disciplina a = criaDisciplina();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Disciplina a WHERE a.sigla = :sigla";
		TypedQuery<Disciplina> query = dao.getEntityManager().createQuery(sql, Disciplina.class);
		query.setParameter("sigla", a.getSigla());
		//
		Disciplina m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		Disciplina a = criaDisciplina();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Disciplina a = criaDisciplina();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Disciplina criaDisciplina() {
		return DisciplinaFabricaTest.getInstance().criaDisciplina(getEntityManager());
	}
}