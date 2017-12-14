package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class GetRegistraionServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException   {
        String fromStr = req.getParameter("fromLogin");
        String password = req.getParameter("password");
        String positionChat = req.getParameter("positionChat");
        boolean registration;

        if(positionChat.equals("PrivateChatRoom")){

                            HashMap hashMap= msgListChatRoom.getMapChatRoomRegistr();
                              registration = hashMap.containsKey(fromStr);
                            if(registration==true){
                                String r= (String) hashMap.get(fromStr);
                                registration= r.equals(password);
                                if(registration==true){    }else registration=false;
                            }else {
                                msgListChatRoom.getMapChatRoomRegistr().put(fromStr, password);
                                registration=true;
                            }
        }else {
                            HashMap hashMap= msgList.getMapRegistr();
                              registration = hashMap.containsKey(fromStr);
                            if(registration==true){
                                String r= (String) hashMap.get(fromStr);
                                registration= r.equals(password);
                                if(registration==true){    }else registration=false;
                            }else {
                                msgList.getMapRegistr().put(fromStr, password);
                                registration=true;
                            }
        }
            OutputStream os = resp.getOutputStream( );
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBoolean(registration);
    }
}
