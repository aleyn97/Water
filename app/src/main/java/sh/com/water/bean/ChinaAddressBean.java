package sh.com.water.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
@XStreamAlias("China")
public class ChinaAddressBean {
    @XStreamImplicit(itemFieldName = "State")
    private List<State> State;

    public List<State> getState() {
        return State;
    }

    public void setState(List<ChinaAddressBean.State> state) {
        State = state;
    }

    public static class State {
        @XStreamAsAttribute()
        @XStreamAlias("Name")
        private String Name;
        @XStreamAsAttribute()
        @XStreamAlias("Code")
        private String Code;
        @XStreamImplicit(itemFieldName = "City")
        private List<City> City;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String code) {
            Code = code;
        }

        public List<City> getCity() {
            return City;
        }

        public void setCity(List<City> city) {
            City = city;
        }

        public static class City {
            @XStreamAsAttribute()
            @XStreamAlias("Name")
            private String Name;
            @XStreamAsAttribute()
            @XStreamAlias("Code")
            private String Code;
            @XStreamImplicit(itemFieldName = "Region")
            private List<Region> Region;

            public List<Region> getRegion() {
                return Region;
            }

            public void setRegion(List<Region> region) {
                Region = region;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String code) {
                Code = code;
            }

            public static class Region {
                @XStreamAsAttribute()
                @XStreamAlias("Name")
                private String Name;
                @XStreamAsAttribute()
                @XStreamAlias("Code")
                private String Code;

                public String getName() {
                    return Name;
                }

                public void setName(String name) {
                    Name = name;
                }

                public String getCode() {
                    return Code;
                }

                public void setCode(String code) {
                    Code = code;
                }
            }
        }
    }
}
