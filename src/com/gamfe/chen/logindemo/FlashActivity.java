package com.gamfe.chen.logindemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

@SuppressLint("HandlerLeak")
public class FlashActivity extends Activity {
	public final static int TIME_UP = 100000000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.flash_view);
	}

	public void onStart() {
		super.onStart();
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.what = TIME_UP;
				handler.sendMessage(msg);
			}
		}.start();
	}

	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == TIME_UP) {
				Intent intent = new Intent();
				intent.setClass(FlashActivity.this, MainActivity.class);
				startActivity(intent);
				FlashActivity.this.finish();
			}
		}

	};

}
