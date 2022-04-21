package com.shelly.demorest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/students")
public class StudentResource {
	
	StudentRepository studrepo = new StudentRepository(); 
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Student> getStudents() {	
		
		System.out.println("Inside getStudents");
		return studrepo.getStudentList();				
	}
	
	
   //test url http://localhost:8080/demorest/webresources/students/student/101	
   @GET
   @Path("/student/{id}")
   @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
   public Student getStudent(@PathParam("id") int id)
   {
	   System.out.println("Inside getStudent with param");
	   return studrepo.getStudent(id);
   }
   
   @POST
   @Path("/student")
   @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
   public Student createStudent(Student s)
   {
	   System.out.println("Inside createStudent for adding student" + s);
	   Student sadedd = studrepo.create(s);
	   return sadedd;
   }
   
   @PUT
   @Path("/student")
   @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
   public Student updateStudent(Student s)
   {
	   System.out.println("Inside updateStudent for updating student" + s);
	   Student supdated = studrepo.update(s);
	   System.out.println("student updated");	  
	   return supdated;
   }
   
   @DELETE
   @Path("/student/{id}")
   public void deleteStudent(@PathParam("id")int id)
   {
	   System.out.println("Inside deleteStudent for deleting student" +id);
	   studrepo.delete(id);
   }
}
