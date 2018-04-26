package com.ccs.app.mvp.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ccs.app.mvp.R;
import com.ccs.app.mvp.utils.ModelProviders;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int i = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MyFragment())
                .commit();

        final MyModel myModel = ModelProviders.of(this).get(MyModel.class);

        final List<String> strings = new ArrayList<>();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                strings.add("s " + i);
                myModel.setItems(strings);

                if(i < 23) new Handler().postDelayed(this, 1000);
            }
        }, 1000);

    }
}
