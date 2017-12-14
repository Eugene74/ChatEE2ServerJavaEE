package ua.kiev.prog;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class GetListServlet extends HttpServlet {
	private MessageList msgList = MessageList.getInstance();
	private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		String loginStr = req.getParameter("login");
		String dateStr = req.getParameter("date");

		if (msgList.getMapRegistr().containsKey(loginStr)) {
			msgList.getMapRegistrStatus().put(loginStr, dateStr);
		}
		if (msgListChatRoom.getMapChatRoomRegistr().containsKey(loginStr)) {
			msgListChatRoom.getMapChatRoomRegistrStatus().put(loginStr, dateStr);
		}
		int from = Integer.parseInt(fromStr);

//* This block for XML format data
		/*MessageListXML messageListXML = new MessageListXML().toXML(from, loginStr, msgList.getList());
		if (messageListXML != null) {
			OutputStream os = resp.getOutputStream();
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(MessageListXML.class);
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(messageListXML, os);
				//	marshaller.marshal(messageListXML, new File("I:\\AllProgram\\fileFromProgram\\messageListTmp.xml"));// for check
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}*/
//* This block for JSON format data
				String json = msgList.toJSON(from, loginStr);
				if (json != null) {
					OutputStream os = resp.getOutputStream();
					os.write(json.getBytes());
				}
	}
}