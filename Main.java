import java.io.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if (args.length ==0) {
            try {
                FileWriter fwriter = new FileWriter("NetCalculator.txt");
                PrintWriter writer = new PrintWriter(fwriter);
                String ip = new LocalHost().getip();
                System.out.println("Ip decimal: " + ip);
                writer.println("Ip decimal: " + ip);
                System.out.println("Ip binary: " + new NetCalculator().ToBinary(ip));
                writer.println("Ip binary: " + new NetCalculator().ToBinary(ip));
                NetCalculator n = new NetCalculator();
                int maskNumber = new LocalHost().getMaskNumber();
                String maskDecimal = new NetCalculator().getMaskDecimal(new LocalHost().getMaskNumber());
                System.out.println("Mask: " + maskNumber);
                writer.println("Mask: " + maskNumber);
                System.out.println("Mask decimal: " + maskDecimal);
                writer.println("Mask decimal: " + maskDecimal);
                System.out.println("Mask binary: " + n.ToBinary(maskDecimal));
                writer.println("Mask binary: " + n.ToBinary(maskDecimal));
                System.out.println("Net class: " + new NetCalculator().NetClass(ip));
                writer.println("Net class: " + new NetCalculator().NetClass(ip));
                String netAdressDecimal = new NetCalculator().NetAdressDecimal(new NetCalculator().ToBinary(new LocalHost().getip()), n.ToBinary(maskDecimal));
                System.out.println("Net adress decimal: " + netAdressDecimal);
                writer.println("Net adress decimal: " + netAdressDecimal);
                System.out.println("Net adress binary: " + new NetCalculator().ToBinary(netAdressDecimal));
                writer.println("Net adress binary: " + new NetCalculator().ToBinary(netAdressDecimal));
                String broadcastAdressDecimal = n.getBroadcastDecimal(netAdressDecimal, maskDecimal);
                System.out.println("Broadcast adress decimal: " + broadcastAdressDecimal);
                writer.println("Broadcast adress decimal: " + broadcastAdressDecimal);
                System.out.println("Broadcast adress binary: " + n.ToBinary(broadcastAdressDecimal));
                writer.println("Broadcast adress binary: " + n.ToBinary(broadcastAdressDecimal));
                int maxAmountOfHosts = n.getHostsAmount(maskNumber);
                System.out.println("Maximum amount of hosts: " + maxAmountOfHosts);
                writer.println("Maximum amount of hosts: " + maxAmountOfHosts);
                String firstHost = n.getFirstHost(netAdressDecimal);
                System.out.println("First host decimal: " + firstHost);
                writer.println("First host decimal: " + firstHost);
                System.out.println("First host binary: " + n.ToBinary(firstHost));
                writer.println("First host binary: " + n.ToBinary(firstHost));
                String lastHost = n.getLastHost(broadcastAdressDecimal);
                System.out.println("First host decimal: " + lastHost);
                writer.println("First host decimal: " + lastHost);
                System.out.println("First host binary: " + n.ToBinary(lastHost));
                writer.println("First host binary: " + n.ToBinary(lastHost));
                if (n.ifPrivate(ip)) {
                    System.out.println("Public/private: private");
                    writer.println("Public/private: private");
                } else {
                    System.out.println("Public/private: public");
                    writer.println("Public/private: public");
                }
                String choose;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Do you want to ping the host?(Y/N): ");
                choose = scanner.nextLine();
                switch (choose) {
                    case "Y": {
                        String command = "ping " + ip;
                        Process process = Runtime.getRuntime().exec(command);
                        Scanner scan = new Scanner(process.getInputStream());
                        while (scan.hasNextLine()) {
                            System.out.println(scan.nextLine());
                        }
                        scan.close();
                        break;
                    }
                    default:
                        break;
                }

                writer.close();
            } catch (UnknownHostException e) {
                System.out.println("Error while trying to get local mask");
            } catch (SocketException e) {
                System.out.println("Error while trying to get local mask");
            } catch (IOException e) {
                System.out.println("Error while opening a file");
            }

        } else {
            String[] el = args[0].split("/");
            String ip = el[0];
            int maskNumber = Integer.parseInt(el[1]);
            if (!new NetCalculator().validate(ip)) {
                System.out.println("The inserted ip is invalid . Program will close.");
            } else if (maskNumber < 0 || maskNumber > 32) {
                System.out.println("The inserted mask is invalid . Program will close.");
            } else {
                try {
                    FileWriter fwriter = new FileWriter("NetCalculatorIntserted.txt");
                    PrintWriter writer = new PrintWriter(fwriter);
                    System.out.println("Ip decimal: " + ip);
                    writer.println("Ip decimal: " + ip);
                    System.out.println("Ip binary: " + new NetCalculator().ToBinary(ip));
                    writer.println("Ip binary: " + new NetCalculator().ToBinary(ip));
                    NetCalculator n = new NetCalculator();
                    String maskDecimal = new NetCalculator().getMaskDecimal(maskNumber);
                    System.out.println("Mask: " + maskNumber);
                    writer.println("Mask: " + maskNumber);
                    System.out.println("Mask decimal: " + maskDecimal);
                    writer.println("Mask decimal: " + maskDecimal);
                    System.out.println("Mask binary: " + n.ToBinary(maskDecimal));
                    writer.println("Mask binary: " + n.ToBinary(maskDecimal));
                    System.out.println("Net class: " + new NetCalculator().NetClass(ip));
                    writer.println("Net class: " + new NetCalculator().NetClass(ip));
                    String netAdressDecimal = new NetCalculator().NetAdressDecimal(new NetCalculator().ToBinary(n.getMaskDecimal(maskNumber)), n.ToBinary(maskDecimal));
                    System.out.println("Net adress decimal: " + netAdressDecimal);
                    writer.println("Net adress decimal: " + netAdressDecimal);
                    System.out.println("Net adress binary: " + new NetCalculator().ToBinary(netAdressDecimal));
                    writer.println("Net adress binary: " + new NetCalculator().ToBinary(netAdressDecimal));
                    String broadcastAdressDecimal = n.getBroadcastDecimal(netAdressDecimal, maskDecimal);
                    System.out.println("Broadcast adress decimal: " + broadcastAdressDecimal);
                    writer.println("Broadcast adress decimal: " + broadcastAdressDecimal);
                    System.out.println("Broadcast adress binary: " + n.ToBinary(broadcastAdressDecimal));
                    writer.println("Broadcast adress binary: " + n.ToBinary(broadcastAdressDecimal));
                    int maxAmountOfHosts = n.getHostsAmount(maskNumber);
                    System.out.println("Maximum amount of hosts: " + maxAmountOfHosts);
                    writer.println("Maximum amount of hosts: " + maxAmountOfHosts);
                    String firstHost = n.getFirstHost(netAdressDecimal);
                    System.out.println("First host decimal: " + firstHost);
                    writer.println("First host decimal: " + firstHost);
                    System.out.println("First host binary: " + n.ToBinary(firstHost));
                    writer.println("First host binary: " + n.ToBinary(firstHost));
                    String lastHost = n.getLastHost(broadcastAdressDecimal);
                    System.out.println("First host decimal: " + lastHost);
                    writer.println("First host decimal: " + lastHost);
                    System.out.println("First host binary: " + n.ToBinary(lastHost));
                    writer.println("First host binary: " + n.ToBinary(lastHost));
                    if (n.ifPrivate(ip)) {
                        System.out.println("Public/private: private");
                        writer.println("Public/private: private");
                    } else {
                        System.out.println("Public/private: public");
                        writer.println("Public/private: public");
                    }
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error while opening the file");
                }
            }


        }
    }
}