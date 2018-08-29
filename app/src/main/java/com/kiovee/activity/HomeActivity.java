package com.kiovee.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kiovee.R;
import com.kiovee.adapter.MainViewPagerAdapter;
import com.kiovee.fragment.HomeFragment;
import com.kiovee.fragment.IssueFragment;
import com.kiovee.fragment.MessageFragment;
import com.kiovee.fragment.PublishFragment;
import com.kiovee.fragment.UserCenterFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int PARTS_COUNT = 5;
    private static final int HOME_INDEX = 0;
    private static final int ISSUE_INDEX = 1;
    private static final int PUBLISH_INDEX = 2;
    private static final int MESSAGE_INDEX = 3;
    private static final int USER_CENTER_INDEX = 4;

    private ViewPager mainViewPager;
    private RelativeLayout homeLayout;
    private ImageView homeImageView;
    private TextView homeTextView;
    private RelativeLayout issueLayout;
    private ImageView issueImageView;
    private TextView issueTextView;
    private RelativeLayout publishLayout;
    private ImageView publishImageView;
    private TextView publishTextView;
    private RelativeLayout messageLayout;
    private ImageView messageImageView;
    private TextView messageTextView;
    private RelativeLayout userCenterLayout;
    private ImageView userCenterImageView;
    private TextView userCenterTextView;

    private SparseArray<Fragment> sMainFragmentArray;
    private SparseArray<View> mBottomLayoutArray;
    private SparseArray<ImageView> mBottomImageViewArray;
    private SparseArray<TextView> mBottomTextViewArray;
    private SparseArray<int[]> mBottomImageResArray;
    private HomeFragment mHomeFragment;
    private IssueFragment mIssueFragment;
    private PublishFragment mPublishFragment;
    private MessageFragment mMessageFragment;
    private UserCenterFragment mUserCenterFragment;
    private long exitTime;
    //private List<View> mBottomLayoutList;
    //private List<ImageView> mBottomImageViewList;
    //private List<TextView> mBottomTextViewList;

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setBottomClickEvent(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initPreData();
        initView();
        initPosData();
        initListener();
    }

    private int setLayoutId() {
        return R.layout.activity_home;
    }

    private void initPreData() {
        sMainFragmentArray = new SparseArray<>(PARTS_COUNT);
        mBottomLayoutArray = new SparseArray<>(PARTS_COUNT);
        mBottomImageViewArray = new SparseArray<>(PARTS_COUNT);
        mBottomTextViewArray = new SparseArray<>(PARTS_COUNT);
        mBottomImageResArray = new SparseArray<>(PARTS_COUNT);
        //mBottomLayoutList = new ArrayList<>();
        //mBottomImageViewList = new ArrayList<>();
        //mBottomTextViewList = new ArrayList<>();

        initMainFragmentArray();
    }

    private void initView() {
        mainViewPager = findViewById(R.id.main_ViewPager);

        homeLayout = findViewById(R.id.home_Layout);
        homeImageView = findViewById(R.id.home_ImageView);
        homeTextView = findViewById(R.id.home_TextView);

        issueLayout = findViewById(R.id.issue_Layout);
        issueImageView = findViewById(R.id.issue_ImageView);
        issueTextView = findViewById(R.id.issue_TextView);

        publishLayout = findViewById(R.id.publish_Layout);
        publishImageView = findViewById(R.id.publish_ImageView);
        publishTextView = findViewById(R.id.publish_TextView);

        messageLayout = findViewById(R.id.message_Layout);
        messageImageView = findViewById(R.id.message_ImageView);
        messageTextView = findViewById(R.id.message_TextView);

        userCenterLayout = findViewById(R.id.userCenter_Layout);
        userCenterImageView = findViewById(R.id.userCenter_ImageView);
        userCenterTextView = findViewById(R.id.userCenter_TextView);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),sMainFragmentArray);

        mainViewPager.setAdapter(mainViewPagerAdapter);
    }

    private void initPosData() {
        initBottomImageRes();
        initBottomLayout();     //初始化底部导航栏
    }

    private void initMainFragmentArray(){
        mHomeFragment = new HomeFragment();
        mIssueFragment = new IssueFragment();
        mPublishFragment = new PublishFragment();
        mMessageFragment = new MessageFragment();
        mUserCenterFragment = new UserCenterFragment();
        for (int i = 0; i < PARTS_COUNT; i++) {
            if(i == 0){
                sMainFragmentArray.append(HOME_INDEX,mHomeFragment);
            }else if(i == 1){
                sMainFragmentArray.append(ISSUE_INDEX,mIssueFragment);
            }else if(i == 2){
                sMainFragmentArray.append(PUBLISH_INDEX,mPublishFragment);
            }else if(i == 3){
                sMainFragmentArray.append(MESSAGE_INDEX,mMessageFragment);
            }else {
                sMainFragmentArray.append(USER_CENTER_INDEX,mUserCenterFragment);
            }
        }
    }

    private void initBottomImageRes() {
        for (int i = 0; i < PARTS_COUNT; i++) {
            if(i == 0){
                mBottomImageResArray.append(HOME_INDEX,new int[]{R.drawable.icon_home_normal,R.drawable.icon_home_select});
            }else if(i == 1){
                mBottomImageResArray.append(ISSUE_INDEX,new int[]{R.drawable.icon_issue_normal,R.drawable.icon_issue_select});
            }else if(i == 2){
                mBottomImageResArray.append(PUBLISH_INDEX,new int[]{R.drawable.icon_publish_normal,R.drawable.icon_publish_select});
            }else if(i == 3){
                mBottomImageResArray.append(MESSAGE_INDEX,new int[]{R.drawable.icon_message_normal,R.drawable.icon_message_select});
            }else {
                mBottomImageResArray.append(USER_CENTER_INDEX,new int[]{R.drawable.icon_user_center_normal,R.drawable.icon_user_center_select});
            }
        }
    }

    private void initBottomLayout() {
        for (int i = 0; i < PARTS_COUNT; i++) {
            if(i == 0){
                mBottomLayoutArray.append(HOME_INDEX,homeLayout);
                mBottomImageViewArray.append(HOME_INDEX,homeImageView);
                mBottomTextViewArray.append(HOME_INDEX,homeTextView);
            }else if(i == 1){
                mBottomLayoutArray.append(ISSUE_INDEX,issueLayout);
                mBottomImageViewArray.append(ISSUE_INDEX,issueImageView);
                mBottomTextViewArray.append(ISSUE_INDEX,issueTextView);
            }else if(i == 2){
                mBottomLayoutArray.append(PUBLISH_INDEX,publishLayout);
                mBottomImageViewArray.append(PUBLISH_INDEX,publishImageView);
                mBottomTextViewArray.append(PUBLISH_INDEX,publishTextView);
            }else if(i == 3){
                mBottomLayoutArray.append(MESSAGE_INDEX,messageLayout);
                mBottomImageViewArray.append(MESSAGE_INDEX,messageImageView);
                mBottomTextViewArray.append(MESSAGE_INDEX,messageTextView);
            }else {
                mBottomLayoutArray.append(USER_CENTER_INDEX,userCenterLayout);
                mBottomImageViewArray.append(USER_CENTER_INDEX,userCenterImageView);
                mBottomTextViewArray.append(USER_CENTER_INDEX,userCenterTextView);
            }
        }
        setBottomClickEvent(HOME_INDEX);
    }

    private void initListener() {
        homeLayout.setOnClickListener(this);
        issueLayout.setOnClickListener(this);
        publishLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        userCenterLayout.setOnClickListener(this);

        mainViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_Layout:
                setBottomClickEvent(HOME_INDEX);
                break;
            case R.id.issue_Layout:
                setBottomClickEvent(ISSUE_INDEX);
                break;
            case R.id.publish_Layout:
                setBottomClickEvent(PUBLISH_INDEX);
                break;
            case R.id.message_Layout:
                setBottomClickEvent(MESSAGE_INDEX);
                break;
            case R.id.userCenter_Layout:
                setBottomClickEvent(USER_CENTER_INDEX);
                break;
            default:

                break;
        }
    }

    private void setBottomClickEvent(int index){
        for (int i = 0; i < PARTS_COUNT; i++) {
            ImageView imageView = mBottomImageViewArray.get(i);
            TextView textView = mBottomTextViewArray.get(i);
            if(i == index){
                imageView.setImageResource(mBottomImageResArray.get(i)[1]);
                textView.setTextColor(Color.RED);
                mainViewPager.setCurrentItem(index);
            }else {
                imageView.setImageResource(mBottomImageResArray.get(i)[0]);
                textView.setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(HomeActivity.this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
