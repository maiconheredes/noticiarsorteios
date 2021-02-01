package br.gov.mg.fazenda.test;

import java.text.NumberFormat;
import java.util.Locale;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.controllers.AbstractController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.LocationController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.PaymentRequestController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.PremiumController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.RequestController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.SocialEntityController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.SweepstakesController;
import br.gov.mg.fazenda.noticiarsorteios.entities.LocationEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.ResultSocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SummaryPremiumsEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SweepstakeEntity;

public class Test extends AbstractController {
	public static void main(String[] args) {
		/*
		 * String body = RequestController.request("sorteios/periodo/2021/2",
		 * 8087);
		 * 
		 * SweepstakeEntity[] sweepstakes = new Gson().fromJson(body,
		 * SweepstakeEntity[].class);
		 * 
		 * for (SweepstakeEntity sweepstake : sweepstakes) {
		 * configureSweepstake(sweepstake); System.out.println("dtPrevSorteio: "
		 * + sweepstake.dtPrevSorteio); System.out.println("tpSorteio: " +
		 * sweepstake.tpSorteio); for (SummaryPremiumsEntity premiacao :
		 * sweepstake.sumarizacaoPremiacoes) { System.out.println("valor " +
		 * premiacao.valorPremioParticipante); System.out.println("valor " +
		 * premiacao.valorPremioEntidadeSocial); } for (SummaryPremiumsEntity
		 * premiacao : sweepstake.sumarizacaoPremiacoesEntidadesSociais) {
		 * System.out.println("valor " + premiacao.valorPremioParticipante);
		 * System.out.println("valor " + premiacao.valorPremioEntidadeSocial); }
		 * }
		 */

		// SweepstakesController.findSweepstakes("12", "2020");

		/*
		 * for (LocationEntity location :
		 * LocationController.findLocations("REGIONAL")) {
		 * System.out.println("location: " + location.id); }
		 */

		/*
		 * for (PremiumEntity premium : PremiumController.findPremiums("1484",
		 * "3101")) { System.out.println("vlrPremioPartic: " +
		 * premium.vlrPremioPartic); System.out.println("vlrPremioEntidSocial: "
		 * + premium.vlrPremioEntidSocial); }
		 */

		/*
		 * ResultSocialEEntity result =
		 * SocialEntityController.findSocialEntities("", "", "", "");
		 * 
		 * for (SocialEEntity social : result.content) {
		 * System.out.println(SocialEntityController.findSocialEntity(social.
		 * cnpj).email); }
		 */

		/*System.out.println(PaymentRequestController.findPremiumDetail("22737746000115",
		"732783").chaveAcesso);*/
		
		//PaymentRequestController.createPayment();

		//System.out.println(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(Float.parseFloat("0")));
	}
}
