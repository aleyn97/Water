package sh.com.water.bean;

/**
 * Created by Administrator on 2017/10/26.
 */

public class IllegaListBean {

    /**
     * id : 59
     * Violation_Content : 透水
     * Violation_OK : 0
     * Violation_Time : 20171026182750
     */

    private int id;
    private String Violation_Content;
    private int Violation_OK;
    private long Violation_Time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViolation_Content() {
        return Violation_Content;
    }

    public void setViolation_Content(String Violation_Content) {
        this.Violation_Content = Violation_Content;
    }

    public int getViolation_OK() {
        return Violation_OK;
    }

    public void setViolation_OK(int Violation_OK) {
        this.Violation_OK = Violation_OK;
    }

    public long getViolation_Time() {
        return Violation_Time;
    }

    public void setViolation_Time(long Violation_Time) {
        this.Violation_Time = Violation_Time;
    }
}
