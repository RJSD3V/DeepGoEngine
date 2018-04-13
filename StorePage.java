/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepgo;

import java.sql.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 *
 * @author USER
 */
public class StorePage {
    
    public static void enterDate() throws ClassNotFoundException
    {
        
        try{
            
        }
        catch(Exception e){
            
        }
        
    }
    
    public static void createDatabase() throws ClassNotFoundException
    {
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
           final String User="username";
           final String Password="Pasword";
           System.out.println("Connecting to Database...");
           
        
            
        }
        catch(Exception e){
        
    }
        
        
        try{
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","Pa$$word");
            
            Statement st=con.createStatement();
            
            String createDatabase="create database SearchEngineDeepGo";
            String useDatabase="use searchenginedeepgo";
            String create_Table_UserDatabase="create table User ( UserName VARCHAR(255), Password VARCHAR(255))";
            String create_Table_Links="CREATE TABLE LINKS_DATABASE(keyword VARCHAR(255), LINK VARCHAR(255), TITLE VARCHAR(255), PRIMARY KEY(keyword))";
             String create_Table_UserHistory="CREATE TABLE HISTORY(keyword VARCHAR(255), LINK VARCHAR(255), TITLE VARCHAR(255), Time VARCHAR(255), PRIMARY KEY(keyword))";
            
            
             
            st.executeUpdate(createDatabase);
            st.executeUpdate(useDatabase);
            st.executeUpdate(create_Table_UserDatabase);
            st.executeUpdate(create_Table_Links);
            
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   
    
    public void store(String Search) throws ClassNotFoundException,IOException
    {
      
        String key;
        String link;
        String title;
        String paragraph;
        String imageLink;
        
        
        AggregateLinks js=new AggregateLinks();
        
        
        key=Search;
        link="https://www.wikipedia.org/wiki/"+Search;
        title=js.getTitle(Search);
        paragraph=js.getContent(Search);
        imageLink=js.getImage(Search);
        
        
        
        
        try{
            
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","Pa$$word");
            
            Statement st=con.createStatement();
        
        
        String insert="INSERT INTO LINKS_DATABASE (keyword, LINK,TITLE) VALUES("+"'"+title+"','"+link+"','"+title+"')";
        System.out.println(insert);
        
        st.executeUpdate("use searchenginedeepgo");
        st.executeUpdate(insert);
        
        
        }
        catch(Exception e)
        {
         
            e.printStackTrace();
        }
    }
        
        
        public void CreateUser(String User,String PassWord) throws ClassNotFoundException,InstantiationException,IllegalAccessException
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            try{
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","Pa$$word");
            
            Statement st=con.createStatement();
            
            ResultSet s=st.executeQuery("select "+User+" from User");
            
            if(s==null)
            {
            st.executeUpdate("INSERT INTO User VALUES('"+User+"','"+PassWord+")");
            }
            else{
                
            }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
           
        
        }
        
        public boolean checkUser(String User,String PassWord) throws ClassNotFoundException,InstantiationException,IllegalAccessException
        {
            boolean throwBack=false;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            try{
            java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","Pa$$word");
            
            Statement state=con.createStatement();
            
            ResultSet st=state.executeQuery("select "+User+" from User if(Password='"+PassWord+"','"+User+"',NULL)");
            
            if(st!=null)
            {
                throwBack=true;
            }
            else
            {
                throwBack=false;
            }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return throwBack;
        }
        
        
        
        
        
        
    
    public static void main(String args[]) throws ClassNotFoundException
    {
       createDatabase();
    }
    
}
