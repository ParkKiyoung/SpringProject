package com.member.anno.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private JdbcTemplate template;
	//오토 와이어드 때문에 세터 안만들어도됨

	@Override
	public List<MemberVO> getMemberList() {
		String sql = "select * from springmember";
		List<MemberVO> arr = template.query(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int arg1) throws SQLException {
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setAddr(rs.getString("addr"));
					vo.setMemo(rs.getString("memo"));
					vo.setName(rs.getString("name"));
					vo.setPass(rs.getString("pass"));
					vo.setReg_date(rs.getString("reg_date"));
				return vo;
			}
		});
		return arr;
	}

	@Override
	public MemberVO findById(String id) {
		String sql = "select * from springmember where id = '"+id+"'";
		MemberVO vo = template.queryForObject(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int arg1) throws SQLException {
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setAddr(rs.getString("addr"));
					vo.setMemo(rs.getString("memo"));
					vo.setName(rs.getString("name"));
					vo.setPass(rs.getString("pass"));
					vo.setReg_date(rs.getString("reg_date"));
				return vo;
			}
		});
		
		return vo;
	}

	@Override
	public void insert(MemberVO user) {
		String sql = "insert into springmember values(?,?,?,?,?,sysdate)";
		Object[] param = new Object[] {
				user.getId(),user.getPass(),user.getName(),user.getAddr(),user.getMemo()
		};
		template.update(sql,param);
	}

	@Override
	public void update(MemberVO user) {
		String sql = "update springmember set pass=?,name=?,addr=?,memo=?, reg_date=sysdate where id =?";
		Object[] param = new Object[] {
				user.getPass(),user.getName(),user.getAddr(),user.getMemo(),user.getId()
		};
		template.update(sql,param);
	}

	@Override
	public void delete(String id) {
		String sql = "delete from springmember where id = '"+id+"'";
		template.execute(sql);
	}
}
