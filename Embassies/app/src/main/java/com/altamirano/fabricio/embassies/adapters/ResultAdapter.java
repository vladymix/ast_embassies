package com.altamirano.fabricio.embassies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.altamirano.fabricio.embassies.R;
import com.altamirano.fabricio.embassies.commons.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultAdapter extends BaseAdapter implements Filterable {
    private final LayoutInflater inflater;
    private Filter filter = null;
    private List<Result> mDisplayedItems = new ArrayList<>();
    private List<Result> source;

    public ResultAdapter(Context ctx, List<Result> source) {
        this.source = source;
        this.mDisplayedItems = source;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return mDisplayedItems.size();
    }

    @Override
    public Filter getFilter() {
        if (filter != null) {
            return filter;
        }

        filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                ArrayList<Result> arrayResults = new ArrayList<>();

                synchronized (ResultAdapter.this) {
                    String values = charSequence.toString().toLowerCase();
                    if (values.length() == 0) {
                        results.count = source.size();
                        results.values = source;
                    } else {
                        for (Result doc : source) {
                            if (doc.getTitle().toLowerCase().contains(values)) {
                                arrayResults.add(doc);
                            }
                        }
                        results.count = arrayResults.size();
                        results.values = arrayResults;
                    }
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                try {
                    mDisplayedItems = (ArrayList<Result>) filterResults.values;
                    notifyDataSetChanged();
                } catch (Exception ignore) {
                }
            }
        };
        return filter;
    }

    @Override
    public Result getItem(int position) {
        if (mDisplayedItems.size() > position) {
            return this.mDisplayedItems.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        if (this.mDisplayedItems.size() > 0) {
            return mDisplayedItems.get(i).hashCode();
        }
        return -1;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.item_results, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (this.mDisplayedItems.size() > i) {
            holder.setData(this.mDisplayedItems.get(i));
        }
        return view;
    }

    public static class ViewHolder {

        @BindView(R.id.image_type)
        ImageView image_type;
        @BindView(R.id.tv_location)
        TextView tv_location;
        @BindView(R.id.tv_street)
        TextView tv_street;
        @BindView(R.id.tv_title)
        TextView tv_title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(Result item) {
            if (item != null) {
                tv_location.setText(item.getAddress().getLocality());
                tv_street.setText(item.getAddress().getStreetaddress());
                tv_title.setText(item.getTitle());

                if (item.getTitle().toLowerCase().contains("consulado")) {
                    image_type.setImageResource(R.drawable.ic_consulado);
                } else {
                    image_type.setImageResource(R.drawable.ic_embassy);
                }
            }
        }
    }
}
