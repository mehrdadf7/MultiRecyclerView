package com.github.mehrdadf7.multirecyclerview.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class BannerViewHolder extends RecyclerView.ViewHolder {

  //layout_banner
  private ImageView imageView;

  public BannerViewHolder(@NonNull View itemView) {
    super(itemView);
    imageView = itemView.findViewById(R.id.imageView);
  }

  public void bind(final ObjectBanner banner) {

    Picasso.get().load(banner.getImageUrl()).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageView);

    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(itemView.getContext(), "بنر " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
      }
    });

  }
}
