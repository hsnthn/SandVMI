package com.cybercrypt.sandvmi.ui.subscriptionscreen;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.appcompat.widget.Toolbar;
        import androidx.fragment.app.Fragment;

        import com.cybercrypt.sandvmi.R;

public class SubscriptionPaymentDetailsFragment extends Fragment {


    public SubscriptionPaymentDetailsFragment() {
        // Required empty public constructor
    }

    public static SubscriptionPaymentDetailsFragment newInstance() {
        SubscriptionPaymentDetailsFragment fragment = new SubscriptionPaymentDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_subscription_payment_details, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_nav_back));
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_payment_details_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("title","gerii");
            }
        });




        return view;
    }


}

