# fetch() 함수 정리
---
## 1. 정의
- `fetch()`는 **바닐라(순수) JavaScript**에서 제공하는 **비동기 HTTP 통신**을 위한 **Promise 기반 API**입니다.
- 과거에 많이 사용되던 **XMLHttpRequest(XHR)** 기반 Ajax 요청을 대체하기 위한 새로운 표준으로, ES6 이후부터 브라우저에 기본 내장되었습니다.
- **React**, **Vue**, **Angular** 같은 현대적인 프론트엔드 프레임워크에서도 간단한 HTTP 통신 시 자주 쓰입니다.

---

## 2. 기본 사용법

```js
fetch(url, options)
  .then(response => {
    // HTTP 응답 객체(Response)
    // response.ok, response.status 등으로 상태 확인
    return response;
  })
  .catch(error => {
    // 네트워크 장애, CORS 실패 등 요청 자체가 실패한 경우
    console.error(error);
  });
```
---

**매개변수**
- url: 요청할 서버(또는 API)의 경로/주소
- options: 요청 방식(method), 헤더(headers), 요청 바디(body) 등을 담은 객체 (선택 사항)
---

**반환**
- fetch()는 Promise<Response>를 반환합니다.
- 정상적으로 서버와 통신에 성공하면 Response 객체를 resolve합니다.
- 네트워크 장애 등으로 통신이 불가능하면 Promise를 reject(오류)합니다.

---

## 3. 자주 사용하는 옵션들 

```js
const options = {
  method: 'POST',               // 기본값 GET
  headers: {
    'Content-Type': 'application/json',  // 전송 데이터 형식
    'Authorization': 'Bearer tokenValue' // 인증 토큰 (필요 시)
  },
  body: JSON.stringify({ name: 'John', age: 30 }) // POST/PUT/PATCH 등에 사용
};
```

![image](https://github.com/user-attachments/assets/8a132327-e84e-4e50-8d82-3a9bcad8b629)

---

## 4. 다양한 사용 예시

# GET 요청 + JSON 응답
```js
fetch('/api/items?category=book')
  .then(resp => resp.json())
  .then(jsonData => {
    console.log(jsonData);
  })
  .catch(err => console.error(err));
```
- 쿼리스트링을 URL에 직접 붙여 전송
- resp.json()을 통해 JSON으로 파싱

---

# POST 요청 + JSON 응답
```js
fetch('/api/users', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ name: 'Alice', age: 23 })
})
  .then(resp => resp.json())
  .then(data => {
    console.log(data);
  })
  .catch(err => console.error(err));
```
- JSON으로 전송할 때 headers에 'Content-Type': 'application/json' 지정
- body에는 JSON.stringify()로 직렬화한 문자열을 넣어야 서버가 올바르게 파싱할 수 있음

![image](https://github.com/user-attachments/assets/5b078825-c980-40cb-ac9c-94bfed7404c2)
