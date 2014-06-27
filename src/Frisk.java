/**
 * Created with IntelliJ IDEA.
 * User: Sriram Ravindran
 * Date: 6/24/14
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Frisk {
    private String fingerprint;
    private int duration;

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        try{
            String command = "cmd /c fpcalc.exe song.mp3 > temp/output.txt";
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
        File file = new File("temp/output.txt");
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

            int failure = 0;
            CreateURL urlObject = new CreateURL();
            String url = urlObject.createURL(1, duration, fingerprint);
           // System.out.println(fingerprint);


            /*  Send the GET request to the Server, and read the ID's from the response */
            String trackAcousticID = AcoustID.getID(url);
           // System.out.println(trackAcousticID);
            if(trackAcousticID.equals("")) failure = 1;

            /*  GET request to the page containing the data */
            TrackInfo info = null;
            if(failure == 0){
                url = urlObject.createURL(2, 0, trackAcousticID);
                info = new TrackInfo().getResults(url);
            }

            scanner.close();
            Files.delete(Paths.get("temp/output.txt"));
            PrintStream fw = new PrintStream("result.txt");

            if(failure == 1){
                fw.println("Sorry, that song is not in our database.");
                System.exit(-1);
            }

            String track = String.format("Title: " + info.getTrackName());
            String artist = String.format("Artist: " + info.getTrackArtist());
            String length = String.format("Length: " + info.getTrackLength());

            System.out.println(track);
            System.out.println(artist);
            System.out.println(length);

            fw.println(track);
            fw.println(artist);
            fw.println(length);

            fw.close();

        }catch (Exception e){
            System.err.println("The process failed. The program will now exit.");
            System.out.println("Please check your internet connection or try later.");
            e.printStackTrace();
            System.exit(-1);
        }finally {
            scanner.close();
        }
    }
}
