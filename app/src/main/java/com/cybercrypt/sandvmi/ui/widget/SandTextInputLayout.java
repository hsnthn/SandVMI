package com.cybercrypt.sandvmi.ui.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cybercrypt.sandvmi.R;
import com.google.android.material.textfield.TextInputLayout;

public class SandTextInputLayout extends TextInputLayout {

    private final Drawable normalDrawable = getResources().getDrawable(R.drawable.bg_edit_text);
    private final Drawable successDrawable = getResources().getDrawable(R.drawable.bg_success_edit_text);
    private final Drawable errorDrawable = getResources().getDrawable(R.drawable.bg_error_edit_text);
    private int status=0;
    public static final int ERROR=1;
    public static final int SUCCESS=2;
    private TextView errorView;

    public SandTextInputLayout(Context context) {
        super(context);
    }

    public SandTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SandTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) throws NoSuchFieldException, IllegalAccessException {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        replaceBackground();
    }

    private int getStatus(){
        return this.status;
    }


    public void setStatus(int status){
        this.status=status;

        replaceBackground();
    }

    @Override
    public void setError(@Nullable CharSequence errorText) {
        super.setError(errorText);

        if (getError()==null){
            setErrorEnabled(false);
        }else{
            setErrorEnabled(true);
        }

    }

    private void replaceBackground() {
        EditText editText = getEditText();
        if (editText != null) {
            if (getStatus() == 0) {
                editText.setBackground(normalDrawable);
            } else if (getStatus() == 2) {
                editText.setBackground(successDrawable);
            } else {
                editText.setBackground(errorDrawable);
            }
            Drawable drawable = editText.getBackground();
            if (drawable != null) {
                drawable.clearColorFilter();
            }
        }
    }
}
