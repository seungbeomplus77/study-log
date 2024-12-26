# 동일한 이름의 파라미터 처리 방식 ( @RequestParam ) 의 예

## 동일한 이름의 파라미터를 String 으로 받기
- 동일한 이름의 파라미터를 String 으로 받으면 java.html 처럼 넘어온다.
```java
@PostMapping("request")
	public String requestSubmit(
			String name,
			int age,
			String subjects,
			Model model) throws Exception {
		
		String s = name + " : " + age + " : " + subjects;
		
		model.addAttribute("message", s);
		
		return "ex12/result";
	}
```

---

## 동일한 이름의 파라미터를 배열로 받기

```java
@PostMapping("request")
	public String requestSubmit(
			String name,
			int age,
			String[] subjects,
			Model model) throws Exception {
		
		String s = name + " : " + age + " : ";
		if(subjects != null) {
			for(String a : subjects) {
				s += a + " ";
			}
		}
		
		model.addAttribute("message", s);
		
		return "ex12/result";
	}
```

---

## 동일한 이름의 파라미터를 List로 받기
- 요청 파라미터를 List로 받기 위해서는 @RequestParam 이 필요
- @RequestParam 을 사용하지 않으면 500 에러
```java
	@PostMapping("request")
	public String requestSubmit(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "age") int age,
			@RequestParam(name = "subjects", required = false) List<String> subjects,
			Model model) throws Exception {
		
		String s = name + " : " + age + " : ";
		if(subjects != null) {
			for(String a : subjects) {
				s += a + " ";
			}
		}
		
		model.addAttribute("message", s);
		
		return "ex12/result";
	}
```

---

## 동일한 이름의 파라미터를 Map으로 받기
- 요청 파라미터를 Map으로 받기 위해서는 @RequestParam 이 필요
- 동일한 이름의 파라미터를 Map 으로 받으면 동일한 이름의 요청은 하나만 받는다.
```java
	@PostMapping("request")
	public String requestSubmit(
			@RequestParam Map<String, String> paramMap,
			Model model) throws Exception {
		
		String s = "";
		Iterator<String> it = paramMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = paramMap.get(key);
			
			s += key + " : " + value + "<br>";
		}
		
		model.addAttribute("message", s);
		return "ex12/result";
	}
}

```