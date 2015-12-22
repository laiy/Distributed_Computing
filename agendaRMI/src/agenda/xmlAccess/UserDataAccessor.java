/**
 * 
 */
package agenda.xmlAccess;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.xpath.XPath;

import agenda.domain.*;
import agenda.exception.*;



import java.io.*;
import java.util.List;
/**
 * @author Tim
 *
 */
public class UserDataAccessor {
	protected  File userXml;
	private static UserDataAccessor uda = null;
	
	/**
	 * 
	 * @param userDataXml
	 * @throws IOException
	 */
	private UserDataAccessor(File userDataXml) throws IOException{
		if(!userDataXml.exists()){
			userDataXml.createNewFile();
			this.createDefaultXml(userXml);
		}
		this.userXml = userDataXml;
	}

	/**
	 * 
	 * @throws IOException
	 */
	private UserDataAccessor()throws IOException{
		File dir = new File("..\\xml");
		if(!dir.exists())
			dir.mkdir();
		userXml = new File("..\\xml\\users.xml");
		if(!this.userXml.exists()){
			this.userXml.createNewFile();
			this.createDefaultXml(userXml);
		}
	}
	
	/**
	 * 
	 * @param userDataXml
	 * @return
	 * @throws IOException
	 */
	public static UserDataAccessor getInstance(File userDataXml)throws IOException{
		    if(uda == null) uda = new UserDataAccessor(userDataXml);
		    return uda;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static UserDataAccessor getInstance() throws IOException{
		    if(uda == null) uda = new UserDataAccessor();
		    return uda;
	}
	
	/**
	 * 
	 * @param xmlFile
	 */
	protected void createDefaultXml(File xmlFile){
		try{
			Element root = new Element("userList");   
			Document doc = new Document(root);
			this.writeToXml(doc);
		}catch(IOException exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param meetingDoc
	 * @throws IOException
	 */
	protected synchronized void writeToXml(Document userDoc)throws IOException{
		Format format = Format.getPrettyFormat();
		format.setEncoding("utf-8");
		format.setIndent(" ");
		XMLOutputter outputter = new XMLOutputter(format);
		outputter.output(userDoc, new FileOutputStream(userXml));
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	protected User makeUser(Element element){
		String name = element.getAttributeValue("name");
		String password = element.getAttributeValue("password");
		return new User(name,password);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
    public synchronized boolean hasUser(String name){
    	try{
    	    SAXBuilder sb=new SAXBuilder(); 
    	    Document users = sb.build(new FileInputStream(userXml));
    	    Element root = users.getRootElement();
    	    XPath query = XPath.newInstance("/userList/user[@name = '" + name + "']");
    	    List nodes = query.selectNodes(root);
    	    if(nodes.size() > 0) return true;
    	    else return false;
    	}catch(Exception exc){
    		exc.printStackTrace();
    		return false;
    	}
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws UserNotRegisteredException
     */
    public synchronized boolean userValidate(User user)throws AgendaException,IOException,JDOMException{
    	SAXBuilder sb=new SAXBuilder(); 
    	Document users = sb.build(new FileInputStream(userXml));
    	Element root = users.getRootElement();
    	XPath query = XPath.newInstance("/userList/user[@name = '" + user.getUserName() + "']");
    	List nodes = query.selectNodes(root);
    	if(nodes.size() == 0) throw new UserNotRegisteredException(user.getUserName());
    	else {
    		String pass = ((Element)nodes.get(0)).getAttributeValue("password");
    		if(pass.equals(user.getPassword())) return true;
    		else return false;
    	}
    }
    
    /**
     * 
     * @param newUser
     * @throws UserNameUsedException
     */
    public synchronized void userRegister(User newUser)throws AgendaException,IOException,JDOMException{
    	if(!hasUser(newUser.getUserName())){
    		SAXBuilder sb=new SAXBuilder(); 
    		Document usersDoc = sb.build(new FileInputStream(userXml));
    		Element root = usersDoc.getRootElement();
    		Element element = new Element("user");
    		element.setAttribute("name", newUser.getUserName());
    		element.setAttribute("password",newUser.getPassword());
    		root.addContent(element);
    		this.writeToXml(usersDoc);
    	}
    	else 
    		throw new UserNameUsedException();
    }
    
    /**
     * 
     * @return
     */
    public synchronized List getAllUser()throws JDOMException,IOException{
    	List users = null;
    	User user = null;
    	SAXBuilder sb = new SAXBuilder();
    	Document userDoc = sb.build(new FileInputStream(userXml));
    	Element root = userDoc.getRootElement();
    	List usersList = root.getChildren();
    	for(int i =0;i<usersList.size();i++){
    		user = makeUser((Element)usersList.get(i));
    		users.add(user);
    	}
    	return users;
    }
    
	/**单元测试函数
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
        try{
		UserDataAccessor uda =  new UserDataAccessor();
		User user = new User("Jane","789");
		uda.userRegister(user);
		if(uda.hasUser("Jane"))
			System.out.println("user register succeed! and user serch works!");
		else
			System.out.println("It doesn't work!");
		if(uda.userValidate(user))
			System.out.println("user validation works!");
		else 
			System.out.println("user validation doesn't works!");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

}
