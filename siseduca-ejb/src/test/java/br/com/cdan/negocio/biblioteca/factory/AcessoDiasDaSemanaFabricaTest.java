package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.negocio.biblioteca.AcessoDiasDaSemanaDao;
import br.com.cdan.negocio.biblioteca.HorarioDeAulaDao;

public class AcessoDiasDaSemanaFabricaTest {
	private static AcessoDiasDaSemanaFabricaTest instance = null;

	public static synchronized AcessoDiasDaSemanaFabricaTest getInstance() {
		if (instance == null) {
			instance = new AcessoDiasDaSemanaFabricaTest();
		}
		return instance;
	}

	public AcessoDiasDaSemana criaAcessoDiasDaSemana() {
		AcessoDiasDaSemana a = new AcessoDiasDaSemana();
		a.setAtivo(Boolean.TRUE);
		a.setDiaDaSemana(EnumDiaDaSemana.QUARTA);
		a.setHoraEntrada(Calendar.getInstance().getTime());
		a.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAula());
		a.setHoraSaida(Calendar.getInstance().getTime());
		return a;
	}

	public AcessoDiasDaSemana criaAcessoDiasDaSemanaPersisitdo(EntityManager em) {
		AcessoDiasDaSemana a = criaAcessoDiasDaSemana();
		AcessoDiasDaSemanaDao dao = new AcessoDiasDaSemanaDao(em);
		//
		HorarioDeAulaDao horarioDeAulaDao = new HorarioDeAulaDao(em);
		HorarioDeAula horarioDeAula = a.getHorarioDeAula();
		horarioDeAulaDao.persist(horarioDeAula);
		a.setHorarioDeAula(horarioDeAula);
		//
		dao.persist(a);
		return a;
	}
}
