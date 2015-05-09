package lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCServerOffer {

//	Modify MCReader.java so that it finds the name of the machine it is running on and returns it to the client 
//	Use the InetAddress class to determine the local address. 
//	Use the methods getLocalHost() and getHostName() or getHostAddress() to print it.
//	Create a DatagramSocket and DatagramPacket to send back the machine name to the client.
	public static void main(String[] args) {
		try {
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
			String response = ("I am: " + myMachine.toString() + " And my Name is: " + myHostName.toString());
			System.out.println(response);
			buf = new byte[65536];
			buf = response.getBytes();
			int port = dp.getPort();
		   
		    DatagramSocket socket = new DatagramSocket();
		    DatagramPacket offer = new DatagramPacket(buf, buf.length, clientMachine, port);
		    socket.send(offer);
		    socket.close();
		    
		    }
		} catch(IOException e) {
		    System.out.println("Exception:"+e);
		}

	}

}
