package lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServerUDP {
	DatagramSocket socket;
	DatagramPacket packet;
	//hejsan!
	public TimeServerUDP(){}
	public void start(){
		initialize();
		String command;
		String result;		
	
		while(true){
			command = receive();
			switch (command.trim()) {
		    	case "time":
		    		result = doCommandA();
		    		break;
			    case "date":
			    	result = doCommandB();
			    	break;
			    default:
			    	result ="Invalid Command";
			    	break;
			}
		    	send(result);
		}
	}	
	
	private void send(String result) {	
		try {
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			byte[] buf = result.getBytes();
			packet = new DatagramPacket(buf, buf.length, address, port);
			socket.send(packet);
		} catch (IOException e) {
		}	
	}
	
	private String doCommandB() {
		String date;
	    DateFormat formato = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
	    date= formato.format(new Date());
	    return date;
	}
	
	private String doCommandA() {
		String date;
	    DateFormat formato = new SimpleDateFormat("hh:mm:ss a zzz", new Locale("es", "ES"));
	    date= formato.format(new Date());
	    return date;	}
	
	private String receive() {
		try {
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			String command = new String(packet.getData());
			return command;
		} catch (IOException e) {
			System.out.println("receive()");
		}
		return null;
	}
	
	private String initialize() {
		try {
			socket = new DatagramSocket(30000);
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
		}	catch (SocketException e) {
			System.out.println("initialize()");
		}
		return null;
	}
	
	public static void main(String args[]) throws Exception{       
		TimeServerUDP server = new TimeServerUDP();
		server.start();
	}
}

