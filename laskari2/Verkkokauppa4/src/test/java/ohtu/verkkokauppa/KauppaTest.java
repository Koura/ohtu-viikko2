/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import static org.mockito.Mockito.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author koura
 */
public class KauppaTest {
    
    public KauppaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aloitaAsiointi method, of class Kauppa.
     */
    @Test
    public void testTilimaksu() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        Tuote palautettava = new Tuote(1, "Koff Portteri", 3);
        when(mockVarasto.saldo(5)).thenReturn(2);
        when(mockVarasto.haeTuote(5)).thenReturn(palautettava);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.tilimaksu("pekka", "1234");
        verify(mockPankki).tilisiirto(eq("pekka"), anyInt(), eq("1234"), eq("33333-44455"), eq(3));
    }
    @Test
    public void testTilimaksu2() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        when(mockVarasto.saldo(1)).thenReturn(4);
        when(mockVarasto.saldo(2)).thenReturn(5);
        when(mockVarasto.haeTuote(2)).thenReturn(new Tuote(1,"moo",5));
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(2,"momo",3));
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "1234");
        verify(mockPankki).tilisiirto(eq("pekka"), anyInt(), eq("1234"), anyString(), eq(8));
    }
    @Test
    public void testTilimaksu3() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        when(mockVarasto.saldo(1)).thenReturn(4);
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(2,"momo",3));
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "1234");
        verify(mockPankki).tilisiirto(eq("pekka"), anyInt(), eq("1234"), anyString(), eq(6));
    }
    @Test
    public void testTilimaksu4() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        when(mockVarasto.saldo(1)).thenReturn(4);
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(2,"momo",3));
        when(mockVarasto.saldo(2)).thenReturn(0);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "1234");
        verify(mockPankki).tilisiirto(eq("pekka"), anyInt(), eq("1234"), anyString(), eq(3));
    }
    @Test
    public void previousShopTest() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        when(mockVarasto.saldo(1)).thenReturn(4);
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(2,"momo",3));
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("Pekka", "1234");
        verify(mockPankki).tilisiirto(eq("Pekka"), anyInt(), eq("1234"), anyString(), eq(0));
    }
    @Test
    public void uusiViiteTest() {
         Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        when(mockViite.uusi()).thenReturn(5);
        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("Pekka", "1234");
        verify(mockPankki).tilisiirto(anyString(), eq(5), anyString(), anyString(), anyInt());
        when(mockViite.uusi()).thenReturn(3);
        kauppa.tilimaksu("","");
        verify(mockPankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());
    }
    @Test
    public void palautusToimiiTest() {
         Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);
        Kauppa kauppa = new Kauppa(mockVarasto,mockPankki,mockViite);
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(1,"momo",4));
        when(mockVarasto.saldo(1)).thenReturn(2);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka", "1234");
        verify(mockPankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));
 
    }
}
