/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */


public class CreateURL {
    public String createURL(int type, int duration, String fingerprint){    // alias acousticID
        String builtURL = "";
        switch(type){
            case 1:
                builtURL =  String.format("http://api.acoustid.org/v2/lookup?client=8XaBELgH&meta=recordingids&duration=%d&fingerprint=%s", duration, fingerprint);
                break;
            case 2:
                builtURL = String.format("http://acoustid.org/track/%s", fingerprint);
            default:
                // Need to throw TypeNotFoundException
        }

        return builtURL;
    }
}
