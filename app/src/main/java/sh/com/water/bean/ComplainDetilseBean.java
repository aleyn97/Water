package sh.com.water.bean;

/**
 * Created by Administrator on 2017/10/28.
 */

public class ComplainDetilseBean {

    /**
     * Complaints_Content : 图片
     * Complaints_Time : 20171028103618
     * Complaints_Phone : 13838298317
     * Complaints_number : 20171028
     * Acceptance_Name : admin_zz
     * Acceptance_Content : 已处理
     * Acceptance_Time : 20171028143700
     * FILE_PATH0 : http://192.168.1.119//:8080/upload/infofiles/TSJY800.jpg
     * FILE_PATH1 : http://192.168.1.119//:8080/upload/infofiles/TSJY800.jpg
     * FILE_PATH2 : http://192.168.1.119//:8080/upload/infofiles/TSJY800.jpg
     * FILE_PATH3 : http://192.168.1.119//:8080/upload/infofiles/TSJY800.jpg
     */

    private String Complaints_Content;
    private long Complaints_Time;
    private String Complaints_Phone;
    private String Complaints_number;
    private String Acceptance_Name;
    private String Acceptance_Content;
    private String Acceptance_Time;
    private String FILE_PATH0;
    private String FILE_PATH1;
    private String FILE_PATH2;
    private String FILE_PATH3;

    public String getFILE_PATH1() {
        return FILE_PATH1;
    }

    public void setFILE_PATH1(String FILE_PATH1) {
        this.FILE_PATH1 = FILE_PATH1;
    }

    public String getFILE_PATH2() {
        return FILE_PATH2;
    }

    public void setFILE_PATH2(String FILE_PATH2) {
        this.FILE_PATH2 = FILE_PATH2;
    }

    public String getFILE_PATH3() {
        return FILE_PATH3;
    }

    public void setFILE_PATH3(String FILE_PATH3) {
        this.FILE_PATH3 = FILE_PATH3;
    }

    public String getComplaints_Content() {
        return Complaints_Content;
    }

    public void setComplaints_Content(String Complaints_Content) {
        this.Complaints_Content = Complaints_Content;
    }

    public long getComplaints_Time() {
        return Complaints_Time;
    }

    public void setComplaints_Time(long Complaints_Time) {
        this.Complaints_Time = Complaints_Time;
    }

    public String getComplaints_Phone() {
        return Complaints_Phone;
    }

    public void setComplaints_Phone(String Complaints_Phone) {
        this.Complaints_Phone = Complaints_Phone;
    }

    public String getComplaints_number() {
        return Complaints_number;
    }

    public void setComplaints_number(String Complaints_number) {
        this.Complaints_number = Complaints_number;
    }

    public String getAcceptance_Name() {
        return Acceptance_Name;
    }

    public void setAcceptance_Name(String Acceptance_Name) {
        this.Acceptance_Name = Acceptance_Name;
    }

    public String getAcceptance_Content() {
        return Acceptance_Content;
    }

    public void setAcceptance_Content(String Acceptance_Content) {
        this.Acceptance_Content = Acceptance_Content;
    }

    public String getAcceptance_Time() {
        return Acceptance_Time;
    }

    public void setAcceptance_Time(String Acceptance_Time) {
        this.Acceptance_Time = Acceptance_Time;
    }

    public String getFILE_PATH0() {
        return FILE_PATH0;
    }

    public void setFILE_PATH0(String FILE_PATH0) {
        this.FILE_PATH0 = FILE_PATH0;
    }
}
