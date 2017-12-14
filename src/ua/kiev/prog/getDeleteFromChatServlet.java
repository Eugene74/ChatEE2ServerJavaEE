package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getDeleteFromChatServlet extends HttpServlet {
    private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String loginStr = req.getParameter("fromLogin");
         msgListChatRoom.deleteUser(loginStr);
    }
}
