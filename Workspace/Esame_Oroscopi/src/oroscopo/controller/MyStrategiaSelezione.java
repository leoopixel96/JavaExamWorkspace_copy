package oroscopo.controller;

import java.util.List;
import java.util.Random;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class MyStrategiaSelezione implements StrategiaSelezione {

	@Override
	public Previsione seleziona(List<Previsione> previsioni, SegnoZodiacale segno) {
		Random casual = new Random();
		boolean trovato = false;
		while(!trovato){
			int num = casual.nextInt(previsioni.size());
			Previsione result = previsioni.get(num);
			if(result.validaPerSegno(segno))
				return result;
		}
		return null;
	}
	
}
