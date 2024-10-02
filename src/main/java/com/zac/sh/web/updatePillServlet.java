package com.zac.sh.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac.sh.pojo.Pill;
import com.zac.sh.service.PillService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updatePill")
public class updatePillServlet extends HttpServlet {
    private PillService pillService;

    @Override
    public void init() throws ServletException {
        pillService = new PillService();  // 使用依赖注入时应注入实例
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 使用 ObjectMapper 从请求中解析出 Pill 对象
        ObjectMapper mapper = new ObjectMapper();
        Pill pill = mapper.readValue(request.getInputStream(), Pill.class);

        // 调用服务层更新药物信息
        boolean success = pillService.updatePill(pill);

        // 返回响应结果
        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Pill updated successfully\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"message\": \"Failed to update pill\"}");
        }
    }
}
