package com.example.sunnny.a5_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list.add("lily");
        list.add("rosy");
        list.add("cindy");
        MyRVAdapter myRVAdapter = new MyRVAdapter();
        recyclerView.setAdapter(myRVAdapter);
    }

    class MyRVAdapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            Holder holder = new Holder(LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item,parent,false));
            return holder;

        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class Holder extends RecyclerView.ViewHolder{
        public TextView textView;
        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
