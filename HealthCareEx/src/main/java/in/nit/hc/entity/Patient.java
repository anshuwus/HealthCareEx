package in.nit.hc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="patient_tab")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="pat_fb_col",length = 20)
	private String firstName;
	
	@Column(name="pat_ln_col",length=20)
	private String lastName;
	
	@Column(name="pat_gen_col",length=10)
	private String gender;
	
	@Column(name="pat_mob_col")
	private Long mobile;
	
	@Column(name="pat_marital_col",length=20)
	private String maritalStatus;
	
	@Column(name="pat_presentAddr_col",length = 20)
	private String presentAddress;
	
	@Column(name="pat_commAddr_col",length = 20)
	private String communicationAddress;
	
	@Column(name="pat_otherDet_col")
	private String otherDetails;
	
}
