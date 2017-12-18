package ua.kiev.prog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListWebServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        List<Message> list = msgList.getList();
        List<Message> list1 = msgListChatRoom.getList();

        String fromStr = request.getParameter("fromLogin");
        request.setAttribute("list", list);
        request.setAttribute("list1", list1);
        request.setAttribute("fromLogin", fromStr);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException ex) {

        }
    }
}
