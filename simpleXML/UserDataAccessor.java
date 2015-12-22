// User.xml数据访问类
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

public class UserDataAccessor {
	protected File userXml;
	private static UserDataAccessor uda = null;

	UserDataAccessor(File userDataXml) throws IOException {
		if (!userDataXml.exists()) {
			userDataXml.createNewFile();
			this.createDefaultXml(userXml);
		}
		this.userXml = userDataXml;
	}

	UserDataAccessor() throws IOException {
		userXml = new File("\\users.xml");
		if (!this.userXml.exists()) {
			this.userXml.createNewFile();
			this.createDefaultXml(userXml);
		}
	}
	public static UserDataAccessor getInstance(File userDataXml)
			throws IOException {
		if (uda == null)
			uda = new UserDataAccessor(userDataXml);
		return uda;
	}

	public static UserDataAccessor getInstance() throws IOException {
		if (uda == null)
			uda = new UserDataAccessor();
		return uda;
	}

	protected void createDefaultXml(File xmlFile) {
		try {
			Element root = new Element("userList");
			Document doc = new Document(root);
			this.writeToXml(doc);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	protected synchronized void writeToXml(Document userDoc) throws IOException {
		Format format = Format.getPrettyFormat();
		format.setEncoding("utf-8");
		format.setIndent(" ");
		XMLOutputter outputter = new XMLOutputter(format);
		outputter.output(userDoc, new FileOutputStream(userXml));
	}

	protected User makeUser(Element element) {
		String name = element.getAttributeValue("name");
		String password = element.getAttributeValue("password");
		return new User(name, password);
	}

	public synchronized boolean hasUser(String name) {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document users = sb.build(new FileInputStream(userXml));
			Element root = users.getRootElement();
			XPath query = XPath.newInstance("/userList/user[@name = '" + name
					+ "']");
			List nodes = query.selectNodes(root);
			if (nodes.size() > 0)
				return true;
			else
				return false;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

	public synchronized boolean userValidate(User user) throws AgendaException,
			IOException, JDOMException {
		SAXBuilder sb = new SAXBuilder();
		Document users = sb.build(new FileInputStream(userXml));
		Element root = users.getRootElement();
		XPath query = XPath.newInstance("/userList/user[@name = '"
				+ user.getUserName() + "']");
		List nodes = query.selectNodes(root);
		if (nodes.size() == 0)
			throw new UserNotRegisteredException(user.getUserName());
		else {
			String pass = ((Element) nodes.get(0))
					.getAttributeValue("password");
			if (pass.equals(user.getPassword()))
				return true;
			else
				return false;
		}
	}

	public synchronized void userRegister(User newUser) throws AgendaException,
			IOException, JDOMException {
		if (!hasUser(newUser.getUserName())) {
			SAXBuilder sb = new SAXBuilder();
			Document usersDoc = sb.build(new FileInputStream(userXml));
			Element root = usersDoc.getRootElement();
			Element element = new Element("user");
			element.setAttribute("name", newUser.getUserName());
			element.setAttribute("password", newUser.getPassword());
			root.addContent(element);
			this.writeToXml(usersDoc);
		} else
			throw new UserNameUsedException();
	}

	public synchronized List getAllUser() throws JDOMException, IOException {
		List users = null;
		User user = null;
		SAXBuilder sb = new SAXBuilder();
		Document userDoc = sb.build(new FileInputStream(userXml));
		Element root = userDoc.getRootElement();
		List usersList = root.getChildren();
		for (int i = 0; i < usersList.size(); i++) {
			user = makeUser((Element) usersList.get(i));
			users.add(user);
		}
		return users;
	}
}
