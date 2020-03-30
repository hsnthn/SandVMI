package com.cybercrypt.sandvmi.ui.homescreen;

        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.KeyEvent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.inputmethod.EditorInfo;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;

        import com.cybercrypt.sandvmi.R;

public class ForgotPasswordFragment extends Fragment {

    private EditText edit_email;
    private Button btn_reset;

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        edit_email = root.findViewById(R.id.edit_fp_email);
        btn_reset = root.findViewById(R.id.btn_fp_reset);

        edit_email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edit_email.clearFocus();
                }
                return false;
            }
        });

        edit_email.addTextChangedListener(textEmptyCheck);


        return root;
    }

    private TextWatcher textEmptyCheck= new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            btn_reset.setEnabled(edit_email.getText().toString().length() > 0 );
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }
    };
}