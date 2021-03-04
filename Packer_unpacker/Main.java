

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
/*
class Main
{
	public static void main(String arg[])
	{
		Scanner sobj=new Scanner(System.in);
		while(true)
		{
		System.out.println("enter your choice");
		System.out.println("1:Packing");
		System.out.println("2.Unpacking");
		System.out.println("3.exit application");
		
		String Dir,Filename;
		
		int choice=0;
		choice=sobj.nextInt();
		
		switch(choice)
		{
			case 1:
			System.out.println("Eneter directory name:");
			 Dir =sobj.next();
			
			System.out.println("enter file name for packing");
		     Filename=sobj.next();
			Packer pobj=new Packer(Dir,Filename);
			break;
			
			case 2:
			System.out.println("Enter packed file name");
                    String name = sobj.next();
                    Unpacker obj = new Unpacker(name);
			
			break;
			
			case 3:
			System.out.println("thank you for using Packer Unpacker");
			System.exit(0);
			break;
			
			default:
			System.out.println("wrong choice");
			break;
			
		}
		
		}
	}
}*/

class Main
{
    public static void main(String arg[])
    {
        Scanner sobj = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter your choice");
            System.out.println("1 : Packing");
            System.out.println("2 : Unpacking");
            System.out.println("3 : Exit");
            String Dir,Filename;
            int choice = 0;
            choice = sobj.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Enter Directory name");
                    Dir = sobj.next();
                    System.out.println("Enter the file name for packing");
                    Filename = sobj.next();
                    Packer pobj = new Packer(Dir,Filename);
                break;
                case 2:
                    System.out.println("Enter packed file name");
                    String name = sobj.next();
                    Unpacker obj = new Unpacker(name);
                    break;
                case 3:
                    System.out.println("Thank you for using Marvellous Packer Unpacker");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }
    }
}

class Packer 
{
	
	public FileOutputStream outstream=null;       //file reference to write data 
	
	//parameterized constructor
	public Packer(String FolderName,String FileName)
	{
		try
		{
		System.out.println("inside packer");
		//create new file for packing
		File outfile=new File(FileName);            //new file create keli 
		outstream=new FileOutputStream(FileName);     //create new file combine.txt 
		
		//set cureent working directory for folder traversal 
		
		System.setProperty("user.dir",FolderName);          //for commercial programming approach if you want you can remove it
		TravelDirecoty(FolderName);
		
		}
		catch(Exception obj)
		{
			System.out.println(obj);
		}
	}
	public void TravelDirecoty(String path)
	{
		File directoryPath=new File(path);   
		//get all files names from directory
		
		File arr[]=directoryPath.listFiles();
		for(File filename:arr)
		{
			//System.out.println(filename.getName());
			//getName :to get name of files 
			
			//System.out.println(filename.getAbsolutePath());
           
		   if(filename.getName().endsWith(".txt"))
		   {
			   //System.out.println(filename.getName());
		        PackFile(filename.getAbsolutePath());
		   }
		   
		}
		
	}
	
	public void PackFile(String FilePath)
    {

		byte Header[] = new byte[100];           //to avoid string and unicode issues
		byte Buffer[] = new byte[1024];
		int length = 0;

		FileInputStream istream = null;           // to read file 

		File fobj = new File(FilePath);            

		String temp = FilePath+" "+fobj.length();          //filename + file length
		
		// Create header of 100 bytes
		for(int i = temp.length(); i< 100; i++)   //to add spaces into header 
		{
			temp = temp + " ";
		}		

		Header = temp.getBytes();            //convert header string to byte 
		try
		{
			//exception prone code 
			
			// open the file for reading
			istream = new FileInputStream(FilePath);

			outstream.write(Header,0,Header.length);      //to write header in file 
			
			while((length = istream.read(Buffer)) > 0)          // to write actual file data  1024 byte chya patit 
			{
				outstream.write(Buffer,0,length);      
			}

			istream.close();
		}
		catch(Exception obj)
		{}
		// System.out.println("Header : "+temp.length());

    }
}



class Unpacker
{    
    public FileOutputStream outstream = null;              //to write data into file 
    public Unpacker(String src)
    {
        //System.out.println("Inside unpacker");
        unpackFile(src);
    }
    public void unpackFile(String FilePath)
    {
        try
        {
            FileInputStream instream = new FileInputStream(FilePath);
            byte Header[] = new byte[100];               //to read header of 100 byte 
            int length = 0;
			int count=0;
			
            while((length = instream.read(Header,0,100)) > 0)
            {
				count++;
				//System.out.println(Header.toString());
				
                String str = new String(Header);
				System.out.println(str);
				
                String ext = str.substring(str.lastIndexOf("\\"));
                ext = ext.substring(1);
                String words[] = ext.split("\\s");         
                String name = words[0];
                int size = Integer.parseInt(words[1]);
                byte arr[] = new byte[size];
                instream.read(arr,0,size);            //to read data of file 
                FileOutputStream fout = new FileOutputStream(name);             
                fout.write(arr,0,size);                 //to write data of file 
            }
			System.out.println("successfully unpacked files"+count);
        }
        catch(Exception obj)
        {}
    }
}