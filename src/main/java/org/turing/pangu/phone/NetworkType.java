package org.turing.pangu.phone;

public class NetworkType {
    /**
     * The absence of APN..
     * @hide
     */
    public static final int TYPE_NONE        = -1;

    /**
     * The Default Mobile data connection.  When active, all data traffic
     * will use this connection by default.
     */
    public static final int TYPE_MOBILE      = 0;
    /**
     * The Default WIFI data connection.  When active, all data traffic
     * will use this connection by default.
     */
    public static final int TYPE_WIFI        = 1;
    /**
     * An MMS-specific Mobile data connection.  This connection may be the
     * same as {@link #TYPE_MOBILE} but it may be different.  This is used
     * by applications needing to talk to the carrier's Multimedia Messaging
     * Service servers.  It may coexist with default data connections.
     */
    public static final int TYPE_MOBILE_MMS  = 2;
    /**
     * A SUPL-specific Mobile data connection.  This connection may be the
     * same as {@link #TYPE_MOBILE} but it may be different.  This is used
     * by applications needing to talk to the carrier's Secure User Plane
     * Location servers for help locating the device.  It may coexist with
     * default data connections.
     */
    public static final int TYPE_MOBILE_SUPL = 3;
    /**
     * A DUN-specific Mobile data connection.  This connection may be the
     * same as {@link #TYPE_MOBILE} but it may be different.  This is used
     * by applicaitons performing a Dial Up Networking bridge so that
     * the carrier is aware of DUN traffic.  It may coexist with default data
     * connections.
     */
    public static final int TYPE_MOBILE_DUN  = 4;
    /**
     * A High Priority Mobile data connection.  This connection is typically
     * the same as {@link #TYPE_MOBILE} but the routing setup is different.
     * Only requesting processes will have access to the Mobile DNS servers
     * and only IP's explicitly requested via {@link #requestRouteToHost}
     * will route over this interface if a default route exists.
     */
    public static final int TYPE_MOBILE_HIPRI = 5;
    /**
     * The Default WiMAX data connection.  When active, all data traffic
     * will use this connection by default.
     */
    public static final int TYPE_WIMAX       = 6;

    /**
     * The Default Bluetooth data connection. When active, all data traffic
     * will use this connection by default.
     */
    public static final int TYPE_BLUETOOTH   = 7;

    /**
     * Dummy data connection.  This should not be used on shipping devices.
     */
    public static final int TYPE_DUMMY       = 8;

    /**
     * The Default Ethernet data connection.  When active, all data traffic
     * will use this connection by default.
     */
    public static final int TYPE_ETHERNET    = 9;

    /**
     * Over the air Adminstration.
     * {@hide}
     */
    public static final int TYPE_MOBILE_FOTA = 10;

    /**
     * IP Multimedia Subsystem
     * {@hide}
     */
    public static final int TYPE_MOBILE_IMS  = 11;

    /**
     * Carrier Branded Services
     * {@hide}
     */
    public static final int TYPE_MOBILE_CBS  = 12;

    /**
     * A Wi-Fi p2p connection. Only requesting processes will have access to
     * the peers connected.
     * {@hide}
     */
    public static final int TYPE_WIFI_P2P    = 13;

    /** {@hide} */
    public static final int MAX_RADIO_TYPE   = TYPE_WIFI_P2P;

    /** {@hide} */
    public static final int MAX_NETWORK_TYPE = TYPE_WIFI_P2P;

    public static final int DEFAULT_NETWORK_PREFERENCE = TYPE_WIFI;
}
