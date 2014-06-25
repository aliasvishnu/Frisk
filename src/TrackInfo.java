import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 9:35 PM
 */
public class TrackInfo {
    private String trackName;
    private String trackArtist;
    private String trackLength;

    public void setTrackName(String trackName){
        this.trackName = trackName;
    }

    public void  setTrackArtist(String trackArtist){
        this.trackArtist = trackArtist;
    }

    public void setTrackLength(String trackLength){
        this.trackLength = trackLength;
    }

    public String getTrackName(){
        return trackName;
    }

    public String getTrackArtist(){
        return trackArtist;
    }

    public String getTrackLength(){
        return trackLength;
    }

    public TrackInfo getResults(String urlString)throws Exception{

        org.jsoup.nodes.Document doc = Jsoup.connect(urlString).get();
        Elements tables =  doc.getElementsByTag("table");
        Elements tdElements = tables.last().getElementsByTag("td");

        // Extract information from the td's
        String trackName = tdElements.first().getElementsByTag("a").first().html();
        String trackArtist = tdElements.get(1).html();
        String trackLength = tdElements.get(2).html();

        setTrackName(trackName);
        setTrackArtist(trackArtist);
        setTrackLength(trackLength);

        return this;
    }
}


