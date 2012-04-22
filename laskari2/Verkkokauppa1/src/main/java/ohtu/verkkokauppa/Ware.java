/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author koura
 */
public interface Ware {
    Tuote haeTuote(int id);
    int saldo(int id);
    void palautaVarastoon(Tuote t);
    void otaVarastosta(Tuote t);
}
