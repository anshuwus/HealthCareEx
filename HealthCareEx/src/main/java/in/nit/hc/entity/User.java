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

@AllArgsConstructor
@Entity
@Table(name="user_tab")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id_col")
	private Long id;
	
	@Column(name="user_display_name_col")
	private String displayName;
	
	@Column(name="user_uname_col")
	private String username;
	
	@Column(name="user_password_col")
	private String password;
	
	@Column(name="user_urole_col")
	private String role;
}
