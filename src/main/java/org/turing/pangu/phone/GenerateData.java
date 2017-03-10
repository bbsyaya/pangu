package org.turing.pangu.phone;

import org.turing.pangu.utils.IMEIUtils;
import org.turing.pangu.utils.RandomUtils;


/**
 * Created by turingkuang on 2017/1/7.
 */
/*
    产生数据类
* */
public class GenerateData {
    public int netType = 1;// 1:中国移动，2：中国联通 3：中国电信
    private int sdk = 0;
    public static final String[] LETTERS = {"a","b","c","d","e","f","g","h","i","j",
            "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static final String[] NUMBER = {"1","2","3","4","5","6","7","8","9","0"};

    public static final String[] LETTERSANDNUMBER = {"1","2","3","4"
            ,"5","6","7","8","9","0","a","b","c","d","e","f","g","h","i","j",
            "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4"
            ,"5","6","7","8","9","0"};

    public static final String[] Hexadecimal = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public static final String[] MODEL = {"三星Galaxy SIII","魅族MX3","三星Galaxy Note II","HUAWEI MT7-TL00"
            ,"红米手机1s","Coolpad 8670","OPPO 3007","华为T8830pro","HUAWEI G750-T20","HUAWEI Y535D-C00","HUAWEI G750-T01"
            ,"Nexus 5","华为荣耀3C","DOOV S2","OPPO R811","华为 S8-701u","金立S5.1","ZTE A880","vivo Y23L","K-Touch T91",
            "酷派8730","Lenovo A880","MI 2S","努比亚NX505J","三星W999","OPPO R823T","魅族MX2","HTC M8t","金立V185","OPPO R1s"
            ,"Redmi Note 2","Lenovo S890","SAMSUNG GT-S5830","Lenovo A770e","HUAWEI G6-C00","Lenovo A360t"
            ,"vivo S7t","金立GN700W","Lenovo A820","OPPO Finder(X907)","HTC 802d","华为荣耀Che1-CL10","Coolpad 9190L",
            "Lenovo A828t","索尼LT22i(Xperia P)","ZTE Q701C","OPPO X9007","HUAWEI T8950","Coolpad 8297D","Nexus 7","TCL P728M"
            ,"红米1S","三星Galaxy Note III","HUAWEI G610-C00","HTC D820u","金立GN708W","HUAWEI P7-L09","三星I8552（Galaxy Win）"
            ,"HUAWEI P7-L07","魅族MX M031","努比亚NX403A","vivo Y22L","Coolpad 8297","vivo S9","OPPO R831t","三星Galaxy Ace"
            ,"Lenovo A830","HUAWEI G700-U00","vivo Xplay3S","中兴红牛（青春版）","vivo E3","海信EG966","MI 3","HM 1S"
            ,"索尼 LT26ii(Xperia SL)","HUAWEI HN3-U01","Coolpad 8297-T01","HUAWEI C8817L","Lenovo A890e","OPPO R831S","三星T800"
            ,"OPPO X909","金立GN708T","OPPO R821t","HTC D820t","Lenovo K50-T5","HUAWEI C8815","华为 H60-L01","K-Touch T789"
            ,"HM 2A","Nexus 6P","nubia NX501","Coolpad 8675"};

    public ChangeDeviceInfo info = new ChangeDeviceInfo();
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
    public ChangeDeviceInfo GenerateAllInfo()
    {
        int random = (int)(Math.random() * 100);
        if(random % 5 == 0)
        {
            netType = 3;
        }else if(random % 4 == 0){
            netType = 2;
        }else
        {
            netType = 1;
        }
        generateAndroidVer();
        generateDisplay();
        info.setImei(generateImei());
        info.setAndroidSerial(generateAndroidSerial());
        info.setAndroidId(generateAndroidId());
        info.setCarrier(generateCarrier());
        info.setCarrierCode(generateCarrierCode());
        info.setImsi(generateImsi());
        info.setMac(generateMacAddress());
        info.setBlueTooth(generateBluetooth());
        info.setBssid(info.getMac());
        info.setPhone(generatePhoneNumber());
        info.setSsid(generateSsid());
        generateBrands();
        info.setUa(generateUserAgent(info.getAndroidVersion(),info.getBrand()));
        info.setSimStatus(getSimStatus());
        info.setPhoneStatus(getPhoneType());
        info.setSimSerial(generateSimSerial());

        info.setDevice(generateProduct());
        info.setBoard(info.getDevice());
        info.setProduct(info.getDevice());
        info.setCpuABI(generateCpu());
        info.setBootloader(generateBootloader());
        return info;
    }
    private String generateBootloader(){
        return "Android/"+ info.getCpuABI() + "/" + info.getCpuABI() + ":" + info.getAndroidVersion()+"/"+info.getModel() + "/eng.work."+RandomUtils.getRandomNumbers(8)+"."+
                RandomUtils.getRandom(6);
    }

    private String generateCpu(){
        String[] list = {"x86","ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi",
                "ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi","ARM","armeabi-v7a","armeabi","neon","X10","X20","mips","arm64- v8a","x86_64","mips64"};
        return list[(int)(Math.random()*list.length)];
    }
    private String generateProduct()
    {
        int random = (int)(Math.random()*10) + 2;
        return RandomUtils.getRandomNumbersAndLetters(random);
    }
    private static String getSimStatus(){
        return "5";
    }

    private static String getPhoneType(){
        return "1";
    }
    // 4.3.094.P1.
    private String generateDisplay(String version,String brand)
    {
        String[] P = {"P1","P2","P3","P4","P1","P2"};
        int random = (int)(Math.random()*6);
        int wei = (int)(Math.random()*5);
        String display = "";
        display = version + "."+ RandomUtils.getRandomNumbers(3)+ "."+P[random]+ "."+brand+"."+RandomUtils.getRandomNumbersAndCapitalLetters(wei);
        return display;
    }
    private static String generateBrand(String model){
        if(model.contains("vivo")){
            return "vivo";
        }else if(model.contains("Lenovo")){
            return "Lenovo";
        }else if(model.contains("三星")){
            return "samsung";
        }else if(model.contains("HUAWEI")){
            return "Huawei";
        }else if(model.contains("Nexus")){
            return "google";
        }else if(model.contains("ZTE")){
            return "ZTE";
        }else if(model.contains("金立")){
            return "GIONEE";
        }else if(model.contains("乐视")){
            return "Letv";
        }else if(model.contains("魅族")){
            return "MEIZU";
        }else if(model.contains("OPPO")){
            return "OPPO";
        }else if(model.contains("酷派")||model.contains("Coolpad")){
            return "Coolpad";
        }else if(model.contains("米") || model.contains("MI")){
            return "Xiaomi";
        }else if(model.contains("HTC")){
            return "HTC";
        }else {
            return "Coolpad";
        }
    }

    private static String generateManufacturer(String model){
    	return generateBrand(model);
    }

    private static String generateModel(){
        int num = (int) (Math.random() * MODEL.length);
        return MODEL[num];
    }
    /*
    * 浏览器UA
    * */
    private static String generateUserAgent(String version,String brand)
    {
        String[] pre = {"Dalvik/1.6.0","Dalvik/1.4.0"};
        int index = (int)Math.random() *10;

        if(index > 4)
        {
            return pre[0] + "(Linux; U; " + version +"; " + brand + " Build/" + RandomUtils.getRandomNumbersAndCapitalLetters(5);
        }else{
            return pre[1] + "(Linux; U; " + version +"; " + brand + " Build/"+ RandomUtils.getRandomNumbersAndCapitalLetters(5);
        }
    }
    private void generateBrands()
    {// brand = Manufacture samsuing
        // MODEL
        info.setModel(generateModel());
        info.setManufacture(generateManufacturer(info.getModel()));
        info.setBrand(generateBrand(info.getModel()));
        info.setDisplay(generateDisplay(info.getAndroidVersion(),info.getBrand()));
    }
    private void generateDisplay()
    {
        Integer width = 0;
        Integer height = 0;
        int random = (int)(Math.random() * 1000);
        if(random % 23 == 0){
            width = 1200;
            height = 1920;
        }else if(random % 22 == 0){
            width = 256;
            height = 320;
        }else if(random % 21 == 0){
            width = 320;
            height = 480;
        }else if(random % 18 == 0){
            width = 600;
            height = 1024;
        }else if(random % 16 == 0){
            width = 480;
            height = 854;
        }else if(random % 15 == 0){
            width = 480;
            height = 960;
        }else if(random % 12 == 0){
            width = 768;
            height = 1024;
        }else if(random % 10 == 0){
            width = 544;
            height = 960;
        }else if(random % 8 == 0){
            width = 800;
            height = 1280;
        }else if(random % 5 == 0){
            width = 1200;
            height = 1920;
        }else if(random % 4 == 0){
            width = 1280;
            height = 800;
        }else if(random % 3 == 0){
            width = 1080;
            height = 1920;
        }else if(random % 2 == 0){
            width = 720;
            height = 1280;
        }else{
            width = 540;
            height = 960;
        }
        info.setWidth(width.toString());
        info.setHeight(height.toString());
    }
    // wifi name
    private  String generateSsid()
    {
        String[] wifiName = {"TPLINK","ChinaNet-","CMCC-",""};
        int random = (int)(Math.random() * 4);
        int count = (int)(Math.random() * 10);
        return wifiName[random] + RandomUtils.getRandomNumbersAndLowerCaseLetters(count);
    }
    private  String generateMacAddress()
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
    private String getVersion(int value){
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
    private  String getSysVersion(int value){
        return getVersion(value);
    }

    private  String getSysVersionValue(){
        int value = (int) (Math.random() * 11 + 11);
        if(value >= 21){
            value = 19;
        }
        sdk = value;
        return value+"";
    }
    private void generateAndroidVer()
    {
        info.setSdk(getSysVersionValue());
        info.setAndroidVersion(getSysVersion(sdk));
    }
    // TAC + FAC + SNR + SP = 15
    private String generateImei()
    {
        String imei = "86" + RandomUtils.getRandomNumbers(4) + "03" + RandomUtils.getRandomNumbers(6);
        imei = IMEIUtils.genCode(imei);
        return imei;
    }
    private  String generateBluetooth(){
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
    private String generateSimSerial()
    {//中国移动的为：898600；898602 ，中国联通的为：898601、898609，中国电信898603，898606。
        String pre = "";
        int random = (int)(Math.random() * 10);
        if( netType == 3 )
        {
            if(random%2 == 0)
                pre = "898603";
            else
                pre = "898606";
        }else if(netType == 2)
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
    private  String generateAndroidSerial()
    {
        return RandomUtils.getRandomNumbersAndCapitalLetters(16);
    }
    private String generateAndroidId()
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
    private String generateImsi()
    {
        String carrior = "";
        String[] telecom={"03","05"};
        String[] unicom={"01","06"};
        String[] mobile={"00","02","07"};
        if( netType == 3 )
        {
            int random = (int)(Math.random() * 2);
            carrior = telecom[random];
        }else if(netType == 2)
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
    private String generateCarrier()
    {
        if( netType == 3 )
        {
            return "China Telecom";
        }else if(netType == 2)
        {
            return "China Unicom";
        }else
        {
            return "China Mobile";
        }
    }
    private String generateCarrierCode()
    {
        if( netType == 3 )
        {

            return "46003";
        }else if(netType == 2)
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
    private String generatePhoneNumber()
    {
        String phone = "";
        String carrior = "";
        String[] telecom={"133","153","180","181","189","177","173"};
        String[] unicom={"130","131","132","155","156","145","185","186","176","185"};
        String[] mobile={"134","135","136","137","138","139","150","151","152","158","157","159","182","183","184","187","188","147","178","184"};

        if( netType == 3 )
        {
        	carrior = "电信";
        }else if(netType == 2)
        {
        	carrior = "联通";
        }else
        {
        	carrior = "移动";
        }
        //phone = phone + carrior;
        
        //phone = phone + RandomUtils.getRandomNumbers(8);
        return phone;
    }
}


