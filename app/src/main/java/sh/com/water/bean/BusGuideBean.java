package sh.com.water.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class BusGuideBean {


    private List<GuideInfoBean> guideInfo;

    public List<GuideInfoBean> getGuideInfo() {
        return guideInfo;
    }

    public void setGuideInfo(List<GuideInfoBean> guideInfo) {
        this.guideInfo = guideInfo;
    }

    public static class GuideInfoBean {
        /**
         * id : 4
         * Guide_title : 用水报装流程
         */

        private int id;
        private String Guide_title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGuide_title() {
            return Guide_title;
        }

        public void setGuide_title(String Guide_title) {
            this.Guide_title = Guide_title;
        }
    }
}
