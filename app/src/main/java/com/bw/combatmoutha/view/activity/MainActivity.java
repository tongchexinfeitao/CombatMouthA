package com.bw.combatmoutha.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bw.combatmoutha.R;
import com.bw.combatmoutha.base.BaseActivity;
import com.bw.combatmoutha.base.BasePresenter;
import com.bw.combatmoutha.view.fragment.HomeFragment;
import com.bw.combatmoutha.view.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initData() {
        HomeFragment homeFragment = new HomeFragment();
        OtherFragment beiJingFragment = OtherFragment.getInstance("北京");
        OtherFragment myFragment = OtherFragment.getInstance("我的");

        fragmentList.add(homeFragment);
        fragmentList.add(beiJingFragment);
        fragmentList.add(myFragment);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.vp);
        radioGroup = findViewById(R.id.rg);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_beijing:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_my:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });

    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
