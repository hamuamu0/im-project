package com.soul.soulproject.ui;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soul.framework.base.BasePagerAdapter;
import com.soul.framework.entity.Contants;
import com.soul.framework.manager.MediaPlayerManager;
import com.soul.framework.utils.AnimationUtils;
import com.soul.framework.utils.SpUtils;
import com.soul.soulproject.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-12
 * @Describe:
 */
public class GuideActivity extends AppCompatActivity {

    List<View> mList = null;
    ViewPager mViewPager;
    ImageView imgMusic;
    TextView txtSkip;
    ImageView imgIndex1;
    ImageView imgIndex2;
    ImageView imgIndex3;
    private View startView;
    private View smileView;
    private View nightView;
    private ImageView imgGuideStart;
    private ImageView imgGuideSmile;
    private ImageView imgGuidNight;
    private AnimationDrawable guideStartDrawable;
    private AnimationDrawable guideSmileDrawable;
    private AnimationDrawable guidNightDrawable;
    private ObjectAnimator objectAnimatorMusic;

    private boolean isPlayMusic = true;
    private MediaPlayerManager mediaPlayerManager;
    private AssetFileDescriptor assetFileDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mediaPlayerManager = new MediaPlayerManager();
        assetFileDescriptor = getResources().openRawResourceFd(R.raw.source);
        findView();
        initView();
        startAnimation();

    }


    /**
     * 查找控件
     */
    private void findView() {
        mViewPager = findViewById(R.id.view_page);
        imgMusic = findViewById(R.id.img_music);
        txtSkip = findViewById(R.id.txt_skip);
        imgIndex1 = findViewById(R.id.img_index1);
        imgIndex2 = findViewById(R.id.img_index2);
        imgIndex3 = findViewById(R.id.img_index3);

        startView = LayoutInflater.from(this).inflate(R.layout.layout_start, null);
        smileView = LayoutInflater.from(this).inflate(R.layout.layout_smile, null);
        nightView = LayoutInflater.from(this).inflate(R.layout.layout_night, null);

        imgGuideStart = startView.findViewById(R.id.img_guide_start);
        imgGuideSmile = smileView.findViewById(R.id.img_guide_smile);
        imgGuidNight = nightView.findViewById(R.id.img_guide_night);

        objectAnimatorMusic = AnimationUtils.rotaiteAnimation(imgMusic);


    }

    /**
     * 初始化状态
     */
    private void initView() {
        mList = new ArrayList<>();

        mList.add(startView);
        mList.add(smileView);
        mList.add(nightView);

        mViewPager.setOffscreenPageLimit(3);
        BasePagerAdapter mBasePagerAdapter = new BasePagerAdapter(mList);
        mViewPager.setAdapter(mBasePagerAdapter);

        //对viewpage滑动进行监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        imgIndex1.setBackgroundResource(R.drawable.img_guide_point_p);
                        imgIndex2.setBackgroundResource(R.drawable.img_guide_point);
                        imgIndex3.setBackgroundResource(R.drawable.img_guide_point);
                        break;

                    case 1:
                        imgIndex1.setBackgroundResource(R.drawable.img_guide_point);
                        imgIndex2.setBackgroundResource(R.drawable.img_guide_point_p);
                        imgIndex3.setBackgroundResource(R.drawable.img_guide_point);
                        break;

                    case 2:
                        imgIndex1.setBackgroundResource(R.drawable.img_guide_point);
                        imgIndex2.setBackgroundResource(R.drawable.img_guide_point);
                        imgIndex3.setBackgroundResource(R.drawable.img_guide_point_p);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        imgMusic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (isPlayMusic) {
                    imgMusic.setBackgroundResource(R.drawable.img_guide_music_off);
                    objectAnimatorMusic.pause();
                    mediaPlayerManager.pausePlay();
                    isPlayMusic = false;
                } else {
                    imgMusic.setBackgroundResource(R.drawable.img_guide_music);
                    objectAnimatorMusic.resume();
                    mediaPlayerManager.continuePlay();
                    isPlayMusic = true;
                }
            }
        });

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                SpUtils.getInstance(GuideActivity.this).putBoolean(Contants.SP_IS_FIRST_APP,false);
                finish();
            }
        });


    }

    private void startAnimation() {

        guideStartDrawable = (AnimationDrawable) imgGuideStart.getBackground();
        guideSmileDrawable = (AnimationDrawable) imgGuideSmile.getBackground();
        guidNightDrawable = (AnimationDrawable) imgGuidNight.getBackground();


    }


    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        guideStartDrawable.start();
        guideSmileDrawable.start();
        guidNightDrawable.start();
        objectAnimatorMusic.start();
        if (!mediaPlayerManager.isPlaying()) {
            mediaPlayerManager.startPlay(assetFileDescriptor);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStop() {
        super.onStop();
        guideStartDrawable.stop();
        guideSmileDrawable.stop();
        guidNightDrawable.stop();
        objectAnimatorMusic.cancel();
        if (mediaPlayerManager.isPlaying()) {
            mediaPlayerManager.pausePlay();
        }

    }
}
