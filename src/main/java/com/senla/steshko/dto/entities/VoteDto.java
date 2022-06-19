package com.senla.steshko.dto.entities;

import lombok.Data;

@Data
public class VoteDto extends AbstractDto{

    private UserDto owner;

    private Long candidate;

    private Long event;
}
