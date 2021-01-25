package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.Gson;

import br.gov.mg.fazenda.noticiarsorteios.entities.BankEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PaymentRequestEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumDetailEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.PremiumEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SocialEEntity;

public class PaymentRequestController extends AbstractController {
	public static ArrayList<BankEntity> findBanks() {
		ArrayList<BankEntity> banks = new ArrayList<BankEntity>();
		
		String response = RequestController.request("pagamentos/bancos", 8095);
		
		BankEntity[] result = new Gson()
				.fromJson(response, BankEntity[].class);
		
		for (BankEntity bank : result) {
			banks.add(bank);
		}
		
		Collections.sort(banks, new Comparator<BankEntity>() {
	        @Override
	        public int compare(BankEntity bank1, BankEntity bank2) {
	            return  bank1.nmBanco.compareTo(bank2.nmBanco);
	        }
	    });
		
		return banks;
	}
	
	public static PremiumDetailEntity findPremiumDetail(String cnpj, String cdRequisicao) {
		PremiumDetailEntity premiumDetail = new PremiumDetailEntity();
		
		String response1 = RequestController.request(
			"sorteios/premiado/entidadesocial/" + cnpj + "/" + cdRequisicao,
			8087
		);
		
		SocialEEntity socialE = new Gson()
				.fromJson(response1, SocialEEntity.class);
		
		if (socialE != null) {
			String response2 = RequestController.request(
				"sorteios/premiado/detalhe/" + socialE.idPremiado,
				8087
			);
				
			premiumDetail = new Gson().fromJson(response2, PremiumDetailEntity.class);
			
			if (premiumDetail != null) {
				premiumDetail.dtRealizSorteio = convertDate(premiumDetail.dtRealizSorteio);
				premiumDetail.vlrPremioPartic = convertCurrenty(premiumDetail.vlrPremioPartic);
				
				for (PremiumEntity premiumEntity : premiumDetail.entidadesSociaisPremiadas) {
					premiumEntity.vlrPremioEntidSocial = convertCurrenty(premiumEntity.vlrPremioEntidSocial);
					premiumEntity.vlrPremioPartic = convertCurrenty(premiumEntity.vlrPremioPartic);					
				}
				
				for (PaymentRequestEntity paymentRequest : premiumDetail.historicoRequisicoesPagamento) {
					paymentRequest.dtRequisicao = convertDate(paymentRequest.dtRequisicao);
				}
			} else return null;
		} else return null;	
		
		return premiumDetail;
	}
}
