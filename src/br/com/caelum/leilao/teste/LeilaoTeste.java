package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTeste {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Mackbook pro 18");
		
		assertEquals(0, leilao.getLances().size());
		leilao.propoe(new Lance(new Usuario("Steve macaco"), 2000.0));
		
		assertEquals(1,  leilao.getLances().size());
		assertEquals(2000.0,  leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Mackbook pro 18");
		leilao.propoe(new Lance(new Usuario("Steve macaco"), 2000.0));
		leilao.propoe(new Lance(new Usuario("Cajureba"), 3000.0));
		
		assertEquals(2, leilao.getLances().size(), 0.00001);
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Marrua de corte");
		Usuario usuario = new Usuario("Jaozim mocoto");
		
		leilao.propoe(new Lance(usuario, 2000.0));
		leilao.propoe(new Lance(usuario, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Marrua de corte");
		Usuario sjobs = new Usuario("Jaozim mocoto");
		Usuario bgats = new Usuario("Mc BGates");
		
		leilao.propoe(new Lance(sjobs, 2000.0));
		leilao.propoe(new Lance(bgats, 3000.0));
		
		leilao.propoe(new Lance(sjobs, 4000.0));
		leilao.propoe(new Lance(bgats, 5000.0));
		
		leilao.propoe(new Lance(sjobs, 6000.0));
		leilao.propoe(new Lance(bgats, 7000.0));
		
		leilao.propoe(new Lance(sjobs, 8000.0));
		leilao.propoe(new Lance(bgats, 9000.0));
		
		leilao.propoe(new Lance(sjobs, 10000.0));
		leilao.propoe(new Lance(bgats, 11000.0));
		
		leilao.propoe(new Lance(sjobs, 12000.0));
		leilao.propoe(new Lance(bgats, 13000.0));
		
		leilao.propoe(new Lance(sjobs, 11000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
		
	}
}
