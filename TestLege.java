public class TestLege {
    public static boolean testID(Lege lege, String forventet) {
        return (lege.hentKontrollID()==forventet);
    }

    public static void main(String[] args) {
        //lege:
        Lege haug = new Lege("Haug");
        Lege johansen = new Spesialist("Johansen", "K32");

        //test:
        System.out.println(testID(haug, "ingen"));
        System.out.println(testID(johansen, "K32"));
    }
}
