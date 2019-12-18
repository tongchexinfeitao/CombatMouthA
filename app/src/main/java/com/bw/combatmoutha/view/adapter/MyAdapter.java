package com.bw.combatmoutha.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.combatmoutha.R;
import com.bw.combatmoutha.model.bean.Bean;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private List<Bean.ResultBean> list;

    public MyAdapter(List<Bean.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //加载布局
        View inflate = View.inflate(viewGroup.getContext(), R.layout.item, viewGroup);
        //直接返回  MyViewHolder
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //获取数据
        Bean.ResultBean resultBean = list.get(i);

        //绑定数据
        myViewHolder.name.setText(resultBean.getCommodityName());
        // TODO: 2019/12/18 price 价格必须加上引号
        myViewHolder.price.setText("" + resultBean.getPrice());

        myViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);

        ViewGroup.LayoutParams layoutParams = myViewHolder.itemView.getLayoutParams();
        layoutParams.height = new Random().nextInt(400) + 200;
        myViewHolder.imageView.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}
