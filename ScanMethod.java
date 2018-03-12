package com.example.yixianglin.antidex2jar;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Debug;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.view.Display;
import java.io.File;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ScanMethod {
    public static List<HooKMethod> a;

    public static class HooKMethod {
        public int index;
        public String classname;
        public String methodname;
        public Class[] type;

        public HooKMethod(int index, String classname, String methodname, Class[] type) {
            this.index=index;
            this.classname=classname;
            this.methodname=methodname;
            this.type=type;

        }
    }

    static {
        List arrayList = new ArrayList();
        a = arrayList;
        arrayList.add(new HooKMethod(0, System.class.getName(), "getString", new Class[]{ContentResolver.class, String.class}));
        a.add(new HooKMethod(1, Editor.class.getName(), "putString", new Class[]{String.class, String.class}));
        a.add(new HooKMethod(2, "android.os.SystemProperties", "get", new Class[]{String.class, String.class}));
        a.add(new HooKMethod(3, TelephonyManager.class.getName(), "getSubscriberId", new Class[0]));
        a.add(new HooKMethod(4, TelephonyManager.class.getName(), "getLine1Number", new Class[0]));
        a.add(new HooKMethod(5, TelephonyManager.class.getName(), "getDeviceId", new Class[0]));
        a.add(new HooKMethod(6, TelephonyManager.class.getName(), "getVoiceMailNumber", new Class[0]));
        a.add(new HooKMethod(7, TelephonyManager.class.getName(), "getSimSerialNumber", new Class[0]));
        a.add(new HooKMethod(8, TelephonyManager.class.getName(), "getNetworkCountryIso", new Class[0]));
        a.add(new HooKMethod(9, TelephonyManager.class.getName(), "getNetworkOperatorName", new Class[0]));
        a.add(new HooKMethod(10, TelephonyManager.class.getName(), "getSimOperatorName", new Class[0]));
        a.add(new HooKMethod(11, TelephonyManager.class.getName(), "getPhoneType", new Class[0]));
        a.add(new HooKMethod(12, TelephonyManager.class.getName(), "getNetworkType", new Class[0]));
        a.add(new HooKMethod(13, TelephonyManager.class.getName(), "getCellLocation", new Class[0]));
        a.add(new HooKMethod(14, TelephonyManager.class.getName(), "getDeviceSoftwareVersion", new Class[0]));
        a.add(new HooKMethod(15, WifiInfo.class.getName(), "getMacAddress", new Class[0]));
        a.add(new HooKMethod(16, WifiInfo.class.getName(), "getIpAddress", new Class[0]));
        a.add(new HooKMethod(17, WifiInfo.class.getName(), "getSSID", new Class[0]));
        a.add(new HooKMethod(18, WifiInfo.class.getName(), "getBSSID", new Class[0]));
        a.add(new HooKMethod(19, WifiManager.class.getName(), "getConnectionInfo", new Class[0]));
        a.add(new HooKMethod(20, WifiManager.class.getName(), "getDhcpInfo", new Class[0]));
        a.add(new HooKMethod(21, WifiManager.class.getName(), "getScanResults", new Class[0]));
        a.add(new HooKMethod(22, NetworkInterface.class.getName(), "getNetworkInterfaces", new Class[0]));
        a.add(new HooKMethod(23, Proxy.class.getName(), "getHost", new Class[]{Context.class}));
        a.add(new HooKMethod(24, Proxy.class.getName(), "getPort", new Class[]{Context.class}));
        a.add(new HooKMethod(25, System.class.getName(), "getProperty", new Class[]{String.class}));
        a.add(new HooKMethod(26, PackageManager.class.getName(), "getInstallerPackageName", new Class[]{String.class}));
        a.add(new HooKMethod(27, PackageManager.class.getName(), "getPackageInfo", new Class[]{String.class, Integer.TYPE}));
        a.add(new HooKMethod(28, PackageManager.class.getName(), "getInstalledPackages", new Class[]{Integer.TYPE}));
        a.add(new HooKMethod(29, File.class.getName(), "getAbsolutePath", new Class[0]));
        a.add(new HooKMethod(30, ActivityManager.class.getName(), "getRunningTasks", new Class[]{Integer.TYPE}));
        a.add(new HooKMethod(31, ComponentName.class.getName(), "getPackageName", new Class[0]));
        a.add(new HooKMethod(32, Modifier.class.getName(), "isNative", new Class[]{Integer.TYPE}));
        a.add(new HooKMethod(33, Debug.class.getName(), "isDebuggerConnected", new Class[0]));
        a.add(new HooKMethod(34, Process.class.getName(), "myPid", new Class[0]));
        a.add(new HooKMethod(35, TimeZone.class.getName(), "getRawOffset", new Class[0]));
        a.add(new HooKMethod(36, TimeZone.class.getName(), "getDSTSavings", new Class[0]));
        a.add(new HooKMethod(37, Locale.class.getName(), "getLanguage", new Class[0]));
        a.add(new HooKMethod(38, Intent.class.getName(), "getIntent", new Class[]{String.class}));
        a.add(new HooKMethod(39, Intent.class.getName(), "getExtra", new Class[]{String.class}));
        a.add(new HooKMethod(40, Intent.class.getName(), "getBooleanExtra", new Class[]{String.class, Boolean.TYPE}));
        a.add(new HooKMethod(41, Intent.class.getName(), "getByteExtra", new Class[]{String.class, Byte.TYPE}));
        a.add(new HooKMethod(42, Intent.class.getName(), "getShortExtra", new Class[]{String.class, Short.TYPE}));
        a.add(new HooKMethod(43, Intent.class.getName(), "getCharExtra", new Class[]{String.class, Character.TYPE}));
        a.add(new HooKMethod(44, Intent.class.getName(), "getIntExtra", new Class[]{String.class, Integer.TYPE}));
        a.add(new HooKMethod(45, Intent.class.getName(), "getLongExtra", new Class[]{String.class, Long.TYPE}));
        a.add(new HooKMethod(46, Intent.class.getName(), "getFloatExtra", new Class[]{String.class, Float.TYPE}));
        a.add(new HooKMethod(47, Intent.class.getName(), "getDoubleExtra", new Class[]{String.class, Double.TYPE}));
        a.add(new HooKMethod(48, Intent.class.getName(), "getStringExtra", new Class[]{String.class}));
        a.add(new HooKMethod(49, Display.class.getName(), "getWidth", new Class[0]));
        a.add(new HooKMethod(50, Display.class.getName(), "getHeight", new Class[0]));
        a.add(new HooKMethod(51, BluetoothAdapter.class.getName(), "getAddress", new Class[0]));
        a.add(new HooKMethod(52, Secure.class.getName(), "getString", new Class[]{ContentResolver.class, String.class}));
        a.add(new HooKMethod(53, ActivityManager.class.getName(), "getMemoryInfo", new Class[]{MemoryInfo.class}));
        a.add(new HooKMethod(54, StatFs.class.getName(), "getBlockSize", new Class[0]));
        a.add(new HooKMethod(55, StatFs.class.getName(), "getBlockSizeLong", new Class[0]));
        a.add(new HooKMethod(56, StatFs.class.getName(), "getBlockCount", new Class[0]));
        a.add(new HooKMethod(57, StatFs.class.getName(), "getBlockCountLong", new Class[0]));
        a.add(new HooKMethod(58, StatFs.class.getName(), "getAvailableBlocks", new Class[0]));
        a.add(new HooKMethod(59, StatFs.class.getName(), "getAvailableBlocksLong", new Class[0]));
        a.add(new HooKMethod(60, Location.class.getName(), "getLatitude", new Class[0]));
        a.add(new HooKMethod(61, Location.class.getName(), "getLongitude", new Class[0]));
        a.add(new HooKMethod(62, InetAddress.class.getName(), "isLoopbackAddress", new Class[0]));
        a.add(new HooKMethod(63, "android.os.SystemProperties", "get", new Class[]{String.class}));
    }

    public static String getMethod(String string){
        for(HooKMethod hooKMethod:a){
            if(string.contains(hooKMethod.classname) && string.contains(hooKMethod.methodname)){
                Class[] clsArr=hooKMethod.type;
                StringBuilder stringBuilder=new StringBuilder();
                for(Class cls:clsArr){
                    if(stringBuilder.length()>0){
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(cls.getName());
                }
                if(string.contains(stringBuilder.toString())){
                    return hooKMethod.classname+"#"+hooKMethod.methodname;
                }
            }

        }
        return "";

    }


}

