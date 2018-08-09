package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import junit.framework.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TesteAvaliador {

	private Avaliador leiloeiro;
	@Before //executa o metodo antes dos testes
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		
		/*
		 * em testes de exception se pode passar retorno
		 * no @Test(expected=) a classe que se espera
		 * */
		
		//try {
			Leilao leilao = new CriadorDeLeilao().para("sapiaca").constroi();
			leiloeiro.avalia(leilao);
			Assert.fail();
		
//		} catch(RuntimeException e) {
//			
//		}
	}
	
	@Test
	public void deveEntenderlancesEmOrdemCrescente() {
		
		Usuario joao = new Usuario("Jaum");
		Usuario xico = new Usuario("xico");
		Usuario sandroca = new Usuario("sandroca");
		
		Leilao leilao = new Leilao("Lixos dos bombeiros");
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(xico, 990));
		leilao.propoe(new Lance(sandroca, 666));
		
		Leilao leilao2 = new CriadorDeLeilao().para("Playstation novo")
				.lance(sandroca, 300.0)
				.lance(xico, 990.0)
				.lance(joao, 666.0)
				.constroi();
		
		
		//Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao2);
		
		double maiorEsperado = 990;
		double menorEsperado = 300;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		//assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		//outra forma de dar uma melhorada no teste, deixar mais legivel
		assertThat(leiloeiro.getMenorLance(), equalTo(300.0));
		

	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario jaum = new Usuario("Jaum");
		
		Leilao leilao = new Leilao("leilao de uma vaca magra");
		
		leilao.propoe(new Lance(jaum, 1000.0));
		
		//Avaliador leiloeiro = new Avaliador();		
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test 
	public void deveEncotrarOsTresMaioresLances() {
		Usuario jaum = new Usuario("jaum");
		Usuario jaum1 = new Usuario("jaum1");

		
		Leilao leilao = new Leilao("Leilao de uma egua");
		
		leilao.propoe(new Lance(jaum, 100.0));
		leilao.propoe(new Lance(jaum1, 200.0));
		leilao.propoe(new Lance(jaum, 300.0));
		leilao.propoe(new Lance(jaum1, 400.0));
		
		//Avaliador leiloreiro = new Avaliador();		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		
		assertEquals(3, maiores.size());
		
//		assertThat(maiores, hasItems(
//				new Lance(jaum, 100.0),
//				new Lance(jaum1, 200.0),
//				new Lance(jaum, 300.0),
//				new Lance(jaum1, 400.0)
//				));
//		
	}
}
