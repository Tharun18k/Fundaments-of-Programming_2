import java.io.*;
import java.util.*;

/**
 * Write a description of class studentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class studentMarks
{
    private ArrayList<studentMarks> studentInfo;
    static String unitName;
    private String fullName;
    private int id;
    private double a1, a2, a3;
    private double totalMarks;
    
    public studentMarks(){
        
    }
    
    public studentMarks( String lastName, String fristName, int id, double a1, double a2, double a3){
        this.fullName = fristName +" " +lastName;
        this.id = id;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.totalMarks=a1+a2+a3;
   }
   
   // Get total marks of student
   public double getTotalMarks(){
       return totalMarks;
   }
    
   // method to setup the main menu
    public void mainMenu() throws Exception {
        
        studentInfo = readInputFile();
        Scanner menuIndex = new Scanner(System.in);
        System.out.println("\nSelect the index \n"+
            "1. Enter 1 to display Student details and Marks \n"+
            "2. Enter 2 to display total assignment marks of all the students \n"+
            "3. Enter 3 to display the list of total marks less than the certain threshold \n"+
            "4. Enter 4 to diaplay the top 10 and lowest 10 student marks \n"+
            "5. Enter 0 to exit the menu \n");
            
        int indexId = menuIndex.nextInt();
        
        switch (indexId){
            case 1:
                System.out.println("Student details and marks");
                for(studentMarks student:studentInfo){
                    System.out.println(student.toString());
                }
                mainMenu();
                break;
            case 2:
                System.out.println("Student details, marks and Total Marks");
                for(studentMarks student:studentInfo){
                    System.out.println(student.toString2());
                }
                mainMenu();
                break;
            case 3:
                System.out.println("Student details, marks and Total Marks of student having marks less than threshold");
                System.out.println("Enter threshold value: ");
                float th_value = menuIndex.nextInt();
                for(studentMarks student:studentInfo){
                    if (student.toString3(th_value) != null){
                        System.out.println(student.toString3(th_value));
                    }
                }
                mainMenu();
                break;
            case 4:
                
                //Sorting the Student info list
                for(int i=0; i<studentInfo.size(); i++){
                    for(int j=i+1; j<studentInfo.size(); j++){
                        studentMarks tempI = studentInfo.get(i);
                        studentMarks tempJ = studentInfo.get(j);
                        
                        if(tempI.getTotalMarks()>tempJ.getTotalMarks()){
                            studentInfo.set(i,tempJ);
                            studentInfo.set(j,tempI);
                        }
                    }    
                }
                
                
                // print top 10 students with highest marks
                System.out.println("Top 10 students with highest marks: \n");
                int k=0;
                for(int i=studentInfo.size();i>0;i--){
                    if(k<10){
                        System.out.println(studentInfo.get(i-1).toString2());
                        k++;
                    }
                    
                }
                
                // print top 10 students with lowest marks
                System.out.println("\nLast 10 students with lowest marks:\n");
                for(int i=0;i<10;i++){
                    System.out.println(studentInfo.get(i).toString2());
                }
                mainMenu();
                break;
            case 0:
                break; 
            default:
                System.out.println("Invalid Index! please enter valid Index");
                mainMenu();
                break;
        }
    }
    
    // Method to read the input csv file and store the records in a list
    public static ArrayList<studentMarks> readInputFile() throws Exception{
        
        ArrayList<studentMarks> studentDetails = new ArrayList<studentMarks>();
        
        Scanner file = new Scanner(new File("prog5001_students_grade_2022.csv"));
        file.useDelimiter(",");
        
        while (file.hasNextLine()){
            String line = file.nextLine();
            String[] studentData;
            
            if(line.contains("Unit")){
                unitName = line.split(":")[1];
            }
            else if(! line.contains("Last Name")){
                studentData = line.split(",");
                
                if(studentData.length ==4){
                    studentDetails.add(new studentMarks(studentData[0],studentData[1],Integer.parseInt(studentData[2]),Double.parseDouble(studentData[3]),0.0,0.0));
                }
                else if(studentData.length ==5){
                    studentDetails.add(new studentMarks(studentData[0],studentData[1],Integer.parseInt(studentData[2]),Double.parseDouble(studentData[3]),Double.parseDouble(studentData[4]),0.0));
                }
                else if(studentData.length ==3){
                    studentDetails.add(new studentMarks(studentData[0],studentData[1],Integer.parseInt(studentData[2]),0.0,0.0,0.0));
                }
                else{
                    
                    if(studentData[3].isEmpty()){
                        studentData[3] = "0.0";
                    }
                    if(studentData[4].isEmpty()){
                        studentData[4] = "0.0";
                    }
                    if(studentData[5].isEmpty()){
                        studentData[5] = "0.0";
                    }
                    studentDetails.add(new studentMarks(studentData[0],studentData[1],Integer.parseInt(studentData[2]),Double.parseDouble(studentData[3]),Double.parseDouble(studentData[4]),Double.parseDouble(studentData[5])));
                }
            }
        }
        file.close();
        return studentDetails;
    }
    
    public String toString(){
        return String.format("Id:%d, FullName:%s, A1_marks:%.2f, A2_marks:%.2f, A3_marks:%.2f", id, fullName,a1,a2,a3);
    }
    
    public String toString2(){
        return String.format("Id:%d, FullName: %s, A1_marks:%.2f, A2_marks:%.2f, A3_marks:%.2f, Total_marks:%.2f", id, fullName,a1,a2,a3,totalMarks);
    }
    
    public String toString3(float th_value){
        if (totalMarks < th_value){
            return String.format("Id:%d, FullName: %s, A1_marks:%.2f, A2_marks:%.2f, A3_marks:%.2f, Total_marks:%.2f", id, fullName,a1,a2,a3,totalMarks);
        }
        else 
        return null;
    }
    
    //main methiod
    public static void main(String[] args) throws Exception{
        studentMarks st = new studentMarks();
        st.mainMenu();
    }
    
    
    
}

