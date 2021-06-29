package com.campusacademy.nch.backenddevteam.model;

import javax.persistence.*;

import java.util.List;
import java.util.Date;

@Entity
@Table(name = "team")
//@Data
//@NoArgsConstructor
public class Team {
	@Id
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
   	@Column(name = "name", // team_name
   			nullable = false, //true
   			length = 100 )
   	private String name;
   	
   	@OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
   	@JoinTable(
            name = "team_members",
            joinColumns = {@JoinColumn(name = "teammember_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")}
    )
    private List<TeamMember> players;

}
