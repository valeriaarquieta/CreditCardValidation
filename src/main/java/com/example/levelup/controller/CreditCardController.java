package com.example.levelup.controller;

	import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	@RestController
	public class CreditCardController {

	    	@PostMapping("/card.html")
	        public ResponseEntity<String> validateCreditCard(@RequestBody CreditCardData creditCardData) {
	            StringBuilder response = new StringBuilder();

	            if (isDateExpiryValid(creditCardData.getDateExpiry())) {
	                response.append("Date of Expiry is valid\n");
	            } else {
	                response.append("Date of Expiry is not valid\n");
	            }
	            if (isCVVValid(creditCardData.getCvv(), creditCardData.getPan())) {
	                response.append("CVV is valid\n");
	            } else {
	                response.append("CVV is not valid\n");
	            }
	            if (isPANValid(creditCardData.getPan())) {
	                response.append("PAN is valid\n");
	            } else {
	                response.append("PAN is not valid\n");
	            }
	            if (isAmericanExpress(creditCardData.getPan())) {
	                response.append("American Express card\n");
	            } else {
	                response.append("Not an American Express card\n");
	            }

	            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
	        }
	
		    public static boolean isDateExpiryValid(String dateExpiry) {
		        SimpleDateFormat format = new SimpleDateFormat("MM/yy");
		        try {
		            Date expiryDate = format.parse(dateExpiry);
		            Date currentDate = new Date();
		            return expiryDate.after(currentDate);
		        } catch (ParseException e) { return false; }
		    }//date
	    public static boolean isCVVValid(String cvv, String string) {
	        if (cvv.length() == 3) {
	            return true;
	        } else if (cvv.length() == 4 && isAmericanExpress(cvv)) {
	            return true;
	        } else {
	            return false; }
	        }//cvv
	    public static boolean isAmericanExpress(String pan) {
	        return pan.startsWith("34") || pan.startsWith("37");
	    }//AmericanExp
	    public static boolean isPANValid(String pan) {
	        int panLength = pan.length();
	        return panLength >= 16 && panLength <= 19;
	    }//PAN
	    @Entity
	    @Table(name="creditCard")
	    public static class CreditCardData {
	    	
	    	@Id
	    	@GeneratedValue (strategy = GenerationType.IDENTITY)
	    	@Column(unique=true, nullable =false)
	    	private Long id;
	    	
	    	@Column( nullable =false)
	        private String dateExpiry;
	    	
	    	@Column( nullable =false)
	        private String cvv;
	    	
	    	@Column(unique=true, nullable =false)
	        private String pan;

			public CreditCardData(Long id, String dateExpiry, String cvv, String pan) {
				super();
				this.id = id;
				this.dateExpiry = dateExpiry;
				this.cvv = cvv;
				this.pan = pan;
			}

			public String getDateExpiry() {
				return dateExpiry;
			}

			public void setDateExpiry(String dateExpiry) {
				this.dateExpiry = dateExpiry;
			}

			public String getCvv() {
				return cvv;
			}

			public void setCvv(String cvv) {
				this.cvv = cvv;
			}

			public String getPan() {
				return pan;
			}

			public void setPan(String pan) {
				this.pan = pan;
			}

			public Long getId() {
				return id;
			}

			@Override
			public String toString() {
				return "CreditCardData [id=" + id + ", dateExpiry=" + dateExpiry + ", cvv=" + cvv + ", pan=" + pan
						+ "]";
			}
	    
	       
			
			}
			
	    }
	    

