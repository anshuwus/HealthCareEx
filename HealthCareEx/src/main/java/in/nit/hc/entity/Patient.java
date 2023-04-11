package in.nit.hc.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	@Column(name="pat_first_name_col",length = 20)
	private String firstName;
	
	@Column(name="pat_last_name_col",length=20)
	private String lastName;
	
	@Column(name="pat_gen_col",length=10)
	private String gender;
	
	@Column(name="pat_mob_col")
	private Long mobile;
	
	@DateTimeFormat(iso=ISO.DATE)
	//@Temporal(TemporalType.DATE)
	@Column(name="pat_dob_col")
	private Date dateOfBirth;
	
	@Column(name="pat_ms_col",length=20)
	private String marialStatus;
	
	@Column(name="pat_presentAddr_col",length = 20)
	private String presentAddr;
	
	@Column(name="pat_commAddr_col",length = 20)
	private String commAdr;
	
	@ElementCollection
	@CollectionTable(name="pat_medi_hist_tab", joinColumns = @JoinColumn(name="pat_medi_hst_id_fk_col"))
	@Column(name="pat_medi_hist_col")
	private Set<String> mediHistory;
	
	@Column(name="pat_other_col")
	private String ifOther;
	
	@Column(name="pat_note_col")
	private String note;
	
}
