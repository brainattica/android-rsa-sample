package brainattica.com.rsasample.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import brainattica.com.rsasample.R;
import brainattica.com.rsasample.crypto.SignatureUtils;
import brainattica.com.rsasample.utils.Preferences;

/**
 * Created by javiermanzanomorilla on 12/05/15.
 */
public class VerifyAndSignFragment extends Fragment implements PagerSlide {

    private TextView encryptedText;

    private TextView signedText;

    private TextView verification;

    private Button verify;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_or_verify, container, false);
        encryptedText = (TextView) view.findViewById(R.id.encrypted_text);
        signedText = (TextView) view.findViewById(R.id.signed_text);
        verification = (TextView) view.findViewById(R.id.verification);
        verify = (Button) view.findViewById(R.id.verify);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Preferences.getString(Preferences.ENCRYPTED_MESSAGE) == null) {
                    Toast.makeText(getActivity(), "You need to encrypt a message before", Toast.LENGTH_LONG).show();
                    return;
                }
                String signature = SignatureUtils.genSignature(Preferences.getString(Preferences.ENCRYPTED_MESSAGE));
                boolean isValid = SignatureUtils.checkSignature(signature, Preferences.getString(Preferences.ENCRYPTED_MESSAGE));
                signedText.setText("Signature: " + signature);
                verification.setText(isValid? "Verification correct" : "Verification failed");
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Preferences.getString(Preferences.ENCRYPTED_MESSAGE) == null) {
            encryptedText.setText("You need to encrypt a message before");
        } else {
            encryptedText.setText("Message to be signed&verified: " + Preferences.getString(Preferences.ENCRYPTED_MESSAGE));
        }
    }

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
