package com.campusacademy.nch.backenddevteam.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.campusacademy.nch.backenddevteam.api.model.TeamMemberDTO;
import com.campusacademy.nch.backenddevteam.model.TeamMember;
import com.campusacademy.nch.backenddevteam.repositories.TeamMemberRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "TeamMembers")
public class TeamMembersController {
	
	private TeamMemberRepository teamMemberRepository;
	
	@GetMapping
    public ResponseEntity<List<TeamMemberDTO>> getAll() {
        return ResponseEntity.ok(this.teamMemberRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList())
        );
    }
	
	@GetMapping(path = "{id}")
    public ResponseEntity<TeamMemberDTO> getById(@PathVariable Long id) {
        return this.teamMemberRepository.findById(id)
                .map(teamMember -> ResponseEntity.ok(map(teamMember)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	 @PostMapping(consumes = APPLICATION_JSON_VALUE)
	    public ResponseEntity<TeamMemberDTO> create(@RequestBody TeamMemberDTO teamMemberDTO) {
	        TeamMember teamMember = map(teamMemberDTO);

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(map(this.teamMemberRepository.save(teamMember)));
	    }
	 
	 @PutMapping(path = "{id}")
	    public ResponseEntity<TeamMemberDTO> update(@PathVariable Long id, @RequestBody TeamMemberDTO teamMemberDTO) {
	        Optional<TeamMember> teamMemberOptional = this.teamMemberRepository.findById(id);
	        if (teamMemberOptional.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }

	        TeamMember teamMemberToUpdate = map(teamMemberDTO);
	        return ResponseEntity.ok(map(this.teamMemberRepository.save(teamMemberToUpdate)));
	    }
	 
	 @DeleteMapping(path = "{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void delete(@PathVariable Long id) {
	        this.teamMemberRepository.deleteById(id);
	    }
	 
	 private TeamMember map(@NonNull TeamMemberDTO teamMemberDTO) {
	        TeamMember teamMember = new TeamMember();
	        teamMember.setTeamMemberName(teamMemberDTO.getTeamMemberName());
	        teamMember.setPays(teamMemberDTO.getPays());
	        teamMember.setBirth(teamMemberDTO.getBirth());
	        teamMember.setTeam(teamMemberDTO.getTeam());
	        return teamMember;
	    }
}
