import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class OlaniyiA3Server {
    Thread serverThread;
    ServerSocket selfSocket;
    Socket connectionSocket;
    BufferedReader clientReader;
    String clientMessage;
    String[] clientMessageArray;
    //Default, port 0 requests system-allocated (dynamic) port
    public OlaniyiA3Server() throws Exception {
        selfSocket = new ServerSocket(0);
    }

    //Alternate, takes port number
    public OlaniyiA3Server(int portNum) throws Exception {
        selfSocket = new ServerSocket(portNum);
    }
    
    int[] stringToArray(String inString) {
        String[] strArr = inString.split(" ");
        int[] returnArray = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++) {
            returnArray[i] = Integer.parseInt(strArr[i]);
        }
        return returnArray;
    }


    //Receives a txt file
    public void run() throws Exception{
        while(true) {
            connectionSocket = selfSocket.accept();
            clientReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientMessage = clientReader.readLine();
            int[] messageArray = stringToArray(clientMessage);
        }
    }
    public static void main(String[] args) throws Exception{
        OlaniyiA3Server newServer = new OlaniyiA3Server(2121);

        newServer.run();
    }
}
