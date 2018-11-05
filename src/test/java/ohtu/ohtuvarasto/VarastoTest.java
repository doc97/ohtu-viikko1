package ohtu.ohtuvarasto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VarastoTest {

    Varasto varasto;
    final double vertailuTarkkuus = 0.0001;
    final double alkuTilavuus = 10;

    @Before
    public void setUp() {
        varasto = new Varasto(alkuTilavuus);
    }

    @Test
    public void konstruktoriMinimiTilavuusNolla() {
        Varasto v = new Varasto(0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        v = new Varasto(-1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheellinenNollataan() {
        Varasto v = new Varasto(-1, -1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriOikeatParametrit() {
        final double tilavuus = 5;
        final double saldo = 4;
        Varasto v = new Varasto(tilavuus, saldo);
        assertEquals(tilavuus, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(saldo, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriSaldoRajoitettu() {
        final double tilavuus = 4;
        final double saldo = 5;
        Varasto v = new Varasto(tilavuus, saldo);
        assertEquals(tilavuus, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(tilavuus, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(alkuTilavuus, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        final double lisays = 8;
        varasto.lisaaVarastoon(lisays);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(lisays, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenLisaysSailyttaaSaldoa() {
        final double negatiivinenLisays = -2;
        varasto.lisaaVarastoon(negatiivinenLisays);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysKasitteleeYlivuodon() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        final double lisays = 8;
        varasto.lisaaVarastoon(lisays);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        final double lisays = 8;
        varasto.lisaaVarastoon(lisays);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void virheellinenOttaminenSailyttaaSaldoa() {
        final double lisays = 8;
        final double otos = -2;
        varasto.lisaaVarastoon(lisays);
        double saatuMaara = varasto.otaVarastosta(otos);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
        assertEquals(lisays, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLiianPaljonTyhjentaaVaraston() {
        final double lisays = 8;
        final double otos = 12;
        varasto.lisaaVarastoon(lisays);
        double saatuMaara = varasto.otaVarastosta(otos);
        assertEquals(lisays, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        final double lisays = 8;
        varasto.lisaaVarastoon(lisays);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        final double odotettuArvo = 4;
        assertEquals(odotettuArvo, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringEsittaaSaldoaJaTilaaJaljella() {
        final double lisays = 7;
        varasto.lisaaVarastoon(lisays);
        assertEquals("saldo = 7.0, vielä tilaa 3.0", varasto.toString());
    }

}
