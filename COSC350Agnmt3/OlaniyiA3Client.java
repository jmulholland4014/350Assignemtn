import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class OlaniyiA3Client {
    Socket selfSocket;
    BufferedReader fromUser;
    DataOutputStream streamToServer;

    public static void main(String[] args) throws Exception {
        OlaniyiA3Client newClient = new OlaniyiA3Client(2121);
        Scanner scanMe = new Scanner(System.in);
        System.out.print("Enter file name: ");
        Scanner fileScan = scanFile(scanMe.nextLine());


        String dvrMessage = arrLstToString(populateArrLstFromFile(fileScan));
        newClient.sendDvrMessage(dvrMessage);
    }
    //TODO what about the server nodes?


    //Alternate, takes port number, InetAddress is set to localhost
    public OlaniyiA3Client(int portNum) throws Exception {
        selfSocket = new Socket("localhost",portNum);
    }

    void sendDvrMessage(String dvrMessage) throws IOException {
        fromUser = new BufferedReader(new InputStreamReader(System.in));
        streamToServer = new DataOutputStream(selfSocket.getOutputStream());

        streamToServer.writeBytes(dvrMessage + "\n");

        selfSocket.close();
    }

    static String arrLstToString(ArrayList<Integer> inArrayList) {
        String returnString = "";
        for(int i = 0; i < inArrayList.size(); i++) {
            returnString += inArrayList.get(i) + " ";
        }
        return returnString;
    }

    //Takes a file and returns an ArrayList
    static ArrayList<Integer> populateArrLstFromFile(Scanner fileScan) {
        ArrayList<Integer> returnArray = new ArrayList<>();
        while(fileScan.hasNext()) {
            if(fileScan.hasNextInt()) {
                returnArray.add(fileScan.nextInt());
            } else {
                fileScan.next();
            }
        }
        return returnArray;
    }

    //Creates scanner from provided file, if file exists
    static Scanner scanFile(String fileName) {
        Scanner fileScan = new Scanner(fileName);
        File sourceFile = new File(fileScan.nextLine());
        try {
            fileScan = new Scanner(sourceFile);
        } catch (FileNotFoundException e) {
            System.out.println("--File Not Found! Exit program!");
            System.exit(0);
        }
        return fileScan;
    }
}
