package com.user5;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserServiceImpl implements UserService {
	private String name;
	private Map<String, String> address;
	private List<String> hobby;
	private Properties tel;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    // address 프로퍼티의 setter 추가
    public void setAddress(Map<String, String> address) {
        this.address = address;
    }

    // hobby 프로퍼티의 setter 추가
    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    // tel 프로퍼티의 setter 추가
    public void setTel(Properties tel) {
        this.tel = tel;
    }
	
    @Override
	public String message() {
		String s = "이름 : " + name + "\n";
		s += "-----------------------\n";
		
		s += "친구 주소록 ...\n";
		Iterator<String> it = address.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = address.get(key);
			
			s += key + " : " + value + "\n";
		}
		s += "-----------------------\n";
		
		s += "전화번호...\n";
		Iterator<Object> it2 = tel.keySet().iterator();
		while(it2.hasNext()) {
			String key = (String)it2.next();
			String value = tel.getProperty(key);
			
			s += key + " : " + value + "\n";
		}
		s += "-----------------------\n";
		
		s += "취미...\n";
		for(String h : hobby) {
			s += h + " ";
		}
		s += "-----------------------\n";
		
		return s;
	}
}
