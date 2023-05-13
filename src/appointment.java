package src;
import java.util.ArrayList;
import java.util.Objects;


public class appointment 
{
	private String name;
	private int startTime, endTime, day;
	public ArrayList<participant> participants;

	public appointment(String n, int start, int end, int d)
	{
		this.name = n;
		this.startTime = start;
		this.endTime = end;
		this.day = d;
		this.participants = new ArrayList<participant>();
	}
	
	public boolean lessThan(appointment a) {
        return name.compareTo(a.name) < 0;
    }

    public boolean greaterThan(appointment a) {
        return name.compareTo(a.name) > 0;
    }
	
	public String getName()
	{
		return name;
	}
	
	public int getStartTime()
	{
		return startTime;
	}
	
	public int getEndTime()
	{
		return endTime;
	}
	
	public int getDay() {
		return day;
	}
	
	public void displayParticipantList()
	{
		if(participants.size()==0)
	    {
			System.out.println("Aucun participant n'est inscrit à ce rdv."); 
	    }
	    else{
	        for(int i=0;i<participants.size();i++)
	        {
	        	System.out.println(participants.get(i)); 
	        }
	    }
	}
	
	public boolean appointmentOverlap (appointment a)
	{
		if(day==a.day)  
	    {
			boolean A=startTime==a.startTime || endTime==a.endTime;
			boolean B=startTime < a.startTime && endTime > a.startTime;
			boolean C=startTime > a.startTime && startTime< a.endTime;
			//boolean D= endTime < a.startTime && endTime;

	        if(A || B ||C) {
	            return true;
	        }
	        else {
	            return false;
	        }
	    }
	    return false;
	}
	
	public void addParticipant(participant p)
	{
		participants.add(p);
	}
	
	public void setDaySchedule(int start, int end, int d)
	{
		this.startTime = start;
		this.endTime = end;
		this.day = d;
	}
	
	public boolean availableParticipants(appointment a) 
	{
	    boolean available = true; 
	    System.out.println("in appointment, <\n");
	    System.out.println(a);
	    System.out.println(participants.size());
	    System.out.println(participants.get(0));
	    System.out.println("fin rdv");
	    for(int i = 0; i < participants.size(); i++) {
	        System.out.println("dans RDV.CPP : ");
	        participants.get(i).displayAppointmentList();
	        System.out.println("end");
	    }
	    return available;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, endTime, name, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		appointment other = (appointment) obj;
		return day == other.day && endTime == other.endTime && Objects.equals(name, other.name)
				&& startTime == other.startTime;
	}

	
	

}
