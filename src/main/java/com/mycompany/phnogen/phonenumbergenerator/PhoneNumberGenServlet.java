/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phnogen.phonenumbergenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author prade
 */
@WebServlet(name = "PhoneNumberGenServlet", urlPatterns = {"/PhoneNumberGenServlet"})
public class PhoneNumberGenServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ServletContext context;
    List<String> data; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        
        /*try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PhoneNumberGenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PhoneNumberGenServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
        String action = request.getParameter("action");
        String phoneDigits = request.getParameter("phonenumber");
        if(phoneDigits == null)
        {
            phoneDigits = request.getParameter("id");
        }
        System.out.println("Phone number :"+ phoneDigits) ;
        
        StringBuffer sb = new StringBuffer();
        PhoneNumberStore phoneNumberStore = new PhoneNumberStore() ;
        //Gson gson = new Gson(); 
        
        JSONArray myArray = new JSONArray();
         JSONObject myDataObject = new JSONObject();
        
        if(phoneDigits==null)
        {
            response.getWriter().write("Null phone digits");
        }
        else if (!phoneDigits.equals("")) {
             
                data = phoneNumberStore.getAlphaNumericPhoneNoAll(phoneDigits) ;
                System.out.println("Number of records :"+ data.size()) ;
                //String jsonArray = gson.toJson(phoneList);
                //JsonElement jsonElement = gson.toJsonTree(phoneList);
                for(String dat: data) {
                    JSONObject myObj = new JSONObject();
                   myObj.put("phoneNumber", dat); 
                   myArray.put(myObj);
                   
                }
                myDataObject.put("data", myArray); 
              
               
                //System.out.println("JSON response :"+ myDataObject.toString()) ;
                
        }
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(myDataObject.toString());
      
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

}
