package logic;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * The <code>JDate</code> class is a custom helper class for the implementation
 * of date attribute for task. It only supports the read and set of year, month
 * and day. A customized <em>toString()</em> method is also defined to convert
 * <code>JDate</code> into string including date information.
 * 
 * <p>
 * Note that <code>JDate</code> is strictly not lenient. An exception is thrown
 * if there is any inconsistency in its calendar fields
 * 
 * @author A0119391A
 * @see GregorianCalendar
 * 
 */
public class JDate extends GregorianCalendar {
	private static final long serialVersionUID = 1L;
	// locale for provide information when toString method is used
	private static final Locale defaultLocale = Locale.ENGLISH;
	private Locale locale;

	/********************************************
	 ************** Constructor *****************
	 ********************************************/

	// note: input range for month value is from 1 to 12
	public JDate(int year, int month, int day) {
		super(year, month, day);
		setLenient(false);
	}

	// empty constructor
	public JDate() {

	}

	/********************************************
	 ************* Public Method ****************
	 ********************************************/
	public void setYear(int newValue) {
		this.set(YEAR, newValue);
	}

	public void setMonth(int newValue) {
		this.set(MONTH, newValue);
	}

	public void setDay(int newValue) {
		this.set(DAY_OF_MONTH, newValue);
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public int getYear() {
		return this.get(YEAR);
	}

	public int getMonth() {
		return this.get(MONTH);
	}

	public int getDay() {
		return this.get(DAY_OF_MONTH);
	}

	public Locale getLocale() {
		return this.locale;
	}

	/**
	 * convert <code>JDate</code> information into string using following
	 * format:<br>
	 * <code>month <em>splitter</em> day <em>splitter</small> year</code>.
	 * 
	 * @param spliter
	 *            a string to split attributes
	 * @return info string
	 */
	public String toString(String splitter) {
		return monthToString() + splitter + getDay() + splitter + getYear();
	}

	/**
	 * default version of <code>toString(String)</code>. with the default
	 * splitter as the space
	 * 
	 * @see #toString(String)
	 */
	public String toString() {
		return toString(" ");
	}

	/**
	 * <em>compareTo</em> method to compare between two JDate.
	 * 
	 * <strong>Note</strong>: if the given JDate is null, it is considered as a
	 * null date without any time information. therefore 1 will be returned
	 * since we want to place all floating tasks in the end.
	 * 
	 * @param another
	 *            another JDate
	 */
	public int compareTo(JDate another) {
		if (another == null) {
			return 1;
		}
		if (this.getYear() < another.getYear()) {
			return -1;
		} else if (this.getYear() > another.getYear()) {
			return 1;
		} else {
			if (this.getMonth() < another.getMonth()) {
				return -1;
			} else if (this.getMonth() > another.getMonth()) {
				return 1;
			} else {
				if (this.getDay() < another.getDay()) {
					return 1;
				} else if (this.getDay() > another.getDay()) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	/********************************************
	 ************* Private Method ***************
	 ********************************************/

	/**
	 * convert month into string according to the locale given
	 */
	private String monthToString() {
		if (locale != null) {
			return this.getDisplayName(MONTH, SHORT, getLocale());
		} else {
			return this.getDisplayName(MONTH, SHORT, defaultLocale);
		}

	}

}
