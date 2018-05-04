package com.ocn.kewanandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.Constants;
import com.ocn.kewanandroid.base.activity.BaseActivity;
import com.ocn.kewanandroid.base.fragment.BaseFragment;
import com.ocn.kewanandroid.component.RxBus;
import com.ocn.kewanandroid.contract.main.MainContract;
import com.ocn.kewanandroid.core.DataManager;
import com.ocn.kewanandroid.core.event.LoginEvent;
import com.ocn.kewanandroid.core.http.cookies.CookiesManager;
import com.ocn.kewanandroid.presenter.main.MainPresenter;
import com.ocn.kewanandroid.ui.hierarchy.fragment.KnowledgeHierarchyFragment;
import com.ocn.kewanandroid.ui.mainpager.MainPagerFragment;
import com.ocn.kewanandroid.ui.navigation.NavigationFragment;
import com.ocn.kewanandroid.ui.project.fragment.ProjectFragment;
import com.ocn.kewanandroid.utils.BottomNavigationViewHelper;
import com.ocn.kewanandroid.utils.CommonAlertDialog;
import com.ocn.kewanandroid.utils.CommonUtils;
import com.ocn.kewanandroid.utils.StatusBarUtil;
import com.squareup.haha.perflib.Main;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationBar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.fragment_group)
    FrameLayout mFragmentGroup;

    @Inject
    DataManager mDataManager;

    private ArrayList<BaseFragment> mFragments;


    private TextView mUsTv;
    private MainPagerFragment mMainPagerFragment;
    private KnowledgeHierarchyFragment mKnowledgeHierarchyFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private int mLastFgIndex;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonAlertDialog.newInstance().cancelDialog(true);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initToolbar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new ArrayList<>();
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        if (savedInstanceState == null) {
            mPresent.setNightModeState(false);
            mMainPagerFragment = MainPagerFragment.getInstance(false,null);
            mFragments.add(mMainPagerFragment);
            initData();
            init();
            switchFragment(Constants.TYPE_MAIN_PAGER);
        } else {
            mBottomNavigationBar.setSelectedItemId(R.id.tab_main_pager);
            mMainPagerFragment = MainPagerFragment.getInstance(true,null);
            mFragments.add(mMainPagerFragment);
            initData();
            init();
            switchFragment(Constants.TYPE_SETTING);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usage:
                break;
            case R.id.action_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }
    @OnClick({R.id.main_floating_action_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_floating_action_btn:
                jumpToTheTop();
                break;
            default:
                break;
        }
    }

    @Override
    public void showSwitchProject() {
        mBottomNavigationBar.setSelectedItemId(R.id.tab_project);
    }

    @Override
    public void showSwitchNavigation() {
        mBottomNavigationBar.setSelectedItemId(R.id.tab_navigation);
    }

    @Override
    public void showLoginView() {
        if(mNavigationView == null){
            return;
        }
        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        mUsTv.setText(mDataManager.getLoginAccount());
        mUsTv.setOnClickListener(null);
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(true);
    }

    @Override
    public void showLogoutView() {
        mUsTv.setText(R.string.login_in);
        mUsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        if (mNavigationView == null) {
            return;
        }
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
    }

    private void init() {
        initNavigationView();
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationBar);
        mDataManager.setCurrentPage(Constants.TYPE_MAIN_PAGER);
        mBottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        mTitleTv.setText(getString(R.string.home_pager));
                        switchFragment(0);
                        mMainPagerFragment.reload();
                        mDataManager.setCurrentPage(Constants.TYPE_MAIN_PAGER);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        mTitleTv.setText(getString(R.string.knowledge_hierarchy));
                        switchFragment(1);
                        mMainPagerFragment.reload();
                        mDataManager.setCurrentPage(Constants.TYPE_KNOWLEDGE);
                        break;
                    case R.id.tab_navigation:
                        switchNavigation();
                        break;
                    case R.id.tab_project:
                        switchProject();
                        break;
                }
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f *scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f *(1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth()*(1-scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    private void jumpToTheTop() {
        switch (mDataManager.getCurrentPage()){
            case Constants.TYPE_MAIN_PAGER:
                break;
            case Constants.TYPE_KNOWLEDGE:
                break;
            case Constants.TYPE_NAVIGATION:
                break;
            case Constants.TYPE_PROJECT:
                break;

        }
    }
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert  actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mTitleTv.setText(getString(R.string.home_pager));
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }
    private void initData() {
        mKnowledgeHierarchyFragment = KnowledgeHierarchyFragment.getInstance(null, null);
        mNavigationFragment = NavigationFragment.getInstance(null, null);
        mProjectFragment = ProjectFragment.getInstance(null,null);

        mFragments.add(mKnowledgeHierarchyFragment);
        mFragments.add(mNavigationFragment);
        mFragments.add(mProjectFragment);
    }


    private void switchProject() {
        mTitleTv.setText(getString(R.string.project));
        switchFragment(3);
        mProjectFragment.reload();
        mDataManager.setCurrentPage(Constants.TYPE_PROJECT);
    }

    private void switchNavigation() {
        mTitleTv.setText(getString(R.string.navigation));
        switchFragment(2);
        mNavigationFragment.reload();
        mDataManager.setCurrentPage(Constants.TYPE_NAVIGATION);
    }

    private void switchFragment(int position) {
        if (position >= Constants.TYPE_COLLECT) {
            mFloatingActionButton.setVisibility(View.INVISIBLE);
            mBottomNavigationBar.setVisibility(View.INVISIBLE);
        }else{
            mFloatingActionButton.setVisibility(View.VISIBLE);
            mBottomNavigationBar.setVisibility(View.VISIBLE);
        }
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()){
            ft.add(R.id.fragment_group,targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    private void initNavigationView() {
        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        if (mDataManager.getLoginStatus()){
            showLoginView();
        } else {
            showLogoutView();
        }

        mNavigationView.getMenu().findItem(R.id.nav_item_wan_android)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startMainPager();
                        return true;
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_my_collect)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (mDataManager.getLoginStatus()) {
                            startCollectFragment();
                            return true;
                        } else {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            CommonUtils.showMessage(MainActivity.this, getString(R.string.login_tint));
                            return true;
                        }
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_about_us)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        return true;
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_logout)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        logout();
                        return true;
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_setting)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        startSettingFragment();
                        return true;
                    }
                });

    }

    private void startSettingFragment() {
        mTitleTv.setText(getString(R.string.setting));
        switchFragment(Constants.TYPE_SETTING);
        mDrawerLayout.closeDrawers();
    }
    private void startMainPager() {
        mTitleTv.setText(getString(R.string.home_pager));
        mBottomNavigationBar.setVisibility(View.VISIBLE);
        mBottomNavigationBar.setSelectedItemId(R.id.tab_main_pager);
        mDrawerLayout.closeDrawers();
    }

    private void startCollectFragment() {
        mTitleTv.setText(getString(R.string.my_collect));
        switchFragment(Constants.TYPE_COLLECT);
        mDrawerLayout.closeDrawers();
    }

    private void logout() {
        CommonAlertDialog.newInstance().showDialog(this, getString(R.string.logout_tint),
                getString(R.string.ok),
                getString(R.string.no),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CommonAlertDialog.newInstance().cancelDialog(true);
                        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
                        mDataManager.setLoginStatus(false);
                        CookiesManager.clearAllCookies();
                        RxBus.getDefault().post(new LoginEvent(false));
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CommonAlertDialog.newInstance().cancelDialog(true);
                    }
                });
    }


}
