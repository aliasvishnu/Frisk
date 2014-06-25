/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */


import java.io.*;
import java.util.Scanner;

public class Frisk {
    private String fingerprint;
    private int duration;

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        try{
            String command = "cmd /c fpcalc.exe Track01.mp3 > output.txt";
            Runtime runTime = Runtime.getRuntime();

            Process process = runTime.exec(command);
            process.waitFor();
            process.destroy();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        Frisk obj = new Frisk();
        obj.getData(obj);
    }

    public void getData(Frisk obj)throws FileNotFoundException{
        File file = new File("output.txt");
        FileReader fr = new FileReader(file);
        Scanner scanner = new Scanner(fr);
        scanner.useDelimiter("%n");

        try{
            int count = 0;
            while(scanner.hasNext()){
                String songInfo = scanner.nextLine();
                if(count == 1){
                    obj.duration = Integer.parseInt(songInfo.substring(9));
                }
                if(count == 2){
                    obj.fingerprint = songInfo.substring(12);
                }
                count++;
            }

            CreateURL urlObject = new CreateURL();
            String url = urlObject.createURL(1, duration, fingerprint);

            /*  Send the GET request to the Server, and read the ID's from the response */
            String trackAcousticID = AcoustID.getID(url);

            /*  GET request to the page containing the data */
            url = urlObject.createURL(2, 0, trackAcousticID);
            TrackInfo info = new TrackInfo().getResults(url);

            System.out.println("Title: " + info.getTrackName());
            System.out.println("Artist: " + info.getTrackArtist());
            System.out.println("Length: " + info.getTrackLength());

        }catch (Exception e){
            System.err.println("The process failed. The program will now exit.");
            System.out.println("Please check your internet connection or try later.");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
