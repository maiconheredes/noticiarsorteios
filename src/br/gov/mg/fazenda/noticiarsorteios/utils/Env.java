package br.gov.mg.fazenda.noticiarsorteios.utils;

public class Env {
	private static String env = "DES";

	public static String getEnv() {
		return env;
	}

	public static void setEnv(String env) {
		Env.env = env;
	}
}
