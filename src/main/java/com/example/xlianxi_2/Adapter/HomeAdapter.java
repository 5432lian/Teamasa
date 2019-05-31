package com.example.xlianxi_2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xlianxi_2.Bean.Bean;
import com.example.xlianxi_2.DbActivity;
import com.example.xlianxi_2.R;


import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Bean.ResultBean.DataBean> list;

    public HomeAdapter(Context mContext, ArrayList<Bean.ResultBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
        ListItem listItem = new ListItem(inflate);
        return listItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListItem listItem = (ListItem) holder;
        listItem.home_te.setText(list.get(position).getContent());
        Glide.with(mContext).load(list.get(position).getUrl1()).into(listItem.home_img);
        listItem.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclick!=null){
                    onclick.onclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ListItem extends RecyclerView.ViewHolder {

        private ImageView home_img;
        private TextView home_te;

        public ListItem(View itemView) {
            super(itemView);
            home_img = itemView.findViewById(R.id.home_img);
            home_te = itemView.findViewById(R.id.home_te);
        }
    }
    private Onclick onclick;
    public interface Onclick{
        void onclick(int position);
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }
}
