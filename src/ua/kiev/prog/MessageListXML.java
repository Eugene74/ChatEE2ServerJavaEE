package ua.kiev.prog;
/*
* This Class to send data in a format XML
* */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageListXML {

    public List<Message> getRes() {
        return res;
    }
    @XmlElement(name="message")
    private   List<Message> res = new ArrayList<Message>();
    public synchronized MessageListXML toXML(int n, String login, List<Message> list ) {
          res = new ArrayList<Message>();
        for (int i = n; i < list.size(); i++){
               Message message= list.get(i);
                 res.add(message); // without choice - just all the messages
        }
        if (res.size() > 0) {

            return this;
        } else
            return null;
    }

}
