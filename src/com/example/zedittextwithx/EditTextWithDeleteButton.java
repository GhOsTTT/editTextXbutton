package com.example.zedittextwithx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class EditTextWithDeleteButton extends LinearLayout {	
	protected EditText editText;
	protected ImageButton clearTextButton;
	public interface TextChangedListener extends TextWatcher{
	}
	TextChangedListener editTextListener = null;
	public void addTextChangedListener(TextChangedListener listener) {
        this.editTextListener = listener;
    }
	public EditTextWithDeleteButton(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.activity_main, this);
	}

	public EditTextWithDeleteButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context, attrs);
	}

	public EditTextWithDeleteButton(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
		initViews(context, attrs);
	}

	private void initViews(Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.EditTextWithDeleteButton, 0, 0);
		String hintText;
		int deleteButtonRes;
		try {
			// get the text and colors specified using the names in attrs.xml
			hintText = a.getString(R.styleable.EditTextWithDeleteButton_hintText);
			deleteButtonRes = a.getResourceId(
					R.styleable.EditTextWithDeleteButton_deleteButtonRes,
					R.drawable.text_field_clear_btn);

		} finally {
			a.recycle();
		}
		editText = createEditText(context, hintText);
		clearTextButton = createImageButton(context, deleteButtonRes);

		this.addView(editText);
		this.addView(clearTextButton);
		editText.addTextChangedListener(txtEntered());
	

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus && editText.getText().toString().length() > 0)
					clearTextButton.setVisibility(View.VISIBLE);
				else
					clearTextButton.setVisibility(View.GONE);

			}
		});
		clearTextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editText.setText("");
			}
		});
	}

	public TextWatcher txtEntered() {
		return new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				 if (editTextListener != null)
			            editTextListener.onTextChanged(s, start, before, count);

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (editTextListener != null)
		            editTextListener.afterTextChanged(s);
				if (editText.getText().toString().length() > 0)
					clearTextButton.setVisibility(View.VISIBLE);
				else
					clearTextButton.setVisibility(View.GONE);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if (editTextListener != null)
		            editTextListener.beforeTextChanged(s, start, count, after);

			}

		};
	}

	@SuppressLint("NewApi")
	private EditText createEditText(Context context, String hintText) {
		editText = new EditText(context);
		editText.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		editText.setLayoutParams(new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
		editText.setHorizontallyScrolling(false);
		editText.setVerticalScrollBarEnabled(true);
		editText.setGravity(Gravity.LEFT);
		editText.setBackground(null);
		editText.setHint(hintText);
		return editText;
	}

	private ImageButton createImageButton(Context context, int deleteButtonRes) {
		clearTextButton = new ImageButton(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		params.gravity = Gravity.CENTER_VERTICAL;
		clearTextButton.setLayoutParams(params);
		clearTextButton.setBackgroundResource(deleteButtonRes);
		clearTextButton.setVisibility(View.GONE);
		return clearTextButton;
	}

}
