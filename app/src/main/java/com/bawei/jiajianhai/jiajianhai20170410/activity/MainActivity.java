package com.bawei.jiajianhai.jiajianhai20170410.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bawei.jiajianhai.jiajianhai20170410.R;
import com.bawei.jiajianhai.jiajianhai20170410.draw.DrawCircle;

public class MainActivity extends AppCompatActivity {
int num=0;
    private DrawCircle circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgressBar = (DrawCircle) findViewById(R.id.progress);

        Button kaishi = (Button) findViewById(R.id.kaishi);
        Button chongzhi = (Button) findViewById(R.id.chongzhi);

        kaishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timer = new Timer();
//                timer.schedule(new MyTask(),1000,1000);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (num<100) {
                            num+=1;
                            circleProgressBar.setTopText("完成 "+num+"%");
                            circleProgressBar.setProgress(num);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = 0;
                circleProgressBar.setProgress(num);
                circleProgressBar.setTopText("完成 "+num+"%");
            }
        });
    }
}
