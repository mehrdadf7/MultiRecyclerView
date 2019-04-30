package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;

public class BannerViewHolder extends RecyclerView.ViewHolder {

    //layout_banner
    private ImageView imageView;
    private AppCompatTextView textView;

    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textView  = itemView.findViewById(R.id.textView);
    }

    public void bind(final ObjectBanner banner) {
        textView.setText(banner.getText());
        imageView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), banner.getBackgroundColor()));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), banner.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
