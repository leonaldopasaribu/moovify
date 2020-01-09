package com.stilldre.moovify.activity;

import android.content.Intent;
import android.provider.Settings;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.stilldre.moovify.R;
import com.stilldre.moovify.adapter.PagerAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0).setText(R.string.now_playing));
        Objects.requireNonNull(tabLayout.getTabAt(1).setText(R.string.upcoming));
        Objects.requireNonNull(tabLayout.getTabAt(2).setText(R.string.favorite));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.action_setting:
//                Intent dIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//                startActivity(dIntent);
//                break;
            case R.id.action_search:
                Intent sIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(sIntent);
                break;
            case R.id.action_reminder:
                Intent rIntent = new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(rIntent);
                break;
            case R.id.action_message:
                Intent mIntent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(mIntent);
                break;
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

}
