package com.thinker.vdongthinker.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zjw on 2019/1/10.
 */
public class CourseJsonBean implements Serializable {

        public String name;
        public List<CourseDetailBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CourseDetailBean> getList() {
            return list;
        }

        public void setList(List<CourseDetailBean> list) {
            this.list = list;
        }
}
