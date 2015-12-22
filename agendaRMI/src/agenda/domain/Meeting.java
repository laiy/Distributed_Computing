package agenda.domain;

/**会议类
 *
 */
public class Meeting {
	/**会议制定者*/
	private String constitutor;
	/**会议另一参加者*/
	private String attendee;
	/**会议时间*/
	private TimeInterval interval;
	/**会议标签*/
	private String label;
	
	/**
	 * 构造函数
	 * @param constitutor 会议制定者
	 * @param attendee 另一参加者
	 * @param interval 会议时间
	 * @param label 会议标签
	 */
	public Meeting(String constitutor,String attendee,TimeInterval interval,String label){
		this.constitutor=constitutor;
		this.attendee=attendee;
		this.interval=interval;
		this.label=label;
	}

	/**
	 * 序列化函数
	 */
	public String toString(){
		return constitutor+" and  "+attendee+" "+interval.toString()+" about "+label;
	}
	
	/**
	 * @return attendee
	 */
	public String getAttendee() {
		return attendee;
	}

	/**
	 * @return constitutor
	 */
	public String getConstitutor() {
		return constitutor;
	}

	/**
	 * @return interval
	 */
	public TimeInterval getInterval() {
		return interval;
	}

	/**
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param attendee 要设置的 attendee
	 */
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	/**
	 * @param constitutor 要设置的 constitutor
	 */
	public void setConstitutor(String constitutor) {
		this.constitutor = constitutor;
	}

	/**
	 * @param interval 要设置的 interval
	 */
	public void setInterval(TimeInterval interval) {
		this.interval = interval;
	}

	/**
	 * @param label 要设置的 label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
