public class Stabel<T> extends Lenkeliste<T> {
    //siste inn er første ut
    
    @Override
    public void leggTil(T x){
        //Nye elementer legges inn FØRST i listen
        stoerrelse++;
        Node nyNode = new Node(x);
        if (forste==null){ //tom liste
            forste = nyNode;
            siste = nyNode;
            return;
        }
        forste.settForrige(nyNode);
        nyNode.settNeste(forste);
        forste = nyNode;
    }
}
