import java.io.*;
import java.util.ArrayList;
class Word implements java.io.Serializable
{
    private String name;
    private String meaning;
    //constructors
    public Word()
    {
        name="";
        meaning="";
    }
    public Word(String a,String b)
    {
        name=a;
        meaning=b;
    }
    public Word(Word a)
    {
        name=a.name;
        meaning=a.meaning;
    }
    //getter setter
    public void SetName(String a)
    {
        name=a;
    }
    public void SetMean(String a)
    {
        meaning=a;
    }
    public String GetName()
    {
        return name;
    }
    public String GetMean()
    {
        return meaning;
    }
    public String toString()
    {
        String mystr= "Name :"+name+"\nMeans: "+meaning+"\n";
        return mystr;
    }
}
class Dictionary implements java.io.Serializable
{
    private ArrayList<Word> MyArray; // sary object iss ma atay jayen ga
    //constructor
    public Dictionary()
    {
        MyArray=new ArrayList<Word>();
    }
    public void AddWord(Word w) //step a of adding record
    {
        MyArray.add(w);
    }
    public void AddnewRecord() //step 2 of adding record
    {
        MakeEmpty();
        FileOutputStream fos =null;
        ObjectOutputStream out =null;
        try {
                fos =new FileOutputStream("MyRecords.txt",false);
                out =new ObjectOutputStream(fos);
                for(int i=0;i<MyArray.size();i++)
                {
                    out.writeObject(MyArray.get(i));
                }
            out.close();
            fos.close();
            } catch (Exception ex){
            System.out.println(ex);
            }
    }
    public void ReadWholeFile() //array ma value store
    {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("MyRecords.txt");
            ois = new ObjectInputStream(fis);
            while(true) //ois!=null
            {
                try{
                Word myword=(Word)ois.readObject();
                MyArray.add(myword);
                }
                catch (EOFException ex1) {
                    break; 
                }
            }
        }   catch (Exception ex) {
                ex.printStackTrace();
            }          
            finally
            {
                try {
                    ois.close();
                    fis.close();
                    
                }
                catch(IOException ex) {
                    System.err.println("An IOException was caught: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
     }
     public void SearchRecord(String a)
     {
         for(int i=0;i<MyArray.size();i++)
         {
             if(MyArray.get(i).GetName().equals(a))
             {
                 System.out.println("Found \n");
                 return;
             }

         }
         System.out.println("Not found");
     }
    public boolean Search(String a,int w)
    {
        for(int i=0;i<MyArray.size();i++)
        {
            Word temp=MyArray.get(i);
            if(temp.GetName().equals(a))
            {
                w=i;
                return true;
            }
        }
        return false;
    }
    public void UpdateRecord(String a,String mean)
    {
        for(int i=0;i<MyArray.size();i++)
        {
            Word temp=MyArray.get(i);
            if(temp.GetName().equals(a))
            {
                MyArray.remove(i);
                Word newWord=new Word(a,mean);
                MyArray.add(newWord);
            }
        }     
        

    }
    public void PrintAll()
    {
        for(int i=0;i<MyArray.size();i++)
        {
            System.out.println(i+". "+MyArray.get(i));
        }
    }
    public void MakeEmpty()
    {
        OutputStream os = null;
        try {
            os = new FileOutputStream("MyRecords.txt");
            }
            catch(IOException ex){
                System.out.println("error in emptying file");
            } finally {
                try {
                    os.close();
                    
                }
                catch(IOException ex) {
                    System.err.println("IOException a gaiy h: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
    }
  
    
}
class Driver
{
    public static void main(String[] args)
    {
        Dictionary Test=new Dictionary(); //implemented a class Dictionary(i)
        Test.ReadWholeFile(); //Start program  with reading objects (ii)
        Test.SearchRecord("Clean"); 
        Test.UpdateRecord("Clean", "saaf");
        Word w= new Word("Door", "darwaza");
        Test.AddWord(w);
        Test.AddnewRecord(); //saving arraylist in file while exiting . . .(iii)
        Test.PrintAll();
    }
   
}