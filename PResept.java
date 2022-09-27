public class PResept extends HvitResept {
    
    PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient,int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale(){
        //rabatt: -108kr
        int pris = legemiddel.hentPris();
        int rabatt = 108;
        pris -= rabatt;
        //merk: aldri lavere enn 0 kr
        if (pris<0){
            return 0;
        }
        return pris;
    }

    @Override
    String reseptNavn(){
        return "HVIT RESEPT: P-RESEPT";
    }

    @Override
    String skrivType(){
        return "p";
    }
}
