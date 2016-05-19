package br.com.cdan.negocio.teste.geral.cep;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.cep.Cidade;
import br.com.cdan.negocio.geral.CidadeDao;
import br.com.cdan.negocio.geral.cep.factory.CidadeFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CidadeDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CidadeDAOTeste.class);
	CidadeDao dao;

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
		dao = new CidadeDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Cidade a = criaCidade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Cidade consulta = dao.find(Cidade.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Cidade a = criaCidade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Cidade consulta = dao.find(Cidade.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Cidade a = criaCidade();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Cidade consulta = dao.find(Cidade.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Cidade.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Cidade a1 = criaCidade();
		dao.persist(a1);
		Cidade a2 = criaCidade();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Cidade a";
		Query query = dao.getEntityManager().createQuery(sql, Cidade.class);
		//
		@SuppressWarnings("unchecked")
		List<Cidade> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Cidade a = criaCidade();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Cidade a WHERE a.descricao = :descricao";
		TypedQuery<Cidade> query = dao.getEntityManager().createQuery(sql, Cidade.class);
		query.setParameter("descricao", a.getDescricao());
		Cidade consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Cidade a = criaCidade();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Cidade criaCidade() {
		return CidadeFabricaTest.getInstance().criaCidade(getEntityManager());
	}
}