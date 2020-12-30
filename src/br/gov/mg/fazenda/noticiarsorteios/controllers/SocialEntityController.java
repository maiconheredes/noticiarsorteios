package br.gov.mg.fazenda.noticiarsorteios.controllers;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.ResultSocialEEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SocialEEntity;

public class SocialEntityController extends AbstractController {
	public static ResultSocialEEntity findSocialEntities(
		String idLocalidade, String cdRegiaoFiscal,
		String nmEntidadeSocial, String page
	) {
		String url = "entidadessociais";
		
		url += "?idLocalidade=" + idLocalidade;
		url += "&cdRegiaoFiscal=" + cdRegiaoFiscal;
		url += "&nmEntidadeSocial=" + nmEntidadeSocial;
		url += "&page=" + page;
		url += "&size=20";
		
		String response = RequestController.request(url, 8085);
		
		ResultSocialEEntity result = new Gson().fromJson(response, ResultSocialEEntity.class);
		
		return result;
	}
	
	public static SocialEEntity findSocialEntity(String cnpj) {
		String response = RequestController.request("entidadessociais/" + cnpj, 8085);
		
		SocialEEntity socialEntity = new Gson().fromJson(response, SocialEEntity.class);
		
		socialEntity.dtAbertura = convertDate(socialEntity.dtAbertura);
		socialEntity.dtFundacao = convertDate(socialEntity.dtFundacao);
		socialEntity.dtInclusao = convertDate(socialEntity.dtInclusao);
		socialEntity.dtNascimentoResponsavel = convertDate(socialEntity.dtNascimentoResponsavel);
		
		return socialEntity;
	}
}
