/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepgo;

import java.awt.event.KeyEvent;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.Date;

/**
 *
 * @author USER
 */
public class DeepGo extends Application {
    
    
    Image wall;
    final ScrollBar sc=new ScrollBar();
    ImageView backdrop;
    Image aboutPageWall;
    String Seed;
   static ArrayList <String> trace=new ArrayList<String>();
     
       
    ImageView aboutWall=new ImageView(aboutPageWall);
    
    
    

     public boolean authenticateLogin(String Login,String Pass,Stage primaryStage,Scene scene) throws ClassNotFoundException,InstantiationException,IllegalAccessException{
      boolean launch=false;
         StorePage pg=new StorePage();
         try{
        boolean check=pg.checkUser(Login, Pass);
        
          if(check==false)
        {
            launch=true;
        }
        else
        {
            launch=false;
        }
    
         }
         catch(ClassNotFoundException e)
         {
             
         }
        
      return launch;
        
        
        
    }
    public boolean UserExists(String Login,String Pass){
        boolean check=true;
        
        return check;
    }
    public void createUser(String Login,String Pass){
        try{
            Class.forName("");
            
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
    }
    
    
    
    public void LoadImageTab(FlowPane root,String s)throws IOException{
        
        AggregateLinks lnks=new AggregateLinks();
        
        String [] links=lnks.getAllImages(s);
      
        
        
        for(int i=0;i<links.length;i++)
        {
            
            System.out.println(links[i]);
        Image image=new Image(links[i]);
        
        ImageView View=new ImageView(image);

        root.getChildren().add(View);

        }
        
    }
    
    
    public void getArticle(String SearchString,Stage primaryStage,Scene scn,Group group) throws IOException,ClassNotFoundException
{
    
    StorePage st=new StorePage();
    
    st.store(SearchString);
    
    Group GoDeepRoot=new Group();
    Scene GoDeepScene=new Scene(GoDeepRoot,1920,1080);
    Group WebRoot=new Group();
    Scene WebScene=new Scene(WebRoot,1920,1080);
    FlowPane fp=new FlowPane();
    Scene ImageTabScene=new Scene(fp);
    Stage DeepGoImage=new Stage();
    
    
    
    
      sc.setLayoutX(scn.getWidth()-sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(1080);
        sc.setMax(sc.getHeight());
        
         sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    group.setLayoutY(-new_val.doubleValue());
            }
        });
         
         
         group.getChildren().add(sc);
 
      try
       {
         
         Button Images=new Button("Images");
               Button ReadMore=new Button("Read More...");
               
               Images.setLayoutX(1100);
               Images.setLayoutY(400);
               ReadMore.setLayoutX(1100);
               ReadMore.setLayoutY(450);
               
               group.getChildren().add(ReadMore);
               
               
               ReadMore.setOnAction(new EventHandler<ActionEvent>(){
                  
                   public void handle(ActionEvent ae)
                   {
                    WebView wb=new WebView();
                    WebEngine we=wb.getEngine();
                    wb.prefHeightProperty().bind(primaryStage.heightProperty());
wb.prefWidthProperty().bind(primaryStage.widthProperty());
                    
                    we.load("https://www.wikipedia.org/wiki/"+SearchString);
                    
                       WebRoot.getChildren().add(wb);
                       
                       primaryStage.setScene(WebScene);
                       
                   }
                   
                   
               });
               
               Images.setOnAction(new EventHandler<ActionEvent>(){
                   
                   public void handle(ActionEvent e) 
                   {
                       try
                       {
                           
                       LoadImageTab(fp,SearchString);
                       
                       primaryStage.setScene(ImageTabScene);
                       primaryStage.setTitle("DeepGo Images"+SearchString);
                       primaryStage.show();
                       
                   }
                       catch(Exception et)
                       {
                           
                       }
                   }
                   
               });
               
               
               
               AggregateLinks js=new AggregateLinks();
       String Title=js.getTitle(SearchString);
       String  paragraph=js.getContent(SearchString);
      
       
      
       
       
       
      Image ns=new Image(js.getImage(SearchString));
      
      ImageView sn=new ImageView(ns);
       
      
       
      
    
    
       
       Text headtodisplay=new Text(Title);
       
       Text firstpara=new Text(paragraph);
       
       headtodisplay.setLayoutX(30);
       headtodisplay.setLayoutY(100);
       
      
       firstpara.setLayoutX(30);
       firstpara.setLayoutY(200);
       firstpara.setWrappingWidth(1000);
       
       headtodisplay.setStyle("-fx-font-family:\"Arial\"; -fx-font-size:30; -fx-text-fill: black");
       firstpara.setStyle("-fx-font-family:\"Arial\"; -fx-font-size:15;");
       
       sn.setLayoutX(1100);
       sn.setLayoutY(20);
       
       
       group.getChildren().add(headtodisplay);
       group.getChildren().add(firstpara);
       group.getChildren().add(sn);
       
       
       
       
       
     } 
      catch(Exception e)
      {
          System.out.println(SearchString+" : Could not Be Found");
      }
      
      try{
             AggregateLinks js=new AggregateLinks();
        String [] links=js.getReferenceLinks(SearchString);
       String [] ref=js.getReference(SearchString);
       
      int top=110;
    for(int i=0;i<ref.length;i++)
    {
       Button x=new Button(ref[i]);
       x.setStyle("-fx-background-color:beige;-fx-text-fill:black;");
       x.setLayoutY(top);
       x.setLayoutX(1100);
       String get=ref[i];
       x.setOnAction(new EventHandler<ActionEvent>(){
           
           public void handle(ActionEvent ae){
               
               
         Button GoBack=new Button("Back");
         
         GoBack.setLayoutX(1100);
         GoBack.setLayoutY(500);
         
         GoBack.setOnAction(new EventHandler<ActionEvent>(){
               public void handle(ActionEvent ae)
               {
                   try{
                   getArticle(Seed,primaryStage,scn,group);
                   }
                   catch(Exception e)
                   {
                   }
                   }
                   
                   
           });
         
         
         
         GoDeepRoot.getChildren().add(GoBack);
         
         try{
               getArticle(get,primaryStage,GoDeepScene,GoDeepRoot);
         }
         catch(Exception t)
         {}
                       
                    
                
               
               
           }
       });
       
      
      
   group.getChildren().add(x); 
       top+=20;
       
    }
       }
    catch(Exception ex){
            
            }               
              
primaryStage.setScene(scn);
trace.add("SearchString");
System.out.println(SearchString);


          }
      




   
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException,IOException {
     //Creating and setting Roots for the different Scenes in the UI of the project.
     
     
        
      
   wall=new Image(new FileInputStream("C:\\Users\\USER\\Downloads\\DeepGo\\src\\deepgo\\DeepBlueWallpaper.jpg"));
   backdrop=new ImageView(wall);
   aboutPageWall=new Image(new FileInputStream("C:\\Users\\USER\\Downloads\\DeepGo\\src\\deepgo\\PaperBack.jpg"));
     


        Group HomeRoot=new Group(backdrop);
        Group infoPageRoot=new Group();
        Group aboutPageRoot=new Group(aboutWall);
        Group helpPageRoot=new Group(aboutWall);
        Group GoDeepRoot=new Group();
        
        
         Scene home=new Scene(HomeRoot,720,720);
         Scene about=new Scene(aboutPageRoot,720,720);
         Scene help=new Scene(helpPageRoot,720,720);
         Scene infoScene=new Scene(infoPageRoot,1920,1080);
        
         Scene GoDeepScene=new Scene(GoDeepRoot,1920,1080);
    
      
 //==========================================================================================================================================================================================
        
       //Scene Home Specification
       int width=720;
       int height=720;
       
       
       Button HomeButton=new Button("    Home    ");
       HomeButton.setMaxWidth(50);
       
       
       Button AboutButton=new  Button("   About   ");
       AboutButton.setMaxWidth(50);
       
       
       Button HelpButton=new Button("   Help   ");
       HelpButton.setMaxWidth(50);
       
       HomeButton.setStyle("-fx-background-color:lightblue;-fx-text-fill:black;");
       AboutButton.setStyle("-fx-background-color:lightblue;-fx-text-fill:black;");
       HelpButton.setStyle("-fx-background-color:lightblue;-fx-text-fill:black;");
       
      HomeButton.setMaxWidth(500);
      HomeButton.setMaxHeight(30);
      HomeButton.setLayoutX(25);
      
      AboutButton.setMaxWidth(500);
      AboutButton.setMaxHeight(30);
      AboutButton.setLayoutX(300);
      
      AboutButton.setOnAction( new EventHandler<ActionEvent>(){
         
          public void handle(ActionEvent e)
          {
              primaryStage.setScene(about);
          }
      });
      Button AGoBack=new Button("Back");
      
      AGoBack.setOnAction(new EventHandler<ActionEvent>(){
         
          public void handle(ActionEvent e)
          {
              primaryStage.setScene(home);
          }
      });
      
     HelpButton.setMaxWidth(500);
     HelpButton.setMaxHeight(300);
      HelpButton.setLayoutX(600);
      
      HelpButton.setOnAction(new EventHandler<ActionEvent>(){
          
          public void handle(ActionEvent e)
          {
              primaryStage.setScene(help);
          }
      });
      
      HomeRoot.getChildren().addAll(HomeButton,AboutButton,HelpButton);
      
       
       
       

    
      Text DeepGoTag=new Text("DeepGo Engine");
      DeepGoTag.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,30));
      DeepGoTag.setFill(Color.WHITE);
      DeepGoTag.setX((width/2)-100);
      DeepGoTag.setY((height/2)-50);
      
      
      
      TextField search=new TextField("");
      search.setPromptText("Search...");
      
      
 
      
      
      search.setLayoutX(270);
      search.setLayoutY(370);
      HomeRoot.getChildren().add(search);
      
      
      
      Button GoButton=new Button("                  Go                  ");
      
    
      GoButton.setStyle("-fx-background-color:lightblue;-fx-text-fill:black;");
      GoButton.setLayoutX(270);
      GoButton.setLayoutY(430);
      GoButton.setMaxHeight(500);
      HomeRoot.getChildren().add(GoButton);
      
      HomeRoot.getChildren().add(DeepGoTag);
      
      
      
     
     
      
     
     
       
       
       //===========================================================================================================================================================================================
        
       //Scene About Specifications
       
       
       Text AboutThisApp=new Text("About Us");
      AboutThisApp.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,30));
      AboutThisApp.setFill(Color.BLACK);
      AboutThisApp.setX((width/2)-50);
      AboutThisApp.setY(100);
      
      Label a=new Label("DeepGo is an application for students who are curious about learning.  " + "\n" +"This is an app that was created to keep a distraction free \n engine that brings to students the very relavant information and nothing else. \n The name DeepGo susggests to every student and professional \n to go thoroughly into whatever it is they want to study or reasearch about. \n Focus on one thing, and follow it through to \n the end and make sure you have no gaps in your knowledge with the DeepGo Engine.\n \n This is just a Demo Version \n \n \n DeepGo @ 2018");
      a.setLayoutX((width/2)-200);
      a.setLayoutY(200);
      
      
      AGoBack.setLayoutX((width/2)-200);
      AGoBack.setLayoutY(500);
      
      aboutPageRoot.getChildren().add(AboutThisApp);
      aboutPageRoot.getChildren().addAll(a,AGoBack);
      
      
      
       
       
 
    
       
       
       //============================================================================================================================================================================================
        
       //Help Page Specifications===========================================================================================================================================================
      
        Text HelpPage=new Text("How To Use DeepGO Engine ?");
        HelpPage.setFont(Font.font("impact",FontWeight.BOLD,FontPosture.REGULAR,30));
        HelpPage.setFill(Color.BLACK);
        HelpPage.setX((width/2)-200);
        HelpPage.setY(100);
        Label h=new Label("To use DeepGo, you must enter a keyword or name \n of the topic you want to research about. \n This is just a demo so please do avoid putting in something Complicated. \n When you see the primary article, you can select on 'Go Deep' \n button to check out other references linked to that Article. \n You can also check out other related topics by clicking on the \n References Button. ");
        
        h.setLayoutX((width/2)-200);
      h.setLayoutY(200);
        
        Button HGoBack=new Button("Go Back");
        
        HGoBack.setLayoutX((width/2)-200);
        HGoBack.setLayoutY(500);
      
     HGoBack.setOnAction(new EventHandler<ActionEvent>(){
          
          public void handle(ActionEvent e)
                  
          {
              primaryStage.setScene(home);
          }
      });
     
     
     helpPageRoot.getChildren().add(HelpPage);
      helpPageRoot.getChildren().addAll(h,HGoBack);
     
     
              
       
       
       
       
       //===================================================================================================================================================================================
       
      //Scene for info Window
   
        
       String SearchString=search.getText();
       
       
       search.setOnAction(new EventHandler<ActionEvent>(){
           
           public void handle(ActionEvent ae)
           {
               Seed=search.getText();
              String Search=search.getText();
              try{
              getArticle(Search,primaryStage,infoScene,infoPageRoot);
              }
              catch(Exception e)
              {}
              }
           
       });
       
      
       GoButton.setOnAction(new EventHandler<ActionEvent>(){
         
          
          public void handle(ActionEvent ae) 
          {
              try{
              Seed=search.getText();
              String Search=search.getText();
       getArticle(Search,primaryStage,infoScene,infoPageRoot);
              }
              catch(Exception E)
              {}
       
           
       
     
          }
      });
       
        
               
         
           
         //=================================================================================================================================================================
         //Adding Traced Web Pages to Database
         
         String[] traceStore=new String[trace.size()];
         traceStore=trace.toArray(traceStore);
         
         
         
         
         StorePage st=new StorePage();
         
        
           
       
       
      //=====================================================================================================================================================================
      
       Group LoginRoot=new Group();
        Group RegisterRoot=new Group();
        
       Scene LoginScene=new Scene(LoginRoot,300,500);
       Scene RegisterScene=new Scene(RegisterRoot,300,500);
        //==========================================================================================================================================================================================
        
       //Scene Login Specification
       
     Label Warning=new Label("");
     Warning.setLayoutX(100);
     Warning.setLayoutY(450);
     Warning.setStyle("-fx-text-fill:red;");
      
     
      Label LoginText=new Label("Login");
      LoginText.setStyle("-fx-font-family:\"impact\"; -fx-font-size: 30;");
      
      LoginText.setLayoutX(60);
      LoginText.setLayoutY(160);
      
      
     TextField UserName=new TextField("User Name");
     
     UserName.setLayoutX(80);
     UserName.setLayoutY(230);
     PasswordField PassWord=new PasswordField();
     PassWord.setPromptText("Your Password");
     
     PassWord.setLayoutX(80);
     PassWord.setLayoutY(270);
     
     Button Login=new Button("Log in");
     Button Register=new Button("Register");
        
     
     Register.setOnAction(new EventHandler<ActionEvent>(){
        
        public void handle(ActionEvent ae){
            primaryStage.setScene(RegisterScene);
        }
    });
     
     Login.setLayoutX(100);
     Login.setLayoutY(320);
     
     Register.setLayoutX(100);
     Register.setLayoutY(350);
     Login.setStyle("-fx-background-color:lightblue; -fx-text-fill: black;");
     Register.setStyle("-fx-background-color:lightblue; -fx-text-fill: black;");
     LoginRoot.getChildren().addAll(LoginText,UserName,PassWord,Login,Register);
     
     
       
//==================================================================================================================================================================================================
    
    //Scene Register Specification
    
    
      Label RegisterText=new Label("Register");
      RegisterText.setStyle("-fx-font-family:\"impact\"; -fx-font-size: 30;");
      RegisterText.setLayoutX(80);
              RegisterText.setLayoutY(150);
      
      
      
     TextField UserName1=new TextField("User Name");
     
     UserName1.setLayoutX(80);
     UserName1.setLayoutY(230);
     
     
     PasswordField PassWord1=new PasswordField();
     PasswordField PassWordRepeat=new PasswordField();
     
   
     PassWord1.setPromptText("Type In a good Password");
     PassWordRepeat.setPromptText("Re-Enter PassWord");
     
     
     PassWord1.setLayoutX(80);
     PassWord1.setLayoutY(270);
     
     PassWordRepeat.setLayoutX(80);
     PassWordRepeat.setLayoutY(300);
     
     
     Button Login1=new Button("Log in");
     Button Register1=new Button("Register");
     
     Login1.setLayoutX(100);
     Login1.setLayoutY(350);
     
     Register1.setLayoutX(100);
     Register1.setLayoutY(380);
     
     Login1.setStyle("-fx-background-color:lightblue; -fx-text-fill: black;");
     Register1.setStyle("-fx-background-color:lightblue; -fx-text-fill: black;");
     
     RegisterRoot.getChildren().addAll(RegisterText,UserName1,PassWord1,PassWordRepeat,Register1,Login1);
     
      
       
  //===============================================================================================================================================================
       
  
  Login.setOnAction(new EventHandler<ActionEvent>(){
     
      public void handle(ActionEvent ae)
      {
          
      }
      
  });
  
  Login1.setOnAction(new EventHandler<ActionEvent>(){
  
   public void handle(ActionEvent ae){
       
       primaryStage.setScene(LoginScene);
  
  
  }
  });
  
  Register.setOnAction(new EventHandler<ActionEvent>(){
     
      public void handle(ActionEvent ae)
      {
          primaryStage.setScene(RegisterScene);
      }
  });
  
  Login.setOnAction(new EventHandler<ActionEvent>(){
  
      public void handle(ActionEvent ae){
          try{
          boolean s=authenticateLogin(UserName.getText(),PassWord.getText(),primaryStage,home);
          
          if(s==true)
          {
              primaryStage.setScene(home);
          }
          else{
              Warning.setText("Your Login is invalid,Please try again");
          }
      }
          catch(Exception e)
          {
          }
      }
      
      
  });
  
  Register1.setOnAction(new EventHandler<ActionEvent>(){
     
      
      public void handle(ActionEvent ae){
          
          
      }
      
  });
       
       
       
        primaryStage.setTitle("DeepGo Engine.");
        primaryStage.setScene(LoginScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
        
    
    }
    
}