package com.campusacademy.nch.backenddevteam.model;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "tournament")
//@Data
//@NoArgsConstructor
public class Tournament {
	@Id
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
   	@Column(name = "name", // memberteam_name
   			nullable = false, //true
   			length = 100 )
   	private String name;
   	
   	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	 @JoinColumn(name = "team_id", referencedColumnName = "id")
	    private List<Team> teams;

}
