package com.cybercrypt.sandvmi.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.core.content.res.ResourcesCompat;

import com.cybercrypt.sandvmi.R;

public class SandEditText extends androidx.appcompat.widget.AppCompatEditText implements TextWatcher {
    private Typeface tf ;
    public SandEditText(Context context) {
        super(context);
    }

    public SandEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SandEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        if (TextUtils.isEmpty(text)) {
            setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_w300));
        } else {
            setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_regular));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
