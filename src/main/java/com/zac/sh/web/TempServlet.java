package com.zac.sh.web;

import com.zac.sh.dao.TemperatureMapper;
import com.zac.sh.pojo.Temperature;
import com.zac.sh.util.SqlSessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/tempServlet")
public class TempServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        SqlSession session = SqlSessionUtil.getFactory().openSession();
        TemperatureMapper mapper = session.getMapper(TemperatureMapper.class);

        // 从请求参数中获取 boxId
        Temperature temperature = mapper.queryTemp();

        String jsonResponse = "{\"temperature\": " + temperature.getValue() + "}";
        response.getWriter().print(jsonResponse);
    }
}
