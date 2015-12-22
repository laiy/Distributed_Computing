/**
 * 
 */
package agenda.xmlAccess;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.*;
import org.jdom.xpath.*;

import agenda.domain.*;
import agenda.exception.*;



/**
 * @author Tim
 *
 */
public class MeetingDataAccessor {
	protected File meetingXml;
	private static MeetingDataAccessor mda = null;
	
	/**
	 * 
	 * @param meetingDataXml
	 * @throws IOException
	 */
	private MeetingDataAccessor(File meetingDataXml)throws IOException{
		if(!meetingDataXml.exists()){
			meetingDataXml.createNewFile();
			this.createDefaultXml(meetingDataXml);
		}
		this.meetingXml = meetingDataXml;
	}

	/**
	 * 
	 * @throws IOException
	 */
	private MeetingDataAccessor()throws IOException{
		File dir = new File("..\\xml");
		if(!dir.exists())
			dir.mkdir();
		this.meetingXml = new File("..\\xml\\meetings.xml");
		if(!meetingXml.exists()){
			meetingXml.createNewFile();
			this.createDefaultXml(meetingXml);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static MeetingDataAccessor getInstance()throws IOException{
		if(mda == null) mda = new MeetingDataAccessor();
		return mda;
	}
	
	/**
	 * 
	 * @param meetingDataXml
	 * @return
	 * @throws IOException
	 */
	public static MeetingDataAccessor getInstance(File meetingDataXml)throws IOException{
		if(mda == null) mda = new MeetingDataAccessor(meetingDataXml);
		return mda;
	}
	
	/**
	 * 
	 * @param xmlFile
	 */
	protected void createDefaultXml(File xmlFile){
		try{
			Element root = new Element("meetingList");   
			Document Doc = new Document(root);
			XMLOutputter XMLOut = new XMLOutputter();
			XMLOut.output(Doc, new FileOutputStream(xmlFile));
		}catch(IOException exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param meetingDoc
	 * @throws IOException
	 */
	protected synchronized void writeToXml(Document meetingDoc)throws IOException{
		Format format = Format.getPrettyFormat();
		format.setEncoding("utf-8");
		format.setIndent(" ");
		XMLOutputter outputter = new XMLOutputter(format);
		outputter.output(meetingDoc, new FileOutputStream(meetingXml));
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	protected Meeting makeMeeting(Element element)throws AgendaException{
		String constitutor = element.getAttributeValue("constitutor");
		String attendee = element.getAttributeValue("attendee");
		String startTime = element.getAttributeValue("startTime");
		String endTime = element.getAttributeValue("endTime");
		String label = element.getAttributeValue("label");
		return new Meeting(constitutor,attendee,new TimeInterval(startTime,endTime),label);
	}
	
	/**
	 * �ж����������Ƿ��ͻ
	 * @param m1 ����һ
	 * @param m2 �����
	 * @return true-��ͻ false-����ͻ
	 */
	public boolean meetingConflict(Meeting m1,Meeting m2){
		String name1 = m1.getConstitutor();
		String name2 = m1.getAttendee();
		String name3 = m2.getConstitutor();
		String name4 = m2.getAttendee();
		if(name1.equals(name3)||name1.equals(name4)||name2.equals(name3)||name2.equals(name4)){
			return m1.getInterval().isOverlap(m2.getInterval());
		}
		else return false;
	}
	
	/**���һ������
	 * @param newMeeting ����ӵĻ���
	 * @throws AgendaException
	 */
	public synchronized void add(Meeting newMeeting)throws AgendaException,IOException,JDOMException{
		boolean conflict=false;
		SAXBuilder sb=new SAXBuilder(); 
		Document meetingDoc = sb.build(new FileInputStream(meetingXml));
		Element root = meetingDoc.getRootElement();
		List meetings = root.getChildren();
		Element element;
		Meeting m;
		for(int i =0;i<meetings.size();i++){
			element = (Element)meetings.get(i);
			m = makeMeeting(element);
			if(meetingConflict(newMeeting,m)){
				conflict = true;
				break;
			}
		}
		if(!conflict){
			Element newEle = new Element("meeting");
			newEle.setAttribute("constitutor",newMeeting.getConstitutor());
			newEle.setAttribute("attendee",newMeeting.getAttendee());
			newEle.setAttribute("startTime",newMeeting.getInterval().getStartTime());
			newEle.setAttribute("endTime",newMeeting.getInterval().getEndTime());
			newEle.setAttribute("label",newMeeting.getLabel());
			root.addContent(newEle);
			writeToXml(meetingDoc);
		}
		else throw new TimeConflictException();
	}
	
	/**ɾ��ĳ�û��趨��ĳ����
	 * @param user ָ���û�
	 * @param label Ҫɾ���Ļ���ı�ǩ
	 * @throws MeetingNotExitException Ҫɾ���Ļ��鲻���ڵ��쳣���
	 */
	public synchronized void delete(User user,String label)
	throws MeetingNotExistException,JDOMException,FileNotFoundException,IOException{
		boolean meetingExist = false;
		SAXBuilder sb=new SAXBuilder(); 
		Document meetingDoc = sb.build(new FileInputStream(meetingXml));
		Element root = meetingDoc.getRootElement();
		XPath xpath = XPath.newInstance("/meetingList/meeting[@label='" + label + "']");
		List meetings = xpath.selectNodes(root);
		Element element;
		for(int i =0;i<meetings.size();i++){
			element = (Element)meetings.get(i);
			if(user.getUserName().equals(element.getAttributeValue("constitutor"))){
				meetingExist = true;
				element.getParentElement().removeContent(element);
			}
		}
		if(meetingExist) writeToXml(meetingDoc);
		else throw new MeetingNotExistException();
	}
	
	/**��ѯĳ�û���ĳ��ʱ���ڵ����л���
	 * @param user ָ���û�
	 * @param start ʱ���ǰ��
	 * @param end ʱ����յ�
	 * @return ���û���ָ��ʱ����ڵ����л���
	 * @throws TimeWrongException
	 */
	public synchronized ArrayList query(User user,TimeInterval ti)
	throws JDOMException,FileNotFoundException,IOException,AgendaException{
		ArrayList meetingList = new ArrayList();
		Meeting m = null;
		String name = user.getUserName();

		SAXBuilder sb = new SAXBuilder(); 
		Document meetingDoc = sb.build(new FileInputStream(meetingXml));
		Element root = meetingDoc.getRootElement();
		XPath xpath = XPath.newInstance
		("/meetingList/meeting[@constitutor='" + name + "' or @attendee='" + name + "']");
		List meetings = xpath.selectNodes(root);
		Element element;
		for(int i =0;i<meetings.size();i++){
			element = (Element)meetings.get(i);
			m = makeMeeting(element);
			if(m.getInterval().isIn(ti))
				meetingList.add(m);
		}
		return meetingList;
	}
	
	/**ɾ��ָ���û��趨�����л���
	 * @param user ָ���û�
	 */
	public synchronized void clear(User user)throws JDOMException,FileNotFoundException,IOException{
		SAXBuilder sb=new SAXBuilder(); 
		Document meetingDoc = sb.build(new FileInputStream(meetingXml));
		Element root = meetingDoc.getRootElement();
		XPath xpath = XPath.newInstance("/meetingList/meeting[@constitutor='" + user.getUserName() + "']");
		List meetings = xpath.selectNodes(root);
		Element element;
		for(int i =0;i<meetings.size();i++){
			element = (Element)meetings.get(i);
			element.getParentElement().removeContent(element);
		}
		writeToXml(meetingDoc);
	}
	
	/**
	 * ��ȡ���л���
	 */
	public synchronized List getAllMeetings()throws JDOMException,FileNotFoundException,IOException,AgendaException{
		List meetings = null;
		SAXBuilder sb=new SAXBuilder(); 
		Document meetingDoc = sb.build(new FileInputStream(meetingXml));
		Element root = meetingDoc.getRootElement();
		meetings = root.getChildren();
		Meeting m = null;
		for(int i=0;i<meetings.size();i++){
			m = makeMeeting((Element)meetings.get(i));
			meetings.add(m);
		}
		return meetings;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			MeetingDataAccessor mm = MeetingDataAccessor.getInstance();
    	//	TimeInterval ti = new TimeInterval("2008-12-03,09:30:00","2008-12-03,12:00:00");
    		User tim=new User("Tim","123");
    //		Meeting m1=new Meeting("Tim","Jane",ti,"love");
   // 		Meeting m3=new Meeting("Bill","Gigi",ti,"feel");
    //		mm.add(m1);
    //		mm.add(m3);
    		
/*            ti=new TimeInterval("2001-01-01,00:00:00","2010-01-01,00:00:00");
    		ArrayList list=mm.query(tim,ti);
    		if(list.size() > 0){
    			for(int i=0;i<list.size();i++){
    				Meeting m=(Meeting)list.get(i);
    				System.out.println(m.toString());
    			}
    		}
    		else System.out.println("no such meetings.");*/
    	//	mm.delete(tim, "love");
    		mm.clear(tim);
	//	}catch(AgendaException e){
		//	e.printInfo();
		}catch(IOException exc){
			;
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
