package br.com.cdan.negocio.pedagogico.curso.factory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeSituacaoInvestimento;
import br.com.cdan.model.estoque.Item;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.geral.Categoria;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pedagogico.curso.TipoDeInvestimento;
import br.com.cdan.negocio.estoque.ItemDao;
import br.com.cdan.negocio.estoque.factory.ItemFabricaTest;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.financeiro.factory.ContaAReceberFabricaTest;
import br.com.cdan.negocio.geral.CategoriaDao;
import br.com.cdan.negocio.geral.factory.CategoriaFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.MatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MatriculaFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.InvestimentoDao;
import br.com.cdan.negocio.pedagogico.curso.TipoDeInvestimentoDao;

public class InvestimentoFabricaTest {
	private static InvestimentoFabricaTest instance = null;

	public static synchronized InvestimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new InvestimentoFabricaTest();
		}
		return instance;
	}

	public Investimento criaInvestimento() {
		Investimento i = new Investimento();
		i.setAtivo(Boolean.TRUE);
		i.setCategoria(CategoriaFabricaTest.getInstance().criaCategoria());
		i.setConsiderarMesAtual(Boolean.TRUE);
		// Contas a receber
		i.setContaAReceber(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		// Contas a receber primeira parcela
		i.setContaAReceberPrimeiraParcela(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		i.setDataDiferenciadaPrimeiraParcela(Calendar.getInstance());
		i.setDataInicial(Calendar.getInstance());
		i.setDescricaoPlano("descricaoPlano");
		i.setItem(ItemFabricaTest.getInstance().criaItem());
		// Matrículas
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		i.setMatriculas(matriculas);
		//
		i.setNumeroDeParcelas(Long.valueOf("1"));
		i.setTipoDeInvestimento(TipoDeInvestimentoFabricaTest.getInstance().criaTipoDeInvestimento());
		i.setTipoDeSituacao(EnumTipoDeSituacaoInvestimento.QUITADA);
		i.setValorDaParcela(BigDecimal.TEN);
		i.setValorDaPrimeiraParcelaDiferenciada(Boolean.TRUE);
		i.setValorPrimeiraParcelaDiferenciado(BigDecimal.TEN);
		//
		return i;
	}

	public Investimento criaInvestimentoPersistido(EntityManager em) {
		Investimento i = criaInvestimento();
		InvestimentoDao dao = new InvestimentoDao(em);
		//
		CategoriaDao categoriaDao = new CategoriaDao(em);
		Categoria categoria = i.getCategoria();
		categoriaDao.persist(categoria);
		i.setCategoria(categoria);
		// Contas a receber
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao(em);
		ContaAReceber contaAReceber3 = i.getContaAReceber();
		ContaAReceber contaAReceber2 = contaAReceber3;
		ContaAReceber contaAReceber = contaAReceber2;
		contaAReceberDao.persist(contaAReceber);
		i.setContaAReceber(contaAReceber);
		// Contas a receber primeira parcela
		ContaAReceber contaAReceberPrimeiraParcela = i.getContaAReceberPrimeiraParcela();
		contaAReceberDao.persist(contaAReceberPrimeiraParcela);
		i.setContaAReceberPrimeiraParcela(contaAReceberPrimeiraParcela);
		//
		ItemDao itemDao = new ItemDao(em);
		Item item = i.getItem();
		itemDao.persist(item);
		i.setItem(item);
		// Matrículas
		MatriculaDao matriculaDao = new MatriculaDao(em);
		Set<Matricula> matriculas = new LinkedHashSet<>();
		i.getMatriculas().forEach(matricula -> {
			matriculaDao.persist(matricula);
			matriculas.add(matricula);
		});
		i.setMatriculas(matriculas);
		//
		TipoDeInvestimentoDao tipoDeInvestimentoDao = new TipoDeInvestimentoDao(em);
		TipoDeInvestimento tipoDeInvestimento = i.getTipoDeInvestimento();
		tipoDeInvestimentoDao.persist(tipoDeInvestimento);
		i.setTipoDeInvestimento(tipoDeInvestimento);
		//
		dao.persist(i);
		return i;
	}
}