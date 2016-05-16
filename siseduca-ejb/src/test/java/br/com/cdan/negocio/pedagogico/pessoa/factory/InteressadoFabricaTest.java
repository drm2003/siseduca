package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.negocio.contato.factory.MelhorHorarioParaContatoFabricaTest;
import br.com.cdan.negocio.contato.factory.MidiaFabricaTest;
import br.com.cdan.negocio.contato.factory.TipoDeContatoFabricaTest;
import br.com.cdan.negocio.geral.factory.OrigemFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.factory.MotivoDeNaoFechamentoDeContratoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.InteressadoDao;

public class InteressadoFabricaTest {
	private static InteressadoFabricaTest instance = null;

	public static synchronized InteressadoFabricaTest getInstance() {
		if (instance == null) {
			instance = new InteressadoFabricaTest();
		}
		return instance;
	}

	public Interessado criaInteressado(EntityManager em) {
		Interessado i = new Interessado();
		i.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		i.setAtendente(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		i.setAtivo(Boolean.TRUE);
		//
		i.setMelhorHorarioParaContato(
				MelhorHorarioParaContatoFabricaTest.getInstance().criaMelhorHorarioParaContatoPersistido(em));
		i.setMidia(MidiaFabricaTest.getInstance().criaMidiaPersistido(em));
		i.setMotivoDeNaoFechamentoDeContrato(MotivoDeNaoFechamentoDeContratoFabricaTest.getInstance()
				.criaMotivosDeNaoFechamentoDeContratoPersistido(em));
		i.setObservacaoDoAluno(ObservacaoDoAlunoFabricaTest.getInstance().criaObservacaoDoAlunoPersistido(em));
		i.setOrigem(OrigemFabricaTest.getInstance().criaOrigemPersistido(em));
		i.setOutraEscolaDoAluno("outraEscolaDoAluno");
		i.setPromotorDeVendas(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		i.setTipoDeContato(TipoDeContatoFabricaTest.getInstance().criaTipoDeContatoPersistido(em));
		return i;
	}

	public Interessado criaInteressadoPersistido(EntityManager em) {
		Interessado i = criaInteressado(em);
		InteressadoDao dao = new InteressadoDao(em);
		dao.persist(i);
		return i;
	}
}
