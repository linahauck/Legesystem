public class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollID;

    
    Spesialist(String navn, String kontrollID){
        super(navn);
        this.kontrollID=kontrollID;
    }
    

    @Override
    public String hentKontrollID(){
        return kontrollID;
    }
    

    @Override
    public String toString(){
        return "Spesialist: "+hentNavn()+", Kontroll ID: "+hentKontrollID();
    }


    @Override
    BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(blaaResept);
        return blaaResept;
    } 
    
}
