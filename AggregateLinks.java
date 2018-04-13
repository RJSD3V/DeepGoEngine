/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepgo;

/**
 *
 * @author USER
 */
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import java.util.*;
public class AggregateLinks {
    
    static ArrayList<String> r;
    static ArrayList<String> rL;
    static ArrayList<String> p;
    static ArrayList<String> t;
    static ArrayList<String> rI;
    
    
    
  
    public String[] getAllImages(String Search)throws IOException
    {
        
        Connection con=Jsoup.connect("https://www.wikipedia.org/wiki/"+Search);
        Document document=con.get();
        Element body=document.getElementById("mw-conent-text");
        Elements im=body.getElementsByTag("a");
        
        rI=new ArrayList<String>();
        
        
        for(Element i:im)
        {
            Elements x=i.getElementsByTag("img");
            
            for(Element xx:x)
            {
                rI.add(xx.attr("abs:src"));
            }
            
        }
        
        String links[]=new String[rI.size()];
        links=rI.toArray(links);
        for(int i=0;i<links.length;i++)
        {
            System.out.println(links[i]);
        }
        return links;
        
    }
    
    public String getTitle(String Search)throws IOException
   {
        Connection con=Jsoup.connect("https://www.wikipedia.org/wiki/"+Search);
        Document document=con.get();
       
        
        Element body=document.getElementById("mw-content-text");
        Elements ref=body.getElementsByTag("h2");
        
       StringBuilder Title=new StringBuilder(document.getElementById("firstHeading").text());
        
        
        return Title.toString();
   }
    
    
    public String[] getReferenceLinks(String Search) throws IOException
    {
       Connection con=Jsoup.connect("https://www.wikipedia.org/wiki/"+Search);
        Document document=con.get();
       
        
        Element body=document.getElementById("mw-content-text");
        Elements ref=body.getElementsByTag("a");
        
        
        rL=new ArrayList<String>();
        
        
         for(Element i:ref)
        {
            rL.add(i.attr("abs:href"));
          
        }
         String [] ReferenceLinks=new String[rL.size()];
         
         ReferenceLinks=rL.toArray(ReferenceLinks);
        
        
      return ReferenceLinks;  
    }
    public String[] getReference(String Search) throws IOException
    {
        
         Connection con=Jsoup.connect("https://www.wikipedia.org/wiki/"+Search);
        Document document=con.get();
       
        
        Element body=document.getElementById("mw-content-text");
        Elements ref=body.getElementsByTag("a");
        
        r=new ArrayList<String>();
       
        
        
        for(Element i:ref)
        {
            
            r.add(i.attr("title"));
        }
        
        

        String [] Reference= new String[r.size()];
        

        Reference=r.toArray(Reference);
        

        return Reference;
        
        
    }
    
    
    public  String getContent(String Search) throws IOException
    {
    
    Connection con=Jsoup.connect("https://www.wikipedia.org/wiki/"+Search);
        
        Document document=con.get();
        
        Element body=document.getElementById("mw-content-text");
        Elements ref=body.getElementsByTag("p");
        
        
       StringBuilder s=new StringBuilder(ref.first().text());
       int c=0;
       for(Element r:ref)
       {
           if(c==10){
                 break;        
           }
           else
           {
               s.append("\n");
               s.append(r.text());
           }
           c++;
       }
       
       
        
       
       return s.toString();
    }
    
    public String getImage(String Search) throws IOException
    {
        
      Connection con=Jsoup.connect("https://www.wikipedia.org/wiki/"+Search);
        
        Document document=con.get();
        
        Element body=document.getElementById("mw-content-text");
        Element IMAGE=body.getElementsByTag("img").first();
        
        
        StringBuilder s=new StringBuilder(IMAGE.attr("abs:src"));
        
       
       return s.toString();
    }
    
    
    
    
    
    
}
