package com.codegym.controller;

import com.codegym.dao.CategoryDAO;
import com.codegym.model.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() {
        categoryDAO = new CategoryDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "delete":
                showDelete(request,response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            default:
                listCategory(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "delete":
                deleteCategory(request,response);
                break;
            case "edit":
                editCategory(request,response);
                break;
            case "create":
                createCategory(request,response);
                break;
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryDAO.deleteCategory(id);

        List<Category>listCategory=categoryDAO.selectAllCategory();
        request.setAttribute("listCategory",listCategory);
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("views/category/display.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category=categoryDAO.selectCategory(id);
        request.setAttribute("category",category);
        RequestDispatcher dispatcher= request.getRequestDispatcher("views/category/delete.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
    private void editCategory(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        Category category=new Category(id,name);
        categoryDAO.updateCategory(category);
        try {
            response.sendRedirect("/CategoryServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.selectCategory(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/category/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void createCategory(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        Category categoryNew=new Category(name);
        categoryDAO.insertCategory(categoryNew);
        try {
            response.sendRedirect("/CategoryServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) {
        List<Category>listCategory=categoryDAO.selectAllCategory();
        request.setAttribute("listCategory",listCategory);
        RequestDispatcher dispatcher=request.getRequestDispatcher("views/category/display.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException |IOException e) {
            e.printStackTrace();
        }

    }
}
