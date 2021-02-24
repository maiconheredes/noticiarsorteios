package br.gov.mg.fazenda.test;

import java.sql.Timestamp;
import br.gov.mg.fazenda.noticiarsorteios.controllers.AbstractController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.LocationController;
import br.gov.mg.fazenda.noticiarsorteios.controllers.SweepstakesController;
import br.gov.mg.fazenda.noticiarsorteios.utils.Env;

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
		
		Env.setEnv("DES");

		//System.out.println(LocationController.findLocations("MUNICIPAL", null).size());
		
		System.out.println(SweepstakesController.findSweepstakes("4", "2020", "12", null).size());
		
		
		//PaymentRequestController.createPayment("1501", "");

		//System.out.println(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(Float.parseFloat("0")));
	}
}
