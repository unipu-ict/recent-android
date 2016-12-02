package iip.hr.recent.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;
import iip.hr.recent.R;
import iip.hr.recent.model.RecordDetail;
import iip.hr.recent.model.RetrofitFactory;
import iip.hr.recent.utilities.DetailsArrayAdapter;
import iip.hr.recent.utilities.IntentFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_date)
    protected TextView date;

    @BindView(R.id.lv_records)
    protected ListView records;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Integer position = (Integer) getIntent().getSerializableExtra(IntentFactory.RECORD);

        if (Optional.of(position).isPresent()) {

            subscription = RetrofitFactory.createService().getRecordDetail(position)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<RecordDetail>() {
                        @Override
                        public void call(RecordDetail details) {
                            final DetailsArrayAdapter adapter = new DetailsArrayAdapter(getApplicationContext(), details.getRecords());
                            records.setAdapter(adapter);
                            records.setOnItemClickListener(onItemClickListener());
//                            loadingProgress.setVisibility(View.GONE);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Toast.makeText(DetailsActivity.this, "Greska. " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        };
        return listener;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) subscription.unsubscribe();
    }
}
