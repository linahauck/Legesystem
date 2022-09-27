public class HvitResept extends Resept {
    
    HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    @Override
    public String farge(){
        return "hvit";
    }

    @Override
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }

    @Override
    String reseptNavn() {
        return "HVIT RESEPT";
    }

    @Override
    String skrivType(){
        return "hvit";
    }

}
