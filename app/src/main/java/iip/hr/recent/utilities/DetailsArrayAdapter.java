package iip.hr.recent.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import iip.hr.recent.R;
import iip.hr.recent.model.Record;

/**
 * @author Mihovil
 */
public class DetailsArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<Record> records;

    public DetailsArrayAdapter(Context context, List<Record> records) {
        super(context, -1);
        this.context = context;
        this.records = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.record_details_row, parent, false);
        TextView recordTime = (TextView) row.findViewById(R.id.tv_record_time);
        TextView recordDesc = (TextView) row.findViewById(R.id.tv_record_desc);
        Record record = records.get(position);
//        recordTime.setText();
//        recordDesc.setText();
        return row;
    }
}
