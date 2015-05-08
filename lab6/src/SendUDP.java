

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUDP {
	public static void main(String args[]) throws Exception{     
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[64];
		byte[] receiveData = new byte[64];
		String sentence=args[2];
		
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 30000);       
		clientSocket.send(sendPacket);
		
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		clientSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();    
	}
}
