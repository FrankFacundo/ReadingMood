package raspberry;
import com.google.gson.*;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.*;

import com.sun.net.ssl.internal.www.protocol.https.Handler;



public class RaspberryPi {

    private String IP;
    private String port;
    private String Ambiance;
    private Socket s;
    private boolean connected;
    private PrintWriter output;
    private BufferedReader input;
    private String response;
    private Config config;



    public RaspberryPi() {

        FileReader file = null;
        String row = null;
        try {
            String path = "/home/clement/Documents/Cours/EcoleInge/PACT/Git/pact35/modules/TestIntegration/src/JavaTest/src/raspberry/config.json";
            file = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(file);
            row = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        this.config = gson.fromJson(row, Config.class);
        this.s = new Socket();
        this.IP = config.IP;
        this.port = config.port;
        this.Ambiance = "";
        this.response = "no_response";
        this.connected = false;
    }


    public Config getConfig() {
        return config;
    }

    public String getAmbiance() {
        return Ambiance;
    }

    private String getIP(){
        return IP;
    }

    private String getPort(){

        return port;
    }

    public boolean connect(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s = new Socket(IP, Integer.parseInt(port));
                    connected = true;
                    s.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    connected = false;
                }
            }
        });

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connected;
    }

    public boolean isConnected(){
        connected = connect();
        return connected;
    }

    public String display (String ambiance_son){

        final String ambiance_ = ambiance_son;
        final Handler handler = new Handler();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        s = new Socket(IP, Integer.parseInt(port));
                        output = new PrintWriter(s.getOutputStream());

                        output.println(ambiance_);
                        output.flush();

                        input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        response = input.readLine();

                        output.close();
                        s.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


            }
        });

        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String setAgriculturalField(){
        return display("AgriculturalField");
    }
    public String setChristmas(){
        return display("Christmas");
    }
    public String setCook(){
        return display("Cook");
    }
    public String setForest(){
        return display("Forest");
    }
    public String setFloralGarden(){
        return display("FloralGarden");
    }
    public String setOcean(){
        return display("Ocean");
    }

    public static byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1)
                bos.write(buf, 0, len);
            buf = bos.toByteArray();
        }
        return buf;
    }


}
