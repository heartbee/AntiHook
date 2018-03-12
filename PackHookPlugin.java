package com.example.yixianglin.antidex2jar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yixianglin on 2018/3/12.
 */

public class PackHookPlugin {
    private int a;
    private Map<String,List<String>> methodDesciption;
    public PackHookPlugin(int i){
        this.methodDesciption=new HashMap();
        this.a=i;
    }

    public void addMethod(String key,String value){
        if(!this.methodDesciption.containsKey(key)){
            List arrayList=new ArrayList();
            arrayList.add(value);
            this.methodDesciption.put(key,arrayList);
        }else if(!((List)this.methodDesciption.get(key)).contains(value)){
            ((List)this.methodDesciption.get(key)).add(value);
        }
    }

    public JSONArray defineMethod(){
        if(this.methodDesciption.isEmpty()){
            return null;
        }

        JSONArray jsonArray=new JSONArray();
        for(String key:this.methodDesciption.keySet()){
            JSONObject jsonObject=new JSONObject();
            JSONArray jsonArray1=new JSONArray((Collection)this.methodDesciption.get(key));
            try {
                jsonObject.put("function",jsonArray1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;

    }
}
