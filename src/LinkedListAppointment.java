package src;

public class LinkedListAppointment 
{
	private NodeAppointment head;

	public LinkedListAppointment()
	{
		this.head = null;
	}
	
	public appointment searchByName(String ln) 
	{
		NodeAppointment crt = head;
	    while(crt!=null && !crt.app.getName().equals(ln))
	    {
	        crt=crt.next;
	    }
	    if(crt==null) return null;
	    else
	    {
	        return crt.app;
	    }
	}
	
	public void addAppointment(appointment a)
	{
		NodeAppointment nouv = new NodeAppointment(a);
		NodeAppointment crt=head;
		NodeAppointment pres=null;

	    if(head==null) 
	    {
	        head=nouv;
	        return ;
	    }
	    if(head.next==null) 
	    {
	       if(nouv.app.lessThan(head.app)) 
	        {
	            nouv.next=head;
	            head.prev=nouv;
	            head=nouv;
	            return ;
	        }
	        if(nouv.app.greaterThan(head.app)) 
	        {
	            head.next=nouv;
	            nouv.prev=head;
	            return ;
	        }
	    }
	    
	    while(crt!=null && nouv.app.greaterThan(crt.app))  
	    {
	        pres=crt;
	        crt=crt.next;
	    }
	    if(crt!=null) 
	    {
	        crt.prev=nouv;
	    }
	    pres.next=nouv;
	    nouv.prev=pres;
	    nouv.next=crt;
	}
	
	public void editAppointment(String name,int start,int end,int day) 
	{
		NodeAppointment crt = head;
	    while(crt!=null && crt.app.getName()!=name)
	    {
	        crt=crt.next;
	    }
	    crt.app.setDaySchedule(start,end,day);
	}
	
	public boolean deleteAppointment(appointment a)
	{
	    if(head!=null)
	    {
	    	NodeAppointment crt=head;
	    	NodeAppointment pres=null;

	       
	        while(crt!=null && !crt.app.equals(a))
	        {
	            pres=crt;
	            crt=crt.next;
	        }


	        if(crt!=null)
	        {
	        	NodeAppointment next=crt.next;
	            crt = null;

	            if(pres!=null)  
	            {
	                pres.next=next;

	            }
	            else 
	            {
	                head=next;
	            }
	            if(next!=null) 
	            {
	            	next.prev=pres;
	            }
	            return true;


	        }
	        else{
	            return false;
	        }
	    }
	    else
	    {
	        return false;
	    }
	}
	
	public void addParticipantAppointment(String name, participant p) 
	{
		NodeAppointment crt=head;
	    while(crt!=null && !crt.app.getName().equals(name))  
	    {
	        crt=crt.next;
	    }
	    crt.app.participants.add(p);
	}
	
	public boolean displayAppointmentsByDate(int d)
	{
	    int cpt=0;
	    NodeAppointment crt=head;

	    while(crt!=null)
	    {
	        if(crt.app.getDay()==d)
	        {
	        	System.out.println(crt.app);
	            cpt++;
	        }
	        crt=crt.next;
	    }

	if(cpt==0) return false;
	return true;
	}
   
	public void displayList()
	{
		NodeAppointment crt=head;
	    while(crt!=null)
	    {
	    	System.out.println(crt.app.getName());
	    	System.out.println(crt.app.getStartTime());
	    	System.out.println(crt.app.getEndTime());
	    	System.out.println(crt.app.getDay());
	        crt=crt.next;
	    }
	}
	
	public void displayAppointmentParticipants(String name)
	{
		NodeAppointment crt=head;
	    while(crt!=null && crt.app.getName().equals(name)) 
	    {
	        crt=crt.next;
	    }
	    crt.app.displayParticipantList();
	}
	
	public boolean availableParticipants(appointment a,appointment newA,LinkedListParticipant lp)  
	{
		NodeAppointment crt=head;
	    while(crt!=null && !crt.app.equals(a) )  
	    {
	        crt=crt.next; //ON A LE RDV A MODIFIER
	    }

	    return lp.searchIfParticipantsAvailable(crt.app,newA);
	}

}
