import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcoustID {
    public static String extractID(int loc, String resource){
        return resource.substring(loc + 7, loc + 7 + 36 );
    }

    public static String getID(String urlString) throws Exception {
        URL url = new URL(urlString);

        URLConnection urlConnection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        String result = "";

        while ((inputLine = in.readLine()) != null){
            result = result.concat(inputLine);
        }
        in.close();

        String pattern = ".id.: ..{36}.";
        Pattern patternString = Pattern.compile(pattern);
        Matcher match = patternString.matcher(result);
        String lastMatch = "";

        while(match.find()){
            lastMatch = extractID(match.start(), result);
        }

        return lastMatch;
    }
}
