/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */


public class CreateURL {
    public String createURL(int duration, String fingerprint){
        String builtURL = String.format("http://api.acoustid.org/v2/lookup?client=8XaBELgH&meta=recordingids&duration=%d&fingerprint=%s", duration, fingerprint);
        return builtURL;
    }
}
