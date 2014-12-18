package rxdx.whatcolorisit;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainFragment extends Fragment {

    private View view;
    private TextView mClock;
    private TextSwitcher mSwitcher;

    private Handler mHandler = new Handler();

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        mClock = (TextView) view.findViewById(R.id.clock);

        mSwitcher = (TextSwitcher) view.findViewById(R.id.switcher);
        configureTextSwitcher();

        refreshUI();

        return view;
    }

    private void configureTextSwitcher() {
        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(getActivity());
                t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                t.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
                t.setTextColor(Color.WHITE);
                return t;
            }
        });

        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);

        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
    }

    private void refreshUI() {
        String clockText = new SimpleDateFormat("HH:mm:ss").format(new Date());
        mClock.setText(clockText);

        String color = "#" + clockText.replaceAll(":", "");
        view.setBackgroundColor(Color.parseColor(color));

        mSwitcher.setText(color);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshUI();
            }
        }, 1000);
    }


}
