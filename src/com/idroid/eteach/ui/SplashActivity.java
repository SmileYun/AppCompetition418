package com.idroid.eteach.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.idroid.eteach.R;
import com.idroid.eteach.util.AnimationController;

public class SplashActivity extends Activity {

	//�ؼ�
	private ImageView jiv_text;
	private ImageView jiv_loading_welcome;
	private ImageView jiv_splash_bear;
	
	//Handler�ӳ�
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        //��ʼ������
        init();
    }

    /**
     * ��ʼ�������ϵĿؼ�
     */
	private void init() {
		jiv_text = (ImageView) this.findViewById(R.id.jiv_bearboy);
		jiv_loading_welcome = (ImageView) this.findViewById(R.id.jiv_loading_welcome);
		jiv_splash_bear = (ImageView) this.findViewById(R.id.jiv_splash_bear);
		
		//����ͼƬ��¼�Ķ���Ч��
		AnimationController.fadeIn(jiv_text, 1000, 0);
		AnimationController.fadeIn(jiv_splash_bear, 1000, 1000);
		AnimationController.fadeIn(jiv_loading_welcome, 1000, 1500);
		
		//�ӳ�2s����������
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, BaseActivityDemo.class);
				startActivity(intent);
				//activity֮���л��Ķ���
				overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
				finish();
			}
		}, 2500);
	}
    

}
