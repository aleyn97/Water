package sh.com.water.bean;

/**
 * Created by Administrator on 2017/9/30.
 */

public class WaterBaikeFoldBean {

    /**
     * wikiInfo : {"id":12,"Wiki_Content":"1.什么样的水是安全饮用水？\r\n\r\n　　答：水是构成人体的重要组成部分，是七大营养素（矿物质、脂类、蛋白质、维生素、碳水化合物、水和膳食纤维）之一，对人体健康起着重要的作用。科学、合理、安全的饮用水，才能保障生命的健康生存。\r\n\r\n　　从饮水与健康的角度来讲，良好的饮用水应该符合以下几点要求：一是干净，不含致病菌、重金属和有害化学物质；二是含有适量的矿物质和微量元素；三是含有新鲜适量的溶解氧；四是偏碱性的，水的分子团要小，活性要强。\r\n\r\n　　目前，我国居民的饮用水主要有：自来水、纯净水、人造矿化水、矿泉水和天然水。\r\n\r\n　　自来水是直接取自天然水源(地表水，地下水)，经过一系列处理工艺净化消毒后再输入到各用户，是目前国内最普遍的生活饮用水。\r\n\r\n　　纯净水一般以城市自来水为水源，把有害物质过滤的同时，也去除了钾、钙、镁、铁、锌等人体所需的矿物元素，不宜多喝。\r\n\r\n　　饮用矿物质水是通过人工添加矿物质来改善水的矿物质含量。这样的水虽然补充了纯净水中部分矿物元素的不足，但是添加的矿物质能否被人体吸收、利用，还需要进一步研究。\r\n\r\n　　矿泉水是指从地下深处自然涌出或人工开采所得到的未受污染的天然地下水。矿泉水含有一定的矿物盐、微量元素和二氧化碳气体，容易被人体吸收。适量饮用矿泉水对身体健康有益。","Wiki_Time":201709301047,"Staff_Name":"系统管理员","Wiki_title":"你不得不知道的\u201c日常用水小知识\u201d","Wiki_Pic":null,"FILE_PATH0":"http://192.168.1.119:8080/upload/infofiles/20170930115800_3858.png"}
     */

    private WikiInfoBean wikiInfo;

    public WikiInfoBean getWikiInfo() {
        return wikiInfo;
    }

    public void setWikiInfo(WikiInfoBean wikiInfo) {
        this.wikiInfo = wikiInfo;
    }

    public static class WikiInfoBean {
        /**
         * id : 12
         * Wiki_Content : 1.什么样的水是安全饮用水？

         　　答：水是构成人体的重要组成部分，是七大营养素（矿物质、脂类、蛋白质、维生素、碳水化合物、水和膳食纤维）之一，对人体健康起着重要的作用。科学、合理、安全的饮用水，才能保障生命的健康生存。

         　　从饮水与健康的角度来讲，良好的饮用水应该符合以下几点要求：一是干净，不含致病菌、重金属和有害化学物质；二是含有适量的矿物质和微量元素；三是含有新鲜适量的溶解氧；四是偏碱性的，水的分子团要小，活性要强。

         　　目前，我国居民的饮用水主要有：自来水、纯净水、人造矿化水、矿泉水和天然水。

         　　自来水是直接取自天然水源(地表水，地下水)，经过一系列处理工艺净化消毒后再输入到各用户，是目前国内最普遍的生活饮用水。

         　　纯净水一般以城市自来水为水源，把有害物质过滤的同时，也去除了钾、钙、镁、铁、锌等人体所需的矿物元素，不宜多喝。

         　　饮用矿物质水是通过人工添加矿物质来改善水的矿物质含量。这样的水虽然补充了纯净水中部分矿物元素的不足，但是添加的矿物质能否被人体吸收、利用，还需要进一步研究。

         　　矿泉水是指从地下深处自然涌出或人工开采所得到的未受污染的天然地下水。矿泉水含有一定的矿物盐、微量元素和二氧化碳气体，容易被人体吸收。适量饮用矿泉水对身体健康有益。
         * Wiki_Time : 201709301047
         * Staff_Name : 系统管理员
         * Wiki_title : 你不得不知道的“日常用水小知识”
         * Wiki_Pic : null
         * FILE_PATH0 : http://192.168.1.119:8080/upload/infofiles/20170930115800_3858.png
         */

        private int id;
        private String Wiki_Content;
        private long Wiki_Time;
        private String Staff_Name;
        private String Wiki_title;
        private Object Wiki_Pic;
        private String FILE_PATH0;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWiki_Content() {
            return Wiki_Content;
        }

        public void setWiki_Content(String Wiki_Content) {
            this.Wiki_Content = Wiki_Content;
        }

        public long getWiki_Time() {
            return Wiki_Time;
        }

        public void setWiki_Time(long Wiki_Time) {
            this.Wiki_Time = Wiki_Time;
        }

        public String getStaff_Name() {
            return Staff_Name;
        }

        public void setStaff_Name(String Staff_Name) {
            this.Staff_Name = Staff_Name;
        }

        public String getWiki_title() {
            return Wiki_title;
        }

        public void setWiki_title(String Wiki_title) {
            this.Wiki_title = Wiki_title;
        }

        public Object getWiki_Pic() {
            return Wiki_Pic;
        }

        public void setWiki_Pic(Object Wiki_Pic) {
            this.Wiki_Pic = Wiki_Pic;
        }

        public String getFILE_PATH0() {
            return FILE_PATH0;
        }

        public void setFILE_PATH0(String FILE_PATH0) {
            this.FILE_PATH0 = FILE_PATH0;
        }
    }
}
