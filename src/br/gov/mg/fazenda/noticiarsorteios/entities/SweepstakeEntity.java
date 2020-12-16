package br.gov.mg.fazenda.noticiarsorteios.entities;

import java.util.ArrayList;

public class SweepstakeEntity {
	public int nrSorteio;
	public String tpSorteio;
	public String dtPrevSorteio;
	public String stSorteio;
	public String dtPublicacao;
	public LotteryExtractionEntity extracaoLoteria;
	public TicketingControlEntity controleBilhetagem;
	public SweepstakeTotalsEntity totaisSorteio;
	public ArrayList<SummaryPremiumsEntity> sumarizacaoPremiacoes;
	public ArrayList<SummaryPremiumsEntity> sumarizacaoPremiacoesEntidadesSociais;	
}
