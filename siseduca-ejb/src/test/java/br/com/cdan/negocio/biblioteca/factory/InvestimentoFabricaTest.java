package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.comum.EnumTipoDeSituacaoInvestimento;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.curso.Investimento;

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
		i.setContasAReceber(ContaAReceberFabricaTest.getInstance().criaContasAReceber());
		// Contas a receber primeira parcela
		i.setContasAReceberPrimeiraParcela(ContaAReceberFabricaTest.getInstance().criaContasAReceberPrimeiraParcela());
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

}
