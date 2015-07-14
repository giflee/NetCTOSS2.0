package com.neu.jianfu.dao;

import com.neu.jianfu.annotation.Myannotation;
import com.neu.jianfu.entity.Admin;
@Myannotation
public interface AdminMapper {

	Admin findByCode(String adminCode);
}
