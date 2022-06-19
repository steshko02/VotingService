package com.senla.steshko.dto.entities;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class EventDto  extends AbstractDto {

    private Date start;

    private Date finish;

    private String name;

    private String description;

    private Set<Long> candidateIds;

    private Set<Long> voteIds;

}
