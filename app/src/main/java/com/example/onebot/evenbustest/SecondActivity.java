package com.example.onebot.evenbustest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import de.greenrobot.event.EventBus;


/**
 * Created by onebot on 2016/10/26.
 */

public class SecondActivity extends AppCompatActivity {

    private Button bt2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        bt2= (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(
                        new FirstEvent("FirstEvent btn clicked"));
            }
        });
    }
}
