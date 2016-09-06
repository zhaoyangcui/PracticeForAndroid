package com.example.sunnny.a11_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sunnny.a11_fragment.word.WordContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(WordContent.WordItem item) {
        Bundle bundle = new Bundle();
        bundle.putString("id",item.id);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.detail,detailFragment).commit();
    }
}
