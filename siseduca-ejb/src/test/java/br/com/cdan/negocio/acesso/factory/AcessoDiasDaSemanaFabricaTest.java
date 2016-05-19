package br.com.cdan.negocio.acesso.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.negocio.acesso.AcessoDiasDaSemanaDao;
import br.com.cdan.negocio.pedagogico.factory.HorarioDeAulaFabricaTest;

public class AcessoDiasDaSemanaFabricaTest {
	private static AcessoDiasDaSemanaFabricaTest instance = null;

	public static synchronized AcessoDiasDaSemanaFabricaTest getInstance() {
		if (instance == null) {
			instance = new AcessoDiasDaSemanaFabricaTest();
		}
		return instance;
	}

	public AcessoDiasDaSemana criaAcessoDiasDaSemana(EntityManager em) {
		AcessoDiasDaSemana a = new AcessoDiasDaSemana();
		a.setAtivo(Boolean.TRUE);
		a.setDiaDaSemana(EnumDiaDaSemana.QUARTA);
		a.setHoraEntrada(Calendar.getInstance().getTime());
		a.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAulaPersistido(em));
		a.setHoraSaida(Calendar.getInstance().getTime());
		return a;
	}

	public AcessoDiasDaSemana criaAcessoDiasDaSemanaPersisitdo(EntityManager em) {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana(em);
		AcessoDiasDaSemanaDao dao = new AcessoDiasDaSemanaDao(em);
		//
		dao.persist(a);
		return a;
	}
}