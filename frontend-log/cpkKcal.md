# Front-end 개발일지

## 📅 작성일: 2024-11-09

## 📝 Task
- DOM을 활용한 간단한 영양소 칼로리 계산기 구현

## 🛠️ Tech Stack
- HTML5
- CSS
- JavaScript

## 💡 Lessons Learned

### 1. 데이터 유효성 검사
- 날짜는 YYYYMMDD 형식, 탄수화물/단백질/지방은 숫자로만 입력 가능하도록 검증
- 빈 값 입력 방지를 위한 Check 메소드 구현

### 2. 이벤트 처리
- keydown, keyup 이벤트 동시 사용 시 중복 실행과 성능 영향 고려 필요

### 3. DOM 조작
```javascript
let row = document.createElement('tr');  // 테이블에 새로운 행 추가
```
- querySelector, querySelectorAll, createElement
- querySelectorAll: 모든 요소를 배열로 반환
- querySelector: 여러 요소가 일치하더라도 첫 번째 일치 요소만 반환(배열X)
- createElement :  지정한 tagName의 HTML 요소를 만들어 반환

```javascript
// CSS 선택자 사용법
const inputForm = document.querySelector('#input-form');  // # : id 선택자 // // HTML5에서 id가 input-form인 첫번째 요소 선택
const inputForm = document.querySelector('.input-form');  // . : class 선택자 // // HTML5에서 class가 input-form인 첫번째 요소 선택
```

## ⚠️ Issues

### 1. 키보드 입력 관련
- 등록 후 엔터키 누르면 등록 된 데이터 삭제 현상
- 첫 입력 필드 엔터키 오작동

### 2. 값 입력 검증
- 음수 입력 가능 문제
- 실수 입력 가능 문제
- 문자 입력 후 알림만 표시
- 큰 숫자 제한 없음

### 3. 데이터 관련
- 새로고침 시 데이터 소실
- 같은 날짜 중복 입력 가능

### 4. UI/UX
- 포커스 위치 불명확
- 삭제 확인 절차 없음
- 데이터 수정 기능 부재
- 등록 성공/실패 기능 없음

## 📄 Result
- 육개장 컵라면의 영양성분을 참고해서 실행결과를 테스트 해봤습니다.
<img src="https://github.com/user-attachments/assets/57f53569-ed41-482a-ac87-90e318ade9c4" alt="육개장영양성분표" width="300" height="auto">

![실행화면](https://github.com/user-attachments/assets/7caa14ef-3df3-47c4-81ad-2df0fc8fc231)


## 📄 Code

<details>
<summary>HTML</summary>


```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영양소 칼로리 계산기</title>

<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.6.0/css/all.css">

</head>
<body>

<div class="body-container">
    <div class="body-title">
       <h3><i class="fa-solid fa-dumbbell"></i> 탄단지 계산기 </h3>
    </div>

    <form name="dietForm" method="post">
    <table class="table score-table">
        <thead>
            <tr>
                <th width="80">식사 한 날짜</th>
                <th width="80">탄수화물</th>
                <th width="80">단백질</th>
                <th width="80">지방</th>
                <th width="120">탄단지 비율</th>
                <th width="120">탄수화물 칼로리</th>
                <th width="120">단백질 칼로리</th>
                <th width="120">지방 칼로리</th>
                <th width="120">총 칼로리</th>
                <th width="80">변경</th>
            </tr>     
        </thead>
        
        <tbody id="diet-main">
            <tr id="input-form" align="center">
                <td><input type="text" name="date" id="date" placeholder="YYYYMMDD" onfocus="removePlaceholder(this)"></td>
                <td><input type="text" name="carbs" id="carbs" placeholder="g" onfocus="removePlaceholder(this)"></td>
                <td><input type="text" name="protein" id="protein" placeholder="g" onfocus="removePlaceholder(this)"></td>
                <td><input type="text" name="fat" id="fat" placeholder="g" onfocus="removePlaceholder(this)"></td>
                <td><input type="text" name="ratio" id="ratio" readonly style="border: none; text-align: center;"></td>
                <td><input type="text" name="carbskcal" id="carbskcal" readonly style="border: none; text-align: center;"></td>
                <td><input type="text" name="proteinkcal" id="proteinkcal" readonly style="border: none; text-align: center;"></td>
                <td><input type="text" name="fatkcal" id="fatkcal" readonly style="border: none; text-align: center;"></td>
                <td><input type="text" name="totalkcal" id="totalkcal" readonly style="border: none; text-align: center;" class="total-calories"></td>
                <td>
                    <button type="button" class="btn" onclick="insertDiet();" style="width: 100%;">등록하기</button>
                </td>
            </tr>
        </tbody>
    </table>
    </form> 
</div>
<div class="calorie-info">
    <p><strong>탄수화물:</strong> 1g당 4칼로리</p>
    <p><strong>단백질:</strong> 1g당 4칼로리</p>
    <p><strong>지방:</strong> 1g당 9칼로리</p>
</div>
</body>
</html>
```
</details>

<details>
<summary>CSS</summary>

```css
<style type="text/css">
* { padding: 0; margin: 0; }
*, *::after, *::before { box-sizing: border-box; }

body {
    font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
    font-size: 14px;
    color: #222;
}

a { color: #222; text-decoration: none; cursor: pointer; }
a:active, a:hover { color: #f28011; text-decoration: underline; }

.btn {
    color: #333;
    border: 1px solid #999;
    background-color: #fff;
    padding: 5px 10px;
    border-radius: 4px;
    font-weight: 500;
    cursor:pointer;
    font-size: 14px;
    font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
    vertical-align: baseline;
}
.btn:active, .btn:focus, .btn:hover {
    background-color: #f8f9fa;
    color:#333;
}
.btn[disabled], fieldset[disabled] .btn {
    pointer-events: none;
    cursor: default;
    opacity: .65;
}

.form-control {
    border: 1px solid #999; border-radius: 4px; background-color: #fff;
    padding: 5px 5px; 
    font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
    vertical-align: baseline;
}
.form-control[readonly] { background-color:#f8f9fa; }

textarea.form-control { height: 170px; resize : none; }

.form-select {
    border: 1px solid #999; border-radius: 4px; background-color: #fff;
    padding: 4px 5px; 
    font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
    vertical-align: baseline;
}
.form-select[readonly] { background-color:#f8f9fa; }

textarea:focus, input:focus { outline: none; }
input[type=checkbox], input[type=radio] { vertical-align: middle; }

.table { width: 100%; border-spacing: 0; border-collapse: collapse; }
.table th, .table td { padding-top: 8px; padding-bottom: 8px; }

.table-border thead > tr { border-top: 2px solid #212529; border-bottom: 1px solid #ced4da; }
.table-border tbody > tr { border-bottom: 1px solid #ced4da; }
.table-border tfoot > tr { border-bottom: 1px solid #ced4da; }
.td-border td { border: 1px solid #ced4da; }

.body-container { width: 1200px; margin: 30px auto; }
.body-title { width:100%; font-size: 16px; font-weight: bold; padding: 13px 0; }

.score-table thead > tr th { padding: 7px 0; border: 1px solid #ced4da; background: #f8f9fa; }
.score-table tbody td { border: 1px solid #ced4da; padding: 7px 0; }
.score-table tbody tr:first-child td { padding: 0; }

.score-table input { width:100%; height: 30px; border:none; padding:10px 10px; }
.score-table span { cursor: pointer; }

tr.over { background: #f5fffa; }

input::placeholder { text-align: center; }
  
.calorie-info {
    background-color: #f8f9fa;
    padding: 10px;
    border-radius: 8px;
    border: 1px solid #ddd;
    font-size: 14px;
    margin-top: 15px;
    text-align: center;
}

.calorie-info p {
    margin: 8px 0;
    font-size: 15px;
}

.calorie-info strong {
    color: #007bff;
    font-size: 16px;
}

.total-calories {
    font-weight: bold;
    color: #007bff;
}
</style>
```
</details>

<details>
<summary>JavaScript</summary>

```javascript
<script type="text/javascript">
function removePlaceholder(input) {
    input.placeholder = '';
}

// 날짜 입력 형식 검증
const isValidDate = data => {
    if(data.length !== 8 && data.length !== 10) return false;
    
    let p = /(\.)|(\-)|(\/)/g;
    data = data.replace(p, "");
    if(data.length!=8) return false;
    
    let format = /^[12][0-9]{7}$/;
    if(! format.test(data)) return false;
    
    let y = parseInt(data.substring(0, 4));
    let m = parseInt(data.substring(4, 6));
    let d = parseInt(data.substring(6));

    if(m<1 || m>12) return false;
    let lastDay = (new Date(y, m, 0)).getDate();
    if(d<1 || d>lastDay) return false;
    
    return true;
};

// 숫자만 입력 검증
const isValidNumber = data => {
    let format = /^(\d+)$/;
    return format.test(data);
};

// 등록했을 때 빈값, 잘못된 형식이 있는지 검증
function check(inputForm) {
    const inputEls = inputForm.querySelectorAll('input');
    let s;
    
    for (let i = 0; i < inputEls.length; i++ ) {
        s = inputEls[i].value.trim();
        if(! s) {
            alert('값을 입력 하세요.');    
            inputEls[i].focus();
            return false;
        }
        
        if(i === 0 && !isValidDate(s)) {
            alert('날짜의 형식이 잘못 되었습니다.');
            inputEls[i].focus();
            return false;
        }
    }
    
    return true;
}

const init = () => {
    const inputForm = document.querySelector('#input-form');
    const inputEls = inputForm.querySelectorAll('input');
    
    for (let el of inputEls ) {
        el.addEventListener('keydown', e => fnReturn(e));
        el.addEventListener('keyup', e => fnKeyup(e));
    }
};

const fnReturn = e => {
    const inputForm = e.currentTarget.closest('tr'); 
    const inputEls = inputForm.querySelectorAll('input'); 
    let target = e.currentTarget;
    
    if (e.key === 'Enter') {
        for (let i = 0; i < inputEls.length; i++ ) {
            if(i < 5 && target === inputEls[i]) {
                inputEls[i+1].focus();
                break;
            } else if(i===5 && target===inputEls[i]) {
                if(target.closest('tr') === target.closest('tbody').firstElementChild) {
                    insertDiet();
                } else {
                    updateOk(target);
                }
                break;
            }
        }
    }
}

const fnKeyup = e => {
    const inputForm = e.currentTarget.closest('tr'); 
    const inputEls = inputForm.querySelectorAll('input');
    let target = e.currentTarget;
    
    let work = 0;
    
    for (let i = 0; i < inputEls.length; i++ ) {
        if(target === inputEls[i]) {
            work = i;
            break;
        }
    }
    
    if (work >= 1 && work <= 3) {
        let tan = inputEls[1].value.trim();
        let dan = inputEls[2].value.trim();
        let gi = inputEls[3].value.trim();
        
        let t = isValidNumber(tan) ? parseInt(tan):0;
        let d = isValidNumber(dan) ? parseInt(dan):0;
        let g = isValidNumber(gi) ? parseInt(gi):0;
        
        let total = t + d + g;
        
        let ratio = {
            carbRatio: 0,
            proteinRatio: 0, 
            fatRatio: 0
        };

        if (total > 0) {
            ratio = {
                carbRatio: (t / total) * 100,
                proteinRatio: (d / total) * 100,
                fatRatio: (g / total) * 100
            };
        }
        
        let tan_g = t * 4;
        let dan_g = d * 4;
        let gi_g = g * 9;
        let total_kcal = tan_g + dan_g + gi_g;
        
        inputEls[4].value = `${Math.round(ratio.carbRatio)} : ${Math.round(ratio.proteinRatio)} : ${Math.round(ratio.fatRatio)}`;
        inputEls[5].value = `${tan_g} kcal`;
        inputEls[6].value = `${dan_g} kcal`;
        inputEls[7].value = `${gi_g} kcal`;
        inputEls[8].value = `${total_kcal} kcal`;
    }
}

function insertDiet() {
    const inputForm = document.querySelector('#input-form');
    const inputEls = inputForm.querySelectorAll('input');
    const dietMain = document.querySelector('#diet-main');
    
    if (!check(inputForm)) {
        return;
    }
    
    let row = document.createElement('tr');
    row.setAttribute('height', '30');
    row.setAttribute('align', 'center');
    row.onmouseover = function() { this.setAttribute('class', 'over'); };
    row.onmouseout = function() { this.removeAttribute('class'); };
    
    for (let i = 0; i < inputEls.length; i++) {
        let cell = document.createElement('td');
        cell.textContent = inputEls[i].value.trim();
        row.appendChild(cell);
    }

    let cell = document.createElement('td');
    let deleteBtn = document.createElement('button');
    deleteBtn.className = 'btn';
    deleteBtn.textContent = '삭제';
    deleteBtn.onclick = function(){ 
        deleteRow(row);
    };
    
    cell.appendChild(deleteBtn);
    row.appendChild(cell);
    
    dietMain.appendChild(row);
    
    for (let i = 0; i < inputEls.length; i++) {
        inputEls[i].value = '';
    }
}

function deleteRow(row) {
    row.remove();
}

window.addEventListener('load', () => init());
</script>
```
