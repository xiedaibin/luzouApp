package com.kot32.ksimplelibrary.activity.t;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kot32.ksimplelibrary.activity.i.IBaseAction;
import com.kot32.ksimplelibrary.activity.i.ITabPageAction;
import com.kot32.ksimplelibrary.activity.t.base.KSimpleBaseActivityImpl;
import com.kot32.ksimplelibrary.widgets.view.KNoScrollViewPager;
import com.kot32.ksimplelibrary.widgets.view.KTabBar;

import java.util.List;

/**
 * Created by kot32 on 15/11/15.
 */
public abstract class KTabActivity extends KSimpleBaseActivityImpl implements IBaseAction {

    private LinearLayout content;

    private KNoScrollViewPager container;

    private KTabBar tabBar;

    private TabAdapter tabAdapter;

    private List<Fragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        fragmentList = getFragmentList();
        if (fragmentList == null || fragmentList.size() == 0) {
            Log.e("警告", "未得到要显示的Fragment 列表");
            return;
        }
        initdata();
        initview();
        initcontroller();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }



    private void initdata() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
    }


    private void initview() {
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        container = new KNoScrollViewPager(this);
        container.setId(999);
        LinearLayout.LayoutParams containerParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        containerParam.weight = 9.5f;
        content.addView(container, containerParam);

        View divider = new View(this);
        divider.setBackgroundColor(Color.LTGRAY);
        divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        content.addView(divider);

        tabBar = new KTabBar(this, getTabStyle());
        LinearLayout.LayoutParams tabParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        tabParam.weight = 1;
        content.addView(tabBar, tabParam);
    }

    private void initcontroller() {
        container.setAdapter(tabAdapter);
        container.setOffscreenPageLimit(0);
        container.setOnPageChangeListener(tabAdapter);

        tabBar.setOnTabClickListener(new KTabBar.OnTabClickListener() {
            @Override
            public void onClick(int index) {
                if (getTabStyle() == KTabBar.TabStyle.STYLE_NORMAL) {
                    container.setCurrentItem(index, false);
                } else if (getTabStyle() == KTabBar.TabStyle.STYLE_GRADUAL) {
                    container.setCurrentItem(index, false);
                }
            }
        });

        //如是果不是渐变模式，不允许KNoScrollViewPager滑动
        if (getTabStyle() == KTabBar.TabStyle.STYLE_NORMAL) {
            container.setNoScroll(true);
        }


    }

    public void addTab(int imgId, int highlightImgId, String text, int fontColor, int highlightFontColor) {
        if (fragmentList == null || fragmentList.size() == 0) {
            Log.e("警告", "未得到要显示的Fragment 列表,无法添加 Tab");
            return;
        }
        tabBar.addTab(imgId, highlightImgId, text, fontColor, highlightFontColor);
    }

    @Override
    public View getCustomContentView(View v) {
        return content;
    }

    public abstract KTabBar.TabStyle getTabStyle();

    public abstract List<Fragment> getFragmentList();

    class TabAdapter extends FragmentPagerAdapter implements KNoScrollViewPager.OnPageChangeListener {


        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public void onPageScrolled(int p, float positionOffset, int positionOffsetPixels) {

            if (positionOffset > 0) {
                if (positionOffset <= 0.02) positionOffset = 0;
                if (positionOffset >= 0.98) positionOffset = 1;

                if (tabBar.getTabView(p) instanceof KTabBar.GradualTabView) {
                    KTabBar.GradualTabView iconLeft = (KTabBar.GradualTabView) tabBar.getTabView(p);
                    KTabBar.GradualTabView iconRight = (KTabBar.GradualTabView) tabBar.getTabView(p + 1);

                    iconLeft.beDarker(positionOffset);
                    iconRight.beLighter(positionOffset);
                }

            }

        }

        @Override
        public void onPageSelected(int position) {
            if(fragmentList.get(position) instanceof ITabPageAction){
                ((ITabPageAction) fragmentList.get(position)).onPageSelected();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    public KTabBar getTabBar() {
        return tabBar;
    }

    @Override
    public int getContentLayoutID() {
        return 0;
    }

}
