package agenda.domain;

import java.util.*;
import java.text.*;

import agenda.exception.*;


/**时间区间类
 *
 */
public class TimeInterval {
	/**时间起点*/
	public Date start;
	public String startTime;
	/**时间终点*/
	public Date end;
	public String endTime;
    
    /**
     * 构造函数
     * @param start 时间起点
     * @param end 时间终点
     * @throws DateParseException 时间格式不对的异常
     * @throws TimeWrongException 终点先于起点的异常
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
     * 判断是否与另一时间区间重叠
     * @param another 另一时间区间
     * @return 是否重叠
     */
    public boolean isOverlap(TimeInterval another){
    	return (start.before(another.start)&&another.start.before(end))
    	||(another.start.before(start)&&start.before(another.end))
        ||(start.equals(another.start)&&end.equals(another.end))
        ||this.isIn(another)
        ||another.isIn(this);
    }
    
    /**
     * 判断是否在另一时间区间内
     * @param another 另一时间区间
     * @return 是否在另一时间区间内
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
     * 序列化函数
     */
    public String toString(){
    	return "From "+ startTime +" to "+ endTime;
    }
    
	/**嵌入式测试
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
