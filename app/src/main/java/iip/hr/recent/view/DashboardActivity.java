package iip.hr.recent.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.common.base.Optional;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iip.hr.recent.R;
import iip.hr.recent.model.Record;
import iip.hr.recent.model.RetrofitFactory;
import iip.hr.recent.model.User;
import iip.hr.recent.utilities.IntentFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.tl_current_month)
    protected TableLayout tableLayout;

    @BindView(R.id.progress_background)
    protected RelativeLayout loadingProgress;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String[] years = {"1996","1997","1998","1998"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, years );
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner1.setAdapter(langAdapter);
        spinner2.setAdapter(langAdapter);

        User user = (User) getIntent().getSerializableExtra(IntentFactory.USER);
        
        if (Optional.of(user).isPresent()) {
            
            subscription = RetrofitFactory.createService().getCurrentMonthRecord()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<Record>>() {
                        @Override
                        public void call(List<Record> records) {
                            TablesFactory tablesFactory = new TablesFactory(getApplicationContext(), tableLayout);
                            tablesFactory.getHeader();
                            tablesFactory.getRows(records);
                            loadingProgress.setVisibility(View.GONE);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Toast.makeText(DashboardActivity.this, "Greska. " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) subscription.unsubscribe();
    }
}
