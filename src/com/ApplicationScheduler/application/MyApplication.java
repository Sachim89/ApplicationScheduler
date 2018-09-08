package com.ApplicationScheduler.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.ApplicationScheduler.Controller.DoctorController;
import com.ApplicationScheduler.Controller.LoginController;
import com.ApplicationScheduler.Controller.WelcomeController;

/**
 * @author Saranya
 *
 */
public class MyApplication extends Application{
	@Override
    public Set<Class<?>> getClasses() {
		System.out.println("ApplicationEntered");
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(LoginController.class);  
        s.add(WelcomeController.class); 
        s.add(DoctorController.class); 
        return s;
    }
}
