package com.campusacademy.nch.backenddevteam.model;

//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.Date;

@Entity
@Table(name = "teammember")
//@Data
//@NoArgsConstructor
public class TeamMember {
	@Id
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
   	@Column(name = "name", // memberteam_name
   			nullable = false, //true
   			length = 100 )
   	private String playername;
   	
	@Column(name = "pays", // memberteam_pays
   			nullable = false, //true
   			length = 100 )
   	private String pays;
	
	@Column(name = "birth", // memberteam_birth
   			nullable = false, //true
   			length = 100 )
   	private Date birth;
   	
 
	 @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	 @JoinColumn(name = "team_id", referencedColumnName = "id")
	    private Team team;
}
