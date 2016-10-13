import com.dexingworld.hanfu.weixin.model.InputMessage;
import com.dexingworld.hanfu.weixin.util.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;

/**
 * Created by wangpeng on 2016/10/13.
 */
public class test {

    public static void main(String[] args) {
        InputMessage inputMessage = new InputMessage();
        inputMessage.setToUserName("wqesad123aswq12");
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.aliasType("xml", InputMessage.class);
        String xml =  xs.toXML(inputMessage);
        System.out.println(xml);
    }
}
