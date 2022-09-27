public class BlaaResept extends Resept {
    //rabatt: betaler 25% av prisen: rundes av til nærmeste heltall int
    BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge(){
        return "blaa";
    }

    public int prisAaBetale(){
        float pris = legemiddel.hentPris();
        double rabatt = 0.25;
        pris *=rabatt;

        return Math.round(pris);
    }

    @Override
    String reseptNavn(){
        return "BLAA RESEPT";
    }

    @Override
    String skrivType(){
        return "blaa";
    }
}
