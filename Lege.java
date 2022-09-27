import java.lang.Comparable;

public class Lege implements Comparable<Lege> {
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();


    Lege(String navn){
        this.navn=navn;
    }


    String hentNavn(){
        return navn;
    }


    String hentKontrollID(){
        return "0";
    }


    @Override
    public String toString(){
        return "Lege: "+hentNavn()+", Kontroll ID: Har ikke Godkjenning";
    }


    @Override
    public int compareTo(Lege listeLege){
        //sorterer leger i alfabetisk rekkefølge
        String nyLegeNavn =  navn.split(" ")[1];
        String listeLegeNavn = listeLege.navn.split(" ")[1];
        String[] delt = nyLegeNavn.split("");
        String[] deltListeLege = listeLegeNavn.split("");

        for (int i = 0; i < delt.length; i++){
            if (delt[i] != deltListeLege[i]){
                return delt[i].compareTo(deltListeLege[i]);
            }
            i++;
        }
        //TODO: sjekk om tid.
        return 0;
    }


    public IndeksertListe<Resept> hentResepter(){
        //henter ut liste med resepter
        return utskrevneResepter;
    }


    HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }

        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvitResept);
        pasient.leggTilResept(hvitResept);
        return hvitResept;
    }
    

    MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }

        MilResept milResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milResept);
        pasient.leggTilResept(milResept);
        return milResept;
    }


    PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }

        PResept pResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(pResept);
        pasient.leggTilResept(pResept);
        return pResept;
    }


    BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }

        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(blaaResept);
        pasient.leggTilResept(blaaResept);
        return blaaResept;
    } 

}
