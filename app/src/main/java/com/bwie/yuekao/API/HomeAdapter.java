package com.bwie.yuekao.API;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.yuekao.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 周旋
 * 2017/11/23  10:31
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    Kbean kbean;
    private MyOnItemClickListener itemClickListener;
    LayoutInflater inflater;
    private final  int TYPE_1 = 0;
    private final  int TYPE_6 = 5;

    public HomeAdapter(Context context,  Kbean kbean) {
        this.context = context;
        this.kbean = kbean;
        inflater = LayoutInflater.from(context);
    }
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_1;
        }else{
            return TYPE_6;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_1:
                View view1 = inflater.inflate(R.layout.item_banner,parent,false);
                MyViewHolder1 holder1 = new MyViewHolder1(view1);
                return holder1;
            case TYPE_6:
                View view = inflater.inflate(R.layout.item_home,parent,false);
                MyViewHolder6 holder = new MyViewHolder6(view);
                return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_1:
                MyViewHolder1 holder1 = (MyViewHolder1) holder;
                holder1.mbanner.setImageLoader(new GlideImageLoader());
                List<String> bannerList = new ArrayList<>();
                for (int i = 0; i < kbean.getRet().getList().size(); i++) {
                    bannerList.add(kbean.getRet().getList().get(i).getPic());
                }
                holder1.mbanner.setImages(bannerList);
                holder1.mbanner.start();
                holder1.mbanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(context,"第几张"+position,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case TYPE_6:
                MyViewHolder6 myViewHolder =(MyViewHolder6) holder;
               ((MyViewHolder6) holder).tv.setText(kbean.getRet().getList().get(position).getTitle());
                Uri uri =  Uri.parse(kbean.getRet().getList().get(position).getPic());
                ((MyViewHolder6) holder).sdv.setImageURI(uri);
                if (itemClickListener != null) {
                    myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            itemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                        }
                    });
                }
                break;
        }
    }

    @Override
    public int getItemCount() {

        return kbean.getRet().getList().size();
    }

    class MyViewHolder6 extends RecyclerView.ViewHolder{
       private TextView tv;
        private SimpleDraweeView sdv;
        public MyViewHolder6(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            sdv = itemView.findViewById(R.id.main_simple_drawee_view);
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{
        private Banner mbanner;
        public MyViewHolder1(View itemView) {
            super(itemView);
            mbanner = (Banner)itemView.findViewById(R.id.banner);
        }
    }
}