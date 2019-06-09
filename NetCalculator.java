public class NetCalculator
{
    String ToBinary(String ip){
        String[] octs=ip.split("\\.");
        int[] octsl=new int[4];
        for(int i=0;i<4;i++)
        {
            octsl[i]=Integer.parseInt(octs[i]);
        }
        String toReturn="";
        if(Integer.toBinaryString(octsl[0]).length()<8)
        {
            for(int i=0;i<8-Integer.toBinaryString(octsl[0]).length();i++)
            {
                toReturn+="0";
            }
        }
        toReturn+=Integer.toBinaryString(octsl[0]);
        for(int i=1;i<4;i++)
        {
            toReturn+=".";
            if(Integer.toBinaryString(octsl[i]).length()<8)
            {
                for(int j=0;j<8-Integer.toBinaryString(octsl[i]).length();j++)
                {
                    toReturn+="0";
                }
            }
            toReturn+=Integer.toBinaryString(octsl[i]);
        }
        return toReturn;
    }
    String BinaryToDecimal(String sABinary)
    {
        String[] splitted=sABinary.split("\\.");
        String toReturn="";
        toReturn+=Integer.toString(Integer.parseInt(splitted[0],2));
        for(int i=1;i<4;i++)
        {
            toReturn+="."+Integer.toString(Integer.parseInt(splitted[i],2));
        }
        return toReturn;
    }
    String NetClass(String ip)
    {
        String firstoct;
        String[] splitted=ToBinary(ip).split("\\.");
        firstoct=splitted[0];
        String three,four;
        String two = String.valueOf(firstoct.charAt(0))+String.valueOf(firstoct.charAt(1));
        three=String.valueOf(firstoct.charAt(0))+String.valueOf(firstoct.charAt(1))+String.valueOf(firstoct.charAt(2));
        four=String.valueOf(firstoct.charAt(0))+String.valueOf(firstoct.charAt(1))+String.valueOf(firstoct.charAt(2))+String.valueOf(firstoct.charAt(3));
        if(firstoct.charAt(0)=='0')
            return "A";
        else if(two.equals("10"))
            return "B";
        else if(three.equals("110"))
            return "C";
        else if(four.equals("1110"))
            return "D";
            else
                return "E";
    }
    String NetAdressDecimal(String ipBinary,String maskBinary)
    {
        String ipnodots="",masknodots="";
        String[] ipsplitted=ipBinary.split("\\.");
        String[] masksplitted=maskBinary.split("\\.");
        for(int i=0;i<4;i++)
        {
            ipnodots+=ipsplitted[i];
            masknodots+=masksplitted[i];
        }
        String netAdressdecnodots="";
        for(int i=0;i<32;i++)
        {
            if(ipnodots.charAt(i)=='1' && masknodots.charAt(i)=='1')
                netAdressdecnodots+="1";
            else
                netAdressdecnodots+="0";
        }
        int i=0;
        String toReturn="";
        while(true)
        {
            toReturn+=netAdressdecnodots.charAt(i);
            if(i==7 || i==15 || i==23)
            {
                toReturn+=".";
            }
            if(i==31)
                break;
            i++;
        }
        return BinaryToDecimal(toReturn);
    }
    String BinaryToNoDots(String ip)
    {
        String[] splitted=ip.split("\\.");
        String toReturn="";
        for(int i=0;i<4;i++)
        {
            toReturn+=splitted[i];
        }
        return toReturn;
    }
    String NoDotsToBinary(String ip)
    {
        int i=0;
        String toReturn="";
        while(true)
        {
            toReturn+=ip.charAt(i);
            if(i==7||i==15||i==23)
            {
                toReturn+=".";

            }
            if(i==31)
                break;
            i++;
        }
        return toReturn;
    }

    public String getMaskBinary(int mask)
    {
        String toReturn="";
        int numofones=0;
        for(int i=0;i<35;i++)
        {
            if(i==8 || i==17 || i==26)
                toReturn+=".";
            else if(numofones<mask)
            {
                toReturn += "1";
                numofones++;
            }
            else
                toReturn+="0";
        }
        return toReturn;
    }
    public String getMaskDecimal(int mask)
    {
        String[] splitted=getMaskBinary(mask).split("\\.");
        String toReturn="";
        toReturn+=Integer.toString(Integer.parseInt(splitted[0],2));
        for(int i=1;i<4;i++)
        {
            toReturn+="."+Integer.toString(Integer.parseInt(splitted[i],2));
        }
        return toReturn;
    }
    String getBroadcastDecimal(String netAdress,String mask)
    {
        String maskNoDots=BinaryToNoDots(ToBinary(mask));
        String maskNoDotsNOT="";
        for(int i=0;i<32;i++)
        {
            if(maskNoDots.charAt(i)=='1')
            {
                maskNoDotsNOT+='0';
            }
            else
            {
                maskNoDotsNOT+='1';
            }
        }
        String maskNOTDecimal=BinaryToDecimal(NoDotsToBinary(maskNoDotsNOT));
        String[] splittedMaskNOT=maskNOTDecimal.split("\\.");
        String[] splittedNetAdress=netAdress.split("\\.");
        String toReturn=Integer.toString(Integer.parseInt(splittedNetAdress[0])+Integer.parseInt(splittedMaskNOT[0]));
        for(int i=1;i<4;i++)
        {
            toReturn+="."+Integer.toString(Integer.parseInt(splittedNetAdress[i])+Integer.parseInt(splittedMaskNOT[i]));
        }
        return toReturn;
    }
    int getHostsAmount(int mask)
    {
        int toReturn= (int) (Math.pow(2,32-mask)-2);
        return toReturn;
    }
    String getFirstHost(String netAdress)
    {
        String[] splitted=netAdress.split("\\.");
        int dodane=Integer.parseInt(splitted[3]);
        dodane+=1;
        String toReturn="";
        for(int i=0;i<3;i++)
        {
            toReturn+=splitted[0]+".";
        }
        toReturn+=Integer.toString(dodane);
        return toReturn;
    }
    String getLastHost(String broadcastAdress)
    {
        String[] splitted=broadcastAdress.split("\\.");
        int dodane=Integer.parseInt(splitted[3]);
        dodane-=1;
        String toReturn="";
        for(int i=0;i<3;i++)
        {
            toReturn+=splitted[0]+".";
        }
        toReturn+=Integer.toString(dodane);
        return toReturn;
    }
    boolean ifPrivate(String ip)
    {
        String[] splitted=ip.split("\\.");
        int[] o=new int[4];
        for(int i=0;i<4;i++)
            o[i]=Integer.parseInt(splitted[i]);
        if((o[0]==10)&&(o[1]>=0 &&o[1]<=255)&&(o[2]>=0 &&o[2]<=255)&&(o[3]>=0 &&o[3]<=255))
            return true;
        else if(o[0]==172&&(o[1]>=16 &&o[1]<=31)&&(o[2]>=0 &&o[2]<=255)&&(o[3]>=0 &&o[3]<=255))
            return true;
        else if(o[0]==192&&(o[1]==168)&&(o[2]>=0 &&o[2]<=255)&&(o[3]>=0 &&o[3]<=255))
            return true;
        else
            return false;
    }

    public boolean validate(String adres)
    {

        int numberofdots=0;
        if(adres.length()<7) {
            return false;
        }
        for(int i=0;i<adres.length();i++)
        {
            if(adres.charAt(i)=='.')
            {
                numberofdots++;
            }
        }
        if(numberofdots!=3)
        {
            return false;
        }
        String[] el= adres.split("\\.");
        if(el.length<4)
        {
            return false;
        }
        for(String a : el)
        {
            try {
                if (Integer.parseInt(a) > 255 || Integer.parseInt(a) < 0) {
                    return false;
                }
            }
            catch(Exception e)
            {
                return false;
            }
        }

        return true;
    }

}
