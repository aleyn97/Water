package sh.com.water.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/24.
 */

public class BusOutBean {

    private List<TimeInfoBean> timeInfo;

    public List<TimeInfoBean> getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(List<TimeInfoBean> timeInfo) {
        this.timeInfo = timeInfo;
    }

    public static class TimeInfoBean {
        /**
         * id : 8
         * PayOffice_Site : 河南省郑州市中原区中原西路178号
         * PayOffice_Name : 中心营业厅
         * PayOffice_B_WGS : null
         * PayOffice_L_WGS : null
         * PayOffice_StartTime : 08:00
         * PayOffice_EndTime : 18:00
         * PayOffice_Phone : 13838298317
         */

        private int id;
        private String PayOffice_Site;
        private String PayOffice_Name;
        private Object PayOffice_B_WGS;
        private Object PayOffice_L_WGS;
        private String PayOffice_StartTime;
        private String PayOffice_EndTime;
        private long PayOffice_Phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPayOffice_Site() {
            return PayOffice_Site;
        }

        public void setPayOffice_Site(String PayOffice_Site) {
            this.PayOffice_Site = PayOffice_Site;
        }

        public String getPayOffice_Name() {
            return PayOffice_Name;
        }

        public void setPayOffice_Name(String PayOffice_Name) {
            this.PayOffice_Name = PayOffice_Name;
        }

        public Object getPayOffice_B_WGS() {
            return PayOffice_B_WGS;
        }

        public void setPayOffice_B_WGS(Object PayOffice_B_WGS) {
            this.PayOffice_B_WGS = PayOffice_B_WGS;
        }

        public Object getPayOffice_L_WGS() {
            return PayOffice_L_WGS;
        }

        public void setPayOffice_L_WGS(Object PayOffice_L_WGS) {
            this.PayOffice_L_WGS = PayOffice_L_WGS;
        }

        public String getPayOffice_StartTime() {
            return PayOffice_StartTime;
        }

        public void setPayOffice_StartTime(String PayOffice_StartTime) {
            this.PayOffice_StartTime = PayOffice_StartTime;
        }

        public String getPayOffice_EndTime() {
            return PayOffice_EndTime;
        }

        public void setPayOffice_EndTime(String PayOffice_EndTime) {
            this.PayOffice_EndTime = PayOffice_EndTime;
        }

        public long getPayOffice_Phone() {
            return PayOffice_Phone;
        }

        public void setPayOffice_Phone(long PayOffice_Phone) {
            this.PayOffice_Phone = PayOffice_Phone;
        }
    }
}
