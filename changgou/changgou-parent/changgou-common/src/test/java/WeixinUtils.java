import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信常用工具方法测试
 */
public class WeixinUtils {
    @Test
    public void demo1() {
        String s = WXPayUtil.generateNonceStr();
        System.out.println(s);
    }

    @Test
    public void demo2() throws Exception {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "ad");
        map.put("name", "zhao");
        map.put("age", "12");

        // 添加签名
        WXPayUtil.generateSignedXml(map, "zjy");
        String xml = WXPayUtil.mapToXml(map);
        System.out.println(xml);

        Map<String, String> map1 = WXPayUtil.xmlToMap(xml);
        System.out.println(map1);


    }
}
