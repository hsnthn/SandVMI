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

public class SubscriptionConfirmationFragment extends Fragment {


    public SubscriptionConfirmationFragment() {
        // Required empty public constructor
    }

    public static SubscriptionConfirmationFragment newInstance() {
        SubscriptionConfirmationFragment fragment = new SubscriptionConfirmationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_subscription_confirmation, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_confirmation_title));




        return view;
    }


}

