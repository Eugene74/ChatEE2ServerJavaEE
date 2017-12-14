package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatRoomMessageList {
    private static final ChatRoomMessageList msgListChatRoom = new ChatRoomMessageList();

    private final List<Message> list = new ArrayList<Message>();

    private final HashMap<String,String> mapChatRoomRegistr = new HashMap<String, String>();

    private final HashMap<String,String> mapChatRoomRegistrStatus = new HashMap<String, String>();

    public static ChatRoomMessageList getInstance() {
        return msgListChatRoom;
    }

    private ChatRoomMessageList() {}

    public synchronized void add(Message m) {
        list.add(m);
    }
    public synchronized String toJSON(int n, String login) {
        List<Message> res = new ArrayList<Message>();
        for (int i = n; i < list.size(); i++){
            Message message= list.get(i);
            if(message.getTo().equals(login)| message.getTo().equals("")){
                res.add(message);
            }else res.add(new Message());
        }
        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(res.toArray());
        } else
            return null;
    }
    public HashMap getMapChatRoomRegistr() {
        return mapChatRoomRegistr;
    }
    public HashMap<String, String> getMapChatRoomRegistrStatus() {
        return mapChatRoomRegistrStatus;
    }

    public void deleteUser(String loginStr){
        List<Message> tmpList=new ArrayList<Message>();

        mapChatRoomRegistr.remove(loginStr);
        mapChatRoomRegistrStatus.remove(loginStr);


        //хотел при выходе из чат комнаты удалить и все сообщения пользователя, но подумал - путь будут :)
       /* for (int i = 0; i <list.size() ; i++) {
         if(!list.get(i).getFrom().equals(loginStr)){
             System.out.println(list.get(i));
             tmpList.add(list.get(i));
         }
        }
        list.clear();
        list.addAll(0, tmpList); */
    }
}
