package com.senla.steshko.dto.entities;

import lombok.Data;

import java.util.Set;

@Data
public class CandidateDto extends  AbstractDto{

    private UserDto user;

    private Long event;

    private String description;

    private Set<Long> voteIds;
}
