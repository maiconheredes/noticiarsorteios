package br.gov.mg.fazenda.noticiarsorteios.controllers;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.ResultSocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.utils.Routes;

public class SocialEntityController extends AbstractController {
	public static ResultSocialEEntity findSocialEntities(
		String idLocalidade, String cdRegiaoFiscal,
		String nmEntidadeSocial, String page,
		HttpServletRequest request
	) {
		String url = Routes.socialEntities();
		
		url += "?idLocalidade=" + idLocalidade;
		url += "&cdRegiaoFiscal=" + cdRegiaoFiscal;
		url += "&nmEntidadeSocial=" + nmEntidadeSocial;
		url += "&page=" + page;
		url += "&size=20";
		
		String response = RequestController.request(url, 8085, "GET", "", request);
		
		ResultSocialEEntity result = new Gson().fromJson(response, ResultSocialEEntity.class);
		
		return result;
	}
	
	public static SocialEEntity findSocialEntity(String cnpj, HttpServletRequest request) {
		String response = RequestController.request(
			Routes.findSocialEntity().replace("{cnpj}", cnpj), 
			8085, "GET", "", request
		);
		
		SocialEEntity socialEntity = new Gson().fromJson(response, SocialEEntity.class);
		
		if (socialEntity != null) {
			socialEntity.dtAbertura = convertDate(socialEntity.dtAbertura);
			socialEntity.dtFundacao = convertDate(socialEntity.dtFundacao);
			socialEntity.dtInclusao = convertDate(socialEntity.dtInclusao);
			socialEntity.dtNascimentoResponsavel = convertDate(socialEntity.dtNascimentoResponsavel);
		}
		
		return socialEntity;
	}
}
