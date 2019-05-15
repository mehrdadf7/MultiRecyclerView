package com.github.mehrdadf7.multirecyclerview.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.ObjectItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.VH> {

  private ArrayList<ObjectItem> list;

  public ItemAdapter(ArrayList<ObjectItem> list) {
    this.list = list;
  }

  @NonNull
  @Override
  public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new VH(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_item_row, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull VH holder, int position) {
    holder.bind(list.get(position));
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class VH extends RecyclerView.ViewHolder {

    private LinearLayoutCompat parent;
    private AppCompatTextView text, counter;

    public VH(@NonNull View itemView) {
      super(itemView);
      parent = itemView.findViewById(R.id.parent);
      counter = itemView.findViewById(R.id.tv_counter);
      text = itemView.findViewById(R.id.text);
    }

    public void bind(final ObjectItem object) {

      Random random = new Random();
      counter.getBackground().setColorFilter(
          Color.rgb(
              random.nextInt(255),
              random.nextInt(255),
              random.nextInt(255)
          ), PorterDuff.Mode.SRC_ATOP
      );
      counter.setText(getAdapterPosition() + 1 + "");
      text.setText(object.getText());

      parent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(itemView.getContext(),
              object.getParentTitle() + ", " + object.getText(),
              Toast.LENGTH_SHORT).show();
        }
      });

    }

  }

}
