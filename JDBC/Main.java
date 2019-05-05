import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

class Main{
	
	private static final String url= "jdbc:mysql://localhost/Lab5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final Scanner sc = new Scanner(System.in);
	public static void main(String args[]){
		String user="root";
		String password = "iamnoob";
		try{
			
			Connection con = DriverManager.getConnection(url,user,password);
			Statement stmt = con.createStatement();
			System.out.println("Success");
			menu(stmt);
			con.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void menu(Statement stmt) throws SQLException, ParseException{
		boolean done=false;
		while(!done){
			System.out.print("1.Add Driver\n2.Delete Driver\n"+
                    "3.Add bus\n4.Delete Bus\n5.Change Driver in TripOffering Table\n"+
                    "6.Delete trip Offering from TripOffering Table\n"+
                    "7.Add a set of trip offerings\n8.Change bus in Trip Offering Table\n"+
                    "9.Display the schedule of all trips\n10.Dsiplay the stops of a given trip\n"+
                    "11.Display the weekly schedule of a given driver and date\n"+
                    "12.add actual Trip stop info\n"+
                    "Enter x to close the application\n"+
	              	"Choose Option : ");
			
		String option=sc.nextLine();
			switch (option){
				case "1":
					add_driver(stmt);
					break;
				case "2":
					delete_driver(stmt);
					break;
                case "3":
                    add_bus(stmt);
                    break;
                case "4":
                    delete_bus(stmt);
                    break;
                case "5":
                    change_driver_from_trip_offering(stmt);
                    break;
                case "6":
                    delete_trip_offering(stmt);
                    break;
                case "7":
                    add_set_of_trip_offering(stmt);
                    break;
                case "8":
                    change_bus_from_trip_offering(stmt);
                    break;
                case "9":
                    display_schedule_of_all_trips(stmt);
                    break;
                case "10":
                    display_stops_of_given_trip(stmt);
                    break;
                case "11":
                    display_weekly_schedule_of_a_given_driver_and_date(stmt);
                    break;
                case "12":
                    add_actual_trip_stop_info(stmt);
                    break;
                case "13":
                	add_trip(stmt);
				case "x":
                case "X":
					done =true;
					break;
				default:
					System.out.println("input : "+option);
					System.out.println("input Error");
			}
		}
	}
	
	public static void display_weekly_schedule_of_a_given_driver_and_date(Statement stmt) throws ParseException, SQLException {
		System.out.print("Driver name: ");
		String driver = sc.nextLine();
		
		System.out.print("Date: ");
		String dateString = sc.nextLine();
		
		String varSql = "SELECT TripNumber, Date, ScheduledStartTime, ScheduleArrivalTime, BusID from TripOffering " + "where DriverName LIKE '" + driver + "' AND DATE_SUB('" + dateString + "',INTERVAL 7 DAY)" + " Order By ScheduledStartTime ";
		
		//query for selected driver and date
		ResultSet rs = stmt.executeQuery(varSql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
    	for(int i = 1; i <= colCount; i++){
    	System.out.print(rsmd.getColumnName(i) + "\t");
    	}
    	System.out.println();
    	//print out rows
    	while(rs.next()){
    	for(int i = 1; i <= colCount; i++)
    	System.out.print(rs.getString(i) + "\t\t");
    	System.out.println();
    	}
    	rs.close();
		}
	
	public static void add_trip(Statement stmt) {
		int result =0;
		System.out.print("Enter the trip number : ");
		String tripNumber = sc.nextLine();
		System.out.print("Enter start location name : ");
		String startLocation = sc.nextLine();
		System.out.print("Enter destination location name : ");
		String desLocation = sc.nextLine();
		
		String varSql = "insert into Trip(TripNumber,StartLocationName,DestinationName) values('" + tripNumber + "','" +
		startLocation + "','" + desLocation +"')";
		
		result = updateDB(varSql, stmt);
		
		printMessage(result,"add trip");
	}
	
    public static void add_actual_trip_stop_info(Statement stmt){
    	int result = 0;
        System.out.print("Enter the Trip Number : ");
        String tripNumber = sc.nextLine();
        System.out.print("Enter the Date (YYYY-MM-DD) : ");
        String date = sc.nextLine();
        System.out.print("Enter the Schedule Start Time (HH:MM:SS) : ");
        String scheduleStartTime = sc.nextLine();
        System.out.print("Enter the Stop Number : ");
        String stopNumber = sc.nextLine();
        System.out.print("Enter the Scheduled Arrival Time (HH:MM:SS) : ");
        String scheduledArrivalTime = sc.nextLine();
        System.out.print("Enter the Actual Arrival Time (HH:MM:SS) : ");
        String actualArrivalTime = sc.nextLine();
        System.out.print("Enter the Number of passenger in : ");
        String numberOfPassengerIn = sc.nextLine();
        System.out.print("Enter the Number of passenger out : ");
        String numberOfPassengerOut = sc.nextLine();
        
        String varSql = "insert into ActualTripStopInfo values ('" + tripNumber +
            "','" + date + "','" + scheduleStartTime + "','" + stopNumber + "','" +
            scheduledArrivalTime + "','" + actualArrivalTime + "','" + numberOfPassengerIn +
            "','" + numberOfPassengerOut + "')";
        result = updateDB(varSql,stmt);
        printMessage(result, "add into actual trip stop info");

    }

    public static void display_stops_of_given_trip(Statement stmt){
    	
    	int result = 0;
        System.out.print("Enter the Trip Number : ");
        String tripNumber = sc.nextLine();
        String varSql = ("select * from TripStopInfo where TripNumber="+tripNumber);
        try{
            ResultSet rs = queryDB(varSql,stmt);
            if(rs.next()==false){
                System.out.println("Result is empty!");
            }else{

                System.out.println("TripNumber\tStopNumber\tSequenceNumber\tDrivingTime");
                do{
                    System.out.println(rs.getString("TripNumber")+"\t\t"+rs.getString("StopNumber")+"\t\t"+
                        rs.getString("SequenceNumber")+"\t\t"+rs.getString("DrivingTime"));
                }while(rs.next());
            }
            System.out.println();
        }catch(SQLException e){
			e.printStackTrace();
		}

    }
    public static void display_schedule_of_all_trips(Statement stmt) throws SQLException{
    	System.out.print("Start Location Name: ");
    	String startLoc = sc.nextLine();
    	System.out.print("Destination Name: ");
    	String destLoc = sc.nextLine();
    	System.out.print("Date: ");
    	String date = sc.nextLine();
    	
    	String varSql = "SELECT A.ScheduledStartTime, A.ScheduleArrivalTime, A.DriverName, A.BusID FROM TripOffering A, Trip B Where B.StartLocationName LIKE '" + startLoc + "' AND " + "B.DestinationName LIKE '" + destLoc + "' AND " + "A.Date = '" + date + "' AND " +
    	"B.TripNumber = A.TripNumber ";
    	
    	ResultSet rs = stmt.executeQuery(varSql);
    	ResultSetMetaData rsmd = rs.getMetaData();
    	int colCount = rsmd.getColumnCount();
    	for(int i = 1; i <= colCount; i++){
    	System.out.print(rsmd.getColumnName(i) + "\t");
    	}
    	System.out.println();
    	//print out rows
    	while(rs.next()){
    	for(int i = 1; i <= colCount; i++)
    	System.out.print(rs.getString(i) + "\t\t");
    	System.out.println();
    	}
    	rs.close();
    	
    }
    public static void add_set_of_trip_offering(Statement stmt){
        boolean done = false;
        int result = 0;
        do{
            System.out.print("Enter the Trip Number : ");
            String tripNumber = sc.nextLine();
            System.out.print("Enter the Date : ");
            String date = sc.nextLine();
            System.out.print("Enter the Scheduled Start Time : ");
            String scheduledStartTime = sc.nextLine();
            System.out.print("Enter the ScheduleArrival Time : ");
            String scheduleArrivalTime = sc.nextLine();
            System.out.print("Enter the Driver Name : ");
            String driverName = sc.nextLine();
            System.out.print("Enter the BusID : ");
            String busID = sc.nextLine();
            String varSql = "insert into TripOffering values (" + tripNumber + ",'" + date + "','" + scheduledStartTime + "','" + scheduleArrivalTime + "','" + driverName + "','" + busID + "')";
            result = updateDB(varSql,stmt);
            printMessage(result, "add a new trip offering");

            System.out.print("Do you want to add another trip? Y or N : ");
            String repeat = sc.nextLine();
            if(repeat.equals("N") || repeat.equals("n")){
                done = true;
            }
        }while(!done);
    }
    public static void delete_trip_offering(Statement stmt){
        int result=0;
        System.out.print("Enter the Trip Number : ");
        String tripNumber = sc.nextLine();
        System.out.print("Enter the Date : ");
        String date = sc.nextLine();
        System.out.print("Enter the Scheduled Start Time : ");
        String scheduledStartTime = sc.nextLine();
        String varSql = "delete from TripOffering where TripNumber='" + tripNumber + "' and Date='" + date + "' and ScheduledStartTime='" + scheduledStartTime  + "'";
        result=updateDB(varSql,stmt);
        printMessage(result, " delete trip offering from TripOffering");

    }
    public static void change_driver_from_trip_offering(Statement stmt){
        int result=0;
        System.out.print("Enter the Trip Number : ");
        String tripNumber = sc.nextLine();
        System.out.print("Enter the Date : ");
        String date = sc.nextLine();
        System.out.print("Enter the Scheduled Start Time : ");
        String scheduledStartTime = sc.nextLine();
        System.out.print("Enter the new driver name : ");
        String newDriverName = sc.nextLine();
        String varSql = "update TripOffering set DriverName='" + newDriverName +
            "' where TripNumber='" + tripNumber + "' and date='"+ date + "' and ScheduledStartTime='" + scheduledStartTime + "'";
        result=updateDB(varSql,stmt);
        printMessage(result, "update Driver in TripOffering Table");

    }
    public static void change_bus_from_trip_offering(Statement stmt){
        int result=0;
        System.out.print("Enter the Trip Number : ");
        String tripNumber = sc.nextLine();
        System.out.print("Enter the Date : ");
        String date = sc.nextLine();
        System.out.print("Enter the Scheduled Start Time : ");
        String scheduledStartTime = sc.nextLine();
        System.out.print("Enter the new BusID : ");
        String busID = sc.nextLine();
        String varSql = "update TripOffering set BusID='" + busID +
            "' where TripNumber='" + tripNumber + "' and date='"+ date + "' and ScheduledStartTime='" + scheduledStartTime + "'";
        result=updateDB(varSql,stmt);
        printMessage(result, "update Bus in TripOffering Table");

    }
	public static void add_driver(Statement stmt){
		int result=0;
		System.out.print("Enter the Driver Name : ");
		String name=sc.nextLine();
		System.out.print("Enter the Phone Number :");
		String phNumber=sc.nextLine();
		String varSql = "insert into Driver(DriverName,DriverTelephoneNumber) values('"+name+"','"+phNumber+"')";
		result=updateDB(varSql,stmt);
        printMessage(result,"add driver");
	}
	public static void delete_driver(Statement stmt){
		int result=0;
		System.out.print("Enter the Driver Name : ");
		String name=sc.nextLine();
		String varSql = "delete from Driver where DriverName like '%"+name+"%'";
		result=updateDB(varSql,stmt);
        printMessage(result,"Delete driver");
	}
    public static void add_bus(Statement stmt){
        int result=0;
        System.out.print("Enter the bus ID : ");
        String busID=sc.nextLine();
        System.out.print("Enter the bus Model : ");
        String model=sc.nextLine();
        System.out.print("Enter the year of bus : ");
        String year=sc.nextLine();
        while(!checkYear(year)){
            System.out.print("Enter the Year again?(XXXX) : ");
            year=sc.nextLine();
        }
        String varSql = "insert into Bus(BusID,Model,Year) values('"+busID+"','"+model+"','"+year+"')";
        result=updateDB(varSql,stmt);
        printMessage(result,"add bus");
    }
    public static void delete_bus(Statement stmt){
        int result=0;
        System.out.print("Enter the bus ID to remove : ");
        String busID = sc.nextLine();
        String varSql = "delete from Bus where BusID="+busID;
        result=updateDB(varSql,stmt);
        printMessage(result,"delete bus");
    }
	public static int updateDB(String varSql,Statement stmt){
		int result=0;
		try{
			result=stmt.executeUpdate(varSql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static ResultSet queryDB(String varSql,Statement stmt) throws SQLException{
        return  stmt.executeQuery(varSql);
    }

	public static void success_message(){
		System.out.println("\nSuccess!\n");
	}
	public static void error(String job){
		System.out.println("\nProblem with "+job+"\n");
	}
    public static boolean checkYear(String year){
        return year.length()==4;
    }
    public static void printMessage(int result,String job){
        if(result==0){
            error(job);
        }else{
            success_message();
        }
    }
}
