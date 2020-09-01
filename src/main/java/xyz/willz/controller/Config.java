package xyz.willz.controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import xyz.willz.dao.BookingDao;

public class Config implements ServletContextListener {
	
	 public void contextInitialized(ServletContextEvent event) {
		 // WebApp Startup
		 System.out.println("Server Started");
		 //this.setTimerForDb();
		 System.out.println("Hii nishi the server is started");
		 
		 System.out.println("Continue rest of the work");
		 
	 }

    public void contextDestroyed(ServletContextEvent event) {
        // Webapp shutdown.
    	System.out.println("Server shutdown");
    	timer.cancel();
    }
    
    final private Timer timer = new Timer();
    final private BookingDao bookingDao = new BookingDao();
	public void setTimerForDb() {
		
		timer.schedule( new TimerTask()
		{
		    //private int i = 0;
		    public void run()
		    {
		    	bookingDao.checkAndClear();
		        System.out.println("Timer Ran: ");
		        //if (i > 5) {timer.cancel();System.out.println(timer.purge());} // Count down ten times, then cancel
		    }
		}, 1000 * 60, 1000 * 60 // the second argument for repetition (1000 = 1s)
		);
		
	}

}
