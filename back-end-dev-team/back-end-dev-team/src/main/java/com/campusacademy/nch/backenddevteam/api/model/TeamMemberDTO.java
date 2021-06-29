package com.campusacademy.nch.backenddevteam.api.model;

import java.util.Date;
import java.util.List;

import com.campusacademy.nch.backenddevteam.model.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamMemberDTO {
	private Long id;
    private String playername;
    String pays;
    Date birth;
    Team team;

	    public TeamMemberDTO(
	            Long id,
	            String playername,
	            String pays,
	            Date birth,
	            Team team
	    ) {
	        super(id, playername);
	        this.pays = pays;
	        this.birth = birth;
	        this.team = team;

}
