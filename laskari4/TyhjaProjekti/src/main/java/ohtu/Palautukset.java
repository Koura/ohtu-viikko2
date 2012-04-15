
package ohtu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Palautukset {
    List<Palautus> palautukset = new ArrayList<Palautus>();

    public void setPalautukset(List<Palautus> palautukset) {
        this.palautukset = palautukset;
    }

    public List<Palautus> getPalautukset() {
        return palautukset;
    }
    
    public int[] yhteistunnit() {
        int[] temp = {0,0};
        for (Iterator<Palautus> it = palautukset.iterator(); it.hasNext();) {
            Palautus palautus = it.next();
            temp[0] += palautus.getTehtavia();
            temp[1] += palautus.getTunteja();
        }
        return temp;
    }
}
