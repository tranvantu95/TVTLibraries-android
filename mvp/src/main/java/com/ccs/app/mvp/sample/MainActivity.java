package com.ccs.app.mvp.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ccs.app.mvp.R;
import com.ccs.app.mvp.utils.ModelProviders;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MyFragment())
                .commit();

        MyModel myModel = ModelProviders.of(this).get(MyModel.class);

        List<String> strings = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            strings.add("s " + i);
        }

        myModel.setItems(strings);
    }
}
