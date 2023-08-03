package kr.co.helf.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.helf.form.AddUserForm;
import kr.co.helf.mapper.UserDao;
import kr.co.helf.vo.Rank;
import kr.co.helf.vo.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public void createCustomer(AddUserForm form) {
		User user = new User();
		
		BeanUtils.copyProperties(form, user);
		
		//비밀번호를 암호화해서 저장시키기
		String encryptedPassword = passwordEncoder.encode(form.getPassword());
		user.setEncryptedPassword(encryptedPassword);
		
		// 등급, 타입 담기
		user.setType("회원");
		Rank rank = new Rank();
		rank.setNo(1);
		user.setRank(rank);
		
		userDao.insertCustomer(user);
		
	}
}














