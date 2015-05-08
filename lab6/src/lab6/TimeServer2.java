package lab6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

//Instead of passing the request as an argument, 
//write a second program with no argument (TimeServer2.java) 
//where the main loop reads inputs (receives requests) 
//and writes outputs (sends a response). 
//Use the int read() method of the System.in object to read the input.

public class TimeServer2 {
	 Scanner sc = new Scanner(System.in);

	public TimeServer2(){
	}
	
	public void startListening(){
	String result;
	String command;
	while(true){
		result = null;
		command = null;
		command = receive();
         switch (command) {
         case "time":
           result = doCommandA();
           break;
         case "date":
           result = doCommandB();
           break;
         default:
         }
         send(result);
		}
	}

	private String receive() {
		 String s = sc.next();
		 return s;
	}
	private void send(String result) {
		if (result == null){
			System.out.println("No Such Command");
		}
		else{
		 System.out.println(result);
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
	     return date;
	}
	
	 public static void main(String args[]) {
		 TimeServer2 server = new TimeServer2();
		 server.startListening();
	 }
	
}
