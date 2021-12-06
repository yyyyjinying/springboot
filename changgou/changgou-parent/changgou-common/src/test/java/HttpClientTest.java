import entity.HttpClient;
import org.junit.Test;

import java.io.IOException;

public class HttpClientTest {
    @Test
    public void demo() throws IOException {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        HttpClient httpClient = new HttpClient(url);
        String xml = "<xml><appid>wx2421b1c4370ec43b</appid></xml>";
        httpClient.setXmlParam(xml);

        httpClient.setHttps(true);

        httpClient.post();

        String content = httpClient.getContent();
        System.out.println(content);


    }
}
