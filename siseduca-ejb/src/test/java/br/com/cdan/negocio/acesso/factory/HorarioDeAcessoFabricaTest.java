package br.com.cdan.negocio.acesso.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.model.acesso.HorarioDeAcesso;
import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.negocio.acesso.AcessoDiasDaSemanaDao;
import br.com.cdan.negocio.acesso.HorarioDeAcessoDao;
import br.com.cdan.negocio.acesso.UsuarioDao;
import br.com.cdan.negocio.pedagogico.CalendarioLetivoDao;
import br.com.cdan.negocio.pedagogico.factory.CalendarioLetivoFabricaTest;

public class HorarioDeAcessoFabricaTest {
	private static HorarioDeAcessoFabricaTest instance = null;

	public static synchronized HorarioDeAcessoFabricaTest getInstance() {
		if (instance == null) {
			instance = new HorarioDeAcessoFabricaTest();
		}
		return instance;
	}

	public HorarioDeAcesso criaHorarioDeAcesso() {
		HorarioDeAcesso h = new HorarioDeAcesso();
		h.setAtivo(Boolean.TRUE);
		// Acessos do dia da semana
		Set<AcessoDiasDaSemana> acessosDiasDaSemana = new LinkedHashSet<>();
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiasDaSemana());
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiasDaSemana());
		h.setAcessoDiasDaSemana(acessosDiasDaSemana);
		// Calendário Letivo
		Set<CalendarioLetivo> calendariosLetivo = new LinkedHashSet<>();
		calendariosLetivo.add(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		calendariosLetivo.add(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		h.setCalendarioLetivo(calendariosLetivo);
		//
		Set<Usuario> usuarios = new LinkedHashSet<>();
		usuarios.add(UsuarioFabricaTest.getInstance().criaUsuario());
		usuarios.add(UsuarioFabricaTest.getInstance().criaUsuario());
		h.setUsuarios(usuarios);
		//
		return h;
	}

	public HorarioDeAcesso criaHorarioDeAcessoPersistido(EntityManager em) {
		HorarioDeAcesso h = criaHorarioDeAcesso();
		HorarioDeAcessoDao dao = new HorarioDeAcessoDao(em);
		h.setAtivo(Boolean.TRUE);
		// Acessos do dia da semana
		Set<AcessoDiasDaSemana> acessosDiasDaSemana = new LinkedHashSet<>();
		AcessoDiasDaSemanaDao acessoDiasDaSemanaDao = new AcessoDiasDaSemanaDao(em);
		h.getAcessoDiasDaSemana().forEach(acessoDiasDaSemana -> {
			acessoDiasDaSemanaDao.persist(acessoDiasDaSemana);
			acessosDiasDaSemana.add(acessoDiasDaSemana);
		});
		;
		h.setAcessoDiasDaSemana(acessosDiasDaSemana);
		// Calendário Letivo
		Set<CalendarioLetivo> calendariosLetivo = new LinkedHashSet<>();
		CalendarioLetivoDao calendarioLetivoDao = new CalendarioLetivoDao(em);
		h.getCalendarioLetivo().forEach(calendarioLetivo -> {
			calendarioLetivoDao.persist(calendarioLetivo);
			calendariosLetivo.add(calendarioLetivo);
		});
		h.setCalendarioLetivo(calendariosLetivo);
		//
		Set<Usuario> usuarios = new LinkedHashSet<>();
		UsuarioDao usuarioDao = new UsuarioDao(em);
		h.getUsuarios().forEach(usuario -> {
			usuarioDao.persist(usuario);
			usuarios.add(usuario);
		});
		;
		h.setUsuarios(usuarios);
		//
		dao.persist(h);
		return h;
	}
}
