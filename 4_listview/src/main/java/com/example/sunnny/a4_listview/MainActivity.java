package com.example.sunnny.a4_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        ArrayList<Map<String,String>> arrayList = new ArrayList<>();
        HashMap<String,String> map_1 = new HashMap<>();
        HashMap<String,String> map_2 = new HashMap<>();
        HashMap<String,String> map_3 = new HashMap<>();
        map_1.put("name","lily");
        map_1.put("classno","01");
        map_1.put("id","2014011444");
        map_2.put("name","rosy");
        //
        map_2.put("classno","02");
        map_2.put("id","2014011443");
        map_3.put("name","cindy");
        map_3.put("classno","03");
        map_3.put("id","2014011442");
        arrayList.add(map_1);
        arrayList.add(map_2);
        arrayList.add(map_3);
        simpleAdapter = new SimpleAdapter(this,arrayList,R.layout.list_item,new String[]{"name","classno","id"},new int[]{R.id.name,R.id.classno,R.id.id});
        listView.setAdapter(simpleAdapter);

    }
}
