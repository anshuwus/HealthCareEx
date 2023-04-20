package in.nit.hc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Appointment_Tab")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="doc_date_col")
	private Date date;
	
	@Column(name="doc_slots_col")
	private Long noOfSlots;
	
	@Column(name="doc_details_col")
	private String details;
	
	@Column(name="doc_fee_col")
	private Double fee;
	
	//------Association mapping----
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="doc_id_fk_col")
	private Doctor doctor;
}
