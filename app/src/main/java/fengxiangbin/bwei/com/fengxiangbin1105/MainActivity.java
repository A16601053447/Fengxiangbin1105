package fengxiangbin.bwei.com.fengxiangbin1105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {
    Toolbar toobar;
    private Boolean isStart = false;
    DiskView mDiskView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toobar = findViewById(R.id.toobar);
        mDiskView = findViewById(R.id.dis);
        toobar.setLogo(R.drawable.ic_launcher_foreground);
        toobar.setTitle("课时作业");
        mDiskView.setCustomOnClickListener(new ClickInterface() {
            @Override
            public void click(View view) {


            }
        });

    }
}
