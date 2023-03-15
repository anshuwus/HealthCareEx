package in.nit.hc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="specialization_tab")
public class Specialization {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="spec_id_col")
	private Long id;
	
	@Column(name="spec_code_col")
	private String code;
	
	@Column(name="spec_note_col")
	private String specName;
	
	@Column(name="spec_note_col")
	private String specNote;
}
