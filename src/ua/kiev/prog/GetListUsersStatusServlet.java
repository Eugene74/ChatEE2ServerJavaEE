package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class GetListUsersStatusServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        String whatChat = req.getParameter("whatChat");
        HashMap hashMap=null;
        if(whatChat.equals("PrivateChatRoom")){
                        hashMap=msgListChatRoom.getMapChatRoomRegistrStatus();
                        hashMap = new CheckStatus().checkStatus(hashMap);
        }else {
                        hashMap= msgList.getMapRegistrStatus();
                        hashMap = new CheckStatus().checkStatus(hashMap);
        }
        Gson gson = new GsonBuilder().create();
        String s=  gson.toJson(hashMap);
        if (s != null) {
            OutputStream os = resp.getOutputStream();
            os.write(s.getBytes());
        }
    }
}
