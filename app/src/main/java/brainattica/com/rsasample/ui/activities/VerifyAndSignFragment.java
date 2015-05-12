package brainattica.com.rsasample.ui.activities;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import brainattica.com.rsasample.R;

/**
 * Created by javiermanzanomorilla on 12/05/15.
 */
public class VerifyAndSignFragment extends Fragment implements PagerFlow {

    @Override
    public List<Integer> getVisibleButtons() {
        List<Integer> res = new ArrayList<>();
        res.add(R.id.prev);
        return res;
    }

    @Override
    public String getSubtitle() {
        return "Verify / Sign";
    }

}
