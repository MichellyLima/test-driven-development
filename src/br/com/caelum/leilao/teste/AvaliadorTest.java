package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvaliadorTest {
    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        //parte 1: cenário
        Usuario usopp = new Usuario("Usopp");
        Usuario franky = new Usuario("Franky");
        Usuario robin = new Usuario("Robin");


        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(usopp, 250.0));
        leilao.propoe(new Lance(franky, 300.0));
        leilao.propoe(new Lance(robin, 400.0));

        //parte 2: ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //parte 3: validação
        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){
        Usuario robin = new Usuario("Robin");
        Leilao leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(robin, 1000));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario robin = new Usuario("Robin");
        Usuario usopp = new Usuario("Usopp");
        Leilao leilao = new Leilao("Playstation 4 Novo");

        leilao.propoe(new Lance(robin, 100.0));
        leilao.propoe(new Lance(usopp, 200.0));
        leilao.propoe(new Lance(robin, 300.0));
        leilao.propoe(new Lance(usopp, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }

}
