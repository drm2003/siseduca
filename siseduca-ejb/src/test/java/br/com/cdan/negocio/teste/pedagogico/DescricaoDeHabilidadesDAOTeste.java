package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.DescricaoDeHabilidades;
import br.com.cdan.negocio.pedagogico.DescricaoDeHabilidadesDao;
import br.com.cdan.negocio.pedagogico.factory.DescricaoDeHabilidadesFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DescricaoDeHabilidadesDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DescricaoDeHabilidadesDAOTeste.class);
	DescricaoDeHabilidadesDao dao;

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
		dao = new DescricaoDeHabilidadesDao(getEntityManager());
	}

	@Test
	public void inserir() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DescricaoDeHabilidades consulta = dao.find(DescricaoDeHabilidades.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		DescricaoDeHabilidades consulta = dao.find(DescricaoDeHabilidades.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DescricaoDeHabilidades consulta = dao.find(DescricaoDeHabilidades.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(DescricaoDeHabilidades.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		DescricaoDeHabilidades a1 = criaDescricaoDeHabilidades();
		dao.persist(a1);
		DescricaoDeHabilidades a2 = criaDescricaoDeHabilidades();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM DescricaoDeHabilidades a";
		TypedQuery<DescricaoDeHabilidades> query = dao.getEntityManager().createQuery(sql,
				DescricaoDeHabilidades.class);
		//
		List<DescricaoDeHabilidades> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		dao.persist(a);
		//
		String sql = "SELECT a FROM DescricaoDeHabilidades a WHERE a.descricao = :descricao";
		TypedQuery<DescricaoDeHabilidades> query = dao.getEntityManager().createQuery(sql,
				DescricaoDeHabilidades.class);
		query.setParameter("descricao", a.getDescricao());
		//
		DescricaoDeHabilidades m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		DescricaoDeHabilidades a = criaDescricaoDeHabilidades();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private DescricaoDeHabilidades criaDescricaoDeHabilidades() {
		return DescricaoDeHabilidadesFabricaTest.getInstance().criaDescricaoDeHabilidades();
	}
}