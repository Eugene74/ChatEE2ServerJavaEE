package ua.kiev.prog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CheckStatus {
  private   static final String ON_LINE = "On-line";
  private   static final String LAST_SEEN = "Last seen";
  private   static final String OUT_OF_PLACE = "Out of place";
  private   static final String OFF_LINE = "Off-line";
    private   DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
    public HashMap<String, String> checkStatus( HashMap<String,String> map){

        Set<Map.Entry<String ,String>> set =map.entrySet();

       HashMap<String, String> mapTmp = new HashMap<String, String>();
        Date date = new Date(System.currentTimeMillis());
        for (Map.Entry<String,String> entry: set ) {
            int status = checkTime(date, entry.getValue());
            switch (status) {
                case 0:
                    mapTmp.put(entry.getKey(), ON_LINE);
                    break;
                case 1:
                    mapTmp.put(entry.getKey(), LAST_SEEN);
                    break;
                case 2:
                    mapTmp.put(entry.getKey(), OUT_OF_PLACE);
                    break;
                case 3:
                    mapTmp.put(entry.getKey(), OFF_LINE);
                    break;
                default:
                    mapTmp.put(entry.getKey(), "oops");
            }
        }
        return mapTmp;
    }
    private int checkTime(Date current,String date){
        Date tmpDate = new Date();
        int i=5;
        try {
            tmpDate = dateFormat.parse(date);
        } catch (ParseException e) {  }
        if((current.getTime()-tmpDate.getTime()) <=10000)  i=0;
        if((current.getTime()-tmpDate.getTime()) >10000 && (current.getTime()-tmpDate.getTime()) <=20000)    i=1;
        if((current.getTime()-tmpDate.getTime()) >20000 && (current.getTime()-tmpDate.getTime()) <=50000)    i=2;
        if((current.getTime()-tmpDate.getTime()) >50000)   i=3;
        return   i;
    }
}
