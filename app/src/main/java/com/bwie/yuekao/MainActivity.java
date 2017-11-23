package com.bwie.yuekao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.yuekao.API.Api;
import com.bwie.yuekao.API.HomeAdapter;
import com.bwie.yuekao.API.Kbean;
import com.bwie.yuekao.API.MyOnItemClickListener;
import com.bwie.yuekao.presenter.Iparsenter;
import com.bwie.yuekao.view.Iview;

public class MainActivity extends AppCompatActivity implements Iview{
    Iparsenter iparsenter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iparsenter = new Iparsenter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        iparsenter.setNews(Api.BASE_PATH,""+2);

    }

    @Override
    public void getData(Kbean kbean) {
        HomeAdapter adapter = new HomeAdapter(MainActivity.this,kbean);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                Toast.makeText(getApplicationContext(), "position" + position + "被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
