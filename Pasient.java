
public class Pasient {
    private String navn;
    private String foedselsnummer;
    private int iD;
    private static int teller = 0;
    private IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    Pasient(String navn, String foedselsnummer){
        this.navn=navn;
        this.foedselsnummer=foedselsnummer;
        teller++;
        iD = teller;
        
    }

    int hentId(){
        return iD;
    }

    String hentNavn(){
        return navn;
    }

    String hentFoedselsnummer(){
        return foedselsnummer;
    }

    void leggTilResept(Resept resept){
        utskrevneResepter.leggTil(resept);
    }
//------Skriv inn pga del E2
    IndeksertListe<Resept> hentReseptListe(){
        return utskrevneResepter;
    }

    @Override
    public String toString(){
        return ""+navn;
    }




    
}
