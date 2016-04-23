package com.gamfe.chen.util;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

public class MyTextWatcher implements TextWatcher {
	public int maxLen;
	public EditText editText;

	public MyTextWatcher(EditText editText, int maxLen) {
		this.maxLen = maxLen;
		this.editText = editText;

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		Editable editable = editText.getText();
		int len = editable.length();

		if (len > maxLen) {
			//得到最后一个字符的位置
			int selEndIndex = Selection.getSelectionEnd(editable);
			//把EditText里面的内容转换成字符串
			String str = editable.toString();
			// 截取新字符串
			String newStr = str.substring(0, maxLen);
			editText.setText(newStr);
			editable = editText.getText();
			// 新字符串的长度
			int newLen = editable.length();
			// 旧光标位置超过字符串长度
			if (selEndIndex > newLen) {
				selEndIndex = editable.length();
			}
			// 设置新光标所在的位置
			Selection.setSelection(editable, selEndIndex);
		}
	}

}
