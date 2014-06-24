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

    public static void main(String args[])throws FileNotFoundException{
        try{
            System.out.println("Reading File");
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec("cmd /c fpcalc.exe Track09.mp3 > output.txt");

            System.out.println("Capture Fingerprint");
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
            String url = urlObject.createURL(duration, fingerprint);

            System.out.println(url);
            System.out.println("Sending request for information");
        }catch (Exception e){
            System.err.println("The process failed. The program will now exit.");
            System.exit(-1);
        }
    }
}
