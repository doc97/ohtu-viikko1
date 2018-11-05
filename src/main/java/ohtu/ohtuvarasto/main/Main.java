package ohtu.ohtuvarasto.main;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    private static void printPostSetup(Varasto mehua, Varasto olutta) {
        System.out.println("Luonnin jälkeen:");
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);

    }

    private static void printOlutGetterit(Varasto olutta) {
        System.out.println("Olutgetterit:");
        System.out.println("getSaldo()     = " + olutta.getSaldo());
        System.out.println("getTilavuus    = " + olutta.getTilavuus());
        System.out.println("paljonkoMahtuu = " + olutta.paljonkoMahtuu());
    }

    private static void printMehuSetterit(Varasto mehua) {
        System.out.println("Mehusetterit:");
        System.out.println("Lisätään 50.7");
        final double lisays = 50.7;
        mehua.lisaaVarastoon(lisays);
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Otetaan 3.14");
        final double otos = 3.14;
        mehua.otaVarastosta(otos);
        System.out.println("Mehuvarasto: " + mehua);
    }

    private static void printVirhetilanteet() {
        System.out.println("Virhetilanteita:");
        System.out.println("new Varasto(-100.0);");
        final double huonoTilavuus = -100.0;
        Varasto huono = new Varasto(huonoTilavuus);
        System.out.println(huono);

        System.out.println("new Varasto(100.0, -50.7)");
        final double hyvaTilavuus = 100.0;
        final double huonoSaldo = -50.7;
        huono = new Varasto(hyvaTilavuus, huonoSaldo);
        System.out.println(huono);
    }

    private static void lisaaVarastoihin(Varasto olutta, Varasto mehua) {
        System.out.println("Olutvarasto: " + olutta);
        System.out.println("olutta.lisaaVarastoon(1000.0)");
        final double lisays = 1000.0;
        olutta.lisaaVarastoon(lisays);
        System.out.println("Olutvarasto: " + olutta);

        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("mehua.lisaaVarastoon(-666.0)");
        final double huonoLisays = -666.0;
        mehua.lisaaVarastoon(huonoLisays);
        System.out.println("Mehuvarasto: " + mehua);
    }

    private static void otaVarastoista(Varasto olutta, Varasto mehua) {
        System.out.println("Olutvarasto: " + olutta);
        System.out.println("olutta.otaVarastosta(1000.0)");
        final double olutOtos = 1000.0;
        double saatiin = olutta.otaVarastosta(olutOtos);
        System.out.println("saatiin " + saatiin);
        System.out.println("Olutvarasto: " + olutta);

        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("mehua.otaVarastosta(-32.9)");
        final double mehuOtos = -32.9;
        saatiin = mehua.otaVarastosta(mehuOtos);
        System.out.println("saatiin " + saatiin);
        System.out.println("Mehuvarasto: " + mehua);
    }

    public static void main(String[] args) {
        final double tilavuus = 100.0;
        final double olutSaldo = 20.2;
        Varasto mehua = new Varasto(tilavuus);
        Varasto olutta = new Varasto(tilavuus, olutSaldo);

        printPostSetup(mehua, olutta);
        printOlutGetterit(olutta);
        printMehuSetterit(mehua);
        printVirhetilanteet();
        lisaaVarastoihin(olutta, mehua);
        otaVarastoista(olutta, mehua);
    }
}
