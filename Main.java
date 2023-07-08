import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
class Employee implements Serializable {
     int empid;
     String empname;
     int salary;

     Employee(int empid,String empname,int salary){
         this.empid=empid;
         this.empname=empname;
         this.salary=salary;
     }
     public String toString(){
         return empid+" "+empname+" "+salary;
     }
    }
    public class Main {
    public static void main(String[] args) throws Exception{
     int choice= -1;
     Scanner sc=new Scanner(System.in);
        Scanner sc1=new Scanner(System.in);
        File file=new File("employee.csvs");
        ArrayList<Employee>al=new ArrayList<Employee>();
        ObjectOutputStream os=null;
        ObjectInputStream oss=null;
        ListIterator li=null;

        if(file.isFile()){
            oss= new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<Employee>)oss.readObject();
            oss.close();
        }
     do{
         System.out.println("1.INSERT");
         System.out.println("2.DISPLAY");
         System.out.println("3.SEARCH");
         System.out.println("4.DELETE");
         System.out.println("5.UPDATE");
         System.out.println("0.EXIT");
         System.out.println("ENTER YOUR CHOICE : ");
         choice=sc.nextInt();

         switch (choice){
             case 1:
                 System.out.println("Enter Employee Id: ");
                 int empid=sc.nextInt();
                 System.out.println("Enter Employee Name: ");
                String empname = sc1.nextLine();
                 System.out.println("Enter Salary: ");
                 int salary=sc.nextInt();
                 al.add(new Employee(empid ,empname , salary));
                 os=new ObjectOutputStream(new FileOutputStream(file));
                 os.writeObject(al);
                 os.close();

                 break;

                 case 2:
                     if(file.isFile()) {
                         oss = new ObjectInputStream(new FileInputStream(file));
                         al = (ArrayList<Employee>) oss.readObject();
                         oss.close();
                         System.out.println("---------------------");
                         li = al.listIterator();
                         while (li.hasNext())
                             System.out.println(li.next());
                         System.out.println("---------------------");
                     }else {
                         System.out.println("File Not Exist....");
                     }
                     break;
                 case 3:
                     if(file.isFile()) {
                         oss= new ObjectInputStream(new FileInputStream(file));
                         al = (ArrayList<Employee>)oss.readObject();
                         oss.close();
                         boolean found = false;
                         System.out.println("Enter Employee ID To Search : ");
                         empid = sc.nextInt();
                         System.out.println("---------------------");
                         li = al.listIterator();
                         while (li.hasNext()) {
                             Employee e = (Employee) li.next();
                             if (e.empid == empid)
                                 System.out.println(e);
                             found = true;
                         }
                         if (!found)
                             System.out.println("Please Enter Valid Emloyee ID");
                         System.out.println("---------------------");
                     }else {
                         System.out.println("File Not Exist....");
                     }
                    break;
             case 4:
                 if(file.isFile()) {
                     oss= new ObjectInputStream(new FileInputStream(file));
                     al = (ArrayList<Employee>)oss.readObject();
                     oss.close();
                     boolean found = false;
                     System.out.println("Enter Employee ID To DELETE : ");
                     empid = sc.nextInt();
                     System.out.println("---------------------");
                     li = al.listIterator();
                     while (li.hasNext()) {
                         Employee e = (Employee) li.next();
                         if (e.empid == empid){
                             System.out.println(e);
                             li.remove();
                             found = true;
                         }
                     }
                     if (found){
                         os=new ObjectOutputStream(new FileOutputStream(file));
                         os.writeObject(al);
                         os.close();
                         System.out.println("Record Deleted Successfully....");
                     }

                     else {
                         System.out.println("Record not Found");
                     }
                     System.out.println("---------------------");
                 }else {
                     System.out.println("File Not Exist....");
                 }
                 break;
             case 5:
                 if(file.isFile()) {
                     oss= new ObjectInputStream(new FileInputStream(file));
                     al = (ArrayList<Employee>)oss.readObject();
                     oss.close();
                     boolean found = false;
                     System.out.println("Enter Employee ID To UPDATE : ");
                     empid = sc.nextInt();
                     System.out.println("---------------------");
                     li = al.listIterator();
                     while (li.hasNext()) {
                         Employee e = (Employee) li.next();
                         if (e.empid == empid){
                             System.out.println("Enter New Employee Name : ");
                             empname=sc1.nextLine();
                             System.out.println("Enter New Salary : ");
                             salary=sc.nextInt();
                             li.set(new Employee(empid,empname,salary));


                             found = true;
                         }
                     }
                     if (found){
                         os=new ObjectOutputStream(new FileOutputStream(file));
                         os.writeObject(al);
                         os.close();
                         System.out.println("Record Deleted Successfully....");
                     }

                     else {
                         System.out.println("Record not Found");
                     }
                     System.out.println("---------------------");
                 }else {
                     System.out.println("File Not Exist....");
                 }

                 break;
         }
     }while(choice!=0);
    }
}