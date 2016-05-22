package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.MotivoDeRenegociacaoDeParcelas;
import br.com.cdan.negocio.financeiro.MotivoDeRenegociacaoDeParcelasDao;
import br.com.cdan.negocio.financeiro.factory.MotivoDeRenegociacaoDeParcelasFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MotivoDeRenegociacaoDeParcelasDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MotivoDeRenegociacaoDeParcelasDAOTeste.class);
	MotivoDeRenegociacaoDeParcelasDao dao;

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
		dao = new MotivoDeRenegociacaoDeParcelasDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeRenegociacaoDeParcelas consulta = dao.find(MotivoDeRenegociacaoDeParcelas.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		MotivoDeRenegociacaoDeParcelas consulta = dao.find(MotivoDeRenegociacaoDeParcelas.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MotivoDeRenegociacaoDeParcelas consulta = dao.find(MotivoDeRenegociacaoDeParcelas.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MotivoDeRenegociacaoDeParcelas.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MotivoDeRenegociacaoDeParcelas a1 = criaMotivoDeRenegociacaoDeParcelas();
		dao.persist(a1);
		MotivoDeRenegociacaoDeParcelas a2 = criaMotivoDeRenegociacaoDeParcelas();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MotivoDeRenegociacaoDeParcelas a";
		Query query = dao.getEntityManager().createQuery(sql, MotivoDeRenegociacaoDeParcelas.class);
		//
		@SuppressWarnings("unchecked")
		List<MotivoDeRenegociacaoDeParcelas> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM MotivoDeRenegociacaoDeParcelas a WHERE a.descricao = :descricao";
		TypedQuery<MotivoDeRenegociacaoDeParcelas> query = dao.getEntityManager().createQuery(sql,
				MotivoDeRenegociacaoDeParcelas.class);
		query.setParameter("descricao", a.getDescricao());
		MotivoDeRenegociacaoDeParcelas consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MotivoDeRenegociacaoDeParcelas a = criaMotivoDeRenegociacaoDeParcelas();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private MotivoDeRenegociacaoDeParcelas criaMotivoDeRenegociacaoDeParcelas() {
		return MotivoDeRenegociacaoDeParcelasFabricaTest.getInstance().criaMotivoDeRenegociacaoDeParcelas();
	}
}