package br.com.cdan.negocio.teste.estoque;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.estoque.ClassificacaoDeItens;
import br.com.cdan.negocio.estoque.ClassificacaoDeItensDao;
import br.com.cdan.negocio.estoque.factory.ClassificacaoDeItensFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ClassificacaoDeItensDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ClassificacaoDeItensDAOTeste.class);
	ClassificacaoDeItensDao dao;

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
		dao = new ClassificacaoDeItensDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClassificacaoDeItens consulta = dao.find(ClassificacaoDeItens.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		ClassificacaoDeItens consulta = dao.find(ClassificacaoDeItens.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ClassificacaoDeItens consulta = dao.find(ClassificacaoDeItens.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ClassificacaoDeItens.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ClassificacaoDeItens a1 = criaClassificacaoDeItens();
		dao.persist(a1);
		ClassificacaoDeItens a2 = criaClassificacaoDeItens();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ClassificacaoDeItens a";
		Query query = dao.getEntityManager().createQuery(sql, ClassificacaoDeItens.class);
		//
		@SuppressWarnings("unchecked")
		List<ClassificacaoDeItens> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM ClassificacaoDeItens a WHERE a.descricao = :descricao";
		TypedQuery<ClassificacaoDeItens> query = dao.getEntityManager().createQuery(sql, ClassificacaoDeItens.class);
		query.setParameter("descricao", a.getDescricao());
		ClassificacaoDeItens consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ClassificacaoDeItens a = criaClassificacaoDeItens();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ClassificacaoDeItens criaClassificacaoDeItens() {
		return ClassificacaoDeItensFabricaTest.getInstance().criaClassificacaoDeItens();
	}
}