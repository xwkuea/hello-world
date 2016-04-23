package com.gamfe.chen.logindemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamfe.chen.util.TimeUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MainScreen extends Activity {
	GridView myGridView;
	public static String Main_Menu_Str[] = { "校园新闻", "一卡通", "考勤系统", "教务系统",
			"图书馆", "数字迎新" };
	public static int Main_Menu_image[] = { R.drawable.xiaoyuanxinxi,
			R.drawable.yikatong, R.drawable.kaoqinxitong,
			R.drawable.jiaowuxitong, R.drawable.tushuxitong,
			R.drawable.shuziyingxin };
	public SimpleAdapter main_menu_adapter;

	public TextView time;

	public static final int UPTIME = 2200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.main_view);

		main_menu_adapter = new SimpleAdapter(MainScreen.this,
				getMainMenuData(), R.layout.main_gridview_item, new String[] {
						"image", "title" }, new int[] { R.id.main_grid_img,
						R.id.main_grid_text });
		myGridView = (GridView) this.findViewById(R.id.myGrid);
		time = (TextView) this.findViewById(R.id.time_text);
		time.setText(TimeUtil.getTime());
		SharedPreferences sp=this.getSharedPreferences("setting", 0);
	}

	@Override
	protected void onStart() {
		super.onStart();
		myGridView.setAdapter(main_menu_adapter);

		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Message mess = new Message();
					mess.what = UPTIME;
					han.sendMessage(mess);
				}
			}
		}.start();
	}

	@SuppressLint("HandlerLeak")
	public Handler han = new Handler() {
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == UPTIME) {
				time.setText(TimeUtil.getTime());
			}
		}

	};

	public List<Map<String, Object>> getMainMenuData() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < MainScreen.Main_Menu_Str.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", Main_Menu_image[i]);
			map.put("title", Main_Menu_Str[i]);
			list.add(map);
		}
		return list;

	}
}
