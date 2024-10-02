package com.zac.sh.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac.sh.dao.PillMapper;
import com.zac.sh.pojo.Pill;
import com.zac.sh.service.PillService;
import com.zac.sh.util.SqlSessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/getPills")
public class PillServlet extends HttpServlet {
    private PillService pillService = new PillService(); // 假设已经正确初始化
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        SqlSession session = SqlSessionUtil.getFactory().openSession();
        PillMapper mapper = session.getMapper(PillMapper.class);

        Pill[] pills = pillService.queryAllPills();

        // 使用 Jackson 将 Java 对象转换为 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(pills);
        System.out.println(jsonResponse);

        // 将 JSON 响应发送给前端
        response.getWriter().write(jsonResponse);

    }
}
