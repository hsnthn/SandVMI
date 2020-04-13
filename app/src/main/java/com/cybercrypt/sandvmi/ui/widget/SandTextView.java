package com.cybercrypt.sandvmi.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.cybercrypt.sandvmi.R;

public class SandTextView extends androidx.appcompat.widget.AppCompatTextView {

    public static final float LETTER_SPACE=0.08f;

    public SandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyLetterSpacing(context,attrs);
    }

    public SandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyLetterSpacing(context,attrs);
    }


    private void applyLetterSpacing(Context context,AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SandTextView);
        boolean cf = a.getBoolean(R.styleable.SandTextView_applyLetterSpace, false);

        if (cf)
            setLetterSpacing(LETTER_SPACE);
        a.recycle();
    }

}
