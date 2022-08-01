package com.cts;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.cts.entity.DrugDetails;
import com.cts.entity.DrugLocationDetails;
import com.cts.repository.DrugDetailsRepo;
import com.cts.repository.DrugLocationRepo;

@EnableFeignClients
@SpringBootApplication
public class DrugServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(DrugDetailsRepo drugDetailsRepository,DrugLocationRepo drugLocationRepo)	 {

	    return args -> {
	       DrugDetails newDrugDetails = DrugDetails.builder().drugId("D1").drugName("Paracetmol").manufactureDate(LocalDate.parse("2022-05-05")).expiryDate(LocalDate.parse("2024-05-05")).manufacturer("Rohith").build();
	       DrugDetails newDrugDetails1 = DrugDetails.builder().drugId("D2").drugName("Citrazine").manufactureDate(LocalDate.parse("2022-05-05")).expiryDate(LocalDate.parse("2024-05-05")).manufacturer("Venkata").build();
	       DrugDetails newDrugDetails2 = DrugDetails.builder().drugId("D3").drugName("Aspirin").manufactureDate(LocalDate.parse("2022-05-05")).expiryDate(LocalDate.parse("2024-05-05")).manufacturer("Indu").build();
	       DrugDetails newDrugDetails3 = DrugDetails.builder().drugId("D4").drugName("Pantop 40").manufactureDate(LocalDate.parse("2022-05-05")).expiryDate(LocalDate.parse("2024-05-05")).manufacturer("Aanchal").build();
	       DrugDetails newDrugDetails4 = DrugDetails.builder().drugId("D5").drugName("Dolo 650").manufactureDate(LocalDate.parse("2022-05-05")).expiryDate(LocalDate.parse("2024-05-05")).manufacturer("Lalit").build();
	       drugDetailsRepository.save(newDrugDetails);
	       drugDetailsRepository.save(newDrugDetails1);
	       drugDetailsRepository.save(newDrugDetails2);
	       drugDetailsRepository.save(newDrugDetails3);
	       drugDetailsRepository.save(newDrugDetails4);
	       
	       
	       DrugLocationDetails newDrugLocationDetails = DrugLocationDetails.builder().serialId("1").location("Chennai").quantity(30).drugDetails(newDrugDetails).build();
	       DrugLocationDetails newDrugLocationDetails1 = DrugLocationDetails.builder().serialId("2").location("Bangalore").quantity(30).drugDetails(newDrugDetails).build();
	       DrugLocationDetails newDrugLocationDetails2 = DrugLocationDetails.builder().serialId("3").location("Hyderabad").quantity(30).drugDetails(newDrugDetails).build();
	       DrugLocationDetails newDrugLocationDetails3 = DrugLocationDetails.builder().serialId("4").location("pune").quantity(30).drugDetails(newDrugDetails).build();
	       DrugLocationDetails newDrugLocationDetails4 = DrugLocationDetails.builder().serialId("5").location("Chennai").quantity(30).drugDetails(newDrugDetails1).build();
	       DrugLocationDetails newDrugLocationDetails5 = DrugLocationDetails.builder().serialId("6").location("Bangalore").quantity(30).drugDetails(newDrugDetails1).build();
	       DrugLocationDetails newDrugLocationDetails6 = DrugLocationDetails.builder().serialId("7").location("Pune").quantity(30).drugDetails(newDrugDetails1).build();
	       DrugLocationDetails newDrugLocationDetails7 = DrugLocationDetails.builder().serialId("8").location("Hyderabad").quantity(30).drugDetails(newDrugDetails1).build();
	       DrugLocationDetails newDrugLocationDetails8 = DrugLocationDetails.builder().serialId("9").location("Chennai").quantity(30).drugDetails(newDrugDetails2).build();
	       DrugLocationDetails newDrugLocationDetails9 = DrugLocationDetails.builder().serialId("10").location("Bangalore").quantity(30).drugDetails(newDrugDetails2).build();
	       DrugLocationDetails newDrugLocationDetails10 = DrugLocationDetails.builder().serialId("11").location("Pune").quantity(30).drugDetails(newDrugDetails3).build();
	       DrugLocationDetails newDrugLocationDetails11 = DrugLocationDetails.builder().serialId("12").location("Hyderabad").quantity(30).drugDetails(newDrugDetails3).build();
	       DrugLocationDetails newDrugLocationDetails12 = DrugLocationDetails.builder().serialId("13").location("Chennai").quantity(30).drugDetails(newDrugDetails3).build();
	       DrugLocationDetails newDrugLocationDetails13 = DrugLocationDetails.builder().serialId("14").location("Bangalore").quantity(30).drugDetails(newDrugDetails3).build();

	       DrugLocationDetails newDrugLocationDetails14 = DrugLocationDetails.builder().serialId("15").location("Chennai").quantity(30).drugDetails(newDrugDetails4).build();
	       DrugLocationDetails newDrugLocationDetails15 = DrugLocationDetails.builder().serialId("16").location("Bangalore").quantity(30).drugDetails(newDrugDetails4).build();
	       DrugLocationDetails newDrugLocationDetails16 = DrugLocationDetails.builder().serialId("17").location("Pune").quantity(30).drugDetails(newDrugDetails4).build();
	       DrugLocationDetails newDrugLocationDetails17 = DrugLocationDetails.builder().serialId("18").location("Hyderabad").quantity(30).drugDetails(newDrugDetails4).build();	       
	       drugLocationRepo.save(newDrugLocationDetails14);
	       drugLocationRepo.save(newDrugLocationDetails15);
	       drugLocationRepo.save(newDrugLocationDetails16);
	       drugLocationRepo.save(newDrugLocationDetails17);	
	       
	       drugLocationRepo.save(newDrugLocationDetails);
	       drugLocationRepo.save(newDrugLocationDetails1);
	       drugLocationRepo.save(newDrugLocationDetails2);
	       drugLocationRepo.save(newDrugLocationDetails3);
	       drugLocationRepo.save(newDrugLocationDetails4);
	       drugLocationRepo.save(newDrugLocationDetails5);
	       drugLocationRepo.save(newDrugLocationDetails6);
	       drugLocationRepo.save(newDrugLocationDetails7);
	       drugLocationRepo.save(newDrugLocationDetails8);
	       drugLocationRepo.save(newDrugLocationDetails9);
	       drugLocationRepo.save(newDrugLocationDetails10);
	       drugLocationRepo.save(newDrugLocationDetails11);
	       drugLocationRepo.save(newDrugLocationDetails12);
	       drugLocationRepo.save(newDrugLocationDetails13);
	    };
	}
	
}
