package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.comum.EnumTipoDeCor;
import br.com.cdan.model.pessoa.ObservacaoDoAluno;
import br.com.cdan.model.pessoa.ProcessoSeletivo;

public class ObservacaoDoAlunoFabricaTest {
	private static ObservacaoDoAlunoFabricaTest instance = null;

	public static synchronized ObservacaoDoAlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ObservacaoDoAlunoFabricaTest();
		}
		return instance;
	}

	public ObservacaoDoAluno criaObservacaoDoAluno() {
		ObservacaoDoAluno o = new ObservacaoDoAluno();
		o.setAlergias("alergias");
		o.setAtivo(Boolean.TRUE);
		o.setCursouOutrasInstituicoesDeEnsino(Boolean.FALSE);
		o.setDoencas("doencas");
		o.setInteressado(InteressadoFabricaTest.getInstance().criaInteressado());
		o.setObservacao("observacao");
		o.setPrimeirosSocorros("primeirosSocorros");
		o.setPrimeirosSocorros("primeirosSocorros");
		//
		Set<ProcessoSeletivo> processosSeletivo = new LinkedHashSet<>();
		processosSeletivo.add(ProcessoSeletivoFabricaTest.getInstance().criaProcessoSeletivo());
		processosSeletivo.add(ProcessoSeletivoFabricaTest.getInstance().criaProcessoSeletivo());
		o.setProcessoSeletivo(processosSeletivo);
		//
		o.setTipoDeCor(EnumTipoDeCor.AMARELO);
		o.setTipoSanguineo("tipoSanguineo");
		return o;
	}

}
