# Front-end 개발일지

## 📅 작성일: 2024-11-10

## 📝 Task
- 이벤트 처리를 활용한 간단한 별점 기능 구현

## 🛠️ Tech Stack
- HTML5
- CSS
- JavaScript

## 💡 Lessons Learned

### 1. 다양한 이벤트들과 getBoundingClientRect
- mousemove : 사용자가 마우스를 요소 위에서 움직일때 발생하는 이벤트, 주로 마우스의 위치나 이동을 추적할때 사용됨.
- click : 사용자가 요소를 클릭할 떄 발생하는 이벤트.
- mouseenter : 마우스가 요소 위에 들어갔을 때 발생하는 이벤트, mouseover와 달리 자식 요소로의 진입에는 반응하지 않음.
- mouseleave : 마우스가 요소 밖으로 나갔을 때 발생하는 이벤트, mouseout과 달리 자식 요소에서 벗어날 때 반응하지 않음.
- transform : css 속성으로 요소의 변형을 적용함, 예를들어 회전, 크기 조정, 이동 등.
- getBoundingClientRect() : 엘리먼트의 크기와 뷰포트에 상대적인 위치 정보를 제공하는 DOMRect 객체를 반환

```
// 여기서 DOMRect 객체란? top, left, right, bottom, width, height이다.
// 사용 예시로 아래 코드에서

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bounding Client</title>
    <style>
        #bc {
            width: 200px;
            height: 100px;
            background-color: skyblue;
            margin-top: 100px;
            margin-left: 50px;
        }
    </style>
</head>
<body>

    <div id="bc">Hello, world!</div>

    <script type="text/javascript">
       
        const element = document.getElementById('bc'); // 'bc' id를 가진 요소 가져오기
        const rect = element.getBoundingClientRect(); // 요소의 크기 및 위치 정보를 얻음
        
   		// 개발자 도구 결과 확인 가능
        console.log('Top:', rect.top);    // 요소의 뷰포트 상단과의 거리
        console.log('Left:', rect.left);  // 요소의 뷰포트 왼쪽과의 거리
        console.log('Width:', rect.width); // 요소의 너비
        console.log('Height:', rect.height); // 요소의 높이
    </script>

</body>
</html>
```




![bounding](https://github.com/user-attachments/assets/44758ea2-0f19-4f53-b14b-db2d4214a588)
- 개발자 도구로 확인한 요소의 크기 및 위치 정보 결과






### 2. DOM 조작 및 Element 객체
`const ratingInput = document.getElementById('rating');`
- getElementById : 위 코드를 예시로 id가 rating인 요소를 찾아서, 그 요소를 Element 객체로 반환

> Element 객체에 대해서는 (https://developer.mozilla.org/en-US/docs/Web/API/Element) 에서 학습 하였습니다.

- Element 객체란?
- Element는 HTML 문서 내의 요소(엘리먼트)를 나타내는 객체입니다. 
- 즉, HTML 문서에서 `<div>, <p>, <a>, <img>` 와 같은 HTML 태그들은 각각 자바스크립트에서 Element 객체로 다뤄짐.

`<div id="myDiv">Hello World</div>`
- 위의 코드에서 `<div>` 태그는 Element 객체로 다뤄진다.

- Element 객체는 HTML 요소에 대한 다양한 속성과 메서드를 제공함. 아래 코드들이 그 예시다.
```
// 속성
<div class="myClass">Hello World</div>

const element = document.querySelector(".myClass");
console.log(element.className); // myClass
// .className (요소의 클래스 이름)

<div id="myDiv">Hello World</div>

const element = document.getElementById("myDiv");
console.log(element.innerHTML); // "Hello World" // innerHTML를 사용하면 요소 안의 HTML 코드를 가져올 수 있습니다.

element.style.color = "red"; // 텍스트 색을 빨간색으로 변경
element.style.fontSize = "20px"; // 폰트 크기 변경
// style 속성을 통해 인라인 스타일을 변경
// 이 방법은 자바스크립트로 스타일을 동적으로 변경할 때 사용됨.
```	
```
// 메서드
<img id="myImage" src="image.jpg" alt="Image">

const img = document.getElementById("myImage");
img.setAttribute("src", "new-image.jpg"); // src 속성을 새 이미지로 변경
img.setAttribute("alt", "New Image"); // alt 속성 변경
// setAttribute() : 요소의 속성을 수정하거나 추가할 때 사용.

<a href="https://www.naver.com" id="myLink">Naver</a>

const link = document.getElementById("myLink");
console.log(link.getAttribute("href")); // "naver.com"
getAttribute() : 요소의 특정 속성 값을 가져올 때 사용.

<div id="parentDiv">
  <p>Initial Content</p>
</div>

const parentDiv = document.getElementById("parentDiv"); // parentDiv id를 가져와서 element 객체로 반환
const newElement = document.createElement("p"); // 새로운 p 태그를 생성

newElement.textContent = "New Child Element"; // 새롭게 생긴 p 태그에 "New Child Element"라는 text를 추가함
parentDiv.appendChild(newElement); // 새로운 p 요소(값)을 추가
// 고로 결과는 Initial Content 텍스트와 New Child Element 텍스트가 나온다.

// textContent : 텍스트만을 다루고, HTML을 해석하지 않음, <strong>도 
// 따라서 HTML 태그를 포함한 텍스트를 변경할 때는 innerHTML을 사용 권장.
// textContent는 주로 보안과 성능을 고려할 때 사용됩니다.
// appendChild() : 새로운 자식 요소를 부모 요소에 추가할 때 사용.

<div id="parentDiv">
  <p id="child1">Child 1</p>
  <p id="child2">Child 2</p>
</div>

const parentDiv = document.getElementById("parentDiv"); // parentDiv id를 가져와서 element 요소를 반환
const child = document.getElementById("child1"); // 마찬가지로 child1 id를 가져와서 element 요소를 반환
parentDiv.removeChild(child); // div id parentDiv의 자식은 child1이므로 child1이 제거되어 child2가 출력됨.
// removeChild() : 자식 요소를 제거
```


## ⚠️ Issues & Solution 

### 1. 별을 처리하는 부분에서 배열 접근 없이 인덱스를 직접 사용
- 직접 index만으로 접근하면 배열 범위와 일치하지 않거나, 잘못된 별에 적용될 수도 있다.
- 배열로 접근 하는게 더 직관적이고 안전한 방법이고, 코드의 중복을 줄일수 있다.

### 2. selectedRating
- selectedRating 변수가 전역 상태로 사용되고 있는데 초기화를 하지않음.
- 그 값이 명확하게 선언되지 않으면 예상치 못한 오류가 발생 할 수 있음.

### 3. 계산 관련
- `let rateValue = 0; let rating = -1;` 이 코드처럼 하나는 값을 0을 갖고, 하나는 값을 1을 갖는 변수 두개를 만들어서 별의 인덱스를 추가하고 없애는게 더 쉬웠을 듯.
- click과 mousemove 이벤트에서 계산을 각각 하는데 계산 함수 로직을 따로 만들어서 호출해왔으면 코드의 중복을 줄일 수 있었을 것 같다.

## 📄 Result
[실행결과](https://seungbeomplus77.github.io/star/ex00.html)

## 📄 Code

<details>
<summary>HTML</summary>


```html
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.6.0/css/all.css"> <!-- 외부 링크에서 모든 아이콘 가져오기 -->
</head>
<body>
<div style="margin: 20px auto; width: 500px;">
	<h2>간단한 별점</h2>
</div>

<div style="margin: 20px auto; width: 500px;">

  <div class="star-bundle">
    <i class="fa-regular fa-star rate"></i> <!-- 빈별 아이콘 가져오기*5 -->
    <i class="fa-regular fa-star rate"></i>
    <i class="fa-regular fa-star rate"></i>
    <i class="fa-regular fa-star rate"></i>
    <i class="fa-regular fa-star rate"></i>
  </div>

<div style="margin: 10px;">
	<input type="text" name="rating" id="rating" value="0" readonly>
</div>
	</div>
```
</details>

<details>
<summary>CSS</summary>

```css
<style type="text/css">
* { margin: 0; padding: 0; box-sizing: border-box; }
*, *::after, *::before { box-sizing: border-box; }

body { font-size: 14px; font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif; }

a { color: #000; text-decoration: none; cursor: pointer; }
a:active, a:hover { text-decoration: underline; color: #F28011; }

.btn {
	padding: 5px 10px;
	font-size: 14px; font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	color: #333; font-weight: 500;
	border: 1px solid #999; border-radius: 4px;
	background-color: #fff;
	cursor:pointer;
	vertical-align: baseline;
}
.btn:active, .btn:focus, .btn:hover {
	color:#333;
	background-color: #f8f9fa;
}
.btn[disabled], fieldset[disabled] .btn {
	pointer-events: none;
	cursor: default;
	opacity: .65;
}

.form-control {
	padding: 5px 5px; 
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	border: 1px solid #999; border-radius: 4px; background-color: #fff;
	vertical-align: baseline;
}
.form-control[readonly] { background-color:#f8f9fa; }

textarea.form-control { height: 170px; resize : none; }

.form-select {
	padding: 4px 5px; 
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	border: 1px solid #999; border-radius: 4px; background-color: #fff;
	vertical-align: baseline;
}
.form-select[readonly] { background-color:#f8f9fa; }

textarea:focus, input:focus { outline: none; }
input[type=checkbox], input[type=radio] { vertical-align: middle; }

.star-bundle {
	display: grid;
	width: 190px;
	grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
	grid-auto-rows: 40px;
	justify-content: center;
	align-items: center;
	text-align: center;
	font-size: 30px;
}

.rate:hover {
	cursor: pointer;
	scale: 108%;
}
.rate {
	color: #95D9F1;
}
</style>

```
</details>

<details>
<summary>JavaScript</summary>

```javascript
<script type="text/javascript">
const starBundle = document.querySelector('.star-bundle'); // 클래스 요소 첫번째만 가져오기
const stars = starBundle.querySelectorAll('.rate'); // 클래스 요소 모두 가져오기
const ratingInput = document.getElementById('rating'); // id rating인것만 가져오기 // 아이디가 중복되면 첫번째 요소를 가져옴

let selectRating = 0; // 선택한 별점 저장

	// 별점 초기화 함수
	const resetStars = () => {
	    stars.forEach(star => {
	        star.className = 'fa-regular fa-star rate';
	    });
	};

	// 별 채우기 함수 (반별 지원)
	const fillStars = (rating) => {
	    resetStars();
	    const fullStars = Math.floor(rating);
	    const hasHalfStar = rating % 1 !== 0;
    
    // 꽉 찬 별 표시
    for(let i = 0; i < fullStars; i++) {
        stars[i].className = 'fa-solid fa-star rate';
    }
    
    // 반별 표시
    if(hasHalfStar && stars[fullStars]) {
        stars[fullStars].className = 'fa-regular fa-star-half-stroke rate';
   		}
	};

	// 각 별에 대한 이벤트
	stars.forEach((star, index) => {
	    star.addEventListener('mousemove', (e) => {
	        const rect = star.getBoundingClientRect();
	        const mouseX = e.clientX - rect.left;
	        const halfWidth = rect.width / 2;
	        
	        let rating;
	        if (mouseX < halfWidth) {
	            rating = index + 0.5;
	        } else {
	            rating = index + 1;
	        }
	        
	        fillStars(rating);
	    });
    
	 // 클릭 이벤트 추가
	    star.addEventListener('click', (e) => {
	        const rect = star.getBoundingClientRect();
	        const mouseX = e.clientX - rect.left;
	        const halfWidth = rect.width / 2;
	        
	        selectedRating = mouseX < halfWidth ? index + 0.5 : index + 1;
	        ratingInput.value = selectedRating.toFixed(1);
	    });
    
	// 호버 효과 추가
    star.addEventListener('mouseenter', () => {
        star.style.transition = 'transform 0.2s';
    });
    
    // 마우스 나갈 때 트랜지션 제거
    star.addEventListener('mouseleave', () => {
        star.style.transition = '';
    });
});

	// 마우스가 별점 영역을 벗어났을 때
	starBundle.addEventListener('mouseleave', () => {
	    stars.forEach(star => {
	        star.style.transform = 'scaleX(1)';
	    });
	
	// 선택된 별점이 있으면 그대로 유지, 없으면 초기화
	    if (selectedRating > 0) {
	        fillStars(selectedRating);
	        ratingInput.value = selectedRating.toFixed(1);
	    } else {
	        resetStars();
	        ratingInput.value = '0.0';
	    }
	});
</script>
```

