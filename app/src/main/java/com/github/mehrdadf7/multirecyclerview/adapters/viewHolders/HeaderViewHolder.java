package com.github.mehrdadf7.multirecyclerview.adapters.viewHolders;

import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.databinding.ViewHeaderBinding;
import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.utils.Handlers;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

  //view_header
  private ViewHeaderBinding binding;

  public HeaderViewHolder(ViewHeaderBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(HeaderModel header) {
    binding.setHeaderModel(header);
    Handlers handlers = new Handlers(binding.getRoot().getContext());
    binding.setHandlers(handlers);
  }

}
