package lab6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class TimeServer1 {
	public TimeServer1(){
		
	}
	public String getDayOrTime(String command){
	String result = null;
         switch (command) {
         case "time":
           result = doCommandA();
           break;
         case "date":
           result = doCommandB();
           break;
         default:
         }
         if(result == null){return "No Such Command";}
         return result;
       
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
		 TimeServer1 server = new TimeServer1();
		 while(true){
		 Scanner sc = new Scanner(System.in);
		 String s = sc.next();
		 System.out.println(server.getDayOrTime(s));
		 }
	 }
}
