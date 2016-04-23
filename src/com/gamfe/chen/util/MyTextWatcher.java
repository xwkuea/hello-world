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
			//�õ����һ���ַ���λ��
			int selEndIndex = Selection.getSelectionEnd(editable);
			//��EditText���������ת�����ַ���
			String str = editable.toString();
			// ��ȡ���ַ���
			String newStr = str.substring(0, maxLen);
			editText.setText(newStr);
			editable = editText.getText();
			// ���ַ����ĳ���
			int newLen = editable.length();
			// �ɹ��λ�ó����ַ�������
			if (selEndIndex > newLen) {
				selEndIndex = editable.length();
			}
			// �����¹�����ڵ�λ��
			Selection.setSelection(editable, selEndIndex);
		}
	}

}
