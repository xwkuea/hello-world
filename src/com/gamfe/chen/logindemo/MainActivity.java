package com.gamfe.chen.logindemo;

import com.gamfe.chen.util.MyTextWatcher;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CommitPrefEdits")
public class MainActivity extends Activity {

	Button loginBun, cancelBun;
	EditText userName, userPass;
	String name, pw;
	TextView tx;
	CheckBox remPass, autoLog;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loginBun = (Button) this.findViewById(R.id.Login_next);
		cancelBun = (Button) this.findViewById(R.id.Login_cancel);
		userName = (EditText) this.findViewById(R.id.input_name);
		userPass = (EditText) this.findViewById(R.id.input_pass);
		tx = (TextView) this.findViewById(R.id.Login_reg);
		remPass = (CheckBox) this.findViewById(R.id.remenber_pass);
		autoLog = (CheckBox) this.findViewById(R.id.auto_login);

		sp = getSharedPreferences("info", Context.MODE_PRIVATE);
	}

	protected void onStart() {
		super.onStart();
		userName.addTextChangedListener(new MyTextWatcher(userName, 15));
		userPass.addTextChangedListener(new MyTextWatcher(userPass, 6));
        
		userName.setText(sp.getString("un", ""));
		userPass.setText(sp.getString("pw", ""));
	}

	public void Login_view(View v) {
		// 取得编辑对象
		SharedPreferences.Editor editor = sp.edit();
		
		// 添加值
		editor.putString("un", userName.getText().toString());
		editor.putString("pw", userPass.getText().toString());
		
		// 提交
		editor.commit();
		
		Intent intet = new Intent();
		intet.setClass(MainActivity.this, MainScreen.class);
		startActivity(intet);
	}

}
