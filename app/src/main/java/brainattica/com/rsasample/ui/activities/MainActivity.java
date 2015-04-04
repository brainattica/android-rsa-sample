package brainattica.com.rsasample.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

import brainattica.com.rsasample.R;

/**
 * Created by javiermanzanomorilla on 03/04/15.
 */
public class MainActivity extends ActionBarActivity {

    private Button generate;

    private Button encryptOrDecrypt;

    private Button signOrVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate = (Button) findViewById(R.id.generate_rsa);
        encryptOrDecrypt = (Button) findViewById(R.id.encrypt_decrypt);
        signOrVerify = (Button) findViewById(R.id.sign_verify);
    }


}
