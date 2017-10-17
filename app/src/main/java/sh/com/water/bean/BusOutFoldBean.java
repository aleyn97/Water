package sh.com.water.bean;

/**
 * Created by Administrator on 2017/9/30.
 */

public class BusOutFoldBean {


    /**
     * guideInfo : {"id":9,"Guide_Content":"水表检定操作流程；逐台检定操作具体步骤：；一、检查校验台各仪表、计量器具、阀门等工作正常；三、关闭调节阀（6）、（7）、（8）、（9），开；四、开启需用的流量计调节阀，排出管道和水表中的空；五、检定水表(Φ15)最大流量；）；差则为不合格；六、检定分界流量；零时关闭并记录读数，开启控制阀（4）排出工作量器；七、检定最小流量；八、鉴定始动流量，操作调节阀(9)\r\n\r\n水表检定操作流程\r\n\r\n逐台检定操作具体步骤：\r\n\r\n一、检查校验台各仪表、计量器具、阀门等工作正常。 二、安装对应水表口径的夹表器，将被检水表(Φ15)水平安装在夹表器中，安装水表时注意水表接口与夹表器接口正确对应，防止水表式夹表器的脱落。","Guide_Time":201709301137,"Staff_Name":"系统管理员","Guide_title":"水表检定流程","Guide_Pic":null,"FILE_PATH0":"http://192.168.1.119:8080/upload/infofiles/20170930113713_5655.png"}
     */

    private GuideInfoBean guideInfo;

    public GuideInfoBean getGuideInfo() {
        return guideInfo;
    }

    public void setGuideInfo(GuideInfoBean guideInfo) {
        this.guideInfo = guideInfo;
    }

    public static class GuideInfoBean {
        /**
         * id : 9
         * Guide_Content : 水表检定操作流程；逐台检定操作具体步骤：；一、检查校验台各仪表、计量器具、阀门等工作正常；三、关闭调节阀（6）、（7）、（8）、（9），开；四、开启需用的流量计调节阀，排出管道和水表中的空；五、检定水表(Φ15)最大流量；）；差则为不合格；六、检定分界流量；零时关闭并记录读数，开启控制阀（4）排出工作量器；七、检定最小流量；八、鉴定始动流量，操作调节阀(9)

         水表检定操作流程

         逐台检定操作具体步骤：

         一、检查校验台各仪表、计量器具、阀门等工作正常。 二、安装对应水表口径的夹表器，将被检水表(Φ15)水平安装在夹表器中，安装水表时注意水表接口与夹表器接口正确对应，防止水表式夹表器的脱落。
         * Guide_Time : 201709301137
         * Staff_Name : 系统管理员
         * Guide_title : 水表检定流程
         * Guide_Pic : null
         * FILE_PATH0 : http://192.168.1.119:8080/upload/infofiles/20170930113713_5655.png
         */

        private int id;
        private String Guide_Content;
        private long Guide_Time;
        private String Staff_Name;
        private String Guide_title;
        private Object Guide_Pic;
        private String FILE_PATH0;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGuide_Content() {
            return Guide_Content;
        }

        public void setGuide_Content(String Guide_Content) {
            this.Guide_Content = Guide_Content;
        }

        public long getGuide_Time() {
            return Guide_Time;
        }

        public void setGuide_Time(long Guide_Time) {
            this.Guide_Time = Guide_Time;
        }

        public String getStaff_Name() {
            return Staff_Name;
        }

        public void setStaff_Name(String Staff_Name) {
            this.Staff_Name = Staff_Name;
        }

        public String getGuide_title() {
            return Guide_title;
        }

        public void setGuide_title(String Guide_title) {
            this.Guide_title = Guide_title;
        }

        public Object getGuide_Pic() {
            return Guide_Pic;
        }

        public void setGuide_Pic(Object Guide_Pic) {
            this.Guide_Pic = Guide_Pic;
        }

        public String getFILE_PATH0() {
            return FILE_PATH0;
        }

        public void setFILE_PATH0(String FILE_PATH0) {
            this.FILE_PATH0 = FILE_PATH0;
        }
    }
}
