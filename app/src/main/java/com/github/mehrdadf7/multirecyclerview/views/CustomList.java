package com.github.mehrdadf7.multirecyclerview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.ItemAdapter;
import com.github.mehrdadf7.multirecyclerview.models.ObjectItem;

import java.util.List;

public class CustomList extends LinearLayoutCompat {

    private AppCompatTextView title;
    private RecyclerView recyclerView;

    public CustomList(Context context) {
        super(context);
        init();
    }

    public CustomList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.view_custom_list, this, true);
        title        = rootView.findViewById(R.id.title);
        recyclerView = rootView.findViewById(R.id.recyclerView);
    }

    public void setOnTitleClickListener(OnClickListener onClickListener) {
        title.setOnClickListener(onClickListener);
    }

    public void setTitle(String titleString) {
        title.setText(titleString);
    }

    public String getTextTitle() {
        return title.getText().toString();
    }

    public void attachList(List<ObjectItem> objects) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
        ItemAdapter itemAdapter = new ItemAdapter(objects);
        recyclerView.setAdapter(itemAdapter);
    }

}
