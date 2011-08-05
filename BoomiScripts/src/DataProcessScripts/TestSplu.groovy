package DataProcessScripts
import java.text.SimpleDateFormat


class TestSplu {
	public static void main(String[] args) {

		def myFile = new File("C:\\Temp\\Project Brief\\Data\\Sample_AnnualForecast_07-04-2011_07-05-2011.csv");

		myFile.eachLine {

			String fromDate = "07-05-2011";

			//String inString = "546546546546|UNRESERVED|1|GNC's 2011 Longevity|1172134|Fat2Fit Sponsorship Program|135402||0|2011-07-16|2011-08-16|0|8493.15|0|0|0|0|0|0|0|8493.15|0|0|0|0|0|0|0|8219.18|0|0|0|0|0|0|0|8493.15|0|0|0|0|0|0|0|8219.18|0|0|0|0|0|0|0|8493.15|0|0|0|0|0|0|0|0|0|0|0|0||0|0|0|0|0|0|0||0|0|0|0|0|0|0||0|0|0|0|0|0|0||0|0|0|0|0|0|0||0|0|0|0|0|0|0||0|0|0|0|0|0|0||0|0|50410.96|0|0|0|0|0|0"
			String inString = "uniqueID|"+it;
			try
			{
				String[] splitter = inString.replace("|","#").split("#")

				println("Array Size: "+splitter.length);


				Date currentMonthDate = new SimpleDateFormat("MM-dd-yyyy").parse(fromDate);
				int fromMonth = Integer.parseInt((new SimpleDateFormat("MM").format(currentMonthDate)));

				Date toMonthDate = new SimpleDateFormat("yyyy-MM-dd").parse(splitter[10]);
				int toMonth = Integer.parseInt((new SimpleDateFormat("MM").format(toMonthDate)));

				int currentPos = 11;

				Calendar dateAdder = GregorianCalendar.getInstance();
				dateAdder.setTime(currentMonthDate);
				Calendar dateAdderTo = GregorianCalendar.getInstance();
				dateAdderTo.setTime(toMonthDate);
				if (dateAdder.DAY_OF_MONTH == dateAdder.getMinimum(Calendar.DAY_OF_MONTH)) {}
				else if (dateAdder.DAY_OF_MONTH != dateAdderTo.DAY_OF_MONTH) {
					dateAdder.set(Calendar.DAY_OF_MONTH,dateAdderTo.DAY_OF_MONTH);
					currentMonthDate = dateAdder.getTime();
				}

				int monthCounter = 0;

				while (currentMonthDate <= toMonthDate) {

					Date adStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(splitter[9]);

					if (currentMonthDate < adStartDate) {
						if (monthCounter == 12) break;
						else monthCounter++;
						if ((currentPos+8) > splitter.length) break;
						currentPos += 8;
						fromMonth++;
						Calendar calendar = GregorianCalendar.getInstance();
						calendar.setTime(currentMonthDate);
						calendar.add(Calendar.MONTH,1);
						currentMonthDate = calendar.getTime();
					} else {

						if (monthCounter == 12) break;
						else monthCounter++;

						String papObject = splitter[0]+"|"+splitter[3]+"|"+splitter[4]+"|"+splitter[5]+"|";
						papObject += splitter[currentPos]+"|";
						papObject += splitter[currentPos+1]+"|";
						papObject += splitter[currentPos+2]+"|";
						papObject += splitter[currentPos+3]+"|";
						papObject +=  splitter[currentPos+4]+"|";
						papObject += splitter[currentPos+5]+"|";
						papObject +=  splitter[currentPos+6]+"|";
						papObject +=  splitter[currentPos+7]+"|";

						// Work out start and end dates
						String startDate;

						Date adDate = new SimpleDateFormat("yyyy-MM-dd").parse(splitter[9]);
						Calendar calendar = GregorianCalendar.getInstance();
						calendar.setTime(currentMonthDate);
						calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
						if (adDate >= calendar.getTime()) startDate = (new SimpleDateFormat("yyyy-MM-dd").format(adDate)).toString();
						else startDate = (new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())).toString();

						papObject += startDate +"|";

						String endDate;

						Date adEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(splitter[10]);
						calendar = GregorianCalendar.getInstance();
						calendar.setTime(currentMonthDate);
						calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
						if (adEndDate <= calendar.getTime()) endDate = (new SimpleDateFormat("yyyy-MM-dd").format(adEndDate)).toString();
						else endDate = (new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())).toString();

						papObject += endDate;
						//

						println("PAP Object: "+papObject);
						if ((currentPos+8) > splitter.length) break;
						currentPos += 8;
						fromMonth++;
						calendar.setTime(currentMonthDate);
						calendar.add(Calendar.MONTH,1);
						currentMonthDate = calendar.getTime();
					}
				}
			} catch (Exception e) {
				println(e.printStackTrace());
			}
		}


		//

	}



}

