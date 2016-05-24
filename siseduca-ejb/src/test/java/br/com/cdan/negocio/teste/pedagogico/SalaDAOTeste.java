package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.Sala;
import br.com.cdan.negocio.pedagogico.SalaDao;
import br.com.cdan.negocio.pedagogico.factory.SalaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SalaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SalaDAOTeste.class);
	SalaDao dao;

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
		dao = new SalaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Sala a = criaSala();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Sala consulta = dao.find(Sala.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Sala a = criaSala();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Sala consulta = dao.find(Sala.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Sala a = criaSala();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Sala consulta = dao.find(Sala.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Sala.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Sala a1 = criaSala();
		dao.persist(a1);
		Sala a2 = criaSala();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Sala a";
		TypedQuery<Sala> query = dao.getEntityManager().createQuery(sql, Sala.class);
		//
		List<Sala> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Sala a = criaSala();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Sala a = criaSala();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Sala a = criaSala();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Sala a = criaSala();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Sala a = criaSala();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Sala a WHERE a.descricao = :descricao";
		TypedQuery<Sala> query = dao.getEntityManager().createQuery(sql, Sala.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Sala m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_nulo() {
		Sala a = criaSala();
		a.setSigla(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_vazio() {
		Sala a = criaSala();
		a.setSigla("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_menor_que_tamanho_minimo_permitido() {
		Sala a = criaSala();
		a.setSigla(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_maior_que_tamanho_maximo_permitido() {
		Sala a = criaSala();
		a.setSigla(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_sigla() {
		Sala a = criaSala();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Sala a WHERE a.sigla = :sigla";
		TypedQuery<Sala> query = dao.getEntityManager().createQuery(sql, Sala.class);
		query.setParameter("sigla", a.getSigla());
		//
		Sala m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_locacao_nulo() {
		Sala a = criaSala();
		a.setLocacao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Sala a = criaSala();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Sala criaSala() {
		return SalaFabricaTest.getInstance().criaSala(getEntityManager());
	}
}