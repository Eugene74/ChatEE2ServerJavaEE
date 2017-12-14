package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class GetListChatRoomServlet extends HttpServlet {
    private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        String loginStr = req.getParameter("login");

        if(msgListChatRoom.getMapChatRoomRegistr().containsKey(loginStr)){
            int from = Integer.parseInt(fromStr);
            String json = msgListChatRoom.toJSON(from, loginStr);
            if (json != null) {
                OutputStream os = resp.getOutputStream();
                os.write(json.getBytes());
            }
        }
    }
}