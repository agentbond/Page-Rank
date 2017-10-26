// SINGH AMBUJ  cs610 3597 PrP option2 : Page Rank

import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class PageRank_3597 {

        int iterations, initialvalue, nico, mich;
        int[] out; 
        String inputfile;
        int[][] AM; 
        final double d = 0.85;
        final double err = 0.00001;
        double[] Source;
        

        PageRank_3597(int iterations, int initialvalue, String inputfile)     
    {
        this.iterations = iterations;
        this.initialvalue = initialvalue;
        this.inputfile = inputfile;
        int f = 0;
        int g = 0;
        try {        
            Scanner sc = new Scanner(new File(inputfile));
            nico = sc.nextInt();
          //  System.out.println("Nicolas : "+nico);
            mich = sc.nextInt();
        //    System.out.println("Michael : "+mich);
            
            
            AM = new int[nico][nico];
            for(int i = 0; i < nico; i++)
            for(int j = 0; j < nico; j++){
                       
             AM[i][j] = 0;
            }
            
            while(sc.hasNextInt())
            {
             f = sc.nextInt();
             g = sc.nextInt();
             AM[f][g] = 1;
            
            }
            

            
            out = new int[nico];
            for(int i = 0; i < nico; i++) {
            out[i] = 0;
            
            for(int j = 0; j < nico; j++) {
            out[i] = out[i] +AM[i][j];
          //  System.out.println("out value : "+i+" "+out[i]);
            }
            }

            Source = new double[nico];
            

            if(initialvalue == 0){
            for(int i = 0; i < nico; i++) {
               Source[i] = 0;
              }
            }
            
            else if(initialvalue == 1){
            for(int i = 0; i < nico; i++) {
              Source[i] = 1;
              }
            }
            
            else if(initialvalue == -1){
            	for(int i =0; i < nico; i++) {
                Source[i] = 1.0/nico;
              }
            }
            else if(initialvalue == -2){
            	for(int i =0; i < nico; i++) {
                Source[i] = 1.0/Math.sqrt(nico);
              }
            }
            else{}
            
            
          sc.close();
        }
        
        
        catch(FileNotFoundException FileNotFound)
        {
        	System.out.println("Requested File Not Found");
        }
    }
    
    public static void main(String[] args)
    {
        if(args.length != 3) {
            System.out.println("Please provide correct input");
            return;
        }
        
        
       
      int iterations = Integer.parseInt(args[0]);
        int initialvalue = Integer.parseInt(args[1]);
        String inputfile = args[2];
       
   
       
            if( !(initialvalue >= -2 && initialvalue <= 1) ) {
            System.out.println("Please provide correct InitialValue");
            return;
        }

        PageRank_3597 pgrank = new PageRank_3597(iterations, initialvalue, inputfile);

        pgrank.pagerank3597();
    }

    boolean checkconverged3597(double[] Source, double[] dest)
    {
        for(int i = 0; i < nico; i++)
        {
          if ( abs(Source[i] - dest[i]) > err )
            return false;
        }
        return true;
    }

   
    void pagerank3597() {
        
    	double[] Mat = new double[nico];
        
    	boolean fl = true;
       
        
        if(nico > 10) {
         iterations = 0;
         for(int i =0; i < nico; i++) {
         Source[i] = 1.0/nico;
      //   System.out.println("Source : "+i+""+Source[i]);
         }
         
         int i = 0;
         do {
         if(fl == true)
          {
          fl = false;
          }
         else
          {
          for(int l = 0; l < nico; l++) {
          Source[l] = Mat[l];
          }
         }
         
         for(int l = 0; l < nico; l++) {
         Mat[l] = 0.0;
         }

         for(int m = 0; m < nico; m++) {
         for(int n = 0; n < nico; n++)
         {
          if(AM[n][m] == 1) {
          Mat[m] = Mat[m] + Source[n]/out[n];
 //         System.out.println("updated value : "+m+""+Mat[m]);
            }
           }
          }

         for(int l = 0; l < nico; l++) {
          Mat[l] = d*Mat[l] + (1-d)/nico;
//        System.out.println("new value of Mat : "+l+""+Mat[l]); 
         }
         
         
         i++;
         } while (checkconverged3597(Source, Mat) != true);

              
         System.out.println("iter  : " + i);
         for(int p = 0 ; p < nico; p++) {
       //   System.out.println("current Value of p : "+p);
        	 System.out.printf("P[" + p + "] = %.6f\n ",Math.round(Mat[p]*1000000.0)/1000000.0);
         }
         
         return;
        }
        
        System.out.print("Base  : 0");
        
        for(int k = 0; k < nico; k++) {
        //	 System.out.println("current Value of k : "+k);
        	System.out.printf(" :P[" + k + "]=%.6f", Math.round(Source[k]*1000000.0)/1000000.0);
        }

        if (iterations != 0) {
        for(int i = 0; i < iterations; i++)
        {
        for(int l = 0; l < nico; l++) {
         Mat[l] = 0.0;
         }
            
        for(int m = 0; m < nico; m++) {
        for(int n = 0; n < nico; n++)
        {
        if(AM[n][m] == 1) {
        Mat[m] = Mat[m] + Source[n]/out[n];
      //  System.out.println("current Value of Mat : "+m+" "+Mat[m]);
         } 
         }
        }

        System.out.println();
        
        System.out.print("Iter : " + (i+1));
        for(int z = 0; z < nico; z++) {
        Mat[z] = d*Mat[z] + (1-d)/nico;
        //  System.out.println("current Value of Mat after d*Mat : "+l+" "+Mat[l]);
        System.out.printf(" :P[" + z + "]=%.6f",Math.round(Mat[z]*1000000.0)/1000000.0);
        }
            
        for(int l = 0; l < nico; l++) {
        Source[l] = Mat[l]; 
         } 
        }
        System.out.println();
        }
        
        else 
        { 
        int i = 0;
        do {
        if(fl == true)
        {
        fl = false;
        }
        else
        {
        for(int l = 0; l < nico; l++) {
         Source[l] = Mat[l];
         //  System.out.println("Value of Source : "+l+" "+Source[l]);
        }
         }
       
        
        for(int l = 0; l < nico; l++) {
        Mat[l] = 0.0;
        }

        for(int m = 0; m < nico; m++) {
        for(int n = 0; n < nico; n++)
         {
        if(AM[n][m] == 1) {
        Mat[m] = Mat[m] + Source[n]/out[n];
        //  System.out.println("current Value of Mat computed: "+m+" "+Mat[m]);
        }
          }
        }

         System.out.println(); 
         
         System.out.print("Iter  : " + (i+1));
         for(int z = 0; z < nico; z++) {
         Mat[z] = d*Mat[z] + (1-d)/nico;
         //  System.out.println("current Value of Mat printing: "+l+" "+Mat[l]);
         System.out.printf(" :P[" + z + "]=%.6f",Math.round(Mat[z]*1000000.0)/1000000.0);
         }
         
         i++;  
         } while (checkconverged3597(Source, Mat) != true);  
        
        }
       }
}