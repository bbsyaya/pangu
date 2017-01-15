package org.turing.pangu.controller.phone.request;

/**
 * Created by turingkuang on 2017/1/7.
 */
public class NeedChangedInfo {
    public String getSdk() {
        return sdk;
    }

    public String getImei() {
        return imei;
    }

    public String getImsi() {
        return imsi;
    }

    public String getMacAddress() {
        return mac;
    }

    public String getBssid() {
        return bssid;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getBrand() {
        return brand;
    }

    public String getManufacture() {
        return manufacture;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBoard() {
        return board;
    }

    public String getOsName() {
        return osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getSimSerial() {
        return simSerial;
    }

    public String getAndroidSerial() {
        return androidSerial;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getUa() {
        return ua;
    }

    public String getIpAddress() {
        return ip;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public void setMacAddress(String macAddress) {
        this.mac = macAddress;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setSimSerial(String simSerial) {
        this.simSerial = simSerial;
    }

    public void setAndroidSerial(String androidSerial) {
        this.androidSerial = androidSerial;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public void setIpAddress(String ipAddress) {
        this.ip = ipAddress;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }
    public String getSimStatus() {
        return simStatus;
    }

    public void setSimStatus(String simStatus) {
        this.simStatus = simStatus;
    }

    public String getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(String phoneStatus) {
        this.phoneStatus = phoneStatus;
    }
    public String getBlueTooth() {
        return blueTooth;
    }

    public void setBlueTooth(String blueTooth) {
        this.blueTooth = blueTooth;
    }

    private String sdk = ""; //19
    private String imei = "";//TAC + FAC + SNR + SP = 15
    private String imsi = "";// MCC+MNC+MSIN
    private String mac = "";//"6C:C4:08:BB:B1:28"
    private String bssid = "";//
    private String ssid = "";// wifi name
    private String phone = "";
    private String width = "";
    private String height = "";
    private String brand = "";
    private String manufacture = "";
    private String carrier = "";
    private String carrierCode = "";
    private String countryCode = "CN";
    private String board = "";
    private String model = "";
    private String osName = "";
    private String osArch = "";
    private String osVersion = "";
    private String latitude = ""; // 纬度
    private String longitude = "";// 经度
    private String simSerial = "";
    private String androidId = "";
    private String androidSerial = "";
    private String androidVersion = "";
    private String ua = "";
    private String ip = "";
    private String display = "";//显示版本
    private String simStatus = ""; // 手机卡状态
    private String phoneStatus = ""; // 手机状态
    private String blueTooth = ""; //蓝牙地址
}
