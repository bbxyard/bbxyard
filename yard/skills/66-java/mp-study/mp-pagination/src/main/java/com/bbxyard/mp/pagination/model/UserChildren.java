package com.bbxyard.mp.pagination.model;

import com.bbxyard.mp.pagination.entity.Children;
import com.bbxyard.mp.pagination.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserChildren extends User {

    private List<Children> c;

}
