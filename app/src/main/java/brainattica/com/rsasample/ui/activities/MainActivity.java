package brainattica.com.rsasample.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import brainattica.com.rsasample.R;
import brainattica.com.rsasample.utils.Preferences;

/**
 * Created by javiermanzanomorilla on 03/04/15.
 */
public class MainActivity extends ActionBarActivity {

    private ViewPager pager;

    Fragment[] fragments = new Fragment[]{
            new GenerateRSAFragment(),
            new EncryptAndDecryptFragment(),
            new VerifyAndSignFragment()
    };

    private Button prev;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preferences.init(this);
        setContentView(R.layout.activity_main);
        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        setTitle("RSA Sample: " + ((PagerSlide)fragments[0]).getSubtitle());
        pager = (ViewPager) findViewById(R.id.pager);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        pager.setAdapter(new PagerAdapter());
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                PagerSlide f = (PagerSlide) fragments[position];
                List<Integer> visibleButtons = f.getVisibleButtons();
                prev.setVisibility(visibleButtons.contains(R.id.prev) ? View.VISIBLE : View.GONE);
                next.setVisibility(visibleButtons.contains(R.id.next) ? View.VISIBLE : View.GONE);
                setTitle("RSA Sample: " + f.getSubtitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem() + 1);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem() - 1);
            }
        });
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

    }

}
