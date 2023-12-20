package com.examportal.serviceimpltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.examportal.entity.Admin;
import com.examportal.repository.AdminRepository;
import com.examportal.serviceimpl.AdminServiceImpl;


@SpringBootTest
class AdminServiceImplTest {
	
	 @Mock
	    private AdminRepository adminRepository;

	    @InjectMocks
	    private AdminServiceImpl adminServiceImpl;

	    @Test
	    void testLogin() {
	        // Create a sample Admin
	        Admin existingAdmin = new Admin();
	        existingAdmin.setAdminMail("admin@example.com");
	        existingAdmin.setAdminPassword("password123");

	        // Stub the behavior of the mock AdminRepository
	        when(adminRepository.findByAdminMail("admin@example.com")).thenReturn(existingAdmin);

	        // Test the login method
	        Admin resultAdmin = adminServiceImpl.login("admin@example.com", "password123");

	        // Verify the expected interactions
	        verify(adminRepository, times(1)).findByAdminMail("admin@example.com");

	        // Assert the result
	        assertNotNull(resultAdmin);
	        assertEquals(existingAdmin, resultAdmin);
	    }
	    
	   
}