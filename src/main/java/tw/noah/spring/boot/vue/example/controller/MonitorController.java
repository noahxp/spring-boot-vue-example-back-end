package tw.noah.spring.boot.vue.example.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MonitorController {

  private final static String success = "SUCCESS";
  private final static String error = "ERROR";

  @Autowired
  private Environment env;

  @RequestMapping(path = "/apis/monitor/http-check")
  public List<MonitorModel> dnsResolver(HttpServletResponse response) {

    // monitor list , first parameter is domain , seconds & third is proxy & proxy port (first parameter is must)
    String[] monitors = { "slack.webhookURI,proxy.host,proxy.port"};

    List<MonitorModel> ret = new ArrayList<>();

    StringBuffer sb = new StringBuffer();
    Arrays.stream(monitors).forEach((v) -> {

      MonitorModel model = new MonitorModel();
      model.setResult(success);

      String[] prop = v.split(",");
      String url = env.getProperty(prop[0]);
      String proxy = prop.length >= 2 ? env.getProperty(prop[1]) : null;
      int proxyPort = prop.length >= 3 && StringUtils.isNotEmpty(env.getProperty(prop[2])) ? Integer.parseInt(env.getProperty(prop[2])) : 0;

      model.setUrl(url);
      model.setProxy(proxy);
      model.setProxyPort(proxyPort);

      if (StringUtils.isNotEmpty(url)) {

        try {
          URL u = new URL(url);
          InetAddress address = null;

          address = InetAddress.getByName(u.getHost());


          model.setDomain(u.getHost());
          model.setPort(u.getDefaultPort());
          model.setIp(address.getHostAddress());

          long cost = monitorInternetConnection(u.getHost(),u.getDefaultPort(),proxy,proxyPort);
          model.setResponseTimes(cost);

        } catch (UnknownHostException | MalformedURLException e) {
          log.error(e, e);
          model.setResult(error);
          response.setStatus(500);
        }

      }

      ret.add(model);

    });

    return ret;
  }

  @Data
  class MonitorModel {
    private String url;
    private String domain;
    private String ip;
    private int port;
    private String proxy;
    @JsonProperty("proxy-port")
    private int proxyPort;
    @JsonProperty("response-times")
    private long responseTimes;
    private String result;
  }

  /**
   * test domain+port , like as 「telnet domain port」
   * @param domain
   * @param port
   */
  private long monitorInternetConnection(String domain,int port,String proxyIp,int proxyPort){

    if (log.isDebugEnabled()) {
      log.debug("check internet connection ->" + domain + "\t" + port + "\t" + proxyIp  + "\t" + proxyPort);
    }

    long time1=0,time2=0;
    Socket s = null;
    try {
      time1 = System.currentTimeMillis();
      if (StringUtils.isNotEmpty(proxyIp) && proxyPort > 0 ){
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
        s = new Socket(proxy);
      }else {
        s = new Socket();
      }
      s.setReuseAddress(true);
      SocketAddress sa = new InetSocketAddress(domain, port);
      s.connect(sa, 3_000);
      s.close();
      time2 = System.currentTimeMillis();

    }catch(IOException ex){
      String ip = null;
      try {
        ip = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        log.error(e,e);
      }
      log.info("ERROR! " + ip + " 連線到 "+ domain +" 異常");
      time2 = System.currentTimeMillis();
    }finally {
      if (s!=null) {
        if (! s.isClosed()) {
          try {
            s.close();
          } catch (IOException e) {
            log.error(e, e);
          }
        }
        s = null;
      }
    }
    return time2-time1;
  }
}
