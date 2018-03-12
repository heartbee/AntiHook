package com.example.yixianglin.antidex2jar;

import android.content.Context;

import org.json.JSONArray;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yixianglin on 2018/3/12.
 */

public class ScanAttack {
    private static ScanAttack mInstance=null;
    public static ScanAttack getmInstance(){
        if(mInstance==null){
           synchronized (ScanAttack.class){
               mInstance=new ScanAttack();
           }
        }
        return mInstance;
    }

    //通过检查环境种是否有hook框架进行检测
    private static boolean scanPackage(Context context, String string){
        boolean bool=false;
        try {
            if(context.getPackageManager().getPackageInfo(string,0).equals(string)){
                bool=true;
            }
            return bool;

        }catch (Exception e){
            return false;
        }
    }

    public static boolean xposedInstalled(Context context){
        boolean bool=scanPackage(context,"de.robv.android.xposed.installer");
        return bool;
    }

    public static boolean cydiaInstalled(Context context){
        boolean bool=scanPackage(context,"com.saurik.substrate");
        return bool;
    }


    //通过检查需要保护的方法属性是否由Java变成了native
    private static JSONArray methodToNative(){
        PackHookPlugin packHookPlugin=new PackHookPlugin(2);
        for(ScanMethod.HooKMethod hooKMethod:ScanMethod.a){
            try {
                int modifiers=Class.forName(hooKMethod.classname).getDeclaredMethod(hooKMethod.methodname,hooKMethod.type).getModifiers();
                if(Modifier.isNative(modifiers)){
                    packHookPlugin.addMethod("native",hooKMethod.methodname+"#"+hooKMethod.type);

                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return packHookPlugin.defineMethod();
    }
    //检查函数调用堆栈中是否有hook框架包
    private static boolean checkXposedStackTrace(Context context){
        int i=0;
        try{
            throw new Exception("checkXposedStackTrance");
        }catch (Exception e){
            StackTraceElement[] stackTraceElements=e.getStackTrace();
            int length=stackTraceElements.length;
            boolean bool=false;
            while (i<length){
                StackTraceElement stackTraceElement=stackTraceElements[i];
                String getClassname=stackTraceElement.getClassName();
                String getMethodname=stackTraceElement.getMethodName();
                if(getClassname.trim().equals("de.robv.android.xposed.XposedBridge") && getMethodname.equals("main")){
                    bool=true;
                }
                if(getClassname.trim().equals("de.robv.android.xposed.XposedBridge") && getMethodname.equals("handleHookedMethod")){
                    bool=true;
                }
                i++;
            }
            return bool;
        }
    }

    private static boolean checkCydiaStackTrace(Context context){
        boolean bool=false;
        try {
            throw new Exception("checkCydiaStackTrace");

        }catch (Exception e){
            StackTraceElement[] stackTraceElements=e.getStackTrace();
            int i=0;
            for (StackTraceElement stackTraceElement:stackTraceElements){
                String getClassname=stackTraceElement.getClassName();
                String getMethodname=stackTraceElement.getMethodName();
                if(getClassname.equals("com.android.internal.os.ZygoteInit")){
                    i++;
                    if(i==2){
                        bool=true;
                    }
                }
                if(getClassname.equals("com.saurik.substrate.MS$2") && getMethodname.equals("invoked")){
                    bool=true;
                }
            }
            return bool;
        }
    }

    //通过反射调用hook类，判断是否有hook框架

    private static boolean checkCydiaClass(Context context){
        boolean bool=false;
        Class clazz=null;
        try {
            clazz=ClassLoader.getSystemClassLoader().loadClass("com.saurik.substrate.SubstrateClassLoader");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(clazz!=null){
            bool=true;
        }
        return bool;
    }

    //检查应用的field是否被hook
    private static String xposedFieldInHook(Context context){
        String result=null;
        try {
            Field declaredField=ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedHelpers").getDeclaredField("fieldCache");
            declaredField.setAccessible(true);
            Map map=(Map)declaredField.get(null);
            ArrayList arrayList=new ArrayList();
            arrayList.add(map.keySet());
            result=arrayList.toString();

        }catch (Exception e){

        }
        return result;
    }

    //检查保护的方法是否被xposed hook
    private static String xposedMethodInHook(Context context){
        String jsonArray=null;
        PackHookPlugin packHookPlugin=new PackHookPlugin(1);
        try {
            Field declaredField=ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedBridge").getDeclaredField("sHookedMethodCallbacks");
            declaredField.setAccessible(true);
            Map map=(Map) declaredField.get(null);
            Class clazz=ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedBridge$CopyOnWriteSortedSet");
            Method getSnapshot=clazz.getDeclaredMethod("getSnapshot");
            Iterator iterator=map.entrySet().iterator();
            if(iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Member member = (Member) entry.getKey();
                Object value = entry.getValue();//CopyOnWriteSortedSet
                String methodInHook = ScanMethod.getMethod(member.toString());
                if (!"".equals(methodInHook) && clazz.isInstance(value)) {
                    for (Object object : (Object[]) getSnapshot.invoke(value, new Object[0])) {
                        String classLoader = object.getClass().getClassLoader().toString();
                        if (classLoader.split("\"").length > 1) {
                            packHookPlugin.addMethod(classLoader.split("\"")[1], methodInHook);
                        }
                    }

                }
            }

            JSONArray jsonArray1=packHookPlugin.defineMethod();
            JSONArray methodToNative=methodToNative();
            if(jsonArray1!=null){
                if(methodToNative!=null){
                    for(int i=0;i<methodToNative.length();i++){
                        jsonArray1.put(methodToNative.getJSONObject(i));
                    }
                }
                jsonArray=jsonArray1.toString();
            }else {
                if(methodToNative!=null){
                    jsonArray=methodToNative.toString();
                }
                jsonArray=null;
            }

        }catch (Exception e){}
        return jsonArray;
    }

}
