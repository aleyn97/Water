package sh.com.water.bean;

/**
 * Created by Administrator on 2017/10/8.
 */

public class StopWaterDetailBean {

    /**
     * noticeInfo : {"id":1,"Notice_Content":"swfdec","Notice_Time":201710081117,"Staff_Name":"系统管理员","Notice_title":"停水tongzhi "}
     */

    private NoticeInfoBean noticeInfo;

    public NoticeInfoBean getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(NoticeInfoBean noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public static class NoticeInfoBean {
        /**
         * id : 1
         * Notice_Content : swfdec
         * Notice_Time : 201710081117
         * Staff_Name : 系统管理员
         * Notice_title : 停水tongzhi
         */

        private int id;
        private String Notice_Content;
        private long Notice_Time;
        private String Staff_Name;
        private String Notice_title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNotice_Content() {
            return Notice_Content;
        }

        public void setNotice_Content(String Notice_Content) {
            this.Notice_Content = Notice_Content;
        }

        public long getNotice_Time() {
            return Notice_Time;
        }

        public void setNotice_Time(long Notice_Time) {
            this.Notice_Time = Notice_Time;
        }

        public String getStaff_Name() {
            return Staff_Name;
        }

        public void setStaff_Name(String Staff_Name) {
            this.Staff_Name = Staff_Name;
        }

        public String getNotice_title() {
            return Notice_title;
        }

        public void setNotice_title(String Notice_title) {
            this.Notice_title = Notice_title;
        }
    }
}
