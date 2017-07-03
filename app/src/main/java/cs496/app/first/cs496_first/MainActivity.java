package cs496.app.first.cs496_first;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        /*TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.tab1).setIndicator(getString(R.string.tab1_str));
        tabHost.addTab(spec1);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(R.id.tab2).setIndicator(getString(R.string.tab2_str));
        tabHost.addTab(spec2);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab3").setContent(R.id.tab3).setIndicator(getString(R.string.tab3_str));
        tabHost.addTab(spec3);*/
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        //mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("A", null), A.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("A", null), A2.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Tab2").setIndicator("B", null), B.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Tab3").setIndicator("B2", null), D.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Tab4").setIndicator("C", null), C.class, null);
    }
}
