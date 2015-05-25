package com.tutorialspoint.lucene;

import java.io.IOException;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester {
	
   String indexDir = "E:\\Lucene\\Index";
	//String indexDir = "Lucene\\Index";
   String dataDir = "E:\\Lucene\\Data";
	//String dataDir = "Lucene\\Data";
   Indexer indexer;
   Searcher searcher;
   static String filename=null;
   public  String tester(String query) {
	   String filename1 = null;
	   Connection con=null;
	   PreparedStatement ps=null;
	   ResultSet rs=null;
	   LuceneTester tester;
	   
       try {
    	   
    	  Class.forName("com.mysql.jdbc.Driver");
       	  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/search","root","");
       	  ps=con.prepareStatement("SELECT filename from Persons where keyword like ?");
          ps.setString(1, "%"+query+"%");
          rs=ps.executeQuery();
          if(rs.next()){
        	
        	  System.out.println("FileName:" +rs.getString(1));
        	  return rs.getString(1);
          }
          else{
       	      
        	  tester = new LuceneTester();
              tester.createIndex();
              filename1=tester.search(query); 
              if(filename!=null){
        	  ps=con.prepareStatement("INSERT INTO Persons(keyword,filename) values(?,?)");
   	          ps.setString(1,query);
   	          ps.setString(2,filename);
   	          ps.executeUpdate();
              }
   	         
   	       }
      } 
       catch (IOException e) {
         e.printStackTrace();
      } 
       catch (ParseException e) {
         e.printStackTrace();
      }
       catch(Exception e){
    	   e.printStackTrace();
       }
	
	return filename1;
   }

   private void createIndex() throws IOException{
      indexer = new Indexer(indexDir);
      int numIndexed;
      long startTime = System.currentTimeMillis();	
      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
      long endTime = System.currentTimeMillis();
      indexer.close();
      System.out.println(numIndexed+" File indexed, time taken: "
         +(endTime-startTime)+" ms");		
   }

   private String search(String searchQuery) throws IOException, ParseException{
      searcher = new Searcher(indexDir);
       String x =null;
       String z=null;
      filename=null;
     
      long startTime = System.currentTimeMillis();
      TopDocs hits = searcher.search(searchQuery);
      long endTime = System.currentTimeMillis();
   
      System.out.println(hits.totalHits +
         " documents found. Time :" + (endTime - startTime));
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
    	  
         Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
            + doc.get(LuceneConstants.FILE_PATH));
            filename=doc.get(LuceneConstants.FILE_PATH);
            x=doc.get(LuceneConstants.FILE_PATH);
      }//z=(searcher.getDocument(scoreDoc)).get(LuceneConstants.FILE_PATH);
      //System.out.println(z);
      searcher.close();
	return x;
   }
}
