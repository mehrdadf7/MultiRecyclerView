package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;
import com.github.mehrdadf7.multirecyclerview.models.ObjectItem;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.viewHolders.BannerViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.ItemViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.SliderViewHolder;

import java.util.List;

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> objects;

    private static final int VIEW_SLIDER = 1;
    private static final int VIEW_BANNER = 2;
    private static final int VIEW_RV_HORIZONTAL = 3;

    public MultiAdapter(List<Object> objects) {
        this.objects = objects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_SLIDER) {
            viewHolder = new SliderViewHolder(layoutInflater.inflate(R.layout.layout_slider, parent, false));
        } else if (viewType == VIEW_BANNER) {
            viewHolder =  new BannerViewHolder(layoutInflater.inflate(R.layout.layout_banner, parent, false));
        } else if (viewType == VIEW_RV_HORIZONTAL) {
            viewHolder =  new ItemViewHolder(layoutInflater.inflate(R.layout.layout_item, parent, false));
        } else {
            viewHolder = null;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SliderViewHolder) {
            List<ObjectSlider> sliders = (List<ObjectSlider>) objects.get(position);
            ((SliderViewHolder) holder).bind(sliders);
        } else if (holder instanceof ItemViewHolder) {
            List<ObjectItem> objectItems = (List<ObjectItem>) objects.get(position);
            ((ItemViewHolder) holder).bind(objectItems);
        } else if (holder instanceof BannerViewHolder) {
            ObjectBanner banner = (ObjectBanner) objects.get(position);
            ((BannerViewHolder) holder).bind(banner);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = objects.get(position);
        if (obj instanceof List && (((List)obj).get(position) instanceof ObjectSlider))
            return VIEW_SLIDER;
        else if (obj instanceof List && (((List)obj).get(position) instanceof ObjectItem))
            return VIEW_RV_HORIZONTAL;
        else if (objects.get(position) instanceof ObjectBanner)
            return VIEW_BANNER;
        else return -1;
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }
}
