import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class LocalHost
{
 public void showip()
 {

     InetAddress localhost = null;
     try {
         localhost = InetAddress.getLocalHost();
     } catch (UnknownHostException e) {
         e.printStackTrace();
     }
     System.out.println("System IP Address : " +
             (localhost.getHostAddress()).trim());
 }
 public String getip()
 {
     InetAddress localhost = null;
     try
     {
         localhost = InetAddress.getLocalHost();
     } catch (UnknownHostException e)
     {
         e.printStackTrace();
     }
     return localhost.getHostAddress();
 }
 public int getMaskNumber() throws UnknownHostException, SocketException {
     InetAddress localhost = null;
     localhost = InetAddress.getLocalHost();
     NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
     int i=networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
     return i;
 }
}
