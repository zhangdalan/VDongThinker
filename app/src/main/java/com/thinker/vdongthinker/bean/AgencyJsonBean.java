package com.thinker.vdongthinker.bean;

import java.util.List;

/**
 * Created by zjw on 2019/1/10.
 */
public class AgencyJsonBean {

        public List<AgencyMallRecyclerBean> list;
        public String name;

        public List<AgencyMallRecyclerBean> getList() {
            return list;
        }

        public void setList(List<AgencyMallRecyclerBean> list) {
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
