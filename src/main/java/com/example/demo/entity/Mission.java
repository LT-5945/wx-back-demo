package com.example.demo.entity;

import lombok.Data;

/**
 * @description
 * @author Zuwy Ling
 * @create 2019-07-01
 **/
@Data
public class Mission {
    private Integer mission_id;
    private String mission_name;
    private String mission_desc;
    private Integer member_id;

}