package com.altamirano.fabricio.embassies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.altamirano.fabricio.embassies.R;
import com.altamirano.fabricio.embassies.commons.LastSearch;
import com.altamirano.fabricio.embassies.commons.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<Result> source;

    public ResultAdapter(Context ctx, List<Result> source) {
        this.source = source;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Result getItem(int i) {
        return source.get(i);
    }

    @Override
    public long getItemId(int i) {
        return source.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.item_results, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        holder.setData(this.source.get(i));

        return view;
    }

    public static class ViewHolder {

        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_location)
        TextView tv_location;
        @BindView(R.id.tv_street)
        TextView tv_street;
        @BindView(R.id.image_type)
        ImageView image_type;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(Result item){
            if(item!=null){
                tv_location.setText(item.getAddress().getLocality());
                tv_street.setText(item.getAddress().getStreetaddress());
                tv_title.setText(item.getTitle());

                if(item.getTitle().toLowerCase().contains("consulado")){
                    image_type.setImageResource(R.drawable.ic_consulado);
                }else{
                    image_type.setImageResource(R.drawable.ic_embassy);
                }
            }
        }
    }
}
