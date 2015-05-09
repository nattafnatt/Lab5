import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendUDP2 {
	public static void main(String args[]) throws Exception{ 
		System.out.println("Welcome!");
		String hostName = discoverServer();
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName(hostName);
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence;		
		sentence=args[1];
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,Integer.parseInt(args[0]) );       
		clientSocket.send(sendPacket);    
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		clientSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();    
	}

	private static String discoverServer() {
		try {
		    System.out.println("Searching for servers...");
		    MulticastSocket ms = new MulticastSocket();
		    ms.setTimeToLive(1);
		    InetAddress ia = InetAddress.getByName("experiment.mcast.net");
		    
		    String s = "Find";
			byte[] buf = s.getBytes();
			DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
			ms.send(dp);

			buf = new byte[655];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			ms.receive(packet);
			String hostName = new String(packet.getData(),0,packet.getLength());
			System.out.println("Server found is: " + hostName);			
		    return hostName;
		   
		} catch(IOException e) {
		    System.out.println("Exception:"+e);
		}
		return null;
	}
}
