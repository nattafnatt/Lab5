package lab6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateDisplayer {
	 public static void main(String args[]) {
		 String date;
	     DateFormat formato = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy hh:mm:ss a zzz", new Locale("es", "ES"));
	     date= formato.format(new Date());
	     System.out.println(date);
	   }

}
