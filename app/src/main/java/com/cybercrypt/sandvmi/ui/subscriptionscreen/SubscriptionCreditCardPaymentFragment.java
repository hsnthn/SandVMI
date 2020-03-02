package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class SubscriptionCreditCardPaymentFragment extends BaseFragment implements View.OnClickListener {

    private EditText edit_card_number;
    private final String EDIT_CARD_NUM_TAG ="CARDNUM";
    private EditText edit_card_name;
    private EditText edit_card_cvv;
    private final String EDIT_CARD_CVV_TAG ="CARDCVV";
    private TableLayout keyboard;

    public SubscriptionCreditCardPaymentFragment() {
        // Required empty public constructor
    }

    public static SubscriptionCreditCardPaymentFragment newInstance() {
        return new SubscriptionCreditCardPaymentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_subscription_credit_card_details, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_nav_back));
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_choose_payment_plan_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionChoosePaymentPlanFragment.newInstance());
            }
        });

        edit_card_number = view.findViewById(R.id.edit_subsc_card_number);
        edit_card_name = view.findViewById(R.id.edit_subsc_card_name);
        edit_card_number.setTag(EDIT_CARD_NUM_TAG);
        edit_card_cvv = view.findViewById(R.id.edit_subsc_card_cvv);
        edit_card_cvv.setTag(EDIT_CARD_CVV_TAG);

        Spinner spinner_months = view.findViewById(R.id.spin_subsc_card_expire_month);
        Spinner spinner_years = view.findViewById(R.id.spin_subsc_card_expire_year);
        Button btn_buy_now = view.findViewById(R.id.btn_credit_card_buy);
        btn_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionConfirmationFragment.newInstance());
            }
        });

        initCustomKeyboard(view);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.months_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_months.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.years_array, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_years.setAdapter(adapter2);



        edit_card_name.setImeOptions(EditorInfo.IME_ACTION_DONE);


        edit_card_number.requestFocus();
        edit_card_number.setOnFocusChangeListener(card_view_focus);
        edit_card_number.setOnTouchListener(card_view_touch);
        edit_card_number.addTextChangedListener(credit_card_num_watcher);

        edit_card_cvv.setOnFocusChangeListener(card_view_focus);
        edit_card_cvv.setOnTouchListener(card_view_touch);


        return view;
    }

    void initCustomKeyboard(View view){
        keyboard = view.findViewById(R.id.keyboard);
        TextView k0=view.findViewById(R.id.t9_key_0);
        TextView k1=view.findViewById(R.id.t9_key_1);
        TextView k2=view.findViewById(R.id.t9_key_2);
        TextView k3=view.findViewById(R.id.t9_key_3);
        TextView k4=view.findViewById(R.id.t9_key_4);
        TextView k5=view.findViewById(R.id.t9_key_5);
        TextView k6=view.findViewById(R.id.t9_key_6);
        TextView k7=view.findViewById(R.id.t9_key_7);
        TextView k8=view.findViewById(R.id.t9_key_8);
        TextView k9=view.findViewById(R.id.t9_key_9);
        TextView kback=view.findViewById(R.id.t9_key_ok);
        TextView kok=view.findViewById(R.id.t9_key_backspace);

        k0.setOnClickListener(this);
        k1.setOnClickListener(this);
        k2.setOnClickListener(this);
        k3.setOnClickListener(this);
        k4.setOnClickListener(this);
        k5.setOnClickListener(this);
        k6.setOnClickListener(this);
        k7.setOnClickListener(this);
        k8.setOnClickListener(this);
        k9.setOnClickListener(this);
        kok.setOnClickListener(this);
        kback.setOnClickListener(this);

    }


    private TextWatcher credit_card_num_watcher = new TextWatcher() {

        private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
        private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
        private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
        private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
        private static final char DIVIDER = ' ';

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // noop
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // noop
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                s.replace(0, s.length(), buildCorrectString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
            }
        }

        private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
            boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
            for (int i = 0; i < s.length(); i++) { // check that every element is right
                if (i > 0 && (i + 1) % dividerModulo == 0) {
                    isCorrect &= divider == s.charAt(i);
                } else {
                    isCorrect &= Character.isDigit(s.charAt(i));
                }
            }
            return isCorrect;
        }

        private String buildCorrectString(char[] digits, int dividerPosition, char divider) {
            final StringBuilder formatted = new StringBuilder();

            for (int i = 0; i < digits.length; i++) {
                if (digits[i] != 0) {
                    formatted.append(digits[i]);
                    if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                        formatted.append(divider);
                    }
                }
            }

            return formatted.toString();
        }

        private char[] getDigitArray(final Editable s, final int size) {
            char[] digits = new char[size];
            int index = 0;
            for (int i = 0; i < s.length() && index < size; i++) {
                char current = s.charAt(i);
                if (Character.isDigit(current)) {
                    digits[index] = current;
                    index++;
                }
            }
            return digits;
        }
    };


    private View.OnFocusChangeListener card_view_focus =   new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!hasFocus) {
                keyboard.setVisibility(View.GONE);
            }else{
                keyboard.setVisibility(View.VISIBLE);
            }

        }
    };

    private View.OnTouchListener card_view_touch = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext().getSystemService(
                    android.content.Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if(v.equals(edit_card_number)){
                edit_card_number.requestFocus();
                keyboard.setVisibility(View.VISIBLE);
            }else if(v.equals(edit_card_cvv)){
                edit_card_cvv.requestFocus();
                keyboard.setVisibility(View.VISIBLE);
            }
            return true;

        }
    };



    /*
    private View.OnKeyListener card_name_ok_click  = new View.OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                Toast.makeText(HelloFormStuff.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        }
    };


*/
    @Override
    public void onClick(View v) {
        EditText focused_view = (EditText) getActivity().getCurrentFocus();
        if (v.getTag() != null && "number_button".equals(v.getTag())) {
            focused_view.append(((TextView) v).getText());
            return;
        }
        switch (v.getId()) {
            case R.id.t9_key_ok: { // handle clear button
                keyboard.setVisibility(View.GONE);
                if (focused_view.getTag().equals(EDIT_CARD_NUM_TAG))
                    showSoftKeyboard(edit_card_name);
            }
            break;
            case R.id.t9_key_backspace: { // handle backspace button
                // delete one character
                Editable editable = focused_view.getText();
                int charCount = editable.length();
                if (charCount > 0) {
                    editable.delete(charCount - 1, charCount);
                }
            }
            break;
        }
    }

    private void showSoftKeyboard(View view) {
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }


}

