package com.github.mehrdadf7.multirecyclerview.viewHolders;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.ObjectItem;
import com.github.mehrdadf7.multirecyclerview.views.CustomList;

import java.util.ArrayList;
import java.util.List;

public class ItemViewHolder extends RecyclerView.ViewHolder {

  //layout_item
  private CustomList customList;

  public ItemViewHolder(@NonNull View itemView) {
    super(itemView);
    customList = itemView.findViewById(R.id.customList);
  }

  public void bind(ArrayList<ObjectItem> objects) {
    customList.attachList(objects);
    customList.setTitle(objects.get(0).getParentTitle());
    customList.setOnTitleClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(itemView.getContext(), customList.getTextTitle(), Toast.LENGTH_SHORT).show();
      }
    });
  }

}
