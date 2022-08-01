///**
// * 
// */
//package com.mailorderpharmacy.refill.service.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.cts.exception.InvalidTokenException;
//import com.cts.feign.AuthFeign;
//import com.cts.model.RefillOrderLine;
//import com.cts.model.TokenValid;
//import com.cts.repo.RefillOrderLineRepository;
//import com.mailorderpharmacy.refill.service.RefillorderSubscriptionServiceImpl;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class RefillOrderSubscriptionServiceImplTest {
//	
//	@InjectMocks
//	private RefillorderSubscriptionServiceImpl refillOrderSubscriptionServiceImpl;
//	
//	@Mock
//	private RefillOrderLineRepository refillOrderSubscriptionRepository;
//	
//	@Mock
//	private AuthFeign authFeign;
//	
//	@Test
//	public void updateRefillOrderSubscriptionTest() throws InvalidTokenException {
//		TokenValid response = new TokenValid("uid", "name", true);
//		when(authFeign.getValidity("token")).thenReturn(response);
//		RefillOrderLine order = new RefillOrderLine(1, "CR7", 3, 3);
//		RefillOrderLine ros = refillOrderSubscriptionServiceImpl.updateRefillOrderSubscription(1,"CR7",3,3,"token");
//		long actual = ros.getSubID();
//		long expected = 1;
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	public void updateRefillOrderSubscriptionTestFalse() throws InvalidTokenException {
//		TokenValid response = new TokenValid("uid", "name", false);
//		when(authFeign.getValidity("token")).thenReturn(response);
//		RefillOrderLine order = new RefillOrderLine(1, "CR7", 3, 3);
//		assertThrows(InvalidTokenException.class,
//				() -> refillOrderSubscriptionServiceImpl.updateRefillOrderSubscription(1,"CR7",3,3,"token"));
//
//	}
//	
//	@Test
//	public void getallTest() throws InvalidTokenException {
//		ArrayList<RefillOrderLine> list = new ArrayList<>();
//		RefillOrderLine refillOrder = new RefillOrderLine(1,101, "CR7", 3, 3);
//		list.add(refillOrder);
//
//		TokenValid response = new TokenValid("uid", "name", true);
//		when(authFeign.getValidity("token")).thenReturn(response);
//		when(refillOrderSubscriptionRepository.findAll()).thenReturn(list);
//		List<RefillOrderLine> l = refillOrderSubscriptionServiceImpl.getAllSubscription("token");
//		assertEquals(list.get(0).getId(), l.get(0).getId());
//	}
//
//	
//	@Test
//	void getallTestFalse() throws InvalidTokenException {
//		TokenValid response = new TokenValid("uid", "name", false);
//		when(authFeign.getValidity("token")).thenReturn(response);
//		assertThrows(InvalidTokenException.class, () -> refillOrderSubscriptionServiceImpl.getAllSubscription("token"));
//
//	}
//	
//	@Test
//	void deleteBySubscriptionIdTest() throws InvalidTokenException {
//		TokenValid response = new TokenValid("uid", "name", true);
//		when(authFeign.getValidity("token")).thenReturn(response);
//		refillOrderSubscriptionServiceImpl.deleteBySubscriptionId(45, "token");
//
//		String expected = String.valueOf(response);
//		String actual = "TokenValid(uid=uid, name=name, isValid=true)";
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void deleteBySubscriptionIdTestFalse() throws InvalidTokenException {
//		TokenValid response = new TokenValid("uid", "name", false);
//		when(authFeign.getValidity("token")).thenReturn(response);
//		assertThrows(InvalidTokenException.class, () -> refillOrderSubscriptionServiceImpl.deleteBySubscriptionId(45, "token"));
//
//	}
//	
//	
//} 
