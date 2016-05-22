package br.com.cdan.negocio.teste.pessoa;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.PessoaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class PessoaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(PessoaDAOTeste.class);
	PessoaDao dao;

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
		dao = new PessoaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Pessoa a = criaPessoa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Pessoa consulta = dao.find(Pessoa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Pessoa a = criaPessoa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome(criarStringDinamicaPorTamanho(10));
		dao.merge(a);
		Pessoa consulta = dao.find(Pessoa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Pessoa a = criaPessoa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Pessoa consulta = dao.find(Pessoa.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Pessoa.class, a.getId()));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultar_todos() {
		Pessoa a1 = criaPessoa();
		dao.persist(a1);
		Pessoa a2 = criaPessoa();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Pessoa a";
		Query query = dao.getEntityManager().createQuery(sql, Pessoa.class);
		//
		List<Pessoa> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		Pessoa a = criaPessoa();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Pessoa a WHERE a.nome = :nome";
		TypedQuery<Pessoa> query = dao.getEntityManager().createQuery(sql, Pessoa.class);
		query.setParameter("nome", a.getNome());
		Pessoa consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Pessoa a = criaPessoa();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Pessoa a = criaPessoa();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Pessoa a = criaPessoa();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Pessoa criaPessoa() {
		return PessoaFabricaTest.getInstance().criaPessoa(getEntityManager());
	}
}