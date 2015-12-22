package agenda.domain;

import java.util.*;
import java.text.*;

import agenda.exception.*;


/**ʱ��������
 *
 */
public class TimeInterval {
	/**ʱ�����*/
	public Date start;
	public String startTime;
	/**ʱ���յ�*/
	public Date end;
	public String endTime;
    
    /**
     * ���캯��
     * @param start ʱ�����
     * @param end ʱ���յ�
     * @throws DateParseException ʱ���ʽ���Ե��쳣
     * @throws TimeWrongException �յ����������쳣
     */
    public TimeInterval(String start,String end)throws DateParseException,TimeWrongException
    {
    	SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
    	try{
    		Date date1 = dateFormat.parse(start);
    		Date date2 = dateFormat.parse(end);
    		if(date1.before(date2)){
    			this.start = date1;
    			this.end = date2;
    			this.startTime = start;
    			this.endTime = end;
    		}
    		else throw new TimeWrongException();
    	}catch(ParseException e)
    	{
    		throw new DateParseException();
    	}
    }
    
    /**
     * �ж��Ƿ�����һʱ�������ص�
     * @param another ��һʱ������
     * @return �Ƿ��ص�
     */
    public boolean isOverlap(TimeInterval another){
    	return (start.before(another.start)&&another.start.before(end))
    	||(another.start.before(start)&&start.before(another.end))
        ||(start.equals(another.start)&&end.equals(another.end))
        ||this.isIn(another)
        ||another.isIn(this);
    }
    
    /**
     * �ж��Ƿ�����һʱ��������
     * @param another ��һʱ������
     * @return �Ƿ�����һʱ��������
     */
    public boolean isIn(TimeInterval another){
    	return (another.start.before(start)&&end.before(another.end))
    	      ||(another.start.before(this.start)&&another.end.equals(this.end))
    	      ||(another.start.equals(this.start)&&another.end.after(this.end));
    }
    
    public String getStartTime(){
    	return this.startTime;
    }
    
    public String getEndTime(){
    	return this.endTime;
    }
    
    /**
     * ���л�����
     */
    public String toString(){
    	return "From "+ startTime +" to "+ endTime;
    }
    
	/**Ƕ��ʽ����
	 * @param args
	 */
	public static void main(String[] args) {
		try{			
		    TimeInterval ti1=new TimeInterval("2008-09-01,12:00:00","2010-07-01,13:00:00");
		  //  TimeInterval ti2=new TimeInterval("2007-09-01,12:00:00","2011-07-01,12:00:00");
		    TimeInterval ti2=new TimeInterval("2008-09-01,12:00:00","2010-07-01,12:00:00");
		    System.out.println(ti1.toString());
		    System.out.println(ti2.toString());
		    System.out.println(ti1.getStartTime());
		    System.out.println(ti1.getEndTime());
		    boolean flag=ti1.isOverlap(ti2);
		    if(flag){
		    	System.out.println("Time overlap~");
		    }
		    else System.out.println("Time not overlap~");
		    boolean flag2 = ti1.isIn(ti2);
		    if(flag2){
		    	System.out.println("Time one is in Time two~");
		    }
		    else System.out.println("Time one is not in time two~");
		}catch(DateParseException e){
			e.printInfo();
		}catch(TimeWrongException e){
			e.printInfo();
		}
	}
}
