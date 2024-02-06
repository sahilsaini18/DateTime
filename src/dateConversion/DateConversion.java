package dateConversion;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Logger;

public class DateConversion {
	private static final Logger log = Logger.getLogger(DateConversion.class.getName());

	/**
	 * @param args In this DateConverter Firstly the date is been taken as a user
	 *             input DateTimeFormatter is used to format the way of writing Then
	 *             the user input is parsed to LocalDateTime To get the unix time
	 *             toEpochsecond() is used
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		log.info("Enter date and time (dd/MM/yyyy HH:mm): ");
		String userInput = sc.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		// Here the input received from the user is a string to process it
		// Parse the user input to LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.parse(userInput, formatter);

		ZoneId istZone = ZoneId.of("Asia/Kolkata");
		ZonedDateTime istTimestamp = localDateTime.atZone(istZone);

		long unixTimestamp = istTimestamp.toEpochSecond();

		log.info("Unix Timestamp: " + unixTimestamp);

		ZoneId utcZone = ZoneId.of("UTC");

		ZonedDateTime utcTimestamp = istTimestamp.withZoneSameInstant(utcZone);

		log.info("UTC Timestamp: " + utcTimestamp.format(formatter));

		// Convert back to local timestamp in IST
		ZonedDateTime convertedIstTimestamp = utcTimestamp.withZoneSameInstant(istZone);

		// Print local timestamp in IST
		log.info("Converted IST Timestamp: " + convertedIstTimestamp.format(formatter));

		sc.close();
	}
}
