package iip.hr.recent.view;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import iip.hr.recent.R;
import iip.hr.recent.model.Record;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author Mihovil
 */
public class TablesFactory {

    private final Context context;
    private final TableLayout tableLayout;

    public TablesFactory(Context context, TableLayout tableLayout) {
        this.context = context;
        this.tableLayout = tableLayout;
    }

    public void getHeader() {
        final TableRow header = createHeader();

        final TextView date = createTextView("Datum");
        final TextView arrived = createTextView("Dolazak");
        final TextView left = createTextView("Odlazak");
        final TextView workHours = createTextView("Radni sati");

        header.addView(date);
        header.addView(arrived);
        header.addView(left);
        header.addView(workHours);

        tableLayout.addView(header, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    }

    public void getRows(final List<Record> records) {
        Integer id = 0;
        for (final Record record : records) {
            final TableRow row = new TableRow(context);
            row.setPadding(8,8,8,8);
            row.setBackgroundResource(R.drawable.selector);
            row.setId(id++);
            row.setLayoutParams(createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));

            TextView date = createTextView(record.getDate());
            TextView arrived = createTextView(record.getArrived());
            TextView left = createTextView(record.getLeft());
            TextView workHours = createTextView(record.getWorkHours().toString());

            row.addView(date);
            row.addView(arrived);
            row.addView(left);
            row.addView(workHours);

            tableLayout.addView(row, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        }
    }

    public TextView createTextView(final String text) {
        TextView view = new TextView(context);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        view.setText(text);
        view.setGravity(View.TEXT_ALIGNMENT_CENTER);
        view.setTextColor(Color.BLACK);
        view.setPadding(8,8,8,8);
        return view;
    }

    public TableRow createHeader() {
        final TableRow row = new TableRow(context);
        row.setBackgroundResource(R.color.pink);
//        row.setPadding(8,8,8,8);
        row.setLayoutParams(createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        return row;
    }

    public TableLayout.LayoutParams createLayoutParams(int w, int h) {
        return new TableLayout.LayoutParams(w, h);
    }

}
