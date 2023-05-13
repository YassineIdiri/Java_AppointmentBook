package src;

public class LinkedListParticipant 
{
	private NodeParticipant head;
	
	public LinkedListParticipant()
	{
		this.head = null;
	}
	

	
	
	public void addParticipant(participant p) 
	{


		NodeParticipant nouv = new NodeParticipant(p);
		NodeParticipant crt = head;
		NodeParticipant pres = null;

	    if(head==null) //CAS N 1 : LISTE VIDE => LE NOUVEL ELEMENT DEVIENT LA TETE
	    {	
	        head= nouv;
	        return ;
	    }
	    if(head.next==null) //CAS N 2 : LISTE CONTENANT 1 ELEMENT (LA TETE)
	    {
	       if(nouv.partici.lessThan(head.partici))
	        {
	            nouv.next=head;
	            head.prev=nouv;
	            head=nouv;
	            return ;
	        }
	        if(nouv.partici.greaterThan(head.partici)) 
	        {
	            head.next=nouv;
	            nouv.prev=head;
	            return ;
	        }
	    }
	    //POUR TOUS LES AUTRES CAS
	    while(crt!=null && nouv.partici.greaterThan(crt.partici))  //tant que nouv est superieur a lelement courant
	    {
	        pres=crt;
	        crt=crt.next;
	    }
	    if(crt!=null) //on nest pas  a la fin
	    {
	        crt.prev=nouv;
	    }
	    pres.next=nouv;
	    nouv.prev=pres;
	    nouv.next=crt;
	}
	
	public participant searchByName(String name) 
	{
		NodeParticipant crt=head;
		
	    while(crt != null && !crt.partici.getLastName().equals(name))
	    {
	        crt=crt.next;
	    }
	    if(crt==null) return null;
	    else
	    {
	    	return crt.partici;
	    }

	}
	
	
	public void EditParticipant(String ln,String fn,String e,int p)
	{
		NodeParticipant crt=head;
	    while(crt!=null && !crt.partici.getLastName().equals(ln))
	    {
	        crt=crt.next;
	    }
	    crt.partici = crt.partici.setEmailPhone(ln,fn,e,p);
	}
	
	//verif dans menu que p na aucun rdv (avec getNbRDV) + que p existe
	public boolean removeParticipant(participant p) //seulement si elle na aucun rdv
	{
	    if(head!=null)
	    {
	    	NodeParticipant crt=head;
	    	NodeParticipant pres=null;

	        //ON CHERCHE P
	        while(crt!=null && !p.equals(crt.partici))  //crt->d_participant!=p)
	        {
	            pres=crt;
	            crt=crt.next;
	        }
	        //if(crt!=null)
	        //{
	            NodeParticipant next = crt.next;
	            crt = null;

	            if(pres!=null)   //pas en tete
	            {
	                pres.next=next;

	            }
	            else // en tete
	            {
	                head=next;
	            }
	            if(next!=null) //pas a la fin
	            {
	                next.prev=pres;
	            }
	            return true;
	        /*}
	        else{
	            return false;
	        }*/
	    }
	    else
	    {
	        return false;
	    }
	}
	
	//verif que particip na pas dautre rdv au mm moment dans menu + verif que la personne existe
	public void addAppointmentParticipant(String ln,String fn, appointment a) //ajouter un RDV au participant
	{
		NodeParticipant crt=head;
		System.out.println(crt.partici.getLastName());
	    while(crt!=null && !crt.partici.getLastName().equals(ln))  //le nom est unique donc recherche par nom seulement
	    {
	        crt=crt.next;
	    }

	    crt.partici.addAppointment(a);
	}
	
	public void displayList()
	{
		NodeParticipant crt=head;
	    while(crt!=null)
	    {
	    	System.out.println(crt.partici.getFirstName());
	    	System.out.println(crt.partici.getLastName());
	    	System.out.println(crt.partici.getEmail());
	    	System.out.println(crt.partici.getPhoneNumber());
	        crt=crt.next;
	    }
	}
	
	public void displayAppointmentParticipant(String ln)
	{
		NodeParticipant crt=head;
	    while(crt!=null && crt.partici.getLastName()!=ln)
	    {
	        crt=crt.next;
	    }

	    crt.partici.displayAppointmentList();
	}

	public boolean setAppointmentTime(participant p,int day,int start,int end)
	{
		NodeParticipant crt=head;
	    while(crt!=null && !crt.partici.equals(p))
	    {
	        crt=crt.next;
	    }
	    if(crt.partici.searchAppointmentByDate(start,end,day)) return true;
	    return false;

	}

	public boolean AppointmentOverlap(participant p, appointment a)
	{
		NodeParticipant crt=head;
	    while(crt!=null && !crt.partici.equals(p))   //on cherche le participant
	    {
	        crt=crt.next;
	    }
	    if(crt.partici.searchOverlapAppointment(a)) return true;
	    return false;
	}

	public int AppointmentCountParticipant(participant p)
	{
		NodeParticipant crt=head;
	    while(crt!=null && !crt.partici.equals(p))
	    {
	        crt=crt.next;
	    }
	    return crt.partici.getAppointmentCount();
	}

	public void removeAppointment(appointment a)
	{
		NodeParticipant crt=head;
	    while(crt!=null )  //parcourt les participants de LDCP
	    {
	        for(int i=0;i<crt.partici.appointments.size();i++)  //parcourt de la liste de RDV de chaque participant crt
	        {
	            if(crt.partici.appointments.get(i).equals(a)) //si on trouve le rdv dans la liste de rdv de crt
	            {
	                crt.partici.deleteRDV(a);
	            }
	        }
	        crt=crt.next;
	    }
	}

	public boolean searchIfParticipantsAvailable(appointment a, appointment newA) //true si tlm dispo
	{
		NodeParticipant crt=head;
	    participant p;
	    boolean avl=true;

	    for(int i=0;i<a.participants.size();i++) //on parcourt les participants du rdv
	    {
	        crt=head;
	        while(crt!=null && !crt.partici.equals(a.participants.get(i)))
	        {
	            crt=crt.next;
	        }
	        if(crt.partici.searchOverlapAppointment(newA))
	        {
	        	avl=false;
	        }


	    }
	    return avl;
	}

}
