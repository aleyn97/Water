package sh.com.water.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

public class WaterBaiKeBean {

    private List<WikiInfoBean> wikiInfo;

    public List<WikiInfoBean> getWikiInfo() {
        return wikiInfo;
    }

    public void setWikiInfo(List<WikiInfoBean> wikiInfo) {
        this.wikiInfo = wikiInfo;
    }

    public static class WikiInfoBean {
        /**
         * id : 8
         * Wiki_title : 夏季用水注意清洁
         */

        private int id;
        private String Wiki_title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWiki_title() {
            return Wiki_title;
        }

        public void setWiki_title(String Wiki_title) {
            this.Wiki_title = Wiki_title;
        }
    }
}
