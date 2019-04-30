package com.github.mehrdadf7.multirecyclerview.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.ItemAdapter;
import com.github.mehrdadf7.multirecyclerview.adapters.MultiAdapter;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;
import com.github.mehrdadf7.multirecyclerview.models.ObjectItem;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView openGithub;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        openGithub   = findViewById(R.id.open_github);

        openGithub  .setOnClickListener(this);
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(this, android.R.anim.fade_in)));
        MultiAdapter adapter = new MultiAdapter(prepareData());
        recyclerView.setAdapter(adapter);
    }

    private List<Object> prepareData() {
        List<Object> objects = new ArrayList<>();

        List<ObjectSlider> sliders = new ArrayList<>();
        String myImageRes = "android.resource://" + getPackageName() + "/" + R.drawable.mehrdad;
        sliders.add(new ObjectSlider(myImageRes));
        sliders.add(new ObjectSlider(myImageRes));
        sliders.add(new ObjectSlider(myImageRes));

        ObjectBanner banner1 = new ObjectBanner(R.color.banner1, "بنر اول");
        ObjectBanner banner2 = new ObjectBanner(R.color.banner2, "بنر دوم");
        ObjectBanner banner3 = new ObjectBanner(R.color.banner3, "بنر سوم");
        ObjectBanner banner4 = new ObjectBanner(R.color.banner4, "بنر چهارم");

        List<ObjectItem> items1 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ObjectItem objectItem = new ObjectItem();
            objectItem.setParentTitle("لیست اول");
            objectItem.setText("متن " + i);
            items1.add(objectItem);
        }

        List<ObjectItem> items2 = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            ObjectItem objectItem = new ObjectItem();
            objectItem.setParentTitle("لیست دوم");
            objectItem.setText("متن " + i);
            items2.add(objectItem);
        }

        List<ObjectItem> items3 = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            ObjectItem objectItem = new ObjectItem();
            objectItem.setParentTitle("لیست سوم");
            objectItem.setText("متن " + i);
            items3.add(objectItem);
        }

        objects.add(sliders);
        objects.add(banner1);
        objects.add(banner2);
        objects.add(items1);
        objects.add(items2);
        objects.add(banner3);
        objects.add(items3);
        objects.add(banner4);

        return objects;
    }

    @Override
    public void onClick(View v) {
        if (v == openGithub) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://github.com/mehrdadf7"));
            Intent.createChooser(intent, "Select Browser");
        }
    }

}
