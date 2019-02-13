package com.member.anno.model;

import java.util.List;

public interface MemberDAO {

	
	List<MemberVO> getMemberList();
	MemberVO findById(String id);
	void insert(MemberVO user);
	void update(MemberVO user);
	void delete(String id);
}
