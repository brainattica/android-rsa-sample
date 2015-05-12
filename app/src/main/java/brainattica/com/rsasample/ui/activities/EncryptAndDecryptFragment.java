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
import brainattica.com.rsasample.crypto.RSA;
import brainattica.com.rsasample.utils.Preferences;

/**
 * Created by javiermanzanomorilla on 12/05/15.
 */
public class EncryptAndDecryptFragment extends Fragment implements PagerSlide {

    private TextView textToBeEncrypted;

    private TextView encryptedText;

    private TextView decryptedText;

    private Button encrypt;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encrypt_or_decrypt, container, false);
        textToBeEncrypted = (TextView) view.findViewById(R.id.text_to_be_encrypted);
        encryptedText = (TextView) view.findViewById(R.id.encrypted_text);
        decryptedText = (TextView) view.findViewById(R.id.decrypted_text);
        encrypt = (Button) view.findViewById(R.id.encrypt);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Preferences.getString(Preferences.RSA_PRIVATE_KEY) == null) {
                    Toast.makeText(getActivity(), "There's now RSA KeyPair generated", Toast.LENGTH_LONG).show();
                    return;
                }
                if (textToBeEncrypted.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Enter a correct message to encrypt", Toast.LENGTH_LONG).show();
                    return;
                }
                String message = textToBeEncrypted.getText().toString();
                String encryptedMessage = RSA.encryptWithStoredKey(message);
                String decryptedMessage = RSA.decryptWithStoredKey(encryptedMessage);
                encryptedText.setText("Encrypted Text: " + encryptedMessage);
                decryptedText.setText("Decrypted Text: " + decryptedMessage);

                Preferences.putString(Preferences.ENCRYPTED_MESSAGE, encryptedMessage);
            }
        });
        return view;
    }

    @Override
    public List<Integer> getVisibleButtons() {
        List<Integer> res = new ArrayList<>();
        res.add(R.id.prev);
        res.add(R.id.next);
        return res;
    }

    @Override
    public String getSubtitle() {
        return "Encrypt / Decrypt";
    }

}
