package naumen.framework.base;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Random;
/*
 * менеджер для получения порта для прокси при многопоточном запуске тестов
 */
public class ProxyPortManager {
    private static HashMap<Long, Integer> portsMap = new HashMap<Long, Integer>();
    private static HashMap<Integer, ProxyServer> proxyServersMap = new HashMap<Integer, ProxyServer>();
    private static Random rand = new Random();

    private static int getPort(Long threadID){
        if (portsMap.containsKey(threadID))
            return portsMap.get(threadID);
        else{
            return getNewPort(threadID);
        }
    }

    /**
     * получить прокси, с которым будем запускать браузер в текущем потоке
     * @param threadID ID текущего потока
     * @return SeleniumProxy
     * @throws UnknownHostException
     */
    public static Proxy getProxy(Long threadID) throws Exception{
        if (portsMap.containsKey(threadID)){
            int port = portsMap.get(threadID);
            return proxyServersMap.get(port).seleniumProxy();
        }
        else{
            int port = getNewPort(threadID);
            ProxyServer server = new ProxyServer(port);
            proxyServersMap.put(port, server);
            server.start();
            server.setCaptureContent(true);
            return server.seleniumProxy();
        }
    }

    /**
     * возвращает прокси-сервер данного потока, либо null
     * @param threadID
     * @return
     */
    public static ProxyServer getServer(Long threadID){
        if (!portsMap.containsKey(threadID))
            return null;
        else{
            int port = portsMap.get(threadID);
            return proxyServersMap.get(port);
        }
    }

    /**
     * останавливаем прокси-сервер, соответствующий данному потоку, закрываем порт
     * @param threadID
     */
    public static void closeProxy(Long threadID) throws Exception{
        if (!portsMap.containsKey(threadID))
            return;
        else{
            int port = portsMap.get(threadID);
            ProxyServer server = proxyServersMap.get(port);
            server.stop();
            proxyServersMap.remove(port);
            removePort(threadID);
        }
    }

    /**
     * заводим новый порт для прокси
     * @param threadID
     * @return
     */
    private static int getNewPort(Long threadID){
        int proxyPort;
        do {
            proxyPort = 4000 + rand.nextInt(1000);
        }
        while(portsMap.values().contains(proxyPort));
        portsMap.put(threadID, proxyPort);
        System.out.println("opening proxy port " + proxyPort);
        return proxyPort;
    }

    /**
     * удаляем порт, соответствующий данному потоку, из списка используемых портов
     * @param threadID
     */
    private static void removePort(Long threadID){
        if (portsMap.containsKey(threadID)){
            System.out.println("closing proxy port " + portsMap.get(threadID));
            portsMap.remove(threadID);
        }
    }
}
