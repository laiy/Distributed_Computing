package agenda.domain;

/**������
 *
 */
public class Meeting {
	/**�����ƶ���*/
	private String constitutor;
	/**������һ�μ���*/
	private String attendee;
	/**����ʱ��*/
	private TimeInterval interval;
	/**�����ǩ*/
	private String label;
	
	/**
	 * ���캯��
	 * @param constitutor �����ƶ���
	 * @param attendee ��һ�μ���
	 * @param interval ����ʱ��
	 * @param label �����ǩ
	 */
	public Meeting(String constitutor,String attendee,TimeInterval interval,String label){
		this.constitutor=constitutor;
		this.attendee=attendee;
		this.interval=interval;
		this.label=label;
	}

	/**
	 * ���л�����
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
	 * @param attendee Ҫ���õ� attendee
	 */
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	/**
	 * @param constitutor Ҫ���õ� constitutor
	 */
	public void setConstitutor(String constitutor) {
		this.constitutor = constitutor;
	}

	/**
	 * @param interval Ҫ���õ� interval
	 */
	public void setInterval(TimeInterval interval) {
		this.interval = interval;
	}

	/**
	 * @param label Ҫ���õ� label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
