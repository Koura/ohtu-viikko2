package ohtu;

import java.io.IOException;
import java.io.InputStream;
import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "13862031";
        if (args.length > 0) {
            studentNr = args[0];
        }
        String url = "http://ohtustats.herokuapp.com/opiskelija/" + studentNr + ".json";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream = method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        System.out.println("Tommi Jalkanen opiskelijanumero: " + studentNr);
        System.out.println("\nminiprojekti: ohtu2012\n");
        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);

        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.println(palautus);
        }
        int[] temp = palautukset.yhteistunnit();
        System.out.println("\nyhteensä " + temp[0] + " tehtävää " + temp[1] + " tuntia");
    }
}
