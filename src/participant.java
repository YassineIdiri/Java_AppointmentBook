package src;
//import com.AppointmentBookManagement.appointment;

import java.util.ArrayList;
import java.util.Objects;

public class participant 
{
	private String lastName, firstName, email;
	private int phoneNumber;
	public ArrayList<appointment> appointments;
	
    public participant(String ln, String fn, String e, int p)
    {
    	this.lastName = ln;
    	this.firstName = fn;
    	this.email = e;
    	this.phoneNumber = p;
    	this.appointments = new ArrayList<appointment>();	
    }
    
    public boolean lessThan(participant p) {
        if (this == p) return false;
        return lastName.compareTo(p.lastName) < 0;
    }

    public boolean greaterThan(participant p) {
        if (this == p) return false;
        return lastName.compareTo(p.lastName) > 0;
    }
    
    public String getFirstName()
    {
    	return firstName;
    }
    
    public String getLastName()
    {
    	return lastName;
    }
    
    public String getEmail()
    {
    	return email;
    }
    
    public int getPhoneNumber()
    {
    	return phoneNumber;
    }
    
    public int getAppointmentCount()
    {
    	return appointments.size();
    }
    
    public void displayAppointmentList()
    {
    	if(appointments.size() == 0) {
            System.out.println("The selected person is not registered for any appointment.");
        }
        else {
            for(int i = 0; i < appointments.size(); i++) {
                System.out.println(appointments.get(i));
            }
        }
    }
    
    public void deleteRDV(appointment a) 
    {
    	int i = 0;
        boolean present = false;
        while (i < appointments.size() && !present) {
            if (appointments.get(i).equals(a)) {
                present = true;
            } else {
                i++;
            }
        }
        if (present) {
            for (int j = i; j < appointments.size() - 1; j++) {
            	appointments.set(j, appointments.get(j + 1));
            }
            appointments.remove(appointments.size() - 1);
        }
    }
    
    public boolean searchAppointmentByDate(int start, int end, int day) 
    {
        int i = 0;
       
        while (i < appointments.size() && day != appointments.get(i).getDay() && start != appointments.get(i).getStartTime() && end != appointments.get(i).getEndTime()) {
            i++;
        }
        if (i >= appointments.size()) {
            return false;
        }
        if (day == appointments.get(i).getDay() && start == appointments.get(i).getStartTime() && end == appointments.get(i).getEndTime()) {
            return true;
        }
        return false;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		participant other = (participant) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && phoneNumber == other.phoneNumber;
	}

	public boolean searchOverlapAppointment(appointment a) {
        int i = 0;
        while (i < appointments.size() && !appointments.get(i).appointmentOverlap(a)) {
            i++;
        }
        if (i >= appointments.size()) return false;
        if (appointments.get(i).appointmentOverlap(a)) return true;
        return false;
    }
    
    public void addAppointment(appointment a) 
    {
    	appointments.add(a);
    }
    
    public participant setEmailPhone(String ln,String fn,String e,int p)
    {
    	this.lastName = ln;
    	this.firstName = fn;
    	this.email = e;
    	this.phoneNumber = p;
        return this;
    }

}