package iip.hr.recent.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Mihovil
 */
public class Record {

    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String ARRIVED = "arrived";
    private static final String LEFT = "left";
    private static final String WORK_HOURS = "workHours";

    @SerializedName(ID)
    private Long id;

    @SerializedName(DATE)
    private String date;

    @SerializedName(ARRIVED)
    private String arrived;

    @SerializedName(LEFT)
    private String left;

    @SerializedName(WORK_HOURS)
    private Integer workHours;

    public Record() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrived() {
        return arrived;
    }

    public void setArrived(String arrived) {
        this.arrived = arrived;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }
}
