package ohtu.verkkokauppa;

public class Kauppa {

    private Ware varasto;
    private Bank pankki;
    private Ostoskori ostoskori;
    private Viitteet viitegeneraattori;
    private String kaupanTili;

    public Kauppa(Ware varastoh, Bank pankkih, Viitteet viitegeneraattorih) {
        varasto = varastoh;
        pankki = pankkih;
        viitegeneraattori = viitegeneraattorih;
        kaupanTili = "33333-44455";
    }

    public Kauppa(Varasto instance, Pankki instance0, Viitegeneraattori instance1) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
