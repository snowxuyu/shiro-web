package org.snowxuyu.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.snowxuyu.shiro.junit.BaseControllerTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by snow on 2015/11/30.
 */
public class UserControllerTest extends BaseControllerTest {

    /**
     * 添加用户测试
     */
    @Test
      @Rollback(false)
      public void addUserTest() {
        try {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/user/addUserList");



            requestBuilder.characterEncoding("UTF-8");
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        } catch (Exception e) {
        }
    }


    @Test
    @Rollback(false)
    public void listUserTest() {
        try {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/user/listUser");


            requestBuilder.characterEncoding("UTF-8");
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            System.out.println("==========>>result:{}"+ JSONObject.toJSONString(result));
        } catch (Exception e) {
        }
    }
}
