package ohtu.ohtuvarasto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void tarkoituksellaEiToimi() {
        fail("Tarkoitus on ettei tämä testi mene läpi!");
    }

    @Test
    public void konstruktoriMinimiTilavuusNolla() {
        Varasto v = new Varasto(0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        v = new Varasto(-2);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheellinenNollataan() {
        Varasto v = new Varasto(-2, -2);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriOikeatParametrit() {
        Varasto v = new Varasto(5, 4);
        assertEquals(5, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(4, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriSaldoRajoitettu() {
        Varasto v = new Varasto(4, 5);
        assertEquals(4, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(4, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenLisaysSailyttaaSaldoa() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysKasitteleeYlivuodon() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void virheellinenOttaminenSailyttaaSaldoa() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-2);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLiianPaljonTyhjentaaVaraston() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(12);
        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringEsittaaSaldoaJaTilaaJaljella() {
        varasto.lisaaVarastoon(7);
        assertEquals("saldo = 7.0, vielä tilaa 3.0", varasto.toString());
    }

}
