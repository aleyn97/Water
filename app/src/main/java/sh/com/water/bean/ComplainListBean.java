package sh.com.water.bean;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ComplainListBean {

    /**
     * id : 78
     * Complaints_Content : 透水
     * Complaints_OK : 0
     * Complaints_Time : 20171028095254
     */

    private int id;
    private String Complaints_Content;
    private int Complaints_OK;
    private long Complaints_Time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplaints_Content() {
        return Complaints_Content;
    }

    public void setComplaints_Content(String Complaints_Content) {
        this.Complaints_Content = Complaints_Content;
    }

    public int getComplaints_OK() {
        return Complaints_OK;
    }

    public void setComplaints_OK(int Complaints_OK) {
        this.Complaints_OK = Complaints_OK;
    }

    public long getComplaints_Time() {
        return Complaints_Time;
    }

    public void setComplaints_Time(long Complaints_Time) {
        this.Complaints_Time = Complaints_Time;
    }
}
