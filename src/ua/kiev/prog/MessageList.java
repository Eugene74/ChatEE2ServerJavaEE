package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageList {

	private static final MessageList msgList = new MessageList();
	private final List<Message> list = new ArrayList<Message>();
	public List<Message> getList() {
		return list;
	}
    private final HashMap<String,String> mapRegistr = new HashMap<String, String>();
	private final HashMap<String,String> mapRegistrStatus = new HashMap<String, String>();
	public static MessageList getInstance() {
		return msgList;
	}
  private MessageList() {}
	public synchronized void add(Message m) {
		list.add(m);
	}
	public synchronized String toJSON(int n, String login) {
		List<Message> res = new ArrayList<Message>();
		for (int i = n; i < list.size(); i++){
			Message message= list.get(i);
			if(message.getTo().equals(login)| message.getTo().equals("")){ // without messages sent by oneself
				res.add(message);
			}else res.add(new Message());// just to return an empty message and see that something is wrong :)
		}
				if (res.size() > 0) {
			Gson gson = new GsonBuilder().create();
			return gson.toJson(res.toArray());
		} else
			return null;
	}
    public HashMap getMapRegistr() {
        return mapRegistr;
    }
	public HashMap<String, String> getMapRegistrStatus() {
		return mapRegistrStatus;
	}
}
