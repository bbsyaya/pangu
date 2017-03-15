package org.turing.pangu.phone;

import org.turing.pangu.engine.PhoneBrandEngine;
import org.turing.pangu.utils.IMEIUtils;
import org.turing.pangu.utils.RandomUtils;


/**
 * Created by turingkuang on 2017/1/7.
 */
/*
    产生数据类
* */
public class GenerateData {
    public static final String[] LETTERS = {"a","b","c","d","e","f","g","h","i","j",
            "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static final String[] NUMBER = {"1","2","3","4","5","6","7","8","9","0"};

    public static final String[] LETTERSANDNUMBER = {"1","2","3","4"
            ,"5","6","7","8","9","0","a","b","c","d","e","f","g","h","i","j",
            "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4"
            ,"5","6","7","8","9","0"};

    public static final String[] Hexadecimal = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    private  static GenerateData mInstance = new GenerateData();
    public static GenerateData getInstance()
    {
        if(null == mInstance )
            return mInstance;

        mInstance = new GenerateData();
        return mInstance;
    }
    /*
    IMEI:868331011992179
    ANDROID ID:5a3b287f2b13bef8   - 16
    SerialNumber:021YLJ212C001879
    SimSerialNumber:898600810110
    * */

    public String generateBootloader( String version, String model){
    	String cpu = generateCpu(); 
        return "Android/"+ cpu + "/" + cpu + ":" + version+"/"+model + "/eng.work."+RandomUtils.getRandomNumbers(RandomUtils.getRandom(5, 8))+"."+
                RandomUtils.getRandom(RandomUtils.getRandom(4, 6));
    }

    public String generateCpu(){
        String[] list = {"x86","ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi",
                "ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi","neon","X10","X20","mips","arm64- v8a","x86_64","mips64"};
        return list[(int)(Math.random()*list.length)];
    }
    public static String getSimStatus(){
        return "5";
    }

    public static String getPhoneType(){
        return "1";
    }
    // 4.3.094.P1.
    public String generateDisplay(String version,String brand)
    {
        String[] P = {"P1","P2","P3","P4","P1","P2"};
        int random = (int)(Math.random()*6);
        int wei = (int)(Math.random()*5);
        String display = "";
        display = version + "."+ RandomUtils.getRandomNumbers(3)+ "."+P[random]+ "."+brand+"."+RandomUtils.getRandomNumbersAndCapitalLetters(wei);
        return display;
    }

    /*
    * 浏览器UA
    * */
    public static String generateUserAgent(String version,String brand)
    {
        String[] pre = {"Dalvik/1.6.0","Dalvik/1.4.0"};
        int index = RandomUtils.getRandom(0, 10);
        if(index > 4)
        {
            return pre[0] + "(Linux; U; " + version +"; " + brand + " Build/" + RandomUtils.getRandomNumbersAndCapitalLetters(5);
        }else{
            return pre[1] + "(Linux; U; " + version +"; " + brand + " Build/"+ RandomUtils.getRandomNumbersAndCapitalLetters(5);
        }
    }
    public  String generateMacAddress()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                int num = (int) (Math.random() * 16);
                stringBuilder.append(Hexadecimal[num]);
            }
            if(i != 5)
                stringBuilder.append(":");
        }
        return stringBuilder.toString();
    }
    public String getVersion(int value){
        String result = "";
        switch (value){
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                result = "2.3.3";
                break;
            case Build.VERSION_CODES.HONEYCOMB:
                result = "3.0";
                break;
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                result = "3.1";
                break;
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                result = "3.2";
                break;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                result = "4.0";
                break;
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                result = "4.0.3";
                break;
            case Build.VERSION_CODES.JELLY_BEAN:
                result = "4.1";
                break;
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                result = "4.2";
                break;
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                result = "4.3";
                break;
            case Build.VERSION_CODES.KITKAT:
                result = "4.4";
                break;
            case Build.VERSION_CODES.KITKAT_WATCH:
                result = "4.4W";
                break;
            //case Build.VERSION_CODES.LOLLIPOP:
              //  result = "5.0";
                //break;
            default:
                result = "4.2.2";
        }
        return result;
    }
    public  String getAndroidVersion(int value){
        return getVersion(value);
    }

    public  String getSdk(){
        int value = RandomUtils.getRandom(14, 20);
        if(value >= 21){
            value = 19;
        }
        return ""+value;
    }
    // TAC + FAC + SNR + SP = 15
    public String generateImei(String pre)
    {
        String imei = ""+pre + RandomUtils.getRandomNumbers(4) + "03" + RandomUtils.getRandomNumbers(6);
        imei = ""+imei + IMEIUtils.genCode(imei);
        return imei;
    }
    public  String generateBluetooth(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                int num = (int) (Math.random() * 16);
                stringBuilder.append(Hexadecimal[num]);
            }
            if(i != 5)
                stringBuilder.append(":");
        }
        return stringBuilder.toString();
    }
    public String generateSimSerial(int carrType)
    {//中国移动的为：898600；898602 ，中国联通的为：898601、898609，中国电信898603，898606。
        String pre = "";
        int random = (int)(Math.random() * 10);
        if( PhoneBrandEngine.CHINA_TELECOM == carrType )
        {
            if(random%2 == 0)
                pre = "898603";
            else
                pre = "898606";
        }else if(PhoneBrandEngine.CHINA_UNICOM == carrType)
        {
            if(random%2 == 0)
                pre = "898601";
            else
                pre = "898609";
        }else
        {
            if(random%2 == 0)
                pre = "898600";
            else
                pre = "898602";
        }
        return pre + RandomUtils.getRandomNumbers(14);
    }
    public String generateBuildId(){
    	return ""+RandomUtils.getRandomCapitalLetters(3)+RandomUtils.getRandomNumbers(2)+RandomUtils.getRandomCapitalLetters(1);
    }
    public String generateOsName()
    {
    	return "Linux";
    }
    public String generateOsArch(String cpu_abi){
    	return cpu_abi;
    }
    public String generateOsVersion(){
    	String[] linux = {"3.4.0","3.4.1","3.4.2","3.5.0","3.5.1","3.5.1","3.6.0","3.6.1","3.6.2"};
    	return linux[RandomUtils.getRandom(0, linux.length)] + " " +RandomUtils.getRandomNumbersAndLowerCaseLetters(8);
    }
    
    public String generateDisplay(){
    	return "";
    }
    public String generateIncremental(String model,String display,String time){
    	return model+"."+display + "." + time;
    }
    public String generateBuildHost(){
    	return "android-test-4.mtv.corp.google.com";
    }
    public String generateFingerprint(BrandBuildInfo info){
    	return info.getBrand()+"/"+info.getModel()+"/"+info.getProduct()+":"+info.getAndroidVersion()+"/"
    			+info.getId()+"/"+info.getIncrement()+":"+info.getType()+"/"+info.getTags();
    }
    public  String generateAndroidSerial()
    {
        return RandomUtils.getRandomNumbersAndCapitalLetters(16);
    }
    public String generateAndroidId()
    {
        return RandomUtils.getRandomNumbersAndCapitalLetters(16);
    }
    /* MCC(3) + MNC(2) + MSIN(10)
    * MCC：Mobile Country Code，移动国家码，MCC的资源由国际电联（ITU）统一分配和管理，唯一识别移动用户所属的国家，共3位，中国为460;
        MNC:Mobile Network Code，移动网络码，2~3位，中国移动系统使用00、02、07，中国联通GSM系统使用01、06，中国电信CDMA系统使用03、05，中国铁通系统使用20。
         if(operatorString.equals("46000") || operatorString.equals("46002")) China Mobile
        //中国移动
    else if(operatorString.equals("46001")) China Unicom
        //中国联通
    else if(operatorString.equals("46003")) China Telecom
        //中国电信
        chinanetcom
    * */
    public String generateImsi(int carrType)
    {
        String carrior = "";
        String[] telecom={"03","05"};
        String[] unicom={"01","06"};
        String[] mobile={"00","02","07"};
        if( PhoneBrandEngine.CHINA_TELECOM == carrType )
        {
            int random = (int)(Math.random() * 2);
            carrior = telecom[random];
        }else if(PhoneBrandEngine.CHINA_UNICOM == carrType)
        {
            int random = (int)(Math.random() * 2);
            carrior = unicom[random];
        }else
        {
            int random = (int)(Math.random() * 3);
            carrior = mobile[random];
        }
        return "460"+carrior+ RandomUtils.getRandomNumbers(10);
    }
    public String generateCarrier(int carrType)
    {
        if( PhoneBrandEngine.CHINA_TELECOM == carrType )
        {
            return "China Telecom";
        }else if(PhoneBrandEngine.CHINA_UNICOM == carrType)
        {
            return "China Unicom";
        }else
        {
            return "China Mobile";
        }
    }
    public String generateCarrierCode(int carrType)
    {
        if( PhoneBrandEngine.CHINA_TELECOM == carrType )
        {

            return "46003";
        }else if(PhoneBrandEngine.CHINA_UNICOM == carrType)
        {
            return "46001";
        }else
        {
            int random = (int)(Math.random() * 10);
            if(random % 2 == 0)
                return "46000";
            else
                return "46002";
        }
    }
}


