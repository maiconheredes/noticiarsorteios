package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;
import br.gov.mg.fazenda.noticiarsorteios.utils.Routes;

public class PremiumController extends AbstractController {
	public static ArrayList<PremiumEntity> findPremiums(
		String idSweepstake, String idLocation, HttpServletRequest request
	) {
		String response = RequestController.request(
			Routes.premiumsByLocation()
				.replace("{idSweepstake}", idSweepstake)
				.replace("{idLocation}", idLocation),
			8087, "GET", "", request
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
