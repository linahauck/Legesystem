public class MilResept extends HvitResept {
    
    MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 0); 
        //0 fordi vi ikke vil hente reit fra super
        reit = 3; //alltid 3 reit
    }

    @Override
    public int prisAaBetale(){
        //100% rabbattert pris!!
        return 0;
    }

    @Override
    String reseptNavn(){
        return "HVIT RESEPT: MILITAERRESEPT";
    }

    @Override
    String skrivType(){
        return "militaer";
    }
    
}
