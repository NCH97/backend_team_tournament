package com.campusacademy.nch.backenddevteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusacademy.nch.backenddevteam.model.TeamMember;


public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

}