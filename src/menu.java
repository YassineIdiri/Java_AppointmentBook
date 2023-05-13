package src;
import java.util.Scanner;

public class menu 
{
	public LinkedListParticipant lp = new LinkedListParticipant();
	public LinkedListAppointment la = new LinkedListAppointment();
	public menu ()
	{	
    } 
	

    public void reception() 
    {
    	int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nRECEPTION");
        System.out.println("+------------------------------+");
        System.out.println("|[1] Management of participants|");
        System.out.println("|[2] Management of appointments|");
        System.out.println("|[3] Display management        |");
        System.out.println("|[4] Relationship management   |");
        System.out.println("+------------------------------+");
        choice = sc.nextInt();

        if (choice == 1) {
        	manageParticipant();
        } else if (choice == 2) {
        	manageAppointments();
        } else if (choice == 3) {
        	manageDisplay();
        } else if (choice == 4) {
        	manageRelation();
        } else {
            System.out.println("[!] The number you have entered does not correspond to any menu.\n");
            reception();
        }
        sc.close();
    }
    
    public void manageParticipant()
    {
    	int choice;
        Scanner scanner = new Scanner(System.in);
    	System.out.println("MANAGE PARTICIPANT");
    	System.out.println("+-----------------------------------------------------+");
    	System.out.println("|[1] Add a participant                                |");
    	System.out.println("|[2] Modify a participant                             |");
    	System.out.println("|[3] Delete a participant                             |");
    	System.out.println("|[4] Reception                                        |");
    	System.out.println("+-----------------------------------------------------+");
    	choice = scanner.nextInt();

    	if(choice==1)
        {
    		System.out.println("[+] ADD A PARTICIPANT [+]");
    		System.out.println("Enter the last name: ");
    		String lastname = scanner.next();
    		System.out.println("Enter the first name: ");
    		String firstname = scanner.next();
    		System.out.println("Enter the phone number: ");
    		int number = scanner.nextInt();
    		System.out.println("Enter the email address: ");
    		String email = scanner.next();
    		
            
            participant p = new participant(lastname,firstname,email,number);
            
            if(lp.searchByName(lastname)!=null)
            {
            	System.out.println("[i] Error: a participant with the same name already exists.");
                manageParticipant();
            }
            else
            {
            	lp.addParticipant(p);
            	System.out.println("[i] Participant added successfully");
            	reception();
            }
            
        }
        else if(choice==2)
        {
            System.out.println("[+] MODIFY A PARTICIPANT [+]");
            System.out.println("Enter the last name : ");
            String lastname = scanner.next();
            System.out.println("Enter the first name : ");
            String firstname = scanner.next();
            System.out.println("Enter the new email address : ");
            String email = scanner.next();
            System.out.println("Enter the new telephone number : ");
            int number = scanner.nextInt();
            
            if(lp.searchByName(lastname)!=null)
            {
                lp.EditParticipant(lastname,firstname,email,number);
                System.out.println("[i] Participant modified successfully");
                reception();
            }
            else
            {
            	System.out.println("[!] The entered name does not belong to any participant");
                manageParticipant();
            }  
        }
        else if(choice==3)
        {

        	System.out.println("[+] DELETE A PARTICIPANT [+]");
        	System.out.println("Enter the last name: ");
        	String lastname = scanner.nextLine();
        	
            participant pp = lp.searchByName(lastname);
            if(lp.searchByName(lastname)!=null)
            {
                if(lp.AppointmentCountParticipant(pp)==0)
                {
                    lp.removeParticipant(pp);
                    System.out.println("[i] Participant deleted successfully");
                    reception();
                }
                else
                {
                	System.out.println("[!] Deletion impossible because the participant is registered for " + lp.AppointmentCountParticipant(pp) + " appointments");
                    manageParticipant();
                }

            }
            else
            {
            	System.out.println("[!] The entered name does not belong to any participant");
                reception();
            }
        }
        else if(choice==4)
        {
        	reception();
        }
        else
        {
        	System.out.println("[!]The entered number does not correspond to any menu.");
            manageParticipant();
        }
        scanner.close();
        
    }
    
    
    public void manageDisplay()
    {
    	System.out.println("DISPLAY");
    	System.out.println("+------------------------------------------+");
    	System.out.println("|[1] Display all appointments for a date   |");
    	System.out.println("|[2] Display participants of an appointment|");
    	System.out.println("|[3] Display appointments of a participant |");
    	System.out.println("|[4] Display all participants              |");
    	System.out.println("|[5] Display all appointments              |");
    	System.out.println("|[6] Reception                             |");
    	System.out.println("+------------------------------------------+");


    	Scanner scanner = new Scanner(System.in);
    	int choice = scanner.nextInt();

        if(choice==1)
        {
        	System.out.println("[+] DISPLAY ALL APPOINTMENTS FOR A DATE [+]");
        	System.out.println("Enter a day number: ");
        	int day = scanner.nextInt();
        	System.out.println("List of appointments: ");
            la.displayAppointmentsByDate(day);

            reception();
        }
        else if(choice==2)
        {
        	System.out.println("[+] DISPLAY ALL PARTICIPANTS OF AN APPOINTMENT ");
        	System.out.println("Enter the name of the appointment: ");
        	String name = scanner.next();
        	
            appointment a;
            if(la.searchByName(name)!=null)
            {
            	System.out.println("List of participants for the appointment:" + name);
                la.displayAppointmentParticipants(name);

                reception();
            }
            else{
            	System.out.println("The entered name does not match any appointment");
            	manageDisplay();
            }


        }
        else if(choice==3)
        {
            System.out.println("[+] DISPLAY ALL APPOINTMENTS FOR A PARTICIPANT [+]");
            System.out.println("Enter the last name of the participant:\n");
            String lastname = scanner.next();

            if(lp.searchByName(lastname)!=null)
            {
                lp.displayAppointmentParticipant(lastname);
                reception();
            }
            else
            {
            	System.out.println("[!] The entered name does not belong to any participant.");
                manageDisplay();
            }
        }
        else if(choice==4)
        {
        	System.out.println("[+] LIST OF PARTICIPANTS [+]");
            lp.displayList();
            reception();
        }
        else if(choice==5)
        {
        	 System.out.println("[+] LIST OF APPOINTMENTS [+]");
             la.displayList();
             reception();
        }
        else if(choice==6)
        {
        	reception();
        }
        else
        {
        	System.out.println("[!] The entered number does not correspond to any menu.");
        	manageDisplay();
        }

        scanner.close();
    }

    public void manageRelation()
    {
    	System.out.println("RELATIONSHIP MANAGEMENT");
    	System.out.println("+---------------------------------------------------------+");
    	System.out.println("|[1] Search if a person has an appointment at a given time|");
    	System.out.println("|[2] Add a participant to an appointment                  |");
    	System.out.println("|[3] Reception                                            |");
    	System.out.println("+---------------------------------------------------------+");

    	Scanner scanner = new Scanner(System.in);
    	int choice = scanner.nextInt();

        if(choice==1)
        {
            System.out.println("[+] SEARCH IF A PERSON HAS AN APPOINTMENT AT A GIVEN TIME [+]");
            System.out.println("Enter the person's last name:\n");
            String lastname = scanner.next();
            System.out.println("Enter the day number:\n");
            int day = scanner.nextInt();
            System.out.println("Enter the start time:\n");
            int startTime = scanner.nextInt();
            System.out.println("Enter the end time:\n");
            int endTime = scanner.nextInt();

            participant pp = lp.searchByName(lastname);
            if(lp.searchByName(lastname)!=null)
            {
                if(lp.setAppointmentTime(pp,day,startTime,endTime))
                {
                	System.out.println("The selected person has an appointment on the entered date.");
                    reception();
                }
                else
                {
                	System.out.println("The selected person has no appointments on the entered date.");
                    reception();
                }

            }
            else
            {
            	System.out.println("[!]The entered name does not belong to any participant.");
            	manageRelation();
            }
        }
        else if(choice==2)
        {

            System.out.println("[+] ADD AN APPOINTMENT TO A PARTICIPANT [+]");

            System.out.println("Enter the person's last name: ");
            String lastname = scanner.next();

            System.out.println("\nEnter the person's first name: ");
            String firstname = scanner.next();
            
            
            if(lp.searchByName(lastname)==null)
            {
            	System.out.println("The entered name does not correspond to any participant.");
                manageRelation();
            }
            
            System.out.println("ENTER THE NAME OF THE APPOINTMENT :");
            String nameApp = scanner.next();
            
            if(la.searchByName(nameApp)==null)
            {
            	System.out.println("The entered name does not correspond to any appointment");
                manageRelation();
            }
            
            participant pp = lp.searchByName(lastname);
            appointment a =  la.searchByName(nameApp);

            if(lp.AppointmentOverlap(pp,a))
            {
            	System.out.println("[!] Unable to add the participant to the meeting because they are not available");
                manageRelation();
            }
            else
            {
            	
                la.addParticipantAppointment(nameApp,pp);
     
                lp.addAppointmentParticipant(lastname,firstname,a);

                System.out.println("Participant successfully added to the appointment");

                reception();

            }

        }

        else if(choice==3)
        {
        	reception();
        }
        else
        {
        	System.out.println("[!]The entered number does not correspond to any menu.");
            manageRelation();
        }
        scanner.close();

    }
    
    public void manageAppointments()
    {
    	System.out.println("APPOINTMENT MANAGEMENT");
    	System.out.println("+-----------------------------------------------------+");
    	System.out.println("|[1] Add an appointment                               |");
    	System.out.println("|[2] Modify an appointment                            |");
    	System.out.println("|[3] Delete an appointment                            |");
    	System.out.println("|[4] Reception                                        |");
    	System.out.println("+-----------------------------------------------------+");

    	Scanner scanner = new Scanner(System.in);
    	int choice = scanner.nextInt();
       
        if(choice==1)
        {
        	System.out.println("\n[+] ADD AN APPOINTMENT ");
        	System.out.println("\nEnter the name: ");
        	String name = scanner.next();
        	System.out.println("\nEnter the day number: ");
        	int day = scanner.nextInt();
        	System.out.println("\nEnter the start time: (hour only, without minutes)");
        	int startTime = scanner.nextInt();
        	System.out.println("\nEnter the end time: (hour only, without minutes)");
        	int endTime = scanner.nextInt();

            if(la.searchByName(name)!=null)
            {
            	System.out.println("[!] Error: an appointment with the same name already exists");
                manageAppointments();
            }
            else
            {
            	appointment a = new appointment(name,day,startTime,endTime);
                la.addAppointment(a);
                System.out.println("[+] Appointment added successfully");
                reception();


            }
        }
        else if(choice==2)
        {
        	System.out.println("\n[+] MODIFY AN APPOINTMENT");
        	System.out.println("\nEnter the name:");
        	String name = scanner.next();
        	System.out.println("\nEnter the new day number:");
        	int day = scanner.nextInt();
        	System.out.println("\nEnter the new start time (hour only, without minutes):");
        	int start = scanner.nextInt();
        	System.out.println("\nEnter the new end time (hour only, without minutes):");
        	int end = scanner.nextInt();

            appointment a =la.searchByName(name);
            if(la.searchByName(name)!=null)
            {
                appointment newa =  new appointment(name,day,start,end);
                if(la.availableParticipants(a,newa,lp))
                {
                    la.editAppointment(name,start,end,day);     //VERIF QUE TT LES PARTICIP DISPOS
                    System.out.println("[i] RDV modified successfully");
                    //listeRDV.afficherListe();
                    reception(); 
                }
                else
                {
                    System.out.println("Modification impossible as at least one participant of the appointment is not available at the new schedule.");
                    reception() ;
                }

            }
            else
            {
            	System.out.println("[!] The entered name does not correspond to any appointment");

                reception() ;

            }


        }
        else if(choice==3)
        {
        	System.out.println("[+] DELETE AN APPOINTMENT ");
        	System.out.println("Enter the name: ");
        	String name = scanner.next();
        	
            appointment a = la.searchByName(name);
            if(la.searchByName(name)!=null)
            {
                la.deleteAppointment(a);
                lp.removeAppointment(a);
                System.out.println("[i] Appointment successfully deleted");
                
                /*cout<<"\nListe des RDV:\n";
                listeRDV.afficherListe();*/
                reception(); 
            }
            else
            {
            	System.out.println("[!] The entered name does not correspond to any appointment.");
                //listeRDV.afficherListe();
                manageAppointments();
            }

        }
        else if(choice==4)
        {
        	reception();
        }
        else
        {
        	System.out.println("[!] The number entered does not correspond to any menu.");
            manageAppointments();
        }
        scanner.close();
    }

	

}
