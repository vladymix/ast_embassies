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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LastSearchAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final List<LastSearch> source;

    public LastSearchAdapter(Context ctx, List<LastSearch> source) {
        this.source = source;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public LastSearch getItem(int i) {
        return source.get(i);
    }

    @Override
    public long getItemId(int i) {
        return source.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.item_last_search, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        holder.setData(this.source.get(i));

        return view;
    }

    public static class ViewHolder {

        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_lat)
        TextView tv_lat;
        @BindView(R.id.tv_lon)
        TextView tv_lon;

        @BindView(R.id.image_type)
        ImageView image_type;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(LastSearch item){
            if(item!=null){
                tv_date.setText(item.getTitle());
                tv_lat.setText(getDatePreview(item.getDate()));
                tv_lon.setText(item.getStreetaddress());

                if(item.getTitle().toLowerCase().contains("consulado")){
                    image_type.setImageResource(R.drawable.ic_consulado);
                }else{
                    image_type.setImageResource(R.drawable.ic_embassy);
                }
            }
        }

        private String getDatePreview(Date date){
            if(date==null){
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm.ss");
            String dateString = sdf.format(date);
            return dateString.toUpperCase();
        }
    }
}
