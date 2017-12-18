package ua.kiev.prog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class addWebRegistrationServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();
    private ChatRoomMessageList msgListChatRoom = ChatRoomMessageList.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("fromLogin");
        String password = req.getParameter("password");
        String positionChat = req.getParameter("positionChat");
        boolean registration;

        if(positionChat.equals("private")){

            HashMap hashMap= msgListChatRoom.getMapChatRoomRegistr();
            registration = hashMap.containsKey(fromStr);
            if(registration==true){
                String r= (String) hashMap.get(fromStr);
                registration= r.equals(password);
                if(registration==true){    }else registration=false;
            }else {
                msgListChatRoom.getMapChatRoomRegistr().put(fromStr, password);
                registration=true;
                // TODO: 17.12.2017  registation in privatechat
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
 // временно и коряво (для теста)- ввожу дату и регистрирую в статус листе для того чтобы из консольного клиента
 // при запросе выдавался ответ о вэб пользователе - что он имеет статус
                Date date = new Date(System.currentTimeMillis());
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
                String timeStatus = dateFormat.format(date);
                msgList.getMapRegistrStatus().put(fromStr, timeStatus);

                req.setAttribute("fromLogin", fromStr);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/results.jsp");
                try {
                    requestDispatcher.forward(req, resp);
                } catch (ServletException ex) {

                }
            }
        }
    }
}
