package iip.hr.recent.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import iip.hr.recent.R;
import iip.hr.recent.service.ApduService;

public class NfcActivity extends AppCompatActivity {

    @BindView(R.id.nfc_inactive)
    protected ImageView inactiveNFC;

    @BindView(R.id.nfc_active)
    protected ImageView activeNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);

        Intent intent = new Intent(getApplicationContext(), ApduService.class);
        intent.putExtra("ndefMessage", "351254714");
        startService(intent);
    }
}
