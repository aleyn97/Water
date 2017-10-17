package sh.com.water.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

public class StopWaterNoticBean {


    private List<NoticeInfoBean> noticeInfo;

    public List<NoticeInfoBean> getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(List<NoticeInfoBean> noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public static class NoticeInfoBean {
        /**
         * id : 1
         * Notice_title : 停水tongzhi
         */

        private int id;
        private String Notice_title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNotice_title() {
            return Notice_title;
        }

        public void setNotice_title(String Notice_title) {
            this.Notice_title = Notice_title;
        }
    }
}
