/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.alvaroweb.udpsocket.server;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.bind.DatatypeConverter;

public class UdpSocketThread extends Thread {

    protected DatagramSocket socket = null;

    public UdpSocketThread() throws IOException {
	this("UDPseverThread");
    }

    public UdpSocketThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    @Override
    public void run() {
        System.out.println("on " + this.getName() + ", waiting for packets...");
        while (true) {
            try {
                byte[] buf = new byte[256];

                // receive packet
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet); // blocks until a packet is recieved


                System.out.println("recived " +
                        DatatypeConverter.printHexBinary(buf) + "on" + new Date() );

		// send the response to the client at "address" and "port"
//                InetAddress address = packet.getAddress();
//                int port = packet.getPort();
//                packet = new DatagramPacket(buf, buf.length, address, port);
//                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //socket.close();
    }
}