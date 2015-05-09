package lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class MultiCastServerThread extends Thread{
	public void run(){
			try {
				while(true){
			    MulticastSocket ms = new MulticastSocket(4099);
			    InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			    ms.joinGroup(ia);
			    while(true){
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf,buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(),0,dp.getLength());
				System.out.println("Received: "+s);
				
				InetAddress myMachine = InetAddress.getLocalHost();
				InetAddress clientMachine = dp.getAddress();
				String myHostName = myMachine.getHostName();
				String response = (myHostName.toString());
				System.out.println(response);
				buf = response.getBytes();
				int port = dp.getPort();
			   
			    DatagramSocket socket = new DatagramSocket();
			    DatagramPacket offer = new DatagramPacket(buf, buf.length, clientMachine, port);
			    socket.send(offer);
			    socket.close();
			    }	   
			    }
			} catch(IOException e) {
			    System.out.println("Exception:"+e);
			}
		}
	}
	


