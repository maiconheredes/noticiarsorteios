package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.gov.mg.fazenda.noticiarsorteios.entities.SummaryPremiumsEntity;
import br.gov.mg.fazenda.noticiarsorteios.entities.SweepstakeEntity;

public abstract class AbstractController {
	protected static void configureSweepstake(SweepstakeEntity sweepstake) {
		sweepstake.dtPrevSorteio = convertDate(sweepstake.dtPrevSorteio);
		sweepstake.dtPublicacao = convertDate(sweepstake.dtPublicacao);
		
		if (sweepstake.extracaoLoteria != null) {
			sweepstake.extracaoLoteria.dtExtracao = convertDate(sweepstake.extracaoLoteria.dtExtracao);
		}
		
		if (sweepstake.controleBilhetagem != null) {
			sweepstake.controleBilhetagem.dtFimRefCompra = convertDate(sweepstake.controleBilhetagem.dtFimRefCompra);
			sweepstake.controleBilhetagem.dtGeracaoBilhete = convertDate(sweepstake.controleBilhetagem.dtGeracaoBilhete);
			sweepstake.controleBilhetagem.dtInicioRefCompra = convertDate(sweepstake.controleBilhetagem.dtInicioRefCompra);
		}
		
		for (SummaryPremiumsEntity premiacao : sweepstake.sumarizacaoPremiacoes) {
			premiacao.valorPremioParticipante = convertCurrenty(premiacao.valorPremioParticipante);
			premiacao.valorPremioEntidadeSocial = convertCurrenty(premiacao.valorPremioEntidadeSocial);
		}
		for (SummaryPremiumsEntity premiacao : sweepstake.sumarizacaoPremiacoesEntidadesSociais) {
			premiacao.valorPremioParticipante = convertCurrenty(premiacao.valorPremioParticipante);
			premiacao.valorPremioEntidadeSocial = convertCurrenty(premiacao.valorPremioEntidadeSocial);
		}
	}
	
	protected static String convertCurrenty(String currenty) {
		if (currenty == null) currenty = "0";
		
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
				.format(Float.parseFloat(currenty));
	}
	
	protected static String convertDate(String stringDate) {
		if (stringDate == null) return stringDate;
		
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stringDate;
	}
}
