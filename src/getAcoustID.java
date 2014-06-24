import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class getAcoustID {
    private String url;

    getAcoustID(String url){
        this.url =  url;
    }


    public static void main(String[] args) throws Exception {
        URL yahoo = new URL("http://www.yahoo.com/");
//      URL yahoo = new URL(url);

        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) System.out.println(inputLine);
        in.close();
    }
}
