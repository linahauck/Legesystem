public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {
    
    @Override
    public void leggTil(T x){
        //sette inn elementer i sortert rekkefølge
        //Minst først, størst sist
        Node nyNode = new Node(x);
        
        //sjekker om liste er tom
        if (forste==null) {
            forste = nyNode;
            siste = nyNode;
        }

        //sjekker om x <= første: true => legger x først i lista
        else if(nyNode.hentData().compareTo(forste.hentData())<=0){
            forste.settForrige(nyNode);
            nyNode.settNeste(forste);
            forste = nyNode;
        }
        

        else{
            
            Node peker = forste;

            
            //går gjennom lista fram til vi finner riktig plassering
            while(peker.hentNeste()!=null &&
                x.compareTo(peker.hentNeste().hentData())>0) {
                    peker = peker.hentNeste();
                } //stopper når vi er på slutten eller x <= neste data 

            if (peker.hentNeste() == null) {
                siste.settNeste(nyNode);
                nyNode.settForrige(siste);
                siste = nyNode;
            }
            else{
                nyNode.settNeste(peker.hentNeste());
                peker.settNeste(nyNode);
                nyNode.settForrige(peker);
            }
            
        }
        stoerrelse++;
    }

}
