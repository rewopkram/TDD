/**
 * 
 */


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	// Days of the week in order representing Java Calendar static variables - dummy is a place marker to take up the 0 index.
	public static final String[] WEEK_DAYS = {"dummy", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	public static final String[] MONTHS = {"January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public static final String[] FY_MONTHS = {"July", "August", "September", "October", "November", "December", "January", "February", "March", "April", "May", "June"};

	public static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat hhmm = new SimpleDateFormat("HH:mm");

	public static DecimalFormat nnnpnn = new DecimalFormat("###0.00");
	/**
	 * 
	 */
	public DateUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static Date getDateStartOfDay(String dateStr) throws Exception {
		Date dt = ddMMyyyy.parse(dateStr);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getDateStartOfDay(Date dt) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getDateEndOfDay(String dateStr) throws Exception {
		Date dt = ddMMyyyy.parse(dateStr);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getDateEndOfDay(Date dt) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getDate(String dateStr) throws Exception {
		return ddMMyyyy.parse(dateStr);
	}
	
	public static Date getMsDate(String dateStr) throws Exception {
		return yyyyMMdd.parse(dateStr);
	}


	public static Date getFirstDayOfWeek(String in) throws Exception {
		return getFirstDayOfWeek(ddMMyyyy.parse(in));
	}

	/**
	 * @param in
	 * @return
	 * get date of Monday
	 */
	public static Date getFirstDayOfWeek(Date in) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(in);
		int currentDOW = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_YEAR, (currentDOW * -1) + 2);
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getMondayPreviousWeek(Date in) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(in);
		// set to same day previous week
		cal.add(Calendar.DAY_OF_YEAR, - 7);
		
		Date dt = getFirstDayOfWeek(cal.getTime());
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getFridayPreviousWeek(Date in) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(in);
		// set to same day previous week
		cal.add(Calendar.DAY_OF_YEAR, - 7);
		
		Date dt = getFriday(cal.getTime());
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		
		return new Date(cal.getTimeInMillis());
	}


	public static Date getLastDayOfWeek(String in) throws Exception {
		return getLastDayOfWeek(ddMMyyyy.parse(in));
	}

	/**
	 * @param in
	 * @return
	 * get date of Sunday
	 */
	public static Date getLastDayOfWeek(Date in) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(in);
		int currentDOW = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_YEAR, (currentDOW * -1) + 8);
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getFriday(Date in) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(in);
		int currentDOW = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_YEAR, (currentDOW * -1) + 6);
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date getNextDay(Date in) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(in);
		int currentDOW = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_YEAR, + 1);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * Gets the Date of the timesheet's start day of the week (ie Monday for some, Saturday for others) by extrapolating from today's date and the index of the timesheets start day
	 * @param today
	 * @param startWeekIdx
	 * @return
	 */
	public static Date getStartOfWeekDate(Date today, int startOfWeekIdx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int currentDOW = cal.get(Calendar.DAY_OF_WEEK);

		if (currentDOW >= startOfWeekIdx) {
			cal.add(Calendar.DAY_OF_YEAR, (startOfWeekIdx - currentDOW));
		}
		else {
			cal.add(Calendar.DAY_OF_YEAR, (startOfWeekIdx - (currentDOW + 7)));
		}
		//cal.add(Calendar.DAY_OF_YEAR, (currentDOW * -1)+2);
		return new Date(cal.getTimeInMillis());
	}

	public static Date getEndOfWeekDate(Date today, int startOfWeekIdx) {
		Calendar cal = Calendar.getInstance();
		Date startWeekDate = getStartOfWeekDate(today, startOfWeekIdx);
		cal.setTime(startWeekDate);
		cal.add(Calendar.DAY_OF_YEAR, 6);

		return new Date(cal.getTimeInMillis());
	}

	public static String getWeekDay(Date d) {
		String day = "";

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		day = DateUtils.WEEK_DAYS[cal.get(Calendar.DAY_OF_WEEK)];

		return day;
	}

	/**
	 * Gets the first day of the current month. The Jira Import will retrieve data up to (not including) this date.
	 * @return
	 */
	public static Date getStartOfMonth() {
		//		Calendar cal = Calendar.getInstance();

		//		cal.set(Calendar.DAY_OF_MONTH, 1);
		//		cal.set(Calendar.HOUR, 0);
		//		cal.set(Calendar.MINUTE, 0);
		//		cal.set(Calendar.SECOND, 0);

		return getStartOfMonth(new Date());
	}

	/**
	 * Returns the first day of the month for the Date parameter with time fields zeroed - ie HH:MM:SS = 00:00:00
	 * @param dt
	 * @return
	 */
	public static Date getStartOfMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date getStartOfPreviousMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, -1);

		return cal.getTime();
	}
	
	public static Date getPreviousMonthDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		
		cal.add(Calendar.MONTH, -1);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	

	public static Date getStartOfNextMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, 1);

		return cal.getTime();
	}

	/**
	 * Returns the last day of the month for the Date parameter with time fields set to end of day - ie HH:MM:SS = 23:59:59
	 * @param dt
	 * @return
	 */
	public static Date getEndOfMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		// get start of dt month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		// change to start of next month
		cal.add(Calendar.MONTH, 1);

		// roll back one day to end of previous month
		cal.add(Calendar.DAY_OF_YEAR, -1);

		return cal.getTime();
	}
	
	public static Date getEndOfPreviousMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		// get start of dt month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		// roll back one day to end of previous month
		cal.add(Calendar.DAY_OF_YEAR, -1);

		return cal.getTime();
	}

	public static String getMonthName(int idx) {
		return MONTHS[idx];
	}

	public static String getMonthName(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return MONTHS[cal.get(Calendar.MONTH)];
	}

	public static int getMonthIndex(String name) {
		for (int i = 0; i < MONTHS.length; i++) {
			if (name.equals(MONTHS[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Date formatted 01/07/YYYY 00:00:00
	 * @param endFinYearYear
	 * @return
	 */
	public static Date getStartFinYearDate(String endFinYearYear) {
		Calendar c = Calendar.getInstance();
		int yr = Integer.parseInt(endFinYearYear);

		c.set(Calendar.DATE, 1);
		c.set(Calendar.MONTH, 6);
		c.set(Calendar.YEAR, yr - 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	/**
	 * Date formatted 30/06/YYYY 00:00:00
	 * @param endFinYearYear
	 * @return
	 */
	public static Date getEndFinYearDate(String endFinYearYear) {
		Calendar c = Calendar.getInstance();

		int yr = Integer.parseInt(endFinYearYear);

		c.set(Calendar.DATE, 30);
		c.set(Calendar.MONTH, 5);
		//c.set(Calendar.MONTH, 6);
		c.set(Calendar.YEAR, yr);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}
	
	/**
	 * Gets the date at the end of the month within the financial year
	 * @param endFinYearYear - ie 2014
	 * @param month - ie August
	 * @return for above params 31/08/2013
	 */
	public static Date getFinYearMonthEndDate(String endFinYearYear, String month) {
		Calendar c = Calendar.getInstance();

		int yr = Integer.parseInt(endFinYearYear);
		int mnthIdx = getMonthIndex(month);
		
		if (mnthIdx > 5) {
			yr--;
		}
		c.set(Calendar.DATE, 10); // date here is irrelevant we just need a day within the month in question to create a date. The getEndOfMonth method will resolve.
		c.set(Calendar.MONTH, mnthIdx);
		c.set(Calendar.YEAR, yr);
		

		return getEndOfMonth(c.getTime());
	}

	public static String getEndFinYearYearFromDate(Date dt) {

		Calendar c = Calendar.getInstance();
		c.setTime(dt);

		int yr = c.get(Calendar.YEAR);

		// increment the year because the date falls in first half of financial year
		if (c.get(Calendar.MONTH) > 5) {
			yr++;
		}
		return Integer.toString(yr);
	}
	
	public static int getFinYearQuarter(Date dt) {
		int qtr = -1;
		
		String month = getMonthName(dt);
		
		for (int i = 0; i < FY_MONTHS.length; i++) {
			if (month.equals(FY_MONTHS[i])) {
				qtr = (i + 3) / 3;
			}
		}
		return qtr;
	}
	
	/**
	 * Returns the last day of the year for the Date parameter with time fields set to end of day - ie HH:MM:SS = 23:59:59
	 * @param dt
	 * @return
	 */
	public static Date getEndOfYear(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}
	
	public static Date rollDate(Date dt, int increment, boolean startDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		if (startDay) {
			// set time to start of day values
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		}
		else {
			// set time to end of day values
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
		}
		
		// roll back one day to end of previous month
		c.add(Calendar.DAY_OF_YEAR, increment);
		
		return c.getTime();
	}
	
	public static Date rollMonth(Date dt, int increment, boolean startDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		if (startDay) {
			// set time to start of day values
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		}
		else {
			// set time to end of day values
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
		}
		
		// roll month forward or back by increment amount
		c.add(Calendar.MONTH, increment);
		
		return c.getTime();
	}
	
	public static Date rollYear(Date dt, int increment, boolean startDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		if (startDay) {
			// set time to start of day values
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		}
		else {
			// set time to end of day values
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
		}
		
		// roll month forward or back by increment amount
		c.add(Calendar.YEAR, increment);
		
		return c.getTime();
	}
}





