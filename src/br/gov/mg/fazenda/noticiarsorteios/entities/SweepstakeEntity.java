package br.gov.mg.fazenda.noticiarsorteios.entities;

import java.util.ArrayList;

public class SweepstakeEntity {
	public int id;
	public int nrSorteio;
	public String tpSorteio;
	public String dtPrevSorteio;
	public String stSorteio;
	public String dtPublicacao;
	public String urlArqBilhetes;
	public String urlArqSorteador;
	public String urlArqResultado;
	public String urlArqGanhadores;
	public String hashArqBilhetes;
	public String hashArqResultado;
	public String hashArqSorteador;
	public LotteryExtractionEntity extracaoLoteria;
	public TicketingControlEntity controleBilhetagem;
	public SweepstakeTotalsEntity totaisSorteio;
	public ArrayList<SummaryPremiumsEntity> sumarizacaoPremiacoes;
	public ArrayList<SummaryPremiumsEntity> sumarizacaoPremiacoesEntidadesSociais;	
	public ArrayList<SummaryPremiumsEntity> sumarizacaoPremiacoesParticipantesEntidadesSociais;
}
