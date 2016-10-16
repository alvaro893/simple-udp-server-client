/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.alvaroweb.udpsocket.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author Alvaro
 */
public class FakeClient {
    int count = 0;
    public static void main(String args[]) throws Exception{
        FakeClient client = new FakeClient();
        
        DatagramSocket socket = new DatagramSocket();
        
        while(true){
            client.sendFrame(socket, client); 
        }
//            // get response (maybe is a good idea to use thread)
//        packet = new DatagramPacket(buf, buf.length);
//        socket.receive(packet);
// 
//        // display response
//        String received = new String(packet.getData(), 0, packet.getLength());
//        System.out.println(received);
//     
//        socket.close();
    }
    
    byte[] createRandomFrame(){
        // -1 is FF on Java
        byte[] frame = new byte[256];
        frame[0] = -1;
        frame[1] = -1;
        frame[2] = -1;
        frame[3] = (byte) count; //sending a count byte
        for(int i = 4; i < frame.length; i++){
            frame[i] = (byte) randombyte(-127, -2);
        }
        count++;
        return frame;
    }
    
    int randombyte(int min, int max){
        int r = (int) (Math.random() * ((max - min) + 1));
        return min + r;
    }
    
    void sendFrame(DatagramSocket socket, FakeClient client) throws Exception{
            byte[] buff = client.createRandomFrame();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buff, buff.length, address, 4445);
            socket.send(packet);
            Thread.sleep(250); // simulate some delay
            System.out.println("sent " + DatatypeConverter.printHexBinary(buff));
    }
}
