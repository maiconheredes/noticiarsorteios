package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;

public class PremiumController extends AbstractController {
	public static ArrayList<PremiumEntity> findPremiums(String idSweepstake, String idLocation) {
		String response = RequestController.request(
			"sorteios/" + idSweepstake + "/premiacoes/" + idLocation, 
			8087, "GET", ""
		);
		PremiumEntity[] originalPremiums = new Gson().fromJson(response, PremiumEntity[].class);
		
		ArrayList<PremiumEntity> premiums = new ArrayList<PremiumEntity>();
		
		for (PremiumEntity premium : originalPremiums) {
			premium.vlrPremioPartic = convertCurrenty(premium.vlrPremioPartic);
			premium.vlrPremioEntidSocial = convertCurrenty(premium.vlrPremioEntidSocial);
			
			premiums.add(premium);
		}
		
		return premiums;
	}
}
