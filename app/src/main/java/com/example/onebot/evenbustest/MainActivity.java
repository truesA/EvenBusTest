package com.example.onebot.evenbustest;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button bt1;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        init();

    }

    private void init() {
        tv1= (TextView) findViewById(R.id.tv1);
        bt1= (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),
                SecondActivity.class);
        startActivity(intent);
    }



    public void onEventMainThread(FirstEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);
        tv1.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
