package iip.hr.recent.model;

import java.util.List;

/**
 * @author Mihovil
 */
public class RecordDetail {

    private String date;

    List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
