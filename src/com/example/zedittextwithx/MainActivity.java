package com.example.zedittextwithx;

import com.example.zedittextwithx.EditTextWithDeleteButton.TextChangedListener;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditTextWithDeleteButton customEdit = (EditTextWithDeleteButton) findViewById(R.id.textView1);
		customEdit.addTextChangedListener(editTextChanged());
	}

	private TextChangedListener editTextChanged() {
		return new TextChangedListener() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		};
	}

}
